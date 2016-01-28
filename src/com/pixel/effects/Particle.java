package com.pixel.effects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.animation.Animation;
import com.pixel.world.World;

public class Particle {
	
	public Particle(World w, Animation a) {
		animation = a;
	}
	
	public void tick(World w, int i) {
		if (!animation.animationCompleted)
			animation.play();
	}
	
	public void render(GameContainer c, Graphics g, World w) {
		if (animation != null) {
			animation.render(c, g, w);
		}
	}

	public Animation animation;
}
