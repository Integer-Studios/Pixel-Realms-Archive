package com.pixel.world;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.entity.Entity;
import com.pixel.piece.Piece;
import com.pixel.tile.Tile;

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
			if (t.posX > World.getMinXToPaint() && t.posX < World.getMaxXToPaint() && t.posY > World.getMinYToPaint() && t.posY < World.getMaxYToPaint()) {
				t.render(c, g, w);
			}
			break;
		case 1:
			//piece
			Piece p = chunk.pieces.get(index);
			if (p.posX > World.getMinXToPaint() && p.posX < World.getMaxXToPaint() && p.posY > World.getMinYToPaint() && p.posY < World.getMaxYToPaint()) {
				p.render(c, g, w);
			}
			break;
		case 2:
			//entity
			Entity e = chunk.entities.get(index);
			if (e.getX() > World.getMinXToPaint() && e.getX() < World.getMaxXToPaint() && e.getY() > World.getMinYToPaint() && e.getY() < World.getMaxYToPaint()) {
				e.render(c, g, w);
			}
			e.render(c, g, w);
			break;
		default:
			break;
		}
	}

}
