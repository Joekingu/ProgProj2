package Collectible;

import main.GamePanel;
import main.KeyHandler;

public class Poingmob{
	int m_deg;
	GamePanel m_gp;
	double time;
	double frq_att;
	
	public Poingmob(int degat, GamePanel a_gp, double freq){
		m_deg=degat;
		m_gp=a_gp;
		frq_att=freq;
		time= System.nanoTime();
	}

	public int getAttaque() {
		return m_deg;
	}
	public double getfrq_att() {
		return frq_att;
	}
	public double gettime() {
		return time;
	}
	public void settime(double temps) {
		time=temps;
	}

}
