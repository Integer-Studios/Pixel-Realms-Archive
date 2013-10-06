package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pixel.entity.EntityAlive;
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

		output.writeBoolean(false);
		output.writeInt(entity.serverID);
		output.writeFloat(damage);

	}

	@Override
	public void readData(DataInputStream input) throws IOException {

		this.serverID = input.readInt();
		this.damage = input.readFloat();

		if (this.damage <= 0) {

			((EntityAlive)World.entities.get(this.serverID)).kill(PixelRealms.world, null);

		}

	}

}
