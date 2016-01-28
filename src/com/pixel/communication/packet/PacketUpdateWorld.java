package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.pixel.entity.Entity;
import com.pixel.entity.EntityPlayer;
import com.pixel.piece.Piece;
import com.pixel.piece.PieceBuilding;
import com.pixel.render.ChunkRenderGroup;
import com.pixel.render.ChunkRenderObject;
import com.pixel.start.PixelRealms;
import com.pixel.tile.Tile;
import com.pixel.world.World;
import com.pixel.world.WorldChunk;
import com.pixel.world.WorldManager;

public class PacketUpdateWorld extends Packet {

	EntityPlayer player;
	
	public PacketUpdateWorld() {
		
		this.id = 6;
		this.player = WorldManager.player;

	}
	
	@Override
	public void writeData(DataOutputStream output) throws IOException {
		
		player.loadedX = (int) player.getX();
		player.loadedY = (int) player.getY();

		output.writeInt((int) WorldManager.player.getX());
		output.writeInt((int) WorldManager.player.getY());

	}

	@Override
	public void readData(DataInputStream input) throws IOException {

		int c = input.readInt();
		int chunkAmount = input.readInt();
		WorldManager.getWorld().c = c;
		
		ArrayList<Integer> ids = new ArrayList<Integer>();

		for (int a = 0; a < chunkAmount; a ++) {

			int cx = input.readInt();
			int cy = input.readInt();
			
			ids.add((cy * (WorldManager.getWorld().c >> 4)) + cx);
			
			WorldChunk chunk = new WorldChunk(WorldManager.getWorld(), cx, cy);

			int tileAmount = input.readInt();
			ChunkRenderGroup tileGroup = new ChunkRenderGroup(0);

			for (int x = 0; x < tileAmount; x ++) {

				int id = input.readInt();
				int posX = input.readInt();
				int posY = input.readInt();
				int metadata = input.readInt();

				new Tile(posX, posY, id, metadata, true);
				tileGroup.objects.put(x, new ChunkRenderObject(chunk, 0, ((posY*WorldManager.getWorld().c)+posX)));

			}
			chunk.renderGroups.put(0, tileGroup);

			int pieceAmount = input.readInt();
			ChunkRenderGroup pieceGroup = new ChunkRenderGroup(1);
			int currentY = 0;

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
						pieceGroup.objects.put(x, new ChunkRenderObject(chunk, 1, ((posY*WorldManager.getWorld().c)+posX)));
					} else {
						chunk.renderGroups.put((currentY + 1)*2, pieceGroup);
						currentY++;
						pieceGroup = new ChunkRenderGroup(1);
						pieceGroup.objects.put(x, new ChunkRenderObject(chunk, 1, ((posY*WorldManager.getWorld().c)+posX)));
					}
				}

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
		
		ArrayList<Integer> worldIDs = new ArrayList<Integer>();
		worldIDs.addAll(WorldManager.getWorld().chunks.keySet());

		for (int i : worldIDs) {

			if (!ids.contains(i)) {
				
				WorldManager.getWorld().chunks.remove(i);

			}
		}
		
//		int posX = (int) player.getX();
//		int posY = (int) player.getY();
//		posX = posX >> 4;
//		posY = posY >> 4;
//		int wC = World.c >> 4;
//		int pID = (posY * wC) + posX;
		player.loadedX = (int) player.getX();
		player.loadedY = (int) player.getY();
		player.updated = true;
		WorldManager.getWorld().playerReset = false;

	}

	public void clear() {

		
	}

}
