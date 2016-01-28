package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pixel.entity.Entity;
import com.pixel.entity.EntityAlive;
import com.pixel.world.World;
import com.pixel.world.WorldManager;

public class PacketMoveLivingEntity extends Packet {

	EntityAlive entity;
	int serverID, entityID;
	public float velocityX, velocityY, posX, posY;
	public int worldID;
	
	public PacketMoveLivingEntity() {
		this.id = 15;
	}
	
	public PacketMoveLivingEntity(EntityAlive entity) {
		
		this.entity = entity;
		this.serverID = entity.serverID;
		this.velocityX = entity.velocityX;
		this.velocityY = entity.velocityY;
		this.worldID = entity.worldID;
		this.posX = entity.getX();
		this.posY = entity.getY();
		
	}
	
	public void writeData(DataOutputStream output) throws IOException {

		output.writeInt(serverID);
		output.writeFloat(velocityX);
		output.writeFloat(velocityY);
		output.writeFloat(posX);
		output.writeFloat(posY);
		output.writeInt(worldID);
		
	}

	public void readData(DataInputStream input) throws IOException {
		
		entityID = input.readInt();
		serverID = input.readInt();
		velocityX = input.readFloat();
		velocityY = input.readFloat();
		posX = input.readFloat();
		posY = input.readFloat();
		worldID = input.readInt();
		
		if (!WorldManager.getWorld().entities.containsKey(serverID)) {
			Entity entity = Entity.getEntity(entityID);
			entity.serverID = serverID;
			WorldManager.propagateEntity(entity);
			
		}
		
		((EntityAlive)WorldManager.getWorld().entities.get(serverID)).setVelocity(velocityX, velocityY);
		((EntityAlive)WorldManager.getWorld().entities.get(serverID)).worldID = worldID;

	}

}
