package com.pixel.building;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.pixel.world.InteriorWorldManager;
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
		
		this.door.worldID = worldID;
		this.door.box = new Rectangle((this.door.x / World.tileConstant) + x, (this.door.y / World.tileConstant) + y, (float)this.door.width / World.tileConstant, ((float)this.door.height / World.tileConstant));

		InteriorWorldManager.addDoor(door, worldID);
		
	}
	
	public Building(int id) {
		
		this.id = id;
		
		this.width = info.get(id).width;
		this.height = info.get(id).height;
		this.door = info.get(id).door;
		this.floorID = info.get(id).floorID;

	}
	
	public static boolean canBuildingFit(int buildingID, int x, int y) {
		
		int width = info.get(buildingID).width;
		int height = info.get(buildingID).height;
		
		for (int b = x; b < (x + width); b ++) {
			
			for (int i = (y - height); i < y; i ++) {

				if (World.pieces.get((i * World.c) + b) != null) {
					
					int tempID = World.pieces.get((i * World.c) + b).id;
					
					if (tempID != 0 && tempID != 1 && tempID != 2 && tempID != 3) {
						
						//Obstruction
						System.out.println("Obstruction");
						return false;
						
					}
					
				}

			}

		}
		
		return true;
	}
	
	static {
		
		info.add(new BuildingInfo(0, 4, 3, 1000).setDoor(new BuildingDoor(46F, -10F, 32, 56, 0)).setTexture("resources/pieces/buildings/cabin/"));
	
	}
	
}
