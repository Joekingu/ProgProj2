package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Ammo extends Entity {
	
	//Attributs
	double m_damage;
	double m_speed;
	int m_size;
	GamePanel m_gp;
	Player m_player;
	KeyHandler m_keyH;

	/**
	 * Constructeur de Ammo
	 * @param a_gp GamePanel, pannel principal du jeu
	 * @param a_keyH KeyHandler, gestionnaire des touches 
	 */
	public Ammo(GamePanel a_gp,  KeyHandler a_keyH, Player player, double damage, double speed, int size) {
		m_gp = a_gp;
		a_keyH = m_keyH;
		m_player = player;
		m_damage = damage;
		m_speed = speed;
		m_size = size;
		this.setDefaultValues();
		this.getImage();
	}
	
	/**
	 * Initialisation des donn�es membres avec des valeurs par d�faut
	 */
	protected void setDefaultValues() {
		m_x = 100;
		m_y = 100;
		m_speed = 4;
	}
	
	public void shoot() {
		if (m_keyH.getval() == 39) {
			m_x+= 10;
		}
		m_keyH.setval(0);
	}
	
	public void update() {
		m_x = m_player.m_x+m_gp.TILE_SIZE/2-m_size/2;
		m_y = m_player.m_y+m_gp.TILE_SIZE/2-m_size/2;
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
