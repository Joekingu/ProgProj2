package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Collectible.Poingmob;
import Collectible.arme;
import main.GamePanel;
import tile.Tile;

/**
 * D�fintition du comportement d'un joueur
 *
 */
public class mob extends Entity{

	GamePanel m_gp;
	Tile m_collision;
	int m_health;  // vie du pnj
	boolean isalive;
	arme weapon;
	double freq_att;
	double time;
	
	/**
	 * Constructeur de Player
	 * @param a_gp GamePanel, pannel principal du jeu
	 * @param a_keyH KeyHandler, gestionnaire des touches 
	 */
	
	public mob(GamePanel a_gp, int health,int x,int y) {
		this.m_gp = a_gp;
		this.m_health=health;
		this.setDefaultValues();
		this.m_collision = new Tile();
		this.isalive = true;
		m_x = x;
		m_y = y;
		time=System.nanoTime();
	}
	protected void setDefaultValues() {
		m_x = 500;
		m_y = 500;
		m_speed = 1;
		weapon= new Poingmob(this,2,20,m_gp,10,1e9);
	}
	
	private boolean in (int x , int[]tab) {
		for (int i=0;i<tab.length;i++) {
			if (x==tab[i]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean collision_entity(Player player) {
		int d = m_gp.TILE_SIZE;
		int ix = player.getx();
		int iy = player.gety();
		double dist_min = d* 3/4;
		if ( dist(ix,m_x,iy,m_y)<dist_min ){
			return true;
		}
		return false;
	}
	
	private boolean collision_mob(ArrayList<mob> list,int x,int y) {
		for(mob i:list) {
				if(this != i) {
				int d = m_gp.TILE_SIZE;
				int ix = i.getx();
				int iy = i.gety();
				double dist_min = d/2;
				if ( dist(ix,m_x+x,iy,m_y+y)<dist_min ){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean test(int x,int y) {
		int d = m_gp.TILE_SIZE;
		int d2 = d/2;
		int px = m_x+x+d2;
		int py = m_y+y+d2;
		if ( in(m_gp.gettileM().map[(px+d2)/d][(py+d2)/d],m_collision.bloc) || 
				in(m_gp.gettileM().map[(px-d2)/d][(py-d2)/d],m_collision.bloc) ||
				in(m_gp.gettileM().map[(px+d2)/d][(py-d2)/d],m_collision.bloc) ||
				in(m_gp.gettileM().map[(px-d2)/d][(py+d2)/d],m_collision.bloc) || 
				collision_mob(m_gp.getListEnnemis(),x,y) || collision_entity(m_gp.getPlayer())) {
			if((System.nanoTime() - time) > weapon.getfrq_att()) {
				time=System.nanoTime();
				weapon.attaquemob();
			}
			return true;
		}
		return false;
	}
	
	public void update(Player player) {
		if(gethealth()<=0) {
			isalive=false;
		}
		int x = player.getx();
		int y = player.gety();
		if (x>m_x) {
			if (!test(1,0)) {
				m_x +=1*m_speed;
			}
		}
		else {
			if (!test(-1,0)) {
				m_x -=1*m_speed;
			}
		}
		if (y>m_y) {
			if (!test(0,1)) {
				m_y +=1*m_speed;
			}
		}
		else {
			if (!test(0,-1)) {
				m_y -=1*m_speed;
			}
		}

	}

	public void gethit(int deg) {
		m_health -= deg;
	}

	public int gethealth() {
		return m_health;
	}
	
	public boolean getisalive() {
		return this.isalive;
	}
	public void draw(Graphics2D a_g2) {
		// r�cup�re l'image du joueur
		BufferedImage l_image = m_idleImage;
		// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		a_g2.drawImage(l_image, m_x, m_y, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
	}
}