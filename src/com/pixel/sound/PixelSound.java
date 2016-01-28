package com.pixel.sound;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import com.pixel.util.Toolkit;

public class PixelSound {
	
	public PixelEffect effect;
	public int id;
	public boolean repeat;
	private boolean started;
	private Sound clip;
	private float volume = 1.0F;
	private boolean mute = true;
	
	public PixelSound(PixelEffect effect, int id) {
		
		this.effect = effect;
		this.id = id;
		this.getEffectClip();
		
		
	}
	
	public PixelSound(PixelEffect effect, int id, float volume) {
		
		this.effect = effect;
		this.id = id;
		this.getEffectClip();
		this.volume = volume;
		
	}
	
	private void getEffectClip() {
		
		try {
			Toolkit t = new Toolkit();
			clip = new Sound(t.getPath() + "resources/sounds/effects/" + effect.fileName);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void update() {
		
		if (!clip.playing() && !repeat && started) {
			
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
		
		float v;
		if (mute)
			v = 0.0F;
		else
			v = volume;
		
		if (repeat) 
			clip.loop(1.0F, v);
		else
			clip.play(1.0F, v);

		started = true;
		
		return id;
		
	}

	public PixelSound stop() {
		
		clip.stop();
		return this;
		
	}

}
