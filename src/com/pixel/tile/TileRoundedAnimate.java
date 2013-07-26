package com.pixel.tile;


import com.pixel.world.World;

public class TileRoundedAnimate extends TileRounded {
	
	public int wait, speed, frames, currentFrame;
	public String initialTexture;
	public boolean shouldRepaint;

	public TileRoundedAnimate(String t, int frames, int speed) {
		super(t);
		this.speed = speed;
		this.frames = frames;
		this.initialTexture = t;
		currentFrame = 0;
		setTexture(initialTexture + currentFrame);
	}
	
	public void tick(World w, Tile t) {
		if (shouldRepaint) {
			t.prevImage = null;
		}
		super.tick(w, t);
	}
	
	public void infoTick(World world) {
		if (shouldRepaint) {
			shouldRepaint = false;
		}
		wait ++;
		if (wait == speed) {
			wait = 0;
			currentFrame ++;
			if (currentFrame == frames) {
				currentFrame = 0;
			}
			setTexture(initialTexture + currentFrame);
			shouldRepaint = true;
		}
		super.infoTick(world);
	}

}
