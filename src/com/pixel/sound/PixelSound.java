package com.pixel.sound;

import java.io.IOException;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public class PixelSound {
	
	public PixelEffect effect;
	public int id;
	public boolean repeat;
	private boolean started;
	private Audio clip;

	
	public PixelSound(PixelEffect effect, int id) {
		
		this.effect = effect;
		this.id = id;
		this.getEffectClip();
		
		
	}
	
	private void getEffectClip() {
		
		try {
			
			clip = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("resources/sounds/effects/" + effect.fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void update() {
		
		if (!clip.isPlaying() && !repeat && started) {
			
			clip.stop();
			clip = null;
			PixelSoundManager.sounds.remove(id);
			
		}
		
	}
	
	public PixelSound setRepeat(boolean repeat) {
		
		this.repeat = true;
		return this;
		
	}
	
	public int start() {
		
		if (repeat) 
			clip.playAsSoundEffect(1.0F, 1.0F, true);
		else
			clip.playAsSoundEffect(1.0F, 1.0F, false);

		started = true;
		
		return id;
		
	}

	public PixelSound stop() {
		
		clip.stop();
		return this;
		
	}

}
