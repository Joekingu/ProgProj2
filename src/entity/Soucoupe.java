package entity;

import main.GamePanel;
import main.KeyHandler;
import tile.Tile;

public class Soucoupe extends Entity{
	GamePanel m_gp;
	KeyHandler m_keyH;
	Tile m_collision;
	Ammo m_ammo;
	boolean m_alive;
	int m_spmat = 0;
	int m_health;
	
	public Soucoupe(int x, int y) {
		
	}
	protected void setDefaultValues() {
		m_x = m_gp.SCREEN_WIDTH / 2;
		m_y = m_gp.SCREEN_HEIGHT / 2;
		m_speed = 0;
		m_health = 50;
		m_alive = true;
	}
}
