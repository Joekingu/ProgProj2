package Collectible;

import entity.Player;
import main.GamePanel;

public abstract class  Collectable {
	boolean pris; 
	abstract void effet(Player p);
	abstract void update();
	abstract void draw(GamePanel gp);
}
