package com.pixel.communication;

public class CommunicationClientWriterThread extends Thread {

	CommunicationClient client;

	public CommunicationClientWriterThread (CommunicationClient client) {
		
		this.client = client;

	}
	
	public void run() {
		CommunicationClient.sent.getAndIncrement();
		
		while (CommunicationClient.isRunning()) {
			
			if (CommunicationClient.isRunning())
				CommunicationClient.sendNetworkPacket(client);
			else 
				break;
			
		}
		
	}

}
