package Collectible;

import java.util.ArrayList;

import main.GamePanel;
import main.KeyHandler;

public abstract class arme{
	int m_deg;
	KeyHandler m_keyH;
	GamePanel m_gp;
	double time;
	
	protected double dist(int x1,int x2,int y1,int y2) {
		return Math.sqrt(Math.pow((x1-x2),2) + Math.pow((y1-y2),2));
	}
	
	public arme(int deg,KeyHandler a_keyH,GamePanel a_gp) {
		m_deg = deg;
		m_keyH = a_keyH;
		m_gp = a_gp;
		time = System.nanoTime();
	}
	
	public abstract void attaque(int dirx,int diry);
	
	public void update() {
		ArrayList<Integer> pressed = m_keyH.getinstance();
		int dirx = 0;
		int diry = 0;
		boolean att= false;
		if (pressed.contains(Integer.valueOf(37)) && pressed.contains(Integer.valueOf(38))) {
			dirx = -1;
			diry = -1;
			att=true;
		} else if (pressed.contains(Integer.valueOf(37)) && pressed.contains(Integer.valueOf(40))) {
			dirx = -1;
			diry = 1;
			att=true;
		} else if (pressed.contains(Integer.valueOf(39)) && pressed.contains(Integer.valueOf(38))) {
			dirx = 1;
			diry = -1;
			att=true;
		} else if (pressed.contains(Integer.valueOf(39)) && pressed.contains(Integer.valueOf(40))) {
			dirx = 1;
			diry = 1;
			att=true;
		} else {
			for (int j = 0; j < m_keyH.taille(); j++) {
				if (m_keyH.getval(j) == 37) {
					dirx = -1;
					att=true;
				} else if (m_keyH.getval(j) == 40) {
					diry = 1;
					att=true;
				} else if (m_keyH.getval(j) == 39) {
					dirx = 1;
					att=true;
				} else if (m_keyH.getval(j) == 38) {
					diry = -1;
					att=true;
				} 
			}
		}
		if(att && System.nanoTime() - time > 1e9) {
			time=System.nanoTime();
			attaque(dirx,diry);
		}
	}
}