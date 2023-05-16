package Collectible;

import java.awt.image.BufferedImage;

import entity.Player;
import main.GamePanel;

public class Potiondevitesse extends Collectable {
	int ajoutdevitesse;
	public Potiondevitesse(int addspeed){
		ajoutdevitesse=addspeed;
		taille=m_gp.TILE_SIZE/3;
	}
	@Override
	void effet(Player p) {
		p.setSpeed(ajoutdevitesse);
	}

	@Override
	void update(Player p) {
		int x = p.getx();
		int y = p.gety();
		if() {
			
		}
	}

	@Override
	void draw() {
		BufferedImage l_image = m_idleImage;
		m_gp.drawImage(l_image, m_x, m_y, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null)
		
	}
	
}
