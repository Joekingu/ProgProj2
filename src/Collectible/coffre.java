package Collectible;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class coffre extends Collectable {
	public coffre(GamePanel gp, int x, int y){
		m_x=x;
		m_y=y;
		m_gp=gp;
		pris=true;
		this.getcoffreImage();
	}
	@Override
	void effet(Player p) {
		p.setcoffretrue();
	}

	@Override
	public void update(Player p) {
		if(collision_entity(p)) {
			if (!p.getcoffre()) {
				this.effet(p);
				this.setStatus(false);
			}
		}
	}
	
	public void draw(Graphics2D a_g2) {
		BufferedImage l_image = m_idleImage;
		a_g2.drawImage(l_image, m_x, m_y, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
	}
	
	public void getcoffreImage() {
		//gestion des expections 
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/Collectibles/chest.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
