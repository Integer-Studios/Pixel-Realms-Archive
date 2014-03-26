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
import com.pixel.world.World;
import com.pixel.world.WorldChunk;

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

//		World.tiles.clear();
//		World.pieces.clear();
//		World.entities.clear();
		
		World.holdTillLoad();
		
		int chunkAmount = input.readInt();

		for (int a = 0; a < chunkAmount; a ++) {

			int cx = input.readInt();
			int cy = input.readInt();

			new WorldChunk(PixelRealms.world, cx, cy);

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
				int lightID = input.readInt();
				int buildingID = -1;
				int worldID = -1;

				if (input.readBoolean()) {
					worldID = input.readInt();
					buildingID = input.readInt();
					new PieceBuilding(worldID, posX, posY, buildingID, damage, metadata, lightID);

				} else
					new Piece(posX, posY, id, damage, metadata, lightID, true);

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

		}

		
		World.release();

//		clear((int)player.getX(), (int)player.getY());
		player.updated = true;
		PixelRealms.world.playerReset = false;
		
	}

	public void clear() {

		
	}

}
