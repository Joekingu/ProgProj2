package Collectible;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

public abstract class arme{
	int m_deg;
	GamePanel m_gp;
	double time;
	double frq_att;
	Entity porteur;
	
	protected double dist(int x1,int x2,int y1,int y2) {
		return Math.sqrt(Math.pow((x1-x2),2) + Math.pow((y1-y2),2));
	}
	
	public arme(Entity porte, int deg,GamePanel a_gp,double frq_att) {
		m_deg = deg;
		m_gp = a_gp;
		time = System.nanoTime();
		this.frq_att = frq_att;
		porteur=porte;
	}
	
	public abstract void attaquejoueur(int dirx,int diry);
	
	public abstract void attaquemob();
	
	public double getfrq_att() {
		return frq_att;
	}
	public int getattaque() {
		return m_deg;
	}

	public abstract void draw(Graphics2D a_g2, int dirx, int diry);
}