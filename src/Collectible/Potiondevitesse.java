package Collectible;

import entity.Player;
import main.GamePanel;

public class Potiondevitesse extends Collectable {
	int ajoutdevitesse;
	public Potiondevitesse(int addspeed){
		ajoutdevitesse=addspeed;
		taille=gp.TILESIZE
	}
	@Override
	void effet(Player p) {
		p.setSpeed(ajoutdevitesse);
	}

	@Override
	void update() {
		if 
	}

	@Override
	void draw(GamePanel gp) {
		// TODO Auto-generated method stub
		
	}
	
}
