package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pixel.piece.Piece;

public class PacketUpdateInteriorPiece extends Packet {
	
	public int worldID, x, y, pieceID, damage, metadata;

	public PacketUpdateInteriorPiece() {}
	
	public PacketUpdateInteriorPiece(int worldID, Piece p) {
		
		this.id = 19;
		this.worldID = worldID;
		this.x = p.posX;
		this.y = p.posY;
		this.pieceID = p.id;
		this.damage = p.damage;
		this.metadata = p.metadata;
		
	}
	
	@Override
	public void writeData(DataOutputStream output) throws IOException {

		output.writeInt(worldID);
		output.writeInt(x);
		output.writeInt(y);
		output.writeInt(pieceID);
		output.writeInt(damage);
		output.writeInt(metadata);

	}

	@Override
	public void readData(DataInputStream input) throws IOException {

		this.worldID = input.readInt();
		this.x = input.readInt();
		this.y = input.readInt();
		this.pieceID = input.readInt();
		this.damage = input.readInt();
		this.metadata = input.readInt();
		
		PacketHandler.processUpdateInteriorPiece(this);
		
	}

}
