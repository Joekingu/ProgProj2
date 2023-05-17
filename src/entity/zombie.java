package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Collectible.Poingmob;
import main.GamePanel;

public class zombie extends mob{

	public zombie(GamePanel a_gp, int health, int x, int y) {
		super(a_gp, health, x, y);
		this.getPlayerImage();
	}
	
	@Override
	protected void setDefaultValues() {
		m_x = 500;
		m_y = 500;
		m_speed = 1;
		weapon = new Poingmob(this,2,10, m_gp,10, 1e9);
	}
	
	public void getPlayerImage() {
		//gestion des expections 
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/Player/zombie.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D a_g2) {
		// r�cup�re l'image du joueur
		BufferedImage l_image = m_idleImage;
		// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		a_g2.drawImage(l_image, m_x, m_y, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
	}
}