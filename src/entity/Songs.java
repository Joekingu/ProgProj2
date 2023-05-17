package entity;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Songs {

	String m_path;
	private Clip clip;

	public Songs(String path) {
		m_path = path;
	}

	public void play() {
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource(m_path));
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void stopSound() {
		clip.stop();
	}
}
