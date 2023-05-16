package Collectible;

import entity.Player;
import entity.mob;
import main.GamePanel;
import main.KeyHandler;

public class mele extends arme{
	int m_porter;
	int m_kb;
	
	public mele(int deg,int porter,KeyHandler a_keyH, GamePanel a_gp,int kb) {
		super(deg, a_keyH,a_gp);
		m_porter = porter;
		m_kb = kb;
	}

	public void attaque(int dirx, int diry) {
		Player p = m_gp.getPlayer();
		int x = p.getx() + dirx*m_gp.TILE_SIZE/2;
		int y = p.gety() + diry*m_gp.TILE_SIZE/2;
		int dist_min = m_gp.TILE_SIZE+m_porter;
		for(mob i : m_gp.getListEnnemis()) {
			if(dist(x,i.getx(),y,i.gety())<dist_min) {
				i.sethealth(m_deg);
				if (dirx != 0) {
					i.setx(dirx*m_kb);
				}
				if (diry != 0) {
					i.sety(diry*m_kb);
				}
			}
		}
	}
	
	
	
}