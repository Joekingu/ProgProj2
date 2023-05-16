package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Gestionnaire d'�v�nements (touche clavier)
 *
 */
public class KeyHandler implements KeyListener{
	int m_val=0;
	
	public int getval() {
		return m_val;
	}
	
	public void setval(int val) {
		m_val = val;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// r�cup�re le code du boutton appuy�
		int code = e.getKeyCode();
		m_val=code;
		System.out.println(code);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
