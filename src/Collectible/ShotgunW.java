package Collectible;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Bullet;
import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

public class ShotgunW extends distance{

	public ShotgunW(Entity p , GamePanel a_gp) {
		super(p,50, a_gp, 5e8);
	}
	
	@Override
	public void attaquejoueur(int dirx, int diry) {
		Bullet balle = new Bullet(m_gp,this, 50, 7,1, dirx,diry );
		m_gp.addTirs(balle);
	}
	@Override
	public void attaquemob() {
		//mettre l'attaque pour les mobs en mode ils regardent la direction et pour une distance au joueur min à un truc qu'on aura choisit, on tire dans cette direction
	}
}
