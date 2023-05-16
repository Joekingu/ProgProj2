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
			m_gp.addListEnnemis(new zombie(m_gp,50,m_mob.getx(),m_mob.gety()));
		}
		
	}
	
}