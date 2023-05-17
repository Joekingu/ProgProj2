package Collectible;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entity.Bullet;
import entity.Player;
import main.GamePanel;
import main.KeyHandler;

public class distance extends arme{
	Player m_player;
	BufferedImage m_idleImage;
	ArrayList<Bullet> tirs= new ArrayList<Bullet>();
	
	public distance(int deg,KeyHandler a_keyH, GamePanel a_gp,double frq_att) {
		super(deg,a_keyH,a_gp,frq_att);
		m_player=m_gp.getPlayer();
	}

	public void attaque(int dirx, int diry) {
		Bullet balle = new Bullet(m_gp,m_player, 5, 4,1, dirx,diry );
		tirs.add(balle);
	}
	
}