package com.pixel.world;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.start.PixelLogger;

public class ChunkRenderObject {
	
	public ChunkRenderObject(WorldChunk chunk, int id, int index) {
		this.chunk = chunk;
		this.id = id;
		this.index = index;
	}
	
	public WorldChunk chunk;
	public int id; 
	public int index;
	
	public void render(GameContainer c, Graphics g, World w) {
		switch(id) {
		case 0:
			//tile
			chunk.tiles.get(index).render(c, g, w);
			break;
		case 1:
			//piece
			chunk.pieces.get(index).render(c, g, w);
			break;
		case 2:
			//entity
			chunk.entities.get(index).render(c, g, w);
			break;
		default:
			break;
		}
	}

}
