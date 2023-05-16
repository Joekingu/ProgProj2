package tile;

import java.awt.image.BufferedImage;

/**
 * 
 * Element graphique de la carte
 */
public class Tile {
	public BufferedImage m_image;		//image
	public boolean m_collision;		//d�but de gestion de collision entre �l�ments
	public int[] bloc= {1,2,6,7,8};
	public int lave = 3;
	public int sand = 4;
	public int snow = 5;
	
	public Tile(){
		m_collision = false;
	}
}
