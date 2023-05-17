package Collectible;

import entity.Entity;
import entity.Player;
import entity.mob;
import main.GamePanel;
import main.KeyHandler;

public class mele extends arme{
	int m_porter;
	int m_kb;
	
	public mele(Entity p,int deg,int porter, GamePanel a_gp,int kb,double frq_att) {
		super(p, deg,a_gp,frq_att);
		m_porter = porter;
		m_kb = kb;
	}
	
	public int getType() {
		return 0;
	}

	@Override
	public void attaquejoueur(int dirx, int diry) {
		int x = porteur.getx() + dirx*m_gp.TILE_SIZE/2;
		int y = porteur.gety() + diry*m_gp.TILE_SIZE/2;
		int dist_min = m_gp.TILE_SIZE+m_porter;
		for(mob i : m_gp.getListEnnemis()) {
			if(dist(x,i.getx(),y,i.gety())<dist_min) {
				i.gethit(m_deg);
				if (dirx != 0 && i.test(dirx, 0)) {
					i.setx(dirx*m_kb);
				}
				if (diry != 0 && i.test(0, diry)) {
					i.sety(diry*m_kb);
				}
			}
		}
	}

	@Override
	public void attaquemob() {
		Player player=m_gp.getPlayer();
		int x = porteur.getx() + m_gp.TILE_SIZE/2;
		int y = porteur.gety() + m_gp.TILE_SIZE/2;
		int dirx=0;
		int diry=0;
		if(player.getx()-porteur.getx()<0) {
			 dirx= -1;
		}else if(0<player.getx()-porteur.getx()) {
			 dirx= 1;
		}
		if(player.gety()-porteur.gety()<0) {
			 diry= -1;
		}else if(0<player.gety()-porteur.gety()) {
			 diry= 1;
		}
		int dist_min = m_gp.TILE_SIZE+m_porter;
		if(dist(x,player.getx(),y,player.gety())<dist_min) {
			m_gp.getPlayer().estblesse(m_deg);
			if (dirx != 0 && player.test(dirx, 0)) {
				player.setx(dirx*m_kb);
			}
			if (diry != 0 && player.test(0,diry)) {
				player.sety(diry*m_kb);
			}
		}
	}
	
	
	
}