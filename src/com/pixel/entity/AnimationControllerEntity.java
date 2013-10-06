package com.pixel.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.animation.AnimationEntity;
import com.pixel.world.World;

public class AnimationControllerEntity {
	
	public AnimationControllerEntity(Entity e, AnimationEntity a) {
		entity = e;
		animation = a;
	}

	public void tick() {
		if (!stopped) {
			if (entity.getY() - entity.getPreviousY() < 0 && animation.isForward()) {
				animation.setToBackAnim();
			} else if (entity.getPreviousY() - entity.getY() < 0 && !animation.isForward()) {
				animation.setToFrontAnim();
			}
			if (entity.getX() - entity.getPreviousX() < 0) {
				animation.setFlip(true);
			} else if (entity.getPreviousX() - entity.getX() < 0) {
				animation.setFlip(false);
			}
			if (entity.getPreviousX() == entity.getX() && entity.getPreviousY() == entity.getY()) {
				animation.pause();
				isStill = true;
			} else {
				animation.play();
				isStill = false;
			}
		}
	}
	
	public void render(GameContainer c, Graphics g, World w) {
		animation.render(c, g, w);
	}

	public Entity entity;
	public AnimationEntity animation;
	public boolean isStill, animForward, stopped;
}
