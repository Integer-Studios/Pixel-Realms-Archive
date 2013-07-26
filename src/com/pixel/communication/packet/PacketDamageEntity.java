package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pixel.entity.EntityAlive;
import com.pixel.entity.EntityAnimal;
import com.pixel.start.PixelRealms;
import com.pixel.world.World;

public class PacketDamageEntity extends Packet {

	EntityAlive entity;
	int serverID;
	int herdID;
	float damage;
	
	public PacketDamageEntity() {
		
		this.id = 12;
		
	}
	
	public PacketDamageEntity(EntityAlive entity, float damage) {
		
		this.id = 12;
		this.entity = entity;
		this.damage = damage;
		
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {

		if (entity instanceof EntityAnimal) {

			EntityAnimal animal = (EntityAnimal) entity;
			
			output.writeBoolean(true);
			output.writeInt(animal.herdID);
			output.writeInt(animal.serverID);
			output.writeFloat(damage);

		} else {
			
			output.writeBoolean(false);
			output.writeInt(entity.serverID);
			output.writeFloat(damage);
			
		}

	}

	@Override
	public void readData(DataInputStream input) throws IOException {

		boolean herd = input.readBoolean();
		
		if (herd) {

			this.herdID = input.readInt();
			this.serverID = input.readInt();
			this.damage = input.readFloat();
			
		} else {
			
			this.serverID = input.readInt();
			this.damage = input.readFloat();
			
		}
		
		if (this.damage <= 0) {
			
			if (herd) {
				
				World.herds.get(this.herdID).entities.get(this.serverID).kill(PixelRealms.world, null);
				
			} else {
				
				((EntityAlive)World.entities.get(this.serverID)).kill(PixelRealms.world, null);
				
			}
			
		}
		
	}

}
