package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pixel.communication.PlayerManager;
import com.pixel.entity.Entity;
import com.pixel.piece.Piece;
import com.pixel.piece.PieceBuilding;
import com.pixel.tile.Tile;
import com.pixel.world.World;

public class PacketWorldData extends Packet {

	public PacketWorldData() {
		
		this.id = 3;

	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {

		
	}

	@Override
	public void readData(DataInputStream input) throws IOException {

		int c = input.readInt();
		World.c = c;
		int tileAmount = input.readInt();
		World.tiles.clear();
		
		for (int x = 0; x < tileAmount; x ++) {
			
			int id = input.readInt();
			int posX = input.readInt();
			int posY = input.readInt();
			int metadata = input.readInt();

			new Tile(posX, posY, id, metadata, true);

		}
		

		int pieceAmount = input.readInt();
		World.pieces.clear();
		
		for (int x = 0; x < pieceAmount; x ++) {

			int id = input.readInt();
			int posX = input.readInt();
			int posY = input.readInt();
			int damage = input.readInt();
			int metadata = input.readInt();
			int buildingID = -1;
			int worldID = -1;
			
			if (input.readBoolean()) {
				worldID = input.readInt();
				buildingID = input.readInt();
				new PieceBuilding(worldID, posX, posY, buildingID, damage, metadata);
				
			} else
				new Piece(posX, posY, id, damage, metadata, true);

		}

		int entityAmount = input.readInt();
		World.entities.clear();
		
		for (int x = 0; x < entityAmount; x ++) {

			int id = input.readInt();
			float posX = input.readFloat();
			float posY = input.readFloat();
			int serverID = input.readInt();
			Entity e = Entity.getEntity(id);
			e.serverID = serverID;
			World.propagateEntity(e);
			e.setPosition(posX, posY);
			
		}
		
		int playerAmount = input.readInt();

		for (int x = 0; x < playerAmount; x ++) {
			int userID = input.readInt();
			System.out.println(userID);
			if (userID != this.userID) 
				PlayerManager.spawnPlayer(Packet.readString(16, input), userID, input.readFloat(), input.readFloat());
			
		}
		
		World.loaded = true;
		
	}
	
}
