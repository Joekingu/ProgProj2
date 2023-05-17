package Collectible;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entity.Bullet;
import entity.Entity;
import entity.Player;
import main.GamePanel;
import main.KeyHandler;

public class distance extends arme{
	BufferedImage m_idleImage;
	ArrayList<Bullet> tirs= new ArrayList<Bullet>();
	
	public distance(Entity p,int deg,GamePanel a_gp,double frq_att) {
		super(p, deg,a_gp,frq_att);
	}
	
	public int getType() {
		return 1;
	}
	@Override
	public void attaquejoueur(int dirx, int diry) {
		Bullet balle = new Bullet(m_gp,porteur, 5, 4,1, dirx,diry );
		tirs.add(balle);
	}
	@Override
	public void attaquemob() {
		//mettre l'attaque pour les mobs en mode ils regardent la direction et pour une distance au joueur min à un truc qu'on aura choisit, on tire dans cette direction
	}
	
	public void update() {
		for (Bullet balle : tirs){
			balle.update();
			if (!balle.isAlive()) {
				tirs.remove(balle);
			}
		}
	}
	
	
	public void getImage() {
		//gestion des expections 
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/items/shotgun.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Dessin de la munition
	 * @param a_g2
	 */
	public void draw(Graphics2D a_g2) {
		// r�cup�re l'image du joueur
		BufferedImage l_image = m_idleImage;
		// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		a_g2.drawImage(l_image, m_player.m_x, m_player.m_y, 4, 1, null);
		for (Bullet balle : tirs){
			balle.draw(a_g2);
		}
	}
	
}