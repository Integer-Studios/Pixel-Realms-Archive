package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pixel.communication.PlayerManager;
import com.pixel.entity.EntityAlive;

public class PacketMovePlayer extends Packet {

	EntityAlive entity;
	int userID;
	float changeX, changeY, posX, posY;
	
	public PacketMovePlayer() {
		this.id = 16;
	}
	
	public PacketMovePlayer(float changeX, float changeY, float posX, float posY) {
		
		this.id = 16;
		this.userID = PlayerManager.currentUserID;
		this.changeX = changeX;
		this.changeY = changeY;
		this.posX = posX; 
		this.posY = posY;
		
	}
	
	public void writeData(DataOutputStream output) throws IOException {

		output.writeInt(userID);
		output.writeFloat(changeX);
		output.writeFloat(changeY);
		output.writeFloat(posX);
		output.writeFloat(posY);
		
	}

	public void readData(DataInputStream input) throws IOException {

		userID = input.readInt();
		changeX = input.readFloat();
		changeY = input.readFloat();

		if (PlayerManager.currentUserID != userID) {
			
			if (PlayerManager.players.containsKey(userID)) {
				System.out.println("RECEIVED MOVEMENT ");

				PlayerManager.players.get(userID).accelerate(changeX, changeY); 
				PlayerManager.players.get(userID).setPosition(posX, posY);
				
			}
			
		}

	}

}
