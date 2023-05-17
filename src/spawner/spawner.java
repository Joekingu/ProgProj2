package spawner;

import entity.mob;
import entity.zombie;
import main.GamePanel;

public class spawner<T extends mob>{
	GamePanel m_gp;
	T m_mob;
	
	public spawner(GamePanel ag,T mob){
		m_gp=ag;
		m_mob = mob;
	}
	
	public void update() {
		if (m_mob instanceof zombie) {
			zombie mob = new zombie(m_gp,50,m_mob.getx(),m_mob.gety());
			m_gp.addListEnnemis(m_gp.random_pos(mob));
		}
		
	}
	
}