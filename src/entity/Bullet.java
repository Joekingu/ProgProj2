package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Collectible.distance;
import main.GamePanel;
import main.KeyHandler;
import tile.Tile;

public class Bullet extends Entity{
	
	//Attributs
	int m_damage;
	int m_speed;
	int m_size;
	GamePanel m_gp;
	distance m_arme;
	int m_dirx;
	int m_diry;
	int m_bx;
	int m_by;
	Tile m_collision;
	boolean m_alive;
	/**
	 * Constructeur de Ammo
	 * @param a_gp GamePanel, pannel principal du jeu
	 * @param a_keyH KeyHandler, gestionnaire des touches 
	 */
	public Bullet(GamePanel a_gp, distance w, int damage, int speed, int size, int dirx, int diry) {
		m_gp = a_gp;
		m_arme=w;
		m_damage = damage;
		m_speed = speed;
		m_size = size;
		m_dirx = dirx;
		m_diry = diry;
		m_bx = m_arme.getX()+ m_size;
		m_by = m_arme.getY() + m_size;
		this.m_collision = new Tile();
		m_alive=true;
		this.getImage();
	}
	
	/**
	 * Initialisation des donn�es membres avec des valeurs par d�faut
	 */
	public boolean collision_mob(ArrayList<mob> list) {
		for (mob i : list) {
			int d = m_gp.TILE_SIZE;
			int ix = i.getx();
			int iy = i.gety();
			double dist_min = d/2;
			if (dist(ix, m_bx, iy, m_by) < dist_min+m_size) {
				i.gethit(m_damage);
				return true;
			}
		}
		return false;
	}
	
	private boolean in (int x , int[]tab) {
		for (int i=0;i<tab.length;i++) {
			if (x==tab[i]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean test() {
		int d = m_gp.TILE_SIZE;
		if ( in(m_gp.gettileM().map[(m_bx+m_dirx*m_speed)/d][(m_by+m_diry*m_speed)/d],m_collision.bloc) || collision_mob(m_gp.getListEnnemis())
				) {
			return true;
		}
		return false;
	}
	
	public boolean isAlive() {
		return m_alive;
	}

	public void update() {
		m_bx+=m_speed*m_dirx;
		m_by+=m_speed*m_diry;
		m_alive=!test();
	}
	
	/**
	 * R�cup�ration de l'image du personnage
	 * @return 
	 */
	public void getImage() {
		//gestion des expections 
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/player/conon.png"));
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
		a_g2.drawImage(l_image, m_bx, m_by,m_gp.TILE_SIZE/2, m_gp.TILE_SIZE/2, null);
	}
	public boolean getStatus() {
		return m_alive;
	}


}
