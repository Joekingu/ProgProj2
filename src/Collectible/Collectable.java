package Collectible;

import java.awt.image.BufferedImage;

import entity.Player;
import main.GamePanel;

public abstract class  Collectable {
	boolean pris; 
	int taille;
	GamePanel m_gp;
	
	void setStatus(boolean etat) {
		pris=etat;
	}
	abstract void effet(Player p);
	abstract void update();
	abstract void draw(GamePanel gp);
	public BufferedImage m_idleImage;
}
