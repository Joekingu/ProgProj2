package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Entit� de base du jeu
 *
 */
public abstract class Entity {
	public int m_x, m_y;				//position sur la map
	public int m_speed;					//D�placement de l'entit�
	public BufferedImage m_idleImage;	//Une image de l'entit�
	
	protected double dist(int x1,int x2,int y1,int y2) {
		return Math.sqrt(Math.pow((x1-x2),2) + Math.pow((y1-y2),2));
	}
	
	public int getx() {
		return m_x;
	}
	
	public int gety() {
		return m_y;
	}
	
	public void setm_x(int x) {
		m_x = x;
	}
	
	public void setm_y(int y) {
		m_y = y;
	}
	
	public void setx(int kb) {
		m_x += kb;
	}
	
	public void sety(int kb) {
		m_y += kb;
	}
	public void setSpeed(int vitesse) {
		m_speed+=vitesse;
	}
	
}
