package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Gestionnaire d'�v�nements (touche clavier)
 *
 */
public class KeyHandler implements KeyListener{
	ArrayList<Integer> pressed= new ArrayList<Integer>();
	
	public int getval(int i) {
		return pressed.get(i);
	}
	
	public void setval(int val, int indice) {
		pressed.set(indice, val);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// r�cup�re le code du boutton appuy�
		int code = e.getKeyCode();
		if(!pressed.contains(code)) {
			pressed.add(code);
		}
		System.out.println("[");
		for(int i=0;i<pressed.size();i++) {
			System.out.println(pressed.get(i));
		}
		System.out.println("]");
		System.out.println(pressed.size());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		pressed.remove(Integer.valueOf(code));
	}
	
	public int taille() {
		return pressed.size();
	}
}
