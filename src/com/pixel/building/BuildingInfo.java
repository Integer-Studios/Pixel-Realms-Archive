package com.pixel.building;

public class BuildingInfo {

	public int width, height;
	public int id;
	public int floorID;
	public String texture;
	public BuildingDoor door;
	public int maxDamage;
	
	public BuildingInfo(int id, int width, int height, int maxDamage) {
		
		this.id = id;
		this.floorID = 3;
		this.width = width;
		this.height = height;
		this.maxDamage = maxDamage;
		
	}
	
	public BuildingInfo setTexture(String texture) {
		
		this.texture = texture;
		return this;
		
	}
	
	public BuildingInfo setDoor(BuildingDoor door) {
		
		this.door = door;
		return this;
		
	}
	
}
