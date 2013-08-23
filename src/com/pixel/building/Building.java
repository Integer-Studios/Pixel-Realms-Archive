package com.pixel.building;

import java.util.ArrayList;

public class Building {

	public static ArrayList<BuildingInfo> info = new ArrayList<BuildingInfo>();

	public int worldID, id, x, y, width, height, floorID;;
	public BuildingDoor door;
	
	public Building(int worldID, int id, int x, int y) {
		
		this.worldID = worldID;
		this.id = id;
		this.x = x;
		this.y = y;
		
		this.width = info.get(id).width;
		this.height = info.get(id).height;
		this.door = info.get(id).door;
		this.floorID = info.get(id).floorID;

	}
	
	public Building(int id) {
		
		this.id = id;
		
		this.width = info.get(id).width;
		this.height = info.get(id).height;
		this.door = info.get(id).door;
		this.floorID = info.get(id).floorID;

	}
	
	static {
		
		info.add(new BuildingInfo(0, 4, 3).setDoor(new BuildingDoor(0F, 0F, 0, 0)).setTexture("resources/pieces/buildings/cabin_1.png"));
	
	}
	
}
