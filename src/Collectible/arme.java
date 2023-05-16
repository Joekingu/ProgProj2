package Collectible;

import java.util.ArrayList;

import main.KeyHandler;

public abstract class arme{
	int m_deg;
	KeyHandler m_keyH;
	
	public arme(int deg,KeyHandler a_keyH) {
		m_deg = deg;
		m_keyH = a_keyH;
	}
	
	public abstract void attaque(int dirx,int diry);
	
	public void update() {
		ArrayList<Integer> pressed = m_keyH.getinstance();
		int dirx = 0;
		int diry = 0;
		if (pressed.contains(Integer.valueOf(37)) && pressed.contains(Integer.valueOf(38))) {
			dirx = -1;
			diry = -1;
		} else if (pressed.contains(Integer.valueOf(37)) && pressed.contains(Integer.valueOf(40))) {
			dirx = -1;
			diry = 1;
		} else if (pressed.contains(Integer.valueOf(39)) && pressed.contains(Integer.valueOf(38))) {
			dirx = 1;
			diry = -1;
		} else if (pressed.contains(Integer.valueOf(39)) && pressed.contains(Integer.valueOf(40))) {
			dirx = 1;
			diry = 1;
		} else {
			for (int j = 0; j < m_keyH.taille(); j++) {
				if (m_keyH.getval(j) == 37) {
					dirx = -1;
				} else if (m_keyH.getval(j) == 40) {
					diry = 1;
				} else if (m_keyH.getval(j) == 39) {
					dirx = 1;
				} else if (m_keyH.getval(j) == 38) {
					diry = -1;
				}
			}
		}
		attaque(dirx,diry);
	}
}