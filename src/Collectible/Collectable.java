package Collectible;

import java.awt.image.BufferedImage;

import entity.Player;
import main.GamePanel;

public abstract class  Collectable {
	boolean pris; 
	int taille;
	GamePanel m_gp;
	int m_x;
	int m_y;
	
	boolean getStatus() {
		return pris;
	}
	void setStatus(boolean etat) {
		pris=etat;
	}
	abstract void effet(Player p);
	abstract void update(Player p);
	abstract void draw();
	public BufferedImage m_idleImage;
}
