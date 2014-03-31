package com.pixel.render;

import java.util.concurrent.ConcurrentHashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.world.World;


public class ChunkRenderGroup {
	
	public ChunkRenderGroup(int id, ConcurrentHashMap<Integer, ChunkRenderObject> objects) {
		this.id = id;
		this.objects = objects;
	}
	
	public void render(GameContainer c, Graphics g, World w) {
		for (ChunkRenderObject obj : objects.values()) {
			obj.render(c, g, w);
		}
	}
	
	public int id;
	public ConcurrentHashMap<Integer, ChunkRenderObject> objects;

}
