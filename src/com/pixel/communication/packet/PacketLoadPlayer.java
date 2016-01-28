package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pixel.communication.CommunicationClient;
import com.pixel.communication.PlayerManager;
import com.pixel.entity.EntityPlayer;
import com.pixel.item.Item;
import com.pixel.item.ItemStack;
import com.pixel.stage.StageWorld;
import com.pixel.start.PixelLogger;
import com.pixel.world.WorldManager;

public class PacketLoadPlayer extends Packet{

	public String username;
	public float posX, posY, health, satisfaction, energy;
	public int itemID, itemAmount, worldID;
	private int serverID;
	
	public PacketLoadPlayer() {this.id = 18;}
	
	public PacketLoadPlayer(String username) {
		
		this.id = 18;
		this.username = username;
		
	}

	
	@Override
	public void writeData(DataOutputStream output) throws IOException {

		Packet.writeString(username, output);
		
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		PlayerManager.playerLoggedIn = true;

		this.username = Packet.readString(16, input);

		this.posX = input.readFloat();
		this.posY = input.readFloat();

		this.health = input.readFloat();
		this.satisfaction = input.readFloat();
		this.energy = input.readFloat();
		
		this.itemID = input.readInt();
		this.itemAmount = input.readInt();
		this.worldID = input.readInt();
		this.serverID = input.readInt();

		if (this.userID == PlayerManager.currentUserID) {
			
			WorldManager.player = new EntityPlayer(posX, posX, health, satisfaction, energy, worldID, serverID);
			WorldManager.player.teleported = true;
			WorldManager.player.updated = true;

			int hotbarSize = input.readInt();
			for (int i = 0; i < hotbarSize; i ++) {
				
				int x = input.readInt();
				int y = input.readInt();
				int itemID = input.readInt();
				int amount = input.readInt();
				input.readInt();
				WorldManager.player.inventory.hotbar.serverSetContent(x, y, new ItemStack(Item.getItemForId(itemID), amount));

			}
			
			int leftSize = input.readInt();
			for (int i = 0; i < leftSize; i ++) {
				
				int x = input.readInt();
				int y = input.readInt();
				int itemID = input.readInt();
				int amount = input.readInt();
				input.readInt();
				WorldManager.player.inventory.inventoryLeft.serverSetContent(x, y, new ItemStack(Item.getItemForId(itemID), amount));

			}
			
			int rightSize = input.readInt();
			for (int i = 0; i < rightSize; i ++) {
				
				int x = input.readInt();
				int y = input.readInt();
				int itemID = input.readInt();
				int amount = input.readInt();
				input.readInt();
				WorldManager.player.inventory.inventoryRight.serverSetContent(x, y, new ItemStack(Item.getItemForId(itemID), amount));

			}
			
			PixelLogger.log("Player Loaded!");
			
			WorldManager.playerLoaded = true;
			
		}
		
	}
	
}
