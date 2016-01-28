package com.pixel.render;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.entity.Entity;
import com.pixel.piece.Piece;
import com.pixel.tile.Tile;
import com.pixel.world.World;
import com.pixel.world.WorldChunk;
import com.pixel.world.WorldManager;

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
			Tile t = chunk.tiles.get(index);
			if (t.posX > WorldManager.getWorld().getMinXToPaint() && t.posX < WorldManager.getWorld().getMaxXToPaint() && t.posY > WorldManager.getWorld().getMinYToPaint() && t.posY < WorldManager.getWorld().getMaxYToPaint()) {
				t.render(c, g, w);
			}
			break;
		case 1:
			//piece
			Piece p = chunk.pieces.get(index);
			if (p.posX > WorldManager.getWorld().getMinXToPaint() && p.posX < WorldManager.getWorld().getMaxXToPaint() && p.posY > WorldManager.getWorld().getMinYToPaint() && p.posY < WorldManager.getWorld().getMaxYToPaint()) {
				p.render(c, g, w);
			}
			break;
		case 2:
			//entity
			System.out.println("TESTING BITCH"); 
			Entity e = WorldManager.getWorld().entities.get(index);
			if (e.getX() > WorldManager.getWorld().getMinXToPaint() && e.getX() < WorldManager.getWorld().getMaxXToPaint() && e.getY() > WorldManager.getWorld().getMinYToPaint() && e.getY() < WorldManager.getWorld().getMaxYToPaint()) {
				e.render(c, g, w);
			}
			e.render(c, g, w);
			break;
		default:
			break;
		}
	}

}
