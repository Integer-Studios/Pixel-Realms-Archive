package com.pixel.entity;


import com.pixel.world.World;

public class EntityAnimal extends EntityAlive {
	
	public EntityAnimal(float width, float height) {
		super(width, height);
	}	
	public EntityAnimal(float x, float y, float width, float height) {
		super(x, y, width, height);
	}
	
	public void kill(World w, Entity damageSource) {
		super.kill(w, damageSource);
		World.entities.remove(this.serverID);
	}
	
}
