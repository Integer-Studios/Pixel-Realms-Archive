package com.pixel.render;


import java.util.concurrent.ConcurrentHashMap;

import com.pixel.world.WorldChunk;


public class ChunkEntityRenderGroup extends ChunkRenderGroup {

	public ChunkEntityRenderGroup(ConcurrentHashMap<Integer, ChunkRenderObject> objects) {
		super(2, objects);
	}
	
	public void reorder(WorldChunk c) {
		
		ConcurrentHashMap<Integer, ChunkRenderObject> orderedObjects = new ConcurrentHashMap<Integer, ChunkRenderObject>();
		for (ChunkRenderObject object : objects.values()) {
			int index = 0;
			for (ChunkRenderObject orderedObject : orderedObjects.values()) {
				if (c.entities.get(object.index).posY < c.entities.get(orderedObject.index).posY) {
					orderedObjects = insertObject(object, orderedObjects, index);
				}
				index++;
			}
			orderedObjects.put(index, object);
		}
		objects = orderedObjects;
	}
	
	private ConcurrentHashMap<Integer, ChunkRenderObject> insertObject(ChunkRenderObject object, ConcurrentHashMap<Integer, ChunkRenderObject> objects, int index) {
		for (int i = objects.size()-1; i >= index; i--) {
			objects.put(i+1, objects.get(i));
		}
		objects.put(index, object);
		return objects;
	}

}
