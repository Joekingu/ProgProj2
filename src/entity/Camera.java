package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Camera extends Entity {
	/**
	 * D�fintition du comportement de la caméra
	 *
	 */
		
		Player m_player;
		
		/**
		 * Constructeur de la caméra
		 * @param on désigne le player qu'on va suivre
		 */
		public Camera(Player p) {
			this.m_player = p;
		}

		/**
		 * Mise � jour des donn�es de la caméra
		 */
		public void update(GamePanel gp) {
			m_x = (m_player.getx()) - (gp.getWidth()/2);
	        m_y = (m_player.gety()) - (gp.getHeight()/2);
		}
		
		public int getx() {
			return m_x;
		}
		
		public int gety() {
			return m_y;
		}
	}

