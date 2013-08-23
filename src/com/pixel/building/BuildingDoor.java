package com.pixel.building;

import com.badlogic.gdx.math.Rectangle;

public class BuildingDoor {

	public float x, y;
	public int width, height;
	public int orientation;
	public Rectangle box;
	
	public BuildingDoor(float x, float y, int width, int height, int orientation) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.orientation = orientation;
		
	}
	
}
