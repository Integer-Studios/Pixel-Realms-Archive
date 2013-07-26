package com.pixel.entity;

import java.io.Serializable;

public class EntityWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int id;
	public float posX, posY, health;
	
	public EntityWrapper(int id, float posX, float posY, float health) {
		
		this.id = id;
		this.posX = posX;
		this.posY = posY;
		this.health = health;
		
	}
	
}
