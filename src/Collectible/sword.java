package Collectible;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class sword extends mele{

	public sword(Entity p,GamePanel a_gp) {
		super(p,25,50,a_gp,40,2e9);
	}
}