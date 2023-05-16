package entity;

import Collectible.Poingmob;
import main.GamePanel;

public class zombie extends mob{

	public zombie(GamePanel a_gp, int health, int x, int y) {
		super(a_gp, health, x, y);
	}
	
	@Override
	protected void setDefaultValues() {
		m_x = 500;
		m_y = 500;
		m_speed = 1;
		weapon = new Poingmob(2,m_gp,1e9);
	}
	
}