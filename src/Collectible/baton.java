package Collectible;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

public abstract class baton extends mele{

	public baton(Entity p,GamePanel a_gp) {
		super(p,5,50,a_gp,20,1e9);
		this.getGOImage();
	}
	
	public void draw(Graphics2D a_g2,int x,int y) {
		int d=m_gp.TILE_SIZE;
		BufferedImage l_image = m_idleImage;
		if (x==0 && y==1) {
			a_g2.rotate(-Math.PI/2);
		}
		if (x==0 && y==-1) {
			a_g2.rotate(Math.PI/2);
		}
		if (x==1 && y==0) {
			a_g2.rotate(0);
		}
		if (x==-1 && y==0) {
			a_g2.rotate(Math.PI);
		}
		if (x==1 && y==1) {
			a_g2.rotate(-Math.PI/4);
		}
		if (x==1 && y==-1) {
			a_g2.rotate(Math.PI/4);
		}
		if (x==-1 && y==1) {
			a_g2.rotate(-3*Math.PI/4);
		}
		if (x==-1 && y==-1) {
			a_g2.rotate(3*Math.PI/4);
		}
		a_g2.drawImage(l_image, porteur.m_x + x*d, porteur.m_y +y*d, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
	}
	
	public void getGOImage() {
		// gestion des expections
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/player/sword_longcut.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}