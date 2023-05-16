package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Ammo extends Entity {
	
	//Attributs
	int m_damage;
	int m_speed;
	int m_size;
	GamePanel m_gp;
	Player m_player;
	int m_direction;

	/**
	 * Constructeur de Ammo
	 * @param a_gp GamePanel, pannel principal du jeu
	 * @param a_keyH KeyHandler, gestionnaire des touches 
	 */
	public Ammo(GamePanel a_gp, Player player, int damage, int speed, int size, int direction) {
		m_gp = a_gp;
		m_player = player;
		m_damage = damage;
		m_speed = speed;
		m_size = size;
		m_direction = direction;
		this.setDefaultValues();
	}
	
	/**
	 * Initialisation des donn�es membres avec des valeurs par d�faut
	 */
	protected void setDefaultValues() {
		m_x = 100;
		m_y = 100;
		m_speed = 4;
	}
	
	public void update() {
		if(m_direction == 0 ) { //Droite
			m_x += 4;
		}
		if(m_direction == 1 ) { //Gauche
			m_x -= 4;
		}
		if(m_direction == 2 ) { //Haut
			m_y -= 4;
		}
		if(m_direction == 3 ) { //Bas
			m_y += 4;
		}
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
		a_g2.drawImage(l_image, m_x, m_y, m_size, m_size, null);
	}
	


}
