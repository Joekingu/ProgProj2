package spawner;

import entity.mob;
import entity.zombie;
import main.GamePanel;

public class spawner<T extends mob>{
	GamePanel m_gp;
	T m_mob;
	double spawn_time;
	double minim;
	double rep;
	
	public spawner(GamePanel ag,T mob, double repet){
		m_gp=ag;
		m_mob = mob;
		spawn_time = System.nanoTime();
		minim=5e8;
		rep=repet;
	}
	
	public void update() {
		if (m_mob instanceof zombie) {
			if(System.nanoTime() - spawn_time > Math.max(minim,rep)) {
				spawn_time = System.nanoTime();
				zombie mob = new zombie(m_gp,50,m_mob.getx(),m_mob.gety());
				m_gp.addListEnnemis(m_gp.random_pos(mob));
				if(minim<rep) {
					rep-=2e8;
				}
		}
		}
	}
	
}