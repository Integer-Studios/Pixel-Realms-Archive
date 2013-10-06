package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pixel.entity.EntityAlive;

public class PacketMoveLivingEntity extends Packet {

	EntityAlive entity;
	int serverID;
	boolean n, w, e, s;
	int speed;
	
	public PacketMoveLivingEntity() {
		this.id = 15;
	}
	
	public PacketMoveLivingEntity(EntityAlive entity, boolean n, boolean w, boolean e, boolean s) {
		
		this.id = 15;
		this.entity = entity;
		this.n = n;
		this.w = w;
		this.e = e;
		this.s = s;
		
	}
	
	public void writeData(DataOutputStream output) throws IOException {

		output.writeInt(entity.serverID);
		output.writeBoolean(n);
		output.writeBoolean(w);
		output.writeBoolean(e);
		output.writeBoolean(s);

		
	}

	public void readData(DataInputStream input) throws IOException {

		serverID = input.readInt();
		n = input.readBoolean();
		w = input.readBoolean();
		e = input.readBoolean();
		s = input.readBoolean();
		speed = input.readInt();

	}

}
