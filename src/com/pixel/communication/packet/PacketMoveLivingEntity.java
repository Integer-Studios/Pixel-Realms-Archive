package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pixel.entity.EntityAlive;
import com.pixel.world.World;

public class PacketMoveLivingEntity extends Packet {

	EntityAlive entity;
	int serverID;
	public float velocityX, velocityY;
	
	public PacketMoveLivingEntity() {
		this.id = 15;
	}
	
	public PacketMoveLivingEntity(EntityAlive entity) {
		
		this.entity = entity;
		this.serverID = entity.serverID;
		this.velocityX = entity.velocityX;
		this.velocityY = entity.velocityY;
		
	}
	
	public void writeData(DataOutputStream output) throws IOException {

		output.writeInt(serverID);
		output.writeFloat(velocityX);
		output.writeFloat(velocityY);
		
	}

	public void readData(DataInputStream input) throws IOException {

		serverID = input.readInt();
		velocityX = input.readFloat();
		velocityY = input.readFloat();
		
		System.out.println("got movelivingentity packet");
		
		((EntityAlive)World.entities.get(serverID)).setVelocity(velocityX, velocityY);
	}

}
