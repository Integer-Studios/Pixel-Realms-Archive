package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pixel.communication.PlayerManager;
import com.pixel.entity.Entity;
import com.pixel.frame.PanelWorld;
import com.pixel.piece.Piece;
import com.pixel.piece.PieceBuilding;
import com.pixel.start.PixelLogger;
import com.pixel.start.PixelRealms;
import com.pixel.tile.Tile;
import com.pixel.world.World;
import com.pixel.world.WorldChunk;

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
		World.chunks.clear();
		int chunkAmount = input.readInt();
		for (int a = 0; a < chunkAmount; a ++) {
			
			int cx = input.readInt();
			int cy = input.readInt();
			PixelLogger.debug("A", cx, cy);

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
				World.propagateEntity(e);
				e.setPosition(posX, posY);
				
			}

		}

		
//		int playerAmount = input.readInt();
//
//		for (int x = 0; x < playerAmount; x ++) {
//			int userID = input.readInt();
//			System.out.println(userID);
//			if (userID != this.userID) 
//				PlayerManager.spawnPlayer(Packet.readString(16, input), userID, input.readFloat(), input.readFloat());
//			
//		}

		World.loaded = true;

		if (!PanelWorld.loaded) {

			PanelWorld.worldLoaded = true;
			PixelLogger.log("World Loaded!");

		}
		
	}
	
}
