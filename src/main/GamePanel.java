package main;

import java.awt.Dimension;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import entity.Bullet;
import entity.Player;
import entity.Songs;
import entity.Soucoupe;
import entity.mob;
import entity.zombie;
import spawner.spawner;
import entity.Camera;
import entity.Entity;
import tile.Tile;
import tile.TileManager;
import Collectible.Collectable;
import Collectible.Potiondevitesse;
import Collectible.ShotgunC;
import Collectible.coffre;
import Collectible.epee;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

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
	public BufferedImage m_GOImage;
	public KeyHandler m_keyH;
	KeyHandler m_keyH_arme;
	Thread m_gameThread;
	Player m_player;
	TileManager m_tileM;
	Bullet m_bullet;
	Camera m_camera;
	ArrayList<Collectable> acollecter;
	ArrayList<Bullet> tirs;
	ArrayList<spawner<mob>> listSpawner;
	ArrayList<mob> listEnnemis;
	double spawn_time;
	double spawner_time;
	double coffre_time;
	double global_time;
	Tile collision = new Tile();
	Soucoupe vaisseau;
	Songs s_fond = new Songs("/songs/fond.aiff");
	Songs s_game_over = new Songs("/songs/Game_over.aiff");
	int viezomb;
	int nbr_coffre;

	/**
	 * Constructeur
	 */
	public GamePanel() {
		m_FPS = 60;
		m_gamestate = 0;
		nbr_coffre=0;
		m_keyH = new KeyHandler();
		m_keyH_arme = new KeyHandler();
		m_tileM = new TileManager(this);
		m_camera = new Camera(m_player);
		tirs = new ArrayList<>();
		listEnnemis = new ArrayList<>();
		acollecter = new ArrayList<>();
		listSpawner = new ArrayList<>();
		spawn_time = System.nanoTime();
		spawner_time = System.nanoTime();
		global_time = System.nanoTime();
		coffre_time = System.nanoTime();
		vaisseau = new Soucoupe(this);
		m_player = new Player(this, m_keyH,m_keyH_arme);
		m_camera = new Camera(m_player);
		viezomb = 10;
		mob mob = new zombie(this, viezomb, 0, 0);
		spawner<mob> fist_spawner = new spawner<>(this, random_pos(mob), 5e9);
		listSpawner.add(fist_spawner);
		this.getGOImage();

		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(m_keyH);
		this.addKeyListener(m_keyH_arme);
		this.setFocusable(true);
		this.makeCollectibles();
	}

	/**
	 * Lancement du thread principal
	 * 
	 * @return
	 */
	public void makeCollectibles() {
		Collectable PotiondeVitesse = new Potiondevitesse(this, 1, 0, 0);
		Collectable epee = new epee(this, 0, 0);
		acollecter.add(random_pos(PotiondeVitesse));
		acollecter.add(random_pos(epee));
		acollecter.add(new ShotgunC(this, 1500, 1000));
	}

	public Player getPlayer() {
		return m_player;
	}

	public void getGOImage() {
		// gestion des expections
		try {
			m_GOImage = ImageIO.read(getClass().getResource("/tiles/game_over.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addListEnnemis(mob ennemi) {
		listEnnemis.add(ennemi);
	}

	public ArrayList<mob> getListEnnemis() {
		return listEnnemis;
	}

	public void addTirs(Bullet b) {
		tirs.add(b);
	}

	public ArrayList<Bullet> getTirs() {
		return tirs;
	}

	public void startGameThread() {
		m_gameThread = new Thread(this);
		m_gameThread.start();
		s_fond.play();
	}

	public void run() {

		double drawInterval = 1000000000 / m_FPS; // rafraichissement chaque 0.0166666 secondes
		double nextDrawTime = System.nanoTime() + drawInterval;
		boolean gameOver = false;

		while (m_gameThread != null) { // Tant que le thread du jeu est actif
			// Permet de mettre � jour les diff�rentes variables du jeu
			if (m_gamestate == 0) {
				if (m_player.isAlive()) {
					if (vaisseau.gethealth() == 3) {
						this.gameWin();
						m_gamestate = 1;
					} else {
						this.update();
					}
					this.update();
					System.out.println(tirs.size());
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
					e.printStackTrace();
				}
			}
			if (m_gamestate == 1) {
				s_fond.stopSound();
				if (gameOver == false) {
					s_game_over.play();
					gameOver = true;
				}
				for (int j = 0; j < m_keyH.taille(); j++) {
					if (m_keyH.getval(j) == 82) {// R
						m_gamestate = 0;
						coffre_time = System.nanoTime();
						spawner_time = System.nanoTime();
						spawn_time = System.nanoTime();
						nbr_coffre=0;
						vaisseau.sethealth(0);
					}
				}
				this.repaint();
			}
		}
	}

	/**
	 * Mise � jour des donn�es des entit�s
	 */
	public void update() {
		m_player.update();
		vaisseau.update(m_player);
//		m_player.getarme().update();
		for (mob i : getListEnnemis()) {
			i.update(m_player);
		}
		for (Bullet i : getTirs()) {
				i.update();
		}
		m_camera.update(this);
		for (Collectable item : acollecter) {
			item.update(m_player);
		}

		for (spawner<mob> i : listSpawner) {
			i.update();
		}
		if (System.nanoTime() - spawner_time > 15e9) {
			spawner_time = System.nanoTime();
			viezomb += 10;
			mob mob = new zombie(this, viezomb, 0, 0);
			spawner<mob> spawner = new spawner<>(this, random_pos(mob), 10e9);
			listSpawner.add(spawner);
		}
		if (System.nanoTime() - coffre_time > 30e9 && nbr_coffre<3) {
			coffre_time = System.nanoTime();
			Collectable coffre = new coffre(this, 0, 0);
			acollecter.add(random_pos(coffre));
			nbr_coffre++;
		}
		this.deleteentity();
	}

	public Soucoupe getsoucoupe() {
		return vaisseau;
	}
	
	public void gameOver() {
		m_player.gameOver();
		listEnnemis.removeAll(listEnnemis);
		listSpawner.removeAll(listSpawner);
		acollecter.removeAll(acollecter);
		viezomb = 10;
		this.makeCollectibles();
	}

	public void gameWin() {
		m_player.gameOver();
		listEnnemis.removeAll(listEnnemis);
		listSpawner.removeAll(listSpawner);
		acollecter.removeAll(acollecter);
		viezomb = 10;
		this.makeCollectibles();
	}

	/**
	 * Affichage des �l�ments
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (m_gamestate == 0) {
			g2.translate(-m_camera.getx(), -m_camera.gety());
			m_tileM.draw(g2, m_camera);
			for (mob i : listEnnemis) {
				if (i.getisalive()) {
					i.draw(g2);
				}
			}
			for (Bullet i1 : tirs) {
				System.out.println("oui");
					i1.draw(g2);
			}
			for (Collectable item : acollecter) {
				if (item.getStatus() == true) {
					item.draw(g2);
				}
			}
			m_player.draw(g2);
			vaisseau.draw(g2);
		}
		if (m_gamestate == 1) {
			g2.setColor(Color.BLACK);
			g2.fillRect(MAX_SCREE_ROW, MAX_SCREEN_COL, SCREEN_WIDTH, SCREEN_HEIGHT);
			BufferedImage l_image = m_GOImage;
			// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et
			// de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
			g2.drawImage(l_image, this.getWidth() / 2 - l_image.getWidth() / 2, 0 - this.getHeight() / 8, SCREEN_WIDTH,
					SCREEN_HEIGHT, null);
			g2.setColor(Color.RED);
			g2.drawString("PRESS '' R '' TO RETRY ", this.getWidth() / 2 - 65, 500);
		}
		g2.dispose();
	}


	public TileManager gettileM() {
		return m_tileM;
	}

	private boolean in(int x, int[] tab) {
		for (int i = 0; i < tab.length; i++) {
			if (x == tab[i]) {
				return true;
			}
		}
		return false;
	}

	public Collectable random_pos(Collectable obj) {
		boolean tmp = true;
		int pos_x = 0;
		int pos_y = 0;
		int d = TILE_SIZE;
		int max_y = gettileM().map.length;
		int max_x = gettileM().map[0].length;
		while (tmp) {
			Random rand = new Random();
			pos_x = rand.nextInt(max_x);
			pos_y = rand.nextInt(max_y);
			if (!in(gettileM().map[pos_x][(pos_y)], collision.bloc)&& gettileM().map[pos_x][(pos_y)] != collision.lave) {
				tmp = false;
			}
		}
		obj.setx(pos_x * d);
		obj.sety(pos_y * d);
		return obj;
	}

	public mob random_pos(mob p) {
		boolean tmp = true;
		int pos_x = 0;
		int pos_y = 0;
		int d = TILE_SIZE;
		int max_y = gettileM().map.length;
		int max_x = gettileM().map[0].length;
		while (tmp) {
			Random rand = new Random();
			pos_x = rand.nextInt(max_x);
			pos_y = rand.nextInt(max_y);
			if (!in(gettileM().map[pos_x][(pos_y)], collision.bloc) && gettileM().map[pos_x][(pos_y)] != collision.lave) {
				tmp = false;
			}
		}
		p.m_x = pos_x * d;
		p.m_y = pos_y * d;
		return p;
	}

	public void deleteentity() {
		for (int i = 0; i < listEnnemis.size(); i++) {
			if (!listEnnemis.get(i).getisalive()) {
				listEnnemis.remove(i);
			}
		}
		for (int i = 0; i < acollecter.size(); i++) {
			if (!acollecter.get(i).getStatus()) {
				acollecter.remove(i);
			}
		}
		for(int i = 0; i<tirs.size();i++) {
			if (!tirs.get(i).getStatus()) {
				tirs.remove(i);
			}
		}
	}
}
