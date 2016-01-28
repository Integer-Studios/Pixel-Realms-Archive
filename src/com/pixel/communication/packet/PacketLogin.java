package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pixel.communication.PlayerManager;
import com.pixel.world.WorldManager;
import com.pixel.entity.EntityAlive;

public class PacketLogin extends Packet {

	public String username;
	public float posX, posY, health, satisfaction, energy;
	public int itemID, itemAmount, worldID, session, serverID;
	
	public PacketLogin(){this.id = 1;}
	
	public PacketLogin(int userID) {

		this.id = 1;
		this.userID = userID;
		
	}
	
	public void writeData(DataOutputStream output) throws IOException {
		
	}

	@Override
	public void readData(DataInputStream input) throws IOException {

		this.username = Packet.readString(16, input);

		this.posX = input.readFloat();
		this.posY = input.readFloat();

		this.health = input.readFloat();
		this.energy = input.readFloat();
		this.satisfaction = input.readFloat();
		this.session = input.readInt();
		this.worldID = input.readInt();
		this.serverID = input.readInt();
		
		if (PlayerManager.currentUserID != this.userID) {
			
			System.out.println("[Pixel Realms] User: " + username + " has logged in with id: " + userID);

			PlayerManager.spawnPlayer(username, userID, posX, posY, serverID);
			System.out.println(username + " " + worldID);
			WorldManager.getWorld().entities.get(serverID).worldID = worldID;
			((EntityAlive)WorldManager.getWorld().entities.get(serverID)).setHealth(health);
			((EntityAlive)WorldManager.getWorld().entities.get(serverID)).satisfaction = satisfaction;
			((EntityAlive)WorldManager.getWorld().entities.get(serverID)).energy = energy;


		} 
		
	}

}
