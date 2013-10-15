package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pixel.entity.Entity;
import com.pixel.entity.EntityPlayer;
import com.pixel.piece.Piece;
import com.pixel.piece.PieceBuilding;
import com.pixel.start.PixelRealms;
import com.pixel.tile.Tile;

public class PacketUpdateWorld extends Packet {

	EntityPlayer player;
	
	public PacketUpdateWorld() {
		
		this.id = 6;
		this.player = PixelRealms.world.player;

	}
	
	@Override
	public void writeData(DataOutputStream output) throws IOException {
		
		player.loadedX = (int) player.getX();
		player.loadedY = (int) player.getY();
		
		output.writeInt((int) PixelRealms.world.player.getX());
		output.writeInt((int) PixelRealms.world.player.getY());

	}

	@Override
	public void readData(DataInputStream input) throws IOException {

		int tileAmount = input.readInt();

		for (int x = 0; x < tileAmount; x ++) {
			
			int id = input.readInt();
			int posX = input.readInt();
			int posY = input.readInt();
			int metadata = input.readInt();

			new Tile(posX, posY, id, metadata, true);

		}
		
		int pieceAmount = input.readInt();
		
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

		for (int x = 0; x < entityAmount; x ++) {

			int id = input.readInt();
			float posX = input.readFloat();
			float posY = input.readFloat();
			int serverID = input.readInt();
			Entity e = Entity.getEntity(id);
			e.serverID = serverID;
			e.setPosition(posX, posY);
			
		}

//		clear((int)player.getX(), (int)player.getY());
		player.updated = true;
		PixelRealms.world.playerReset = false;
		
	}

	public void clear() {

		
	}

}
