package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketEntityAnimation extends Packet {

	public int animationID, entityID;
	public boolean player;
	
	public PacketEntityAnimation(int animationID) {
		
		this.id = 22;
		this.animationID = animationID;
		this.player = true;
		
	}
	
	@Override
	public void writeData(DataOutputStream output) throws IOException {

		output.writeInt(animationID);
		output.writeBoolean(player);
		
	}

	@Override
	public void readData(DataInputStream input) throws IOException {

		this.animationID = input.readInt();
		this.player = input.readBoolean();
		this.entityID = input.readInt();
		
		PacketHandler.processEntityAnimation(this);

	}

}
