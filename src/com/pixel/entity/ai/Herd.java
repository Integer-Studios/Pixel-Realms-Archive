package com.pixel.entity.ai;

import java.util.concurrent.ConcurrentHashMap;

import com.pixel.entity.EntityAnimal;
import com.pixel.world.World;

public class Herd {

	public ConcurrentHashMap<Integer, EntityAnimal> entities = new ConcurrentHashMap<Integer, EntityAnimal>();
	public float x, y;	
	public float targetX, targetY;
	public int timeSpent;
	public int id;
	
	public Herd(EntityAnimal entity, int herdID, int serverID) {
		
		this.id = herdID;
		entities.put(serverID, entity);
		World.herds.put(id, this);
		
	}
	
}
