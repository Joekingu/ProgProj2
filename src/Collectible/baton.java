package Collectible;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

public class baton extends mele{

	public baton(Entity p,GamePanel a_gp) {
		super(p,5,50,a_gp,20,1e9);
	}
}