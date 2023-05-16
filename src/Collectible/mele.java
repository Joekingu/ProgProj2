package Collectible;

import main.KeyHandler;

public class mele extends arme{
	int m_porter;
	
	public mele(int deg,int porter,KeyHandler a_keyH) {
		super(deg, a_keyH);
		m_porter = porter;
	}
	
}