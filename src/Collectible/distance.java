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

public abstract class distance extends arme{
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
	
}