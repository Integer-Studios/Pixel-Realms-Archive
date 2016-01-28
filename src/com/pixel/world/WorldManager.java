package com.pixel.world;

import java.util.ArrayList;

import com.pixel.entity.Entity;
import com.pixel.entity.EntityPlayer;
import com.pixel.piece.Piece;
import com.pixel.stage.StageWorld;
import com.pixel.tile.Tile;

public class WorldManager {
	
	public static StageWorld stage;
	public static int currentWorld;
	public static ArrayList<World> worlds = new ArrayList<World>();
	public static EntityPlayer player;
	public static boolean playerLoaded;
	public static boolean worldLoaded;
	
	public static World getWorld() {
		
		return worlds.get(currentWorld);
		
	}
	
	public static void createWorld(int c) {
		
		worlds.add(new World(c));
		
	}
	
	public static void propagateTile(Tile tile) {
		worlds.get(currentWorld).propagateTile(tile);
	}
	
	public static void propagatePiece(Piece piece) {
		worlds.get(currentWorld).propagatePiece(piece);
	}

	public static void propagateEntity(Entity entity) {
		worlds.get(currentWorld).propagateEntity(entity);
	}

	public static void init(StageWorld stageWorld) {
		
		stage = stageWorld;
		
	}

	public static void disinstantiate() {

		stage = null;
		worlds = new ArrayList<World>();
		currentWorld = -1;
		
	}
	

}
