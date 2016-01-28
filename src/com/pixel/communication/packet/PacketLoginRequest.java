package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pixel.start.MainLoop;
import com.pixel.stage.StageWorld;

public class PacketLoginRequest extends Packet {

	public int session;
	public int userID;
	public int serverID;
	public String username;
	
	public PacketLoginRequest() {this.id = 2;}
	
	public PacketLoginRequest(int userID, int session, String username) {

		this.id = 2;
		this.userID = userID;
		this.session = session;
		this.username = username;
		
	}
	
	@Override
	public void writeData(DataOutputStream output) throws IOException {
		System.out.println("AA");

		output.writeInt(userID);
		output.writeInt(session);
		Packet.writeString(username, output);
		System.out.println("A");
	}

	@Override
	public void readData(DataInputStream input) throws IOException {

		userID = input.readInt();
		session = input.readInt();
		serverID = input.readInt();
		PacketHandler.handleLoginRequest(this);
		
	}

}
