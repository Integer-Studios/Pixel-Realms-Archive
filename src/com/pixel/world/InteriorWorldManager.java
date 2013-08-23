package com.pixel.world;

import java.util.concurrent.ConcurrentHashMap;

public class InteriorWorldManager {

	public static ConcurrentHashMap<Integer, InteriorWorld> interiors = new ConcurrentHashMap<Integer, InteriorWorld>();

	public static void addWorld(InteriorWorld world, int worldID) {
		
		interiors.put(worldID, world);
		
	}
	
}
