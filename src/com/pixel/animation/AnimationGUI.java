package com.pixel.animation;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.pixel.start.TextureLoader;


public class AnimationGUI extends Animation {

	public AnimationGUI(String p, int x, int y, int w, int h, int s, int sp) {
		super(p, x, y, w, h, s, sp, 0);
	}
	
	public void render(GameContainer c, Graphics g) {
		if (paused) {
			Image image = TextureLoader.load(images[0]);
			image.draw((int)posX, (int)posY, width, height);
		} else {
			tick();
			Image image = TextureLoader.load(images[currentFrame]);
			if (image == null) {
				image = TextureLoader.load(images[lastWorkingFrame]);
			} else {
				lastWorkingFrame = currentFrame;
			}
			image.draw((int)posX, (int)posY, width, height);
		}
	}

}
