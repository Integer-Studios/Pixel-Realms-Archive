 package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pixel.entity.Entity;
import com.pixel.frame.PanelWorld;
import com.pixel.piece.Piece;
import com.pixel.piece.PieceBuilding;
import com.pixel.render.ChunkEntityRenderGroup;
import com.pixel.render.ChunkRenderGroup;
import com.pixel.render.ChunkRenderObject;
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
		int amt = 0;
		for (int a = 0; a < chunkAmount; a ++) {
			
			int cx = input.readInt();
			int cy = input.readInt();
			amt ++;
			System.out.println("C: " + amt + " " + cx + " " + cy);

			WorldChunk chunk = new WorldChunk(PixelRealms.world, cx, cy);
			
			ChunkRenderGroup tileGroup = new ChunkRenderGroup(0);
			int tileAmount = input.readInt();
			
			for (int x = 0; x < tileAmount; x ++) {

				int id = input.readInt();
				int posX = input.readInt();
				int posY = input.readInt();
				int metadata = input.readInt();
				new Tile(posX, posY, id, metadata, true);
				tileGroup.objects.put(x, new ChunkRenderObject(chunk, 0, ((posY*World.c)+posX)));
			}
			
			chunk.renderGroups.put(0, tileGroup);

			ChunkRenderGroup pieceGroup = new ChunkRenderGroup(1);
			int currentY = 0;
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

				} else {
					new Piece(posX, posY, id, damage, metadata, lightID, true);
					if (posY == currentY) {
						pieceGroup.objects.put(x, new ChunkRenderObject(chunk, 1, ((posY*World.c)+posX)));
					} else {
//						System.out.println(posY + " X " + currentY);
						chunk.renderGroups.put((currentY + 1)*2, pieceGroup);
						currentY++;
						pieceGroup = new ChunkRenderGroup(1);
						pieceGroup.objects.put(x, new ChunkRenderObject(chunk, 1, ((posY*World.c)+posX)));
					}
				}

			}
			
			int entityAmount = input.readInt();
			
			for (int x = 0; x < entityAmount; x ++) {

				int id = input.readInt();
				//how to do this global
				float posX = input.readFloat();
				float posY = input.readFloat();
				int serverID = input.readInt();
				Entity e = Entity.getEntity(id);
				e.serverID = serverID;
				World.propagateEntity(e);
				e.setPosition(posX, posY);
				
				int posYint = (int)posY;
				PixelLogger.debug("PacketWorldData Entities PosY:", posY, posYint);
				//9.8, 9; 9.3, 9; 9.0, 9; 8.9, 8;
				if ((posY -(float)posYint) < World.pieceLayerOffset) {
					ChunkEntityRenderGroup group = (ChunkEntityRenderGroup)chunk.renderGroups.get((2*posYint)+1);
					group.objects.put(x, new ChunkRenderObject(chunk, 2, id));
					group.reorder(chunk);
					chunk.renderGroups.put((2*posYint)+1, group);
				} else {
					ChunkEntityRenderGroup group = (ChunkEntityRenderGroup)chunk.renderGroups.get((2*(posYint-1))+1);
					group.objects.put(x, new ChunkRenderObject(chunk, 2, id));
					group.reorder(chunk);
					chunk.renderGroups.put((2*(posYint-1))+1, group);
				}
				
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
