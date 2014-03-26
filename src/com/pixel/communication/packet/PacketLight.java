package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pixel.lighting.PixelLight;
import com.pixel.lighting.PixelLightType;
import com.pixel.lighting.PixelLightingManager;

public class PacketLight extends Packet {

	public int lightID, width, height;
	public int proposalID = -1;
	public float x, y;
	public PixelLightType type;
	
	public PacketLight() {this.id = 25;}
	
	public PacketLight(PixelLight light, int proposalID) {
		
		this.id = 25;
		this.proposalID = proposalID;
		this.lightID = light.id;
		this.x = light.posX;
		this.y = light.posY;
		this.width = light.width;
		this.height = light.height;
		this.type = light.type;
		
	}
	
	public PacketLight(int lightID, float x, float y, int width, int height, PixelLightType type) {
		
		this.id = 25;
		this.lightID = lightID;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
		
	}
	
	@Override
	public void writeData(DataOutputStream output) throws IOException {

		output.writeInt(proposalID);
		output.writeInt(lightID);
		output.writeFloat(x);
		output.writeFloat(y);
		output.writeInt(width);
		output.writeInt(height);
		output.writeInt(type.getTypeID());

	}

	@Override
	public void readData(DataInputStream input) throws IOException {

		this.proposalID = input.readInt();
		this.lightID = input.readInt();
		this.x = input.readFloat();
		this.y = input.readFloat();
		this.width = input.readInt();
		this.height = input.readInt();
		this.type = PixelLightType.getTypeForID(input.readInt());

		PixelLight p = new PixelLight(lightID, x, y, width, height, type);

		if (proposalID != -1) {
			
			PixelLightingManager.confirmProposal(proposalID, p);
			
		}

		
	}

}
