package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Gun extends Entity{
	
	int m_x;
	int m_y;
	GamePanel m_gp;
	KeyHandler m_keyH;
	int m_speed;
	Player m_p;
	ArrayList<Ammo> m_muni;
	
	public Gun(GamePanel a_gp, KeyHandler a_keyH, Player p, int speed) {
		m_muni = new ArrayList<Ammo>(); // Create an ArrayList object
		m_p = p;
		m_gp = a_gp;
		m_keyH = a_keyH;
		m_speed = speed;
		m_x = p.m_x+m_gp.TILE_SIZE/2;
		m_y = p.m_y+m_gp.TILE_SIZE/2;
	}
	
	public void update() {
		for(int j = 0; j<m_keyH.taille();j++) {
			if (m_keyH.getval(j) == 37) {
				m_muni.add(new Ammo(m_gp, m_p, 3, 4, 3, 0));
			}
			if (m_keyH.getval(j) == 38) {
				m_muni.add(new Ammo(m_gp, m_p, 3, 4, 3, 1));
			}
			if (m_keyH.getval(j) == 39) {
				m_muni.add(new Ammo(m_gp, m_p, 3, 4, 3, 2));
			}
			if (m_keyH.getval(j) == 40) {
				m_muni.add(new Ammo(m_gp, m_p, 3, 4, 3, 3));
			}
		}
		System.out.println(m_muni.size());
	}
	
	/**
	 * R�cup�ration de l'image du personnage
	 */
	public void getImage() {
		//gestion des expections 
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/tiles/SNOW.png"));
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
		a_g2.drawImage(l_image, m_x, m_y, 40, 40, null);
	}

}
