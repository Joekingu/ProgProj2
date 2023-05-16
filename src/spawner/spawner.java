package spawner;

import entity.mob;
import main.GamePanel;

public class spawner<T extends mob>{
	GamePanel m_gp;
	T m_mob;
	
	public spawner(GamePanel ag,T mob){
		m_gp=ag;
		m_mob = mob;
	}
	
	public void update() {
		T ennemi = m_mob;
		m_gp.addListEntity(ennemi);
	}
	
}