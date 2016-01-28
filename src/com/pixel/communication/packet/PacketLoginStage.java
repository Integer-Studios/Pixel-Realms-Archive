package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketLoginStage extends Packet {

	public int stageID;
	
	public PacketLoginStage() {this.id = 24;}
	
	public PacketLoginStage(int stageID) {
		
		this.id = 24;
		this.stageID = stageID;
		
	}
	
	@Override
	public void writeData(DataOutputStream output) throws IOException {

	}

	@Override
	public void readData(DataInputStream input) throws IOException {

		this.stageID = input.readInt();
		
		PacketHandler.processLoginStage(this);
		
	}

}
