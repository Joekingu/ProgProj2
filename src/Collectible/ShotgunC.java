package Collectible;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Bullet;
import entity.Player;
import main.GamePanel;

public class ShotgunC extends Collectable{
	public ShotgunC(GamePanel gp, int x, int y) {
		m_x=x;
		m_y=y;
		m_gp=gp;
		pris=true;
		this.getImage();
	}
	
	
	public void update(Player p) {
		if(collision_entity(p)) {
			this.effet(p);
			this.setStatus(false);
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
		a_g2.drawImage(l_image, m_x, m_y,m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
	}


	@Override
	void effet(Player p) {
		p.setarme(new ShotgunW(m_gp.m_keyH,m_gp));
	}

}
