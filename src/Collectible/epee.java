package Collectible;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class epee extends Collectable {
	public epee(GamePanel gp, int x, int y){
		m_x=x;
		m_y=y;
		m_gp=gp;
		pris=true;
		this.getepee();
	}

	@Override
	public void update(Player p) {
		if(collision_entity(p)) {
			this.effet(p);
			this.setStatus(false);
		}
	}
	
	public void draw(Graphics2D a_g2) {
		BufferedImage l_image = m_idleImage;
		a_g2.drawImage(l_image, m_x, m_y, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
	}
	
	public void getepee() {
		//gestion des expections 
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/Player/sword_longcut.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	void effet(Player p) {
		p.setarme(new sword(p,m_gp));
		
	}
	
}