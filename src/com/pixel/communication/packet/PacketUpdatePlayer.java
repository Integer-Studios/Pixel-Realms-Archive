package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pixel.communication.PlayerManager;
import com.pixel.item.Item;
import com.pixel.item.ItemStack;
import com.pixel.render.ChunkEntityRenderGroup;
import com.pixel.render.ChunkRenderObject;
import com.pixel.start.PixelLogger;
import com.pixel.start.PixelRealms;
import com.pixel.world.World;
import com.pixel.world.WorldChunk;

public class PacketUpdatePlayer extends Packet {

	public String username;
	public float posX, posY, health, satisfaction, energy;
	public int itemID, itemAmount, worldID;
	
	public PacketUpdatePlayer() {}

	public PacketUpdatePlayer(String username, float posX, float posY, float health, float satisfaction, float energy, ItemStack i) {
		// TODO Auto-generated constructor stub
		this.id = 2;
		this.username = username; 
		this.posX = posX;
		this.posY = posY;
		this.health = health;
		this.satisfaction = satisfaction;
		this.energy = energy;
		if (i != null) {
			this.itemID = i.item.id;
			this.itemAmount = i.size;
		}

	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		Packet.writeString(username, output);
		output.writeFloat(this.posX);
		output.writeFloat(this.posY);
		output.writeFloat(this.health);
		output.writeFloat(this.satisfaction);
		output.writeFloat(this.energy);
		output.writeInt(this.itemID);
		output.writeInt(this.itemAmount);

	}

	@Override
	public void readData(DataInputStream input) throws IOException {

		this.username = Packet.readString(16, input);
		
		this.posX = input.readFloat();
		this.posY = input.readFloat();

		this.health = input.readFloat();
		this.satisfaction = input.readFloat();
		this.energy = input.readFloat();
		
		this.itemID = input.readInt();
		this.itemAmount = input.readInt();
		this.worldID = input.readInt();

		if (PlayerManager.currentUserID != this.userID) {

			if (PlayerManager.players.containsKey(this.userID)) {

				if (worldID != PixelRealms.world.player.worldID) {
					
					PlayerManager.despawnPlayerEntity(userID);
					return;
					
				}
				PlayerManager.players.get(userID).setX(posX);
				PlayerManager.players.get(userID).setY(posY);
				PlayerManager.players.get(userID).setHealth(health);
				PlayerManager.players.get(userID).setSatisfaction(satisfaction);
				PlayerManager.players.get(userID).setEnergy(energy);
				PlayerManager.players.get(userID).setSelectedItem(new ItemStack(Item.getItemForId(itemID), itemAmount));
				PlayerManager.players.get(userID).worldID = worldID;

			} else if (worldID == PixelRealms.world.player.worldID) {
					PlayerManager.spawnPlayer(username, userID, posX, posY);
					PlayerManager.players.get(userID).setSatisfaction(satisfaction);
					PlayerManager.players.get(userID).setHealth(health);
					PlayerManager.players.get(userID).setEnergy(energy);
					PlayerManager.players.get(userID).setSelectedItem(new ItemStack(Item.getItemForId(itemID), itemAmount));
					PlayerManager.players.get(userID).worldID = worldID;
					
					int posYint = (int)posY;
					WorldChunk chunk = World.getChunk(Math.round(posX), Math.round(posY));
					PixelLogger.debug("PacketWorldData Entities PosY:", posY, posYint);
					//9.8, 9; 9.3, 9; 9.0, 9; 8.9, 8;
					if ((posY -(float)posYint) < World.pieceLayerOffset) {
						ChunkEntityRenderGroup group = (ChunkEntityRenderGroup)chunk.renderGroups.get((2*posYint)+1);
						group.objects.put(group.objects.size(), new ChunkRenderObject(chunk, 2, id));
						group.reorder(chunk);
						chunk.renderGroups.put((2*posYint)+1, group);
					} else {
						ChunkEntityRenderGroup group = (ChunkEntityRenderGroup)chunk.renderGroups.get((2*(posYint-1))+1);
						group.objects.put(group.objects.size(), new ChunkRenderObject(chunk, 2, id));
						group.reorder(chunk);
						chunk.renderGroups.put((2*(posYint-1))+1, group);
					}
			}

		} else {
			
			PixelRealms.world.playerReset = false;
			PixelRealms.world.player.teleported = true;
			PixelRealms.world.player.setPosition(posX,  posY);
			PixelRealms.world.player.setHealth(health);
			PixelRealms.world.player.setSatisfaction(satisfaction);
			PixelRealms.world.player.setEnergy(energy);
//
//			if (worldID != PixelRealms.world.player.worldID) {
//				
//				if (worldID == -1) 
//					PixelRealms.world.leaveInterior();
//				else {
//					
//					CommunicationClient.addPacket(new PacketLoadInterior(worldID));
//					
//				}
//				
//			}
			
			PixelRealms.world.player.worldID = worldID;

		}
		
		loaded = true;

	}

}
