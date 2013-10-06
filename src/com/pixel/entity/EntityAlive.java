package com.pixel.entity;

import com.pixel.world.World;

public class EntityAlive extends Entity {
	
	public float health, satisfaction;
	public int bodyID = -1;

	public EntityAlive(float width, float height) {
		super(width, height);
	}
	
	public EntityAlive(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	public void setMaxHealth(float health) {
		
		this.health = health;
		
	}
	
	public void setHealth(float health) {
		
		this.health = health;
		
	}
	
	public void setBodyPiece(int i) {
		bodyID = i;
	}
	
	public float getHealth() {
		
		return health;
		
	}
	
	public float getSatisfaction() {
		return satisfaction;
	}
	
	
	public void setSatisfaction(float satisfaction) {

		this.satisfaction = satisfaction;
		
	}
	
	
	public void damage(World w, float damage, Entity damageSource) {
		this.health -= damage;
		if (this.health <= 0.0F) {
			this.kill(w, damageSource);
			
		}
	}
	
	public void update(EntityAlive entity) {
		
		this.posX = entity.getX();
		this.posY = entity.getY();
		this.health = entity.health;
		
	}
	
	public void kill(World w, Entity damageSource) {
		health = 0;
		World.entities.remove(this.serverID);
	}
	
	public void spawnBody(World w) {

		int newX = Math.round(posX);
		int newY = Math.round(posY);
		
		if (bodyID != -1) {
			
			w.setPiece(newX, newY, bodyID);
			
		}
		
	}
	
}
