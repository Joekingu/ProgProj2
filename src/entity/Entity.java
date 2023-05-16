package entity;

import java.awt.image.BufferedImage;

/**
 * Entit� de base du jeu
 *
 */
public abstract class Entity {
	public int m_x, m_y;				//position sur la map
	public int m_speed;					//D�placement de l'entit�
	public BufferedImage m_idleImage;	//Une image de l'entit�
	
	public int getx() {
		return m_x;
	}
	
	public int gety() {
		return m_y;
	}
}
