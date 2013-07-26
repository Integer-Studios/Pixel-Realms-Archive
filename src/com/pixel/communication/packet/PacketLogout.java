package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pixel.communication.CommunicationClient;
import com.pixel.communication.PlayerManager;

public class PacketLogout extends Packet {

	public PacketLogout() {
		
		this.id = 9;
		
	}
	
	@Override
	public void writeData(DataOutputStream output) throws IOException {

		
	}

	@Override
	public void readData(DataInputStream input) throws IOException {

		if (this.userID != PlayerManager.currentUserID) {
			PlayerManager.players.remove(this.userID);
		} else {
			
			//kicked
			PlayerManager.kicked = true;
			CommunicationClient.disconnect();
			
		}

	}

}
