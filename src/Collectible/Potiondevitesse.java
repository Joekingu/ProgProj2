package Collectible;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class Potiondevitesse extends Collectable {
	int ajoutdevitesse;
	public Potiondevitesse(int addspeed, int x, int y){
		ajoutdevitesse=addspeed;
		taille=m_gp.TILE_SIZE/3;
		m_x=x;
		m_y=y;
	}
	@Override
	void effet(Player p) {
		p.setSpeed(ajoutdevitesse);
	}

	@Override
	void update(Player p) {
		if(collision_entity(p)) {
			this.effet(p);
			this.setStatus(false);
		}
	}
	public void getPotiondevitesseImage() {
		//gestion des expections 
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/Collectibles/potion.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	void draw(Graphics2D a_g2) {
		BufferedImage l_image = m_idleImage;
		a_g2.drawImage(l_image, m_x, m_y, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
		
	}
	
}
