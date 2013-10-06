package com.pixel.building;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.pixel.world.World;

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
		this.door.box = new Rectangle((this.door.x / World.tileConstant) + x, (this.door.y / World.tileConstant) + y, (float)this.door.width / World.tileConstant, ((float)this.door.height / World.tileConstant));

	}
	
	public Building(int id) {
		
		this.id = id;
		
		this.width = info.get(id).width;
		this.height = info.get(id).height;
		this.door = info.get(id).door;
		this.floorID = info.get(id).floorID;

	}
	
	static {
		
		info.add(new BuildingInfo(0, 4, 3).setDoor(new BuildingDoor(46F, 88F, 32, 56, 0)).setTexture("resources/pieces/buildings/cabin_1.png"));
	
	}
	
}
