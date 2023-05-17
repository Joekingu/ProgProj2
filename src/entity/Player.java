package entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Collectible.arme;
import Collectible.baton;
import main.GamePanel;
import main.KeyHandler;
import tile.Tile;
import tile.TileManager;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * D�fintition du comportement d'un joueur
 *
 */
public class Player extends Entity {

	GamePanel m_gp;
	KeyHandler m_keyH;
	KeyHandler m_keyH_arme;
	Tile m_collision;
	arme m_arme;
	boolean m_alive;
	int m_spmat = 0;
	int m_health;
	double time;

	/**
	 * Constructeur de Player
	 * 
	 * @param a_gp   GamePanel, pannel principal du jeu
	 * @param a_keyH KeyHandler, gestionnaire des touches
	 */
	public Player(GamePanel a_gp, KeyHandler a_keyH,KeyHandler a_keyH_arme) {
		this.m_gp = a_gp;
		this.m_keyH = a_keyH;
		this.m_keyH_arme = a_keyH_arme;
		this.setDefaultValues();
		this.getPlayerImage();
		this.m_collision = new Tile();
	}

	/**
	 * Initialisation des donn�es membres avec des valeurs par d�faut
	 */
	protected void setDefaultValues() {
		m_x = 450;
		m_y = 500;
		m_speed = 2;
		m_health = 50;
		m_alive = true;
		m_arme = new baton(this,m_gp);
	}

	/**
	 * R�cup�ration de l'image du personnage
	 */
	public void getPlayerImage() {
		// gestion des expections
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/Player/superhero.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isAlive() {
		return m_alive;
	}

	/**
	 * Mise � jour des donn�es du joueur
	 */
	private boolean in(int x, int[] tab) {
		for (int i = 0; i < tab.length; i++) {
			if (x == tab[i]) {
				return true;
			}
		}
		return false;
	}

	private boolean collision_entity(ArrayList<mob> list, int x, int y) {
		for (Entity i : list) {
			int d = m_gp.TILE_SIZE;
			int ix = i.getx();
			int iy = i.gety();
			double dist_min = d * 3 / 4;
			if (dist(ix, m_x + x, iy, m_y + y) < dist_min) {
				return true;
			}
		}
		return false;
	}

	public boolean test(int x, int y) {
		int d = m_gp.TILE_SIZE;
		int d2 = (d+1) / 2;
		int px = m_x + x + d2;
		int py = m_y + y + d2;
		if (in(m_gp.gettileM().map[(px + d2) / d][(py + d2) / d], m_collision.bloc)
				|| in(m_gp.gettileM().map[(px - d2) / d][(py - d2) / d], m_collision.bloc)
				|| in(m_gp.gettileM().map[(px + d2) / d][(py - d2) / d], m_collision.bloc)
				|| in(m_gp.gettileM().map[(px - d2) / d][(py + d2) / d], m_collision.bloc)
				|| collision_entity(m_gp.getListEnnemis(), x, y)) {
			return true;
		}
		return false;
	}
	
	private int testMat() {
		int d = m_gp.TILE_SIZE;
		int d2 = d/2;
		int px = m_x+d2;
		int py = m_y+d2;
		if(m_gp.gettileM().map[(px+d2)/d][(py+d2)/d]==m_collision.snow) {
			m_spmat=3;
		}else if(m_gp.gettileM().map[(px+d2)/d][(py+d2)/d]==m_collision.sand) {
			m_spmat=-1;
		}else {
			m_spmat=0;
		}
		if(m_gp.gettileM().map[(px+d2)/d][(py+d2)/d]==m_collision.lave) {
			Songs s = new Songs("/songs/lava.wav");
			s.play();
			return 2;
		}
		return 0;
	}
	
	public void update() {
		ArrayList<Integer> pressed = m_keyH.getinstance();
		ArrayList<Integer> direction = m_keyH_arme.getinstance();
		if (m_health<=0) {
			m_alive=false;
		}
		testMat();
		if(testMat()!=0) {
			this.estblesse(testMat());
		}
		if (pressed.contains(Integer.valueOf(90)) && pressed.contains(Integer.valueOf(81))) {
			if (!test(0, -((m_speed+m_spmat)*3))) {
				m_y -= 2 * (m_speed+m_spmat);
			}
			if (!test(-((m_speed+m_spmat)*3), 0)) {
				m_x -= 2 * (m_speed+m_spmat);
			}
			
		} else if (pressed.contains(Integer.valueOf(90)) && pressed.contains(Integer.valueOf(68))) {
			if (!test(0, -((m_speed+m_spmat)*3))) {
				m_y -= 2 * (m_speed+m_spmat);
			}
			if (!test((m_speed+m_spmat)*3, 0)) {
				m_x += 2 * (m_speed+m_spmat);
			}
			
		} else if (pressed.contains(Integer.valueOf(68)) && pressed.contains(Integer.valueOf(83))) {
			if (!test((m_speed+m_spmat)*3, 0)) {
				m_x += 2 * (m_speed+m_spmat);
			}
			if (!test(0, (m_speed+m_spmat)*3)) {
				m_y += 2 * (m_speed+m_spmat);
			}

		} else if (pressed.contains(Integer.valueOf(81)) && pressed.contains(Integer.valueOf(83))) {
			if (!test(-((m_speed+m_spmat)*3), 0)) {
				m_x -= 2 * (m_speed+m_spmat);
			}
			if (!test(0, (m_speed+m_spmat)*3)) {
				m_y += 2 * (m_speed+m_spmat);
			}
		} else {
			for (int j = 0; j < m_keyH.taille(); j++) {
				if (m_keyH.getval(j) == 90) {
					if (!test(0, -((m_speed+m_spmat)*3))) {
						m_y -= 3 * (m_speed+m_spmat);
					}
				} else if (m_keyH.getval(j) == 83) {
					if (!test(0, (m_speed+m_spmat)*3)) {
						m_y += 3 * (m_speed+m_spmat);
					}
				} else if (m_keyH.getval(j) == 68) {
					if (!test((m_speed+m_spmat)*3, 0)) {
						m_x += 3 * (m_speed+m_spmat);
					}
				} else if (m_keyH.getval(j) == 81) {
					if (!test(-((m_speed+m_spmat)*3), 0)) {
						m_x -= 3 * (m_speed+m_spmat);
					}
				}

			}
		}
		int dirx = 0;
		int diry = 0;
		boolean att= false;
		if (direction.contains(Integer.valueOf(37)) && direction.contains(Integer.valueOf(38))) {
			dirx = -1;
			diry = -1;
			att=true;
		} else if (direction.contains(Integer.valueOf(37)) && direction.contains(Integer.valueOf(40))) {
			dirx = -1;
			diry = 1;
			att=true;
		} else if (direction.contains(Integer.valueOf(39)) && direction.contains(Integer.valueOf(38))) {
			dirx = 1;
			diry = -1;
			att=true;
		} else if (direction.contains(Integer.valueOf(39)) && direction.contains(Integer.valueOf(40))) {
			dirx = 1;
			diry = 1;
			att=true;
		} else {
			for (int j = 0; j < m_keyH_arme.taille(); j++) {
				if (m_keyH_arme.getval(j) == 37) {
					dirx = -1;
					att=true;
				} else if (m_keyH_arme.getval(j) == 40) {
					diry = 1;
					att=true;
				} else if (m_keyH_arme.getval(j) == 39) {
					dirx = 1;
					att=true;
				} else if (m_keyH_arme.getval(j) == 38) {
					diry = -1;
					att=true;
				} 
			}
		}
		if(att && System.nanoTime() - time > m_arme.getfrq_att()) {
			time=System.nanoTime();
			m_arme.attaquejoueur(dirx,diry);
		}
	}

	public int gethealth() {
		return m_health;
	}

	public void gameOver() {
		this.setDefaultValues();
	}

	/**
	 * Affichage du l'image du joueur dans la fen�tre du jeu
	 * 
	 * @param a_g2 Graphics2D
	 */
	public void draw(Graphics2D a_g2) {
		// r�cup�re l'image du joueur
		BufferedImage l_image = m_idleImage;
		// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et
		// de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		a_g2.drawImage(l_image, m_x, m_y, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
		a_g2.setStroke(new BasicStroke(2f));
		a_g2.drawRoundRect(m_x + 3, m_y - 25, 50, 10, 10, 10);
		a_g2.setColor(Color.RED);
		a_g2.fillRoundRect(m_x + 3, m_y - 25, m_health, 10, 10, 10);
	}

	public int getx() {
		return m_x;
	}

	public int gety() {
		return m_y;
	}
	
	public arme getarme() {
		return m_arme;
	}
	
	public void setarme(arme epee) {
		m_arme = epee;
	}

	public void estblesse(int degat) {
		m_health -= degat;
		Songs s = new Songs("/songs/coup.aiff");
		s.play();
	}

}
