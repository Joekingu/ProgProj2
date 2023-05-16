package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Bullet extends Entity {
	
	//Attributs
	int m_damage;
	int m_speed;
	int m_size;
	GamePanel m_gp;
	Player m_player;
	int m_dirx;
	int m_diry;
	int m_bx;
	int m_by;

	/**
	 * Constructeur de Ammo
	 * @param a_gp GamePanel, pannel principal du jeu
	 * @param a_keyH KeyHandler, gestionnaire des touches 
	 */
	public Bullet(GamePanel a_gp, Player player, int damage, int speed, int size, int dirx, int diry) {
		m_gp = a_gp;
		m_player = player;
		m_damage = damage;
		m_speed = speed;
		m_size = size;
		m_dirx = dirx;
		m_diry = diry;
		m_bx = m_player.m_x + m_dirx;
		m_by = m_player.m_y + m_diry;
	}
	
	/**
	 * Initialisation des donn�es membres avec des valeurs par d�faut
	 */
	
	public void update() {
		m_bx+=m_speed*m_dirx;
		m_by+=m_speed*m_diry;
	}
	
	/**
	 * R�cup�ration de l'image du personnage
	 */
	public void getImage() {
		//gestion des expections 
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/items/bullet1.png"));
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
