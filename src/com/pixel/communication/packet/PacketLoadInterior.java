package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import com.pixel.entity.Entity;
import com.pixel.entity.EntityAlive;
import com.pixel.piece.Piece;
import com.pixel.start.PixelRealms;
import com.pixel.tile.Tile;
import com.pixel.world.InteriorWorld;

public class PacketLoadInterior extends Packet {

	int worldID;
	
	public PacketLoadInterior() {this.id = 14;}
	
 	public PacketLoadInterior(int worldID) {
		
		this.worldID = worldID;
		this.id = 14;
		
	}
	
	@Override
	public void writeData(DataOutputStream output) throws IOException {
		
		output.writeInt(worldID);
		
	}

	@Override
	public void readData(DataInputStream input) throws IOException {

		worldID = input.readInt();
		int c = input.readInt();
		
		ConcurrentHashMap<Integer, Tile> tiles = new ConcurrentHashMap<Integer, Tile>();
		Piece[] pieces;
		ConcurrentHashMap<Integer, Entity> entities = new ConcurrentHashMap<Integer, Entity>();
		
		int tileAmount = input.readInt();
		
		for (int x = 0; x < tileAmount; x ++) {
			
			int tileID = input.readInt();
			int posX = input.readInt();
			int posY = input.readInt();
			tiles.put((posY * c) + posX, new Tile(posX, posY, tileID , false));

		}
		
		int pieceAmount = input.readInt();
		pieces = new Piece[c * c];
		for (int x = 0; x < pieceAmount; x ++) {
			
			int pieceID = input.readInt();
			int posX = input.readInt();
			int posY = input.readInt();
			int metadata = input.readInt();
			int damage = input.readInt();
			pieces[(posY * c) + posX] = new Piece(posX, posY, pieceID, damage, metadata, false);

		}
		
		
		int entityAmount = input.readInt();
		
		for (int x = 0; x < entityAmount; x ++) {
			
			int entityID = input.readInt();
			float posX = input.readFloat();
			float posY = input.readFloat(); 
			float health = input.readFloat();
			int serverID = input.readInt();
			
			EntityAlive entity = (EntityAlive) Entity.getEntity(entityID);
			entity.setPosition(posX, posY);
			entity.setHealth(health);
			entity.serverID = serverID;
			entity.worldID = worldID;
			
			entities.put(serverID, entity);

		}
		System.out.println(entityAmount + " B");
		
		new InteriorWorld(worldID, c, tiles, pieces, entities);
		
		PixelRealms.world.loadInterior(worldID);
		
	}

}
