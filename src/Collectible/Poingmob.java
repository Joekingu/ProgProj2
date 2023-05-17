package Collectible;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

public class Poingmob extends mele{
	
	public Poingmob(Entity p,int deg,int porter, GamePanel a_gp,int kb,double frq_att){
		super(p,deg, porter, a_gp, kb , frq_att);
	}
	public void getImage() {
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/items/shotgun.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D a_g2, int x, int y) {
		// r�cup�re l'image du joueur
		BufferedImage l_image = m_idleImage;

		// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et
		// de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		a_g2.drawImage(l_image, porteur.getx()+m_gp.TILE_SIZE,porteur.gety(), m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);

	}

}
