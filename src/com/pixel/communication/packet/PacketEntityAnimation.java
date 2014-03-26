package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketEntityAnimation extends Packet {

	public int animationID, entityID;
	public boolean player, remove;
	
	public PacketEntityAnimation() {this.id = 22;}
	
	public PacketEntityAnimation(int animationID, boolean remove) {
		
		this.id = 22;
		this.animationID = animationID;
		this.remove = remove;
		this.player = true;
		
	}
	
	@Override
	public void writeData(DataOutputStream output) throws IOException {

		output.writeInt(animationID);
		output.writeBoolean(remove);
		output.writeBoolean(player);
		output.writeInt(entityID);
		
	}

	@Override
	public void readData(DataInputStream input) throws IOException {

		this.animationID = input.readInt();
		this.remove = input.readBoolean();
		this.player = input.readBoolean();
		this.entityID = input.readInt();
		
		PacketHandler.processEntityAnimation(this);

	}

}
