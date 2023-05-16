package Collectible;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import entity.Player;
import main.GamePanel;

public abstract class  Collectable {
	boolean pris; 
	GamePanel m_gp;
	int m_x;
	int m_y;
	public BufferedImage m_idleImage;
	
	public boolean getStatus() {
		return pris;
	}
	public void setStatus(boolean etat) {
		pris=etat;
	}
	protected double dist(int x1,int x2,int y1,int y2) {
		return Math.sqrt(Math.pow((x1-x2),2) + Math.pow((y1-y2),2));
	}
	protected boolean collision_entity(Entity player) {
		int d = m_gp.TILE_SIZE;
		int ix = player.getx();
		int iy = player.gety();
		double dist_min = d*3/4;
		if ( dist(ix,m_x,iy,m_y)<dist_min ){
			return true;
		}
		return false;
	}
	abstract void effet(Player p);
	public abstract void update(Player p);
	public abstract void draw(Graphics2D a_g2);
}
