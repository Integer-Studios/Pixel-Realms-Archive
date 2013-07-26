package com.pixel.player;

import java.io.Serializable;

public class PlayerWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public float posX, posY, health;
	
	public PlayerWrapper(float posX, float posY, float health) {
		
		this.posX = posX;
		this.posY = posY;
		this.health = health;
		
	}
	
}
