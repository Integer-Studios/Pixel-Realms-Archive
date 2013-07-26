package com.pixel.animation;

import com.pixel.entity.Entity;

public class AnimationPlayerComplete extends AnimationPlayer {

	public AnimationPlayerComplete(String p, Entity e) {
		super(p, e);
	}
	
	public void tick() {
		System.out.println(shouldPause + " " + currentFrame + " " + (size-1));
		if (shouldPause && (currentFrame == size-1)) {
			paused = true;
			shouldPause = false;
		}
		super.tick();
	}
	
	public void pause() {
		shouldPause = true;
	}
	
	public void play() {
		super.play();
		shouldPause = false;
	}
	
	public boolean shouldPause;

}
