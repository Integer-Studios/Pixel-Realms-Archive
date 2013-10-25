package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pixel.piece.Piece;

public class PacketUpdatePiece extends Packet {

	public int metadata;
	public int posX;
	public int posY;
	
	public PacketUpdatePiece(Piece piece) {
		
		this.id = 20;
		this.metadata = piece.metadata;
		this.posX = piece.posX;
		this.posY = piece.posY;
		
	}
	
	@Override
	public void writeData(DataOutputStream output) throws IOException {

		output.writeInt(posX);
		output.writeInt(posY);
		output.writeInt(metadata);
		
	}

	@Override
	public void readData(DataInputStream input) throws IOException {

		this.posX = input.readInt();
		this.posY = input.readInt();
		this.metadata = input.readInt();
		
		PacketHandler.processUpdatePiece(this);
		
	}

}
