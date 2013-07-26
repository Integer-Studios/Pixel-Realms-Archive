package com.pixel.animation;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.world.World;

public class MultiPieceAnimation {
	
	public MultiPieceAnimation(Animation[] a) {
		animations = a;
	}
	
	public void render(GameContainer c, Graphics g, World w) {
		for (int i = 0; i < animations.length; i++) {
			animations[i].render(c, g, w);
		}
	}
	
	public void play() {
		for (int i = 0; i < animations.length; i++) {
			animations[i].play();
		}
	}
	
	public void pause() {
		for (int i = 0; i < animations.length; i++) {
			animations[i].pause();
		}
	}
	
	public void setFlip(boolean b) {
		for (int i = 0; i < animations.length; i++) {
			animations[i].setFlip(b);
		}
		flip = b;
	}
	
	public boolean getFlip() {
		return flip;
	}
	
	public void setAnimations(Animation[] a) {
		animations = a;
	}
	
	public Animation[] getAnimations() {
		return animations;
	}
	
	public void setPaths(String[] paths) {
		for (int i = 0; i < animations.length; i++) {
			animations[i].setPath(paths[i]);
		}
	}

	public Animation[] animations;
	public boolean flip = false;
}
