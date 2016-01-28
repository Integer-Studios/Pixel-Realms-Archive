package com.pixel.render;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.world.World;


public class ChunkRenderGroup {
	
	public ChunkRenderGroup(int id, Map<Integer, ChunkRenderObject> objects) {
		this.id = id;
		this.objects = objects;
	}
	
	public ChunkRenderGroup(int id) {
		
		objects = Collections.synchronizedMap(new LinkedHashMap<Integer, ChunkRenderObject>());
		
	}
	
	public void render(GameContainer c, Graphics g, World w) {
		for (ChunkRenderObject obj : objects.values()) {
			
			obj.render(c, g, w);
		}
	}
	
	public int id;
	public Map<Integer, ChunkRenderObject> objects;

}
