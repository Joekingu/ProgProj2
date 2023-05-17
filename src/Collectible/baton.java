package Collectible;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

public class baton extends mele {

	public baton(Entity p, GamePanel a_gp) {
		super(p, 5, 50, a_gp, 20, 1e9);
		this.getImage();
	}

	public void getImage() {
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/items/baton.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D a_g2, int x,int y) {
		// r�cup�re l'image du joueur
		BufferedImage l_image = m_idleImage;

		// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et
		// de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		a_g2.drawImage(l_image, porteur.getx()+m_gp.TILE_SIZE, porteur.gety(), m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);

	}
}