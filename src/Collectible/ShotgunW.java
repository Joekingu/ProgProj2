package Collectible;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Bullet;
import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

public class ShotgunW extends distance{

	public ShotgunW(Entity p , GamePanel a_gp) {
		super(p,50, a_gp, 5e8);
		this.getImage();
	}
	public void getImage() {
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/items/shotgun.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D a_g2, int x, int y) {
		// r�cup�re l'image du joueur
		BufferedImage l_image = m_idleImage;

		// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et
		// de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		a_g2.drawImage(l_image, porteur.getx()+m_gp.TILE_SIZE,porteur.gety(), m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);

	}
	
	@Override
	public void attaquejoueur(int dirx, int diry) {
		Bullet balle = new Bullet(m_gp,this, 50, 7,1, dirx,diry );
		m_gp.addTirs(balle);
	}
	@Override
	public void attaquemob() {
		//mettre l'attaque pour les mobs en mode ils regardent la direction et pour une distance au joueur min à un truc qu'on aura choisit, on tire dans cette direction
	}
}
