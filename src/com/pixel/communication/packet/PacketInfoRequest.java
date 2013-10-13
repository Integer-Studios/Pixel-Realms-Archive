package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketInfoRequest extends Packet {

	String request;
	
	public PacketInfoRequest() {
		
		this.id = 17;
		
	}
	
	public PacketInfoRequest(String request) {
		
		this.id = 17;
		this.request = request;
		
	}
	
	@Override
	public void writeData(DataOutputStream output) throws IOException {
		// TODO Auto-generated method stub
		Packet.writeString(request, output);
		
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		// TODO Auto-generated method stub
		request = Packet.readString(16, input);
	}

}
