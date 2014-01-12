package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketLoginStage extends Packet {

	public int stageID;
	public float completion;
	
	public PacketLoginStage() {this.id = 24;}
	
	public PacketLoginStage(int stageID, float completion) {
		
		this.id = 24;
		this.stageID = stageID;
		this.completion = completion;
		
	}
	
	@Override
	public void writeData(DataOutputStream output) throws IOException {

	}

	@Override
	public void readData(DataInputStream input) throws IOException {

		this.stageID = input.readInt();
		this.completion = input.readFloat();
		
		PacketHandler.processLoginStage(this);
		
	}

}
