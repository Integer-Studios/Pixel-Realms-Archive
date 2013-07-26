package com.pixel.animation;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.entity.Entity;
import com.pixel.world.World;

public class AnimationEntity extends Animation {

	public AnimationEntity(String p, Entity e, int w, int h, int s,
			int sp, int sh) {
		super(p+"front/", e.getX(), e.getY(), w, h, s, sp, sh);
		entity = e;
	}

	public void setToBackAnim() {
		isForward = false;
		setPath(path.replaceAll("/front/", "/back/"));
	}

	public void setToFrontAnim() {
		isForward = true;
		setPath(path.replaceAll("/back/", "/front/"));
	}

	public boolean isForward() {
		return isForward;
	}

	public void render(GameContainer c, Graphics g, World w) {
		posX = entity.getX();
		if (shouldFloat)
			posY = entity.getY() - entity.floatingOffset;
		else
			posY = entity.getY();
		super.render(c, g, w);
	}

	public void advanceJumpOffset() {
		switch (currentFrame) {
		case 0:
			jumpOffset = 0.0F;
			break;
		case 1:
			jumpOffset = 0.1F;
			break;
		case 2:
			jumpOffset = 0.17F;
			break;
		case 3:
			jumpOffset = 0.2F;
			break;
		case 4:
			jumpOffset = 0.17F;
			break;
		case 5:
			jumpOffset = 0.1F;
			break;
		default:
			jumpOffset = 1.0F;
			break;
		}
		
	}
		
	public AnimationEntity setShouldFLoat(boolean b) {
		shouldFloat = b;
		return this;
	}

	public Entity entity;
	public boolean isForward = true;
	@SuppressWarnings("unused")
	private float jumpOffset = 0.0F;
	public boolean shouldFloat = true;
}
