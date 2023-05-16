package main;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;

import entity.Ammo;
import entity.Player;
import entity.mob;
import entity.zombie;
import spawner.spawner;
import entity.Camera;
import entity.Entity;
import entity.Gun;
import tile.TileManager;
import Collectible.Collectable;
import Collectible.Potiondevitesse;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Panel principal du jeu contenant la map principale
 *
 */
public class GamePanel extends JPanel implements Runnable {

	// Param�tres de l'�cran
	final int ORIGINAL_TILE_SIZE = 1; // une tuile de taille 16x16
	public final int SCALE = 60; // �chelle utilis�e pour agrandir l'affichage
	public final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // 48x48
	public final int MAX_SCREEN_COL = 10;
	public final int MAX_SCREE_ROW = 10; // ces valeurs donnent une r�solution 4:3
	public final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; // 768 pixels
	public final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREE_ROW; // 576 pixels

	// FPS : taux de rafraichissement
	int m_FPS;

	// état du jeu : 0 playing , 1 Game Over , 2 Menu
	int m_gamestate;

	// Cr�ation des diff�rentes instances (Player, KeyHandler, TileManager,
	// GameThread ...)
	KeyHandler m_keyH;
	Thread m_gameThread;
	Player m_player;
	TileManager m_tileM;
	Ammo m_ammo;
	Gun m_gun;
	Camera m_camera;
	ArrayList<Collectable> acollecter;
	ArrayList<spawner<mob>> listSpawner;
	ArrayList<mob> listEnnemis;

	/**
	 * Constructeur
	 */
	public GamePanel() {
		m_FPS = 60;
		m_gamestate = 0;
		m_keyH = new KeyHandler();
		m_player = new Player(this, m_keyH, m_ammo);
		m_ammo = new Ammo(this, m_player, 10, 10, TILE_SIZE/4, 0);
		m_gun = new Gun(this,m_keyH, m_player, 4);
		m_ammo = new Ammo(this, m_keyH, m_player, 10, 10, TILE_SIZE / 4);
		m_tileM = new TileManager(this);
		m_camera = new Camera(m_player);
		listEnnemis = new ArrayList<>();
		acollecter = new ArrayList<>();
		listSpawner = new ArrayList<>();
		spawner<zombie> t = new spawner<>(this,new zombie(this,50,400,400));
		t.update();
		spawner<zombie> t1 = new spawner<>(this,new zombie(this,50,800,800));
		t1.update();

		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(m_keyH);
		this.setFocusable(true);
		this.makeCollectibles();
	}

	/**
	 * Lancement du thread principal
	 * 
	 * @return
	 */
	public void makeCollectibles() {
		acollecter.add(new Potiondevitesse(this, 1, 1000, 800));
	}

	public Entity getPlayer() {
		return m_player;
	}
	
	public void addListEnnemis(mob ennemi) {
		listEnnemis.add(ennemi);
	}
	
	public ArrayList<mob> getListEnnemis() {
		return listEnnemis;
	}

	public void startGameThread() {
		m_gameThread = new Thread(this);
		m_gameThread.start();
	}

	public void run() {

		double drawInterval = 1000000000 / m_FPS; // rafraichissement chaque 0.0166666 secondes
		double nextDrawTime = System.nanoTime() + drawInterval;

		while (m_gameThread != null) { // Tant que le thread du jeu est actif
			// Permet de mettre � jour les diff�rentes variables du jeu
			if (m_gamestate == 0) {
				if (m_player.isAlive()) {
					this.update();
				} else {
					this.gameOver();
					m_gamestate = 1;

				}

				// Dessine sur l'�cran le personnage et la map avec les nouvelles informations.
				// la m�thode "paintComponent" doit obligatoirement �tre appel�e avec
				// "repaint()"
				this.repaint();

				// Calcule le temps de pause du thread
				try {
					double remainingTime = nextDrawTime - System.nanoTime();
					remainingTime = remainingTime / 1000000;

					if (remainingTime < 0) {
						remainingTime = 0;
					}

					Thread.sleep((long) remainingTime);
					nextDrawTime += drawInterval;

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
//			if (m_gamestate == 1) {
//				for (int j = 0; j < m_keyH.taille(); j++) {
//					if (m_keyH.getval(j) == 82) {
//						m_gamestate=0;
//					}
//				}
//			}
		}
	}

	/**
	 * Mise � jour des donn�es des entit�s
	 */
	public void update() {
		m_player.update();
		for ( Entity i : listEnnemis) {
			i.update(m_player);
		}
		m_camera.update(this);
		m_gun.update();
		m_ammo.update();
		for (Collectable item : acollecter) {
			if (item.getStatus() == true) {
				item.update(m_player);
			}
		}
	}

	public void gameOver() {
		m_player.gameOver();
		listEnnemis.removeAll(listEnnemis);
		for (int i = 0; i < acollecter.size(); i += 1) {
			acollecter.get(i).setStatus(true);
		}

	}

	/**
	 * Affichage des �l�ments
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (m_gamestate==0) {
		g2.translate(-m_camera.getx(), -m_camera.gety());
		m_tileM.draw(g2, m_camera);
		m_pnj.draw(g2);
		for ( mob i : listEnnemis) {
			i.draw(g2);
		}
		m_ammo.draw(g2);
		m_player.draw(g2);
		m_gun.draw(g2);
		m_ammo.draw(g2);
		for(Collectable item:acollecter) {
			if(item.getStatus()== true) {
				item.draw(g2);
			}
		}
		if (m_gamestate == 1) {
			g2.setColor(Color.BLACK);
			g2.fillRect(MAX_SCREE_ROW, MAX_SCREEN_COL, SCREEN_WIDTH, SCREEN_HEIGHT);
			g2.setColor(Color.RED);
			g2.drawString("C4EST MORT POTO", 200, 200);
		}
		g2.dispose();
	}

	public TileManager gettileM() {
		return m_tileM;
	}
}
