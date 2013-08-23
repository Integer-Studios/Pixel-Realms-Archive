package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketUpdateInventoryContent extends Packet {
	
	int x, y, itemID, size, inventory;
	
	public PacketUpdateInventoryContent() {}
	
	public PacketUpdateInventoryContent(int x, int y, int itemID, int size, int inventory) {
		
		this.id = 11;
		this.x = x;
		this.y = y;
		this.itemID = itemID;
		this.size = size;
		this.inventory = inventory;
		
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		
		output.writeInt(this.x);
		output.writeInt(this.y);
		output.writeInt(this.itemID);
		output.writeInt(this.size);
		output.writeInt(this.inventory);
		
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		
		x = input.readInt();
		y = input.readInt();
		itemID = input.readInt();
		size = input.readInt();
		inventory = input.readInt();
		
		if (inventory != 0 || y < 1)
		PacketHandler.processUpdateInventoryContent(this);
		
	}

}
