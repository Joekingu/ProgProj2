package entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import tile.Tile;

public class Soucoupe extends Entity{
	GamePanel m_gp;
	KeyHandler m_keyH;
	Tile m_collision;
	boolean m_alive;
	int m_spmat = 0;
	int m_health;
	public BufferedImage m_idleImage1;
	public BufferedImage m_idleImage2;
	public BufferedImage m_idleImage3;
	public BufferedImage m_idleImage4;
	public BufferedImage m_idleImage5;
	public BufferedImage m_idleImage6;
	public BufferedImage m_idleImage7;
	public BufferedImage m_idleImage8;
	public BufferedImage m_idleImage9;

	
	public Soucoupe(GamePanel a_gp) {
		this.m_gp = a_gp;
		this.setDefaultValues();
		this.getSoucoupeImages();
		this.m_collision = new Tile();
		this.getSoucoupeImages();
		
	}
	protected void setDefaultValues() {
		m_x = 1500;
		m_y = 500;
		m_speed = 0;
		m_health = 50;
		m_alive = true;
	}
	public void getSoucoupeImages() {
	try {
		m_idleImage = ImageIO.read(getClass().getResource("/tiles/vaisseau1.png"));
		m_idleImage2 = ImageIO.read(getClass().getResource("/tiles/vaisseau2.png"));
		m_idleImage3 = ImageIO.read(getClass().getResource("/tiles/vaisseau3.png"));
		m_idleImage4 = ImageIO.read(getClass().getResource("/tiles/vaisseau4.png"));
		m_idleImage5 = ImageIO.read(getClass().getResource("/tiles/vaisseau5.png"));
		m_idleImage6 = ImageIO.read(getClass().getResource("/tiles/vaisseau6.png"));
		m_idleImage7 = ImageIO.read(getClass().getResource("/tiles/vaisseau7.png"));
		m_idleImage8 = ImageIO.read(getClass().getResource("/tiles/vaisseau8.png"));
		m_idleImage9 = ImageIO.read(getClass().getResource("/tiles/vaisseau9.png"));
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
	public void draw(Graphics2D a_g2) {
		// r�cup�re l'image du joueur
		BufferedImage l_image = m_idleImage;
		BufferedImage l_image2 = m_idleImage2;
		BufferedImage l_image3 = m_idleImage3;
		BufferedImage l_image4 = m_idleImage4;
		BufferedImage l_image5 = m_idleImage5;
		BufferedImage l_image6 = m_idleImage6;
		BufferedImage l_image7 = m_idleImage7;
		BufferedImage l_image8 = m_idleImage8;
		BufferedImage l_image9 = m_idleImage9;
		// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et
		// de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		a_g2.drawImage(l_image, m_x, m_y, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
		a_g2.drawImage(l_image2, m_x+m_gp.TILE_SIZE, m_y, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
		a_g2.drawImage(l_image3, m_x+(m_gp.TILE_SIZE*2), m_y, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
		a_g2.drawImage(l_image4, m_x, m_y+m_gp.TILE_SIZE, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
		a_g2.drawImage(l_image5, m_x+m_gp.TILE_SIZE, m_y+m_gp.TILE_SIZE, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
		a_g2.drawImage(l_image6, m_x+(m_gp.TILE_SIZE*2), m_y+m_gp.TILE_SIZE, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
		a_g2.drawImage(l_image7, m_x, m_y+(m_gp.TILE_SIZE*2), m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
		a_g2.drawImage(l_image8, m_x+m_gp.TILE_SIZE, m_y+(m_gp.TILE_SIZE*2), m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
		a_g2.drawImage(l_image9, m_x+(m_gp.TILE_SIZE*2), m_y+(m_gp.TILE_SIZE*2), m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
	}
}
