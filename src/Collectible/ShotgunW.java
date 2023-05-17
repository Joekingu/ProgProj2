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
	BufferedImage m_gun;

	public ShotgunW(Entity p , GamePanel a_gp) {
		super(p,50, a_gp, 5e8);
		getImage();
	}
	
	private void getImage() {
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/items/shotgun.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	@Override
	public void draw(Graphics2D a_g2,int dirx,int diry) {
		BufferedImage l_image = m_gun;
		double rad=0;
		// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		if(dirx==0 && diry==0) {rad=Math.toRadians(0);}
		if(dirx==1 && diry==0) {rad=Math.toRadians(0);}
		if(dirx==1 && diry==-1) {rad=Math.toRadians(45);}
		if(dirx==0 && diry==-1) {rad=Math.toRadians(90);}
		if(dirx==-1 && diry==-1) {rad=Math.toRadians(135);}
		if(dirx==-1 && diry==0) {rad=Math.toRadians(180);}
		if(dirx==-1 && diry==1) {rad=Math.toRadians(225);}
		if(dirx==0 && diry==1) {rad=Math.toRadians(270);}
		if(dirx==1 && diry==1) {rad=Math.toRadians(315);}		
		a_g2.rotate(rad);
		a_g2.drawImage(l_image, porteur.m_x+m_gp.TILE_SIZE*dirx, porteur.m_y+m_gp.TILE_SIZE*diry ,m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
	}
}
