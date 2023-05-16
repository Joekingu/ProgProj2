package Collectible;

import main.GamePanel;
import main.KeyHandler;

public class mele extends arme{
	int m_porter;
	
	public mele(int deg,int porter,KeyHandler a_keyH, GamePanel a_gp) {
		super(deg, a_keyH,a_gp);
		m_porter = porter;
	}

	public void attaque(int dirx, int diry) {
		
	}
	
	
	
}