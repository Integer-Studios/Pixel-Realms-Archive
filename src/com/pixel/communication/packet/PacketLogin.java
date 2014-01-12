package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pixel.communication.PlayerManager;
import com.pixel.frame.PanelWorld;

public class PacketLogin extends Packet {

	public String username;
	public float posX, posY, health, satisfaction, energy;
	public int itemID, itemAmount, worldID, session;
	
	public PacketLogin(){}
	
	public PacketLogin(String username, int session) {

		this.id = 1;
		this.username = username;
		this.session = session;

	}
	
	public void writeData(DataOutputStream output) throws IOException {
	
		Packet.writeString(username, output);
		output.writeInt(this.session);
		
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
		
		if (PlayerManager.currentUserID != this.userID) {
			
			System.out.println("[Pixel Realms] User: " + username + " has logged in with id: " + userID);

			PlayerManager.spawnPlayer(username, userID, posX, posY);
			System.out.println(username + " " + worldID);
			PlayerManager.players.get(userID).worldID = worldID;
			PlayerManager.players.get(userID).health = health;
			PlayerManager.players.get(userID).satisfaction = satisfaction;
			PlayerManager.players.get(userID).energy = energy;


		} else {
			
			PanelWorld.loginRebound = true;
			
		}
		
	}

}
