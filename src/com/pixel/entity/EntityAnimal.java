package com.pixel.entity;

import com.pixel.world.World;

public class EntityAnimal extends EntityAlive {

	public EntityAnimationController controller;
	
	public EntityAnimal(float width, float height) {
		super(width, height);
	}	
	public EntityAnimal(float x, float y, float width, float height) {
		super(x, y, width, height);
	}
	
	public void damage(World w, float damage, Entity damageSource) {
		super.damage(w, damage, damageSource);
	}
	
	public void kill(World w, Entity damageSource) {
		health = 0;
		
		World.entities.remove(this.serverID);

	}
	
	public void tick(World w) {
		super.tick(w);
	
		if (controller != null) {
			controller.tick();
		}

	}
	
}
