package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketUpdateTile extends Packet {
	
	public int tileID, posX, posY;

	public PacketUpdateTile() {}
	
	public PacketUpdateTile(int id, int x, int y) {
		
		this.id = 4;
		this.posX = x;
		this.posY = y;
		this.tileID = id;
		
	}
	
	@Override
	public void writeData(DataOutputStream output) throws IOException {

		output.writeInt(tileID);
		output.writeInt(posX);
		output.writeInt(posY);

	}

	@Override
	public void readData(DataInputStream input) throws IOException {

		tileID = input.readInt();
		posX = input.readInt();
		posY = input.readInt();

		PacketHandler.processTileUpdate(this);
		loaded = true;

	}

}
