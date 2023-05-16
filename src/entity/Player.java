	package entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import tile.Tile;
import tile.TileManager;

/**
 * D�fintition du comportement d'un joueur
 *
 */
public class Player extends Entity{

	GamePanel m_gp;
	KeyHandler m_keyH;
	Tile m_collision;
	Ammo m_ammo;
	boolean m_alive;
	
	int m_health;
	/**
	 * Constructeur de Player
	 * @param a_gp GamePanel, pannel principal du jeu
	 * @param a_keyH KeyHandler, gestionnaire des touches 
	 */
	public Player(GamePanel a_gp, KeyHandler a_keyH, Ammo ammo) {
		this.m_gp = a_gp;
		this.m_keyH = a_keyH;
		this.setDefaultValues();
		this.getPlayerImage();
		m_ammo=ammo;
		this.m_collision = new Tile();
	}
	
	/**
	 * Initialisation des donn�es membres avec des valeurs par d�faut
	 */
	protected void setDefaultValues() {
		m_x = m_gp.SCREEN_WIDTH/2;
		m_y = m_gp.SCREEN_HEIGHT/2;
		m_speed = 2;
		m_health=50;
		m_alive=true;
	}
	
	/**
	 * R�cup�ration de l'image du personnage
	 */
	public void getPlayerImage() {
		//gestion des expections 
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/Player/superhero.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isAlive() {
		return m_alive;
	}
	
	/**
	 * Mise � jour des donn�es du joueur
	 */
//	private boolean in (int x , int[]tab) {
//		for (int i=0;i<tab.length;i++) {
//			if (x==tab[i]) {
//				return true;
//			}
//		}
//		return false;
//	}
	
	private boolean test(int x,int y) {
		int d = m_gp.TILE_SIZE;
		int d2 = d/2;
		int px = m_x+x+d2;
		int py = m_y+y+d2;
		if (m_gp.gettileM().map[(px+d2)/d][(py+d2)/d] == 1 || 
				m_gp.gettileM().map[(px-d2)/d][(py-d2)/d] == 1 ||
				m_gp.gettileM().map[(px+d2)/d][(py-d2)/d] == 1 ||
				m_gp.gettileM().map[(px-d2)/d][(py+d2)/d] == 1 ) {
			m_collision.collision();
			return true;
		}
		return false;
	}
	
	public void update() {
		for(int j = 0; j<m_keyH.taille();j++) {
			if (m_keyH.getval(j) == 90) {
				if (!test(0,-10)) {
					m_y-= 2*m_speed;
				}
			}
			if (m_keyH.getval(j) == 83) {
				if (!test(0,10)) {
					m_y+= 2*m_speed;
				}
			}
			if (m_keyH.getval(j) == 68) {
				if (!test(10,0)) {
					m_x+= 2*m_speed;
				}
			}
			if (m_keyH.getval(j) == 81) {
				if (!test(-10,0)) {
					m_x-= 2*m_speed;
				}
			}
		}
		
	}
	
	public int gethealth(){
		return m_health;
	}
	
	
	/**
	 * Affichage du l'image du joueur dans la fen�tre du jeu
	 * @param a_g2 Graphics2D 
	 */
	public void draw(Graphics2D a_g2) {
		// r�cup�re l'image du joueur
		BufferedImage l_image = m_idleImage;
		// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		a_g2.drawImage(l_image, m_x, m_y, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
		a_g2.setStroke(new BasicStroke(2f));
		a_g2.drawRoundRect(m_x+3,m_y-25,50,10,10,10);
		a_g2.setColor(Color.RED);
		a_g2.fillRoundRect(m_x+3,m_y-25,m_health,10,10,10);
	}
	
	public int getx() {
		return m_x;
	}
	
	public int gety() {
		return m_y;
	}
}
