package com.pixel.world;

import java.util.concurrent.ConcurrentHashMap;

import com.badlogic.gdx.math.Rectangle;
import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import com.pixel.building.BuildingDoor;
import com.pixel.entity.Entity;
import com.pixel.piece.Piece;
import com.pixel.tile.Tile;

public class InteriorWorld {
	
	public int c;
	public int worldID;
	public ConcurrentHashMap<Integer, Tile> tiles = new ConcurrentHashMap<Integer, Tile>();
	public ConcurrentLinkedHashMap<Integer, Piece> pieces = new ConcurrentLinkedHashMap.Builder<Integer, Piece>().maximumWeightedCapacity(10000000).build();
	public ConcurrentHashMap<Integer, Entity> entities = new ConcurrentHashMap<Integer, Entity>();
	public boolean building;
	public BuildingDoor door;
	public int wallID;
	
	public InteriorWorld(int worldID, int c, ConcurrentHashMap<Integer, Tile> tiles, ConcurrentLinkedHashMap<Integer, Piece> pieces, ConcurrentHashMap<Integer, Entity> entities) {
		
		this.tiles = tiles;
		this.pieces = pieces;
		this.entities = entities;
		this.c = c;
		InteriorWorldManager.addWorld(this, worldID);
		
	}
	
	public InteriorWorld(int worldID, BuildingDoor door) {
		
		this.worldID = worldID;
		this.door = door;
		this.door.box = new Rectangle(door.box.x, door.box.y, door.box.width, door.box.height);
		InteriorWorldManager.addWorld(this, worldID);
		
	}
	
}
