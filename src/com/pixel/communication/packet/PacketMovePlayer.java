package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pixel.communication.PlayerManager;
import com.pixel.entity.EntityAlive;
import com.pixel.world.World;

public class PacketMovePlayer extends Packet {

	EntityAlive entity;
	int userID;
	float velocityX, velocityY, posX, posY;
	
	public PacketMovePlayer() {
		this.id = 16;
	}
	
	public PacketMovePlayer(float changeX, float changeY, float posX, float posY) {
		
		this.id = 16;
		this.userID = PlayerManager.currentUserID;
		this.velocityX = changeX;
		this.velocityY = changeY;
		this.posX = posX; 
		this.posY = posY;
		
	}
	
	public void writeData(DataOutputStream output) throws IOException {

		output.writeInt(userID);
		output.writeFloat(velocityX);
		output.writeFloat(velocityY);
		output.writeFloat(posX);
		output.writeFloat(posY);
		
	}

	public void readData(DataInputStream input) throws IOException {
	
		userID = input.readInt();
		velocityX = input.readFloat();
		velocityY = input.readFloat();
		posX = input.readFloat();
		posY = input.readFloat();
		if (!World.loaded)
			return;
		if (PlayerManager.currentUserID != userID) {
			
			if (PlayerManager.players.containsKey(userID)) {
				System.out.println("RECEIVED MOVEMENT ");
				System.out.println(posX + " " + posY + " " + PlayerManager.players.get(userID).getX() + " " + PlayerManager.players.get(userID).getY());
				PlayerManager.players.get(userID).setVelocity(velocityX, velocityY); 
				
			}
			
		}

	}

}
