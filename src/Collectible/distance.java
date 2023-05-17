package Collectible;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entity.Bullet;
import entity.Entity;
import entity.Player;
import main.GamePanel;
import main.KeyHandler;

public class distance extends arme{
	BufferedImage m_idleImage;
	
	public distance(Entity p,int deg,GamePanel a_gp,double frq_att) {
		super(p, deg,a_gp,frq_att);
	}
	
	public int getX() {
		return porteur.m_x;
	}
	public int getY() {
		return porteur.m_y;
	}
	
	public int getType() {
		return 1;
	}
	@Override
	public void attaquejoueur(int dirx, int diry) {
		Bullet balle = new Bullet(m_gp,this, 20, 7,1, dirx,diry );
		m_gp.addTirs(balle);
	}
	@Override
	public void attaquemob() {
		//mettre l'attaque pour les mobs en mode ils regardent la direction et pour une distance au joueur min à un truc qu'on aura choisit, on tire dans cette direction
	}
	
}