package tile;

import java.awt.image.BufferedImage;

/**
 * 
 * Element graphique de la carte
 */
public class Tile {
	public BufferedImage m_image;		//image
	public boolean m_collision;		//d�but de gestion de collision entre �l�ments
	public int[] bloc= {1,6};
	
	public Tile(){
		m_collision = false;
	}
	
	public void collision() {
		m_collision = true;
		m_collision = false;
	}
	
	
}
