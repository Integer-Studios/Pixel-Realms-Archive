package com.pixel.world;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.entity.Entity;
import com.pixel.piece.Piece;
import com.pixel.piece.PieceBuilding;
import com.pixel.render.ChunkEntityRenderGroup;
import com.pixel.render.ChunkRenderGroup;
import com.pixel.render.ChunkRenderObject;
import com.pixel.tile.Tile;

public class WorldChunk {
	
	public Map<Integer, Tile> tiles;
	public Map<Integer, Piece> pieces;
	public Map<Integer, PieceBuilding> buildings;
	public Map<Integer, Entity> entities;
	public Map<Integer, ChunkRenderGroup> renderGroups;
	public World world; 
	public int x, y;
	
	public WorldChunk(World world) {
		
		this.world = world;
		tiles = Collections.synchronizedMap(new LinkedHashMap<Integer, Tile>());
		pieces = Collections.synchronizedMap(new LinkedHashMap<Integer, Piece>());
		buildings = Collections.synchronizedMap(new LinkedHashMap<Integer, PieceBuilding>());
		entities = Collections.synchronizedMap(new LinkedHashMap<Integer, Entity>());
		renderGroups = Collections.synchronizedMap(new LinkedHashMap<Integer, ChunkRenderGroup>());

		initializeRenderGroups();
	}
	
	public WorldChunk(World world, int x, int y) {

		this.world = world;
		this.x = x; 
		this.y = y;
		tiles = Collections.synchronizedMap(new LinkedHashMap<Integer, Tile>());
		pieces = Collections.synchronizedMap(new LinkedHashMap<Integer, Piece>());
		buildings = Collections.synchronizedMap(new LinkedHashMap<Integer, PieceBuilding>());
		entities = Collections.synchronizedMap(new LinkedHashMap<Integer, Entity>());
		renderGroups = Collections.synchronizedMap(new LinkedHashMap<Integer, ChunkRenderGroup>());		initializeRenderGroups();
		World.propagateChunk(this);
		
	}
	
	public void initializeRenderGroups() {
		for (int i = 0; i < 33; i++) {
			if (i == 0) {
				renderGroups.put(0, new ChunkRenderGroup(0, new ConcurrentHashMap<Integer, ChunkRenderObject>()));
			} else if (i % 2 == 0) {
				renderGroups.put(i, new ChunkRenderGroup(1, new ConcurrentHashMap<Integer, ChunkRenderObject>()));
			} else {
				renderGroups.put(i, new ChunkEntityRenderGroup(new ConcurrentHashMap<Integer, ChunkRenderObject>()));
			}
		}
	}

	public void tick() {
		
		for (Entity entity : entities.values()) {
			try {
				Thread.sleep(2);
			} catch (Exception e){}

			entity.tick(world);

		}
		
		for (Piece piece : pieces.values()) {
			try {
				Thread.sleep(2);
			} catch (Exception e){}

			piece.tick(world);

		}

		
	}
	
	public void render(GameContainer c, Graphics g, World w) {
		for (ChunkRenderGroup group : renderGroups.values()) {
			group.render(c, g, w);
		}
		//tiles
		//entities from 0-0.85
		//pieces at 0
		//entities from 0.85-1.85
		//pieces at 1
		//etc
	}

	public void propagateTile(Tile tile) {

		tiles.put((tile.posY * (World.c)) + tile.posX, tile);
		
	}
	
	public void propagatePiece(Piece piece) {

		pieces.put((piece.posY * (World.c)) + piece.posX, piece);
		
	}
	
	public Tile getTile(int x, int y) {

		return tiles.get((y * (World.c)) + x);
	
	}

	public Piece getPiece(int x, int y) {

		return pieces.get((y * (World.c)) + x);
	
	}
	
	public void setTile(Tile t) {
		
		tiles.put((t.posY * (World.c)) + t.posX, t);
		
	}

	public void setPiece(Piece p) {
		
		pieces.put((p.posY * (World.c)) + p.posX, p);
		
	}

	public void tick(World w) {
		
		for (Tile t : tiles.values()) {
			
			t.tick(w);
			
		}
		
		for (Piece p : pieces.values()) {
			
			p.tick(w);
			
		}
		
		for (Entity e : entities.values()) {
			
			e.tick(w);
			
		}
		
	}
	
}
