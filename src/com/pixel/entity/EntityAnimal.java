package com.pixel.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.world.World;

public class EntityAnimal extends EntityAlive {

	public AnimationControllerEntity controller;
	
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
	
	public void render(GameContainer c, Graphics g, World w) {
		super.render(c, g, w);
		controller.render(c, g, w);
	}
	
	public void tick(World w) {
		super.tick(w);
	
		if (controller != null) {
			controller.tick();
		}

	}
	
}
