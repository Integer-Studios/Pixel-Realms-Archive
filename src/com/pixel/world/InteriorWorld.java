package com.pixel.world;

import java.util.concurrent.ConcurrentHashMap;

import com.pixel.entity.Entity;
import com.pixel.piece.Piece;
import com.pixel.tile.Tile;

public class InteriorWorld {
	
	public int c;
	public int worldID;
	public ConcurrentHashMap<Integer, Tile> tiles = new ConcurrentHashMap<Integer, Tile>();
	public Piece[] pieces;
	public ConcurrentHashMap<Integer, Entity> entities = new ConcurrentHashMap<Integer, Entity>();
	public boolean building;
	public int wallID;
	
	public InteriorWorld(int worldID, int c, ConcurrentHashMap<Integer, Tile> tiles, Piece[] pieces, ConcurrentHashMap<Integer, Entity> entities) {
		
		this.tiles = tiles;
		this.pieces = pieces;
		this.entities = entities;
		this.c = c;
		InteriorWorldManager.addWorld(this, worldID);
		
	}
	
}
