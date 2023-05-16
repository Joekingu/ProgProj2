package entity;

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
		m_deg=2;
	}
	
}