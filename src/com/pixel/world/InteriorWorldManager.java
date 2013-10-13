package com.pixel.world;

import java.util.concurrent.ConcurrentHashMap;

import com.pixel.building.BuildingDoor;

public class InteriorWorldManager {

	public static ConcurrentHashMap<Integer, InteriorWorld> interiors = new ConcurrentHashMap<Integer, InteriorWorld>();
	public static ConcurrentHashMap<Integer, BuildingDoor> doors = new ConcurrentHashMap<Integer, BuildingDoor>();

	public static void addWorld(InteriorWorld world, int worldID) {
		
		interiors.put(worldID, world);

	}

	public static void addDoor(BuildingDoor door, int worldID) {
		
		doors.put(worldID, door.duplicate());

	}

}
