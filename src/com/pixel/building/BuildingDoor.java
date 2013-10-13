package com.pixel.building;

import com.badlogic.gdx.math.Rectangle;

public class BuildingDoor {

	public float x, y;
	public int width, height;
	public int orientation;
	public Rectangle box;
	public int worldID;
	
	public BuildingDoor(float x, float y, int width, int height, int orientation) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.orientation = orientation;
		
	}
	
	public BuildingDoor duplicate() {
		
		BuildingDoor temp = new BuildingDoor(x, y, width, height, orientation);
		temp.box = new Rectangle(box.x, box.y, box.width, box.height);
		temp.worldID = worldID;
		
		return temp;

	}
	
}
