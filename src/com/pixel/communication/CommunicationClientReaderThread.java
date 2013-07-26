package com.pixel.communication;

import com.pixel.communication.packet.Packet;

public class CommunicationClientReaderThread extends Thread  {

	CommunicationClient client;
	Packet packet;
	
	public CommunicationClientReaderThread (CommunicationClient client) {

		this.client = client;
		packet = null;


	}

	public void run(){

		while (CommunicationClient.isRunning()) {

			if (packet == null && CommunicationClient.isRunning()) {
				
				packet = CommunicationClient.readNetworkPacket(client);
				
			} else if (packet.loaded && CommunicationClient.isRunning()) {

				packet = null;

			} else {
				
				break;
				
			}
			
		}
		
	}
	
}
