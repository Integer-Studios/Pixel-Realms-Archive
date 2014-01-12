package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pixel.communication.CommunicationClient;
import com.pixel.communication.PlayerManager;
import com.pixel.frame.PanelWorld;
import com.pixel.start.PixelLogger;
import com.pixel.start.PixelRealms;

public class PacketLoadPlayer extends Packet{

	public String username;
	public float posX, posY, health, satisfaction, energy;
	public int itemID, itemAmount, worldID;
	
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

		if (this.userID == PlayerManager.currentUserID) {
			
			PixelRealms.world.playerReset = false;
			PixelRealms.world.player.teleported = true;
			PixelRealms.world.player.setPosition(posX, posY);
			PixelRealms.world.player.setHealth(health);
			PixelRealms.world.player.setSatisfaction(satisfaction);
			PixelRealms.world.player.setEnergy(energy);
			PixelRealms.world.player.worldID = worldID;

			if (worldID != -1) {
				
				//load interior not world
				CommunicationClient.addPacket(new PacketLoadInterior(worldID));
				
			} else {

				CommunicationClient.addPacket(new PacketWorldData());
				//load world
				
			}
			
			PixelLogger.log("Player Loaded!");
			
			PanelWorld.playerLoaded = true;
			
		}
		
	}
	
}
