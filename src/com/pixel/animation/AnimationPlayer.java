package com.pixel.animation;

import com.pixel.entity.Entity;
import com.pixel.world.World;

public class AnimationPlayer extends AnimationEntity {

	public AnimationPlayer(String p, Entity e) {
		super(p, e, World.tileConstant, World.tileConstant*2, 8, 3, 2);
	}
	
	public AnimationPlayer(String p, Entity e, int s) {
		super(p, e, 48, 96, s, 3, 2);
	}

	public AnimationPlayer setShouldFLoat(boolean b) {
		shouldFloat = b;
		return this;
	}
}
