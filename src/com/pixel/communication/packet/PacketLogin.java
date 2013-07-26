package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pixel.communication.CommunicationClient;
import com.pixel.communication.PlayerManager;

public class PacketLogin extends Packet {

	public String username;
	public float posX, posY, health, direction;
	public int action, session;
	
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
		this.direction = input.readFloat();
		this.action = input.readInt();
		this.session = input.readInt();
		
		System.out.println("[Pixel Realms] User: " + username + " has logged in with id: " + userID);
		
		if (PlayerManager.currentUserID != this.userID) {

			PlayerManager.spawnPlayer(username, userID, posX, posY);

		} else {
			
			CommunicationClient.addPacket(new PacketWorldData());
			
		}
		
	}

}
