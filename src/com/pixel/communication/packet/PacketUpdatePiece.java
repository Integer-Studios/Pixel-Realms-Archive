package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pixel.piece.Piece;

public class PacketUpdatePiece extends Packet {

	public int pieceID, posX, posY, damage, metadata;
	public int buildingID = -1;
	public int worldID;
	public Piece piece;
	
	public PacketUpdatePiece() {}
	
	public PacketUpdatePiece(Piece piece) {
		
		this.id = 5;
		this.pieceID = piece.id;
		this.posX = piece.posX;
		this.posY = piece.posY;
		this.damage = piece.damage;
		this.metadata = piece.metadata;
		this.piece = piece;
		
	}
	
	public PacketUpdatePiece(int buildingID, int x, int y) {
		
		this.id = 5;
		this.posX = x;
		this.posY = y;
		this.buildingID = buildingID;
		
	}


	@Override
	public void writeData(DataOutputStream output) throws IOException {

		output.writeInt(pieceID);
		output.writeInt(posX);
		output.writeInt(posY);
		output.writeInt(damage);
		output.writeInt(metadata);
		
		if (buildingID != -1) {
			
			output.writeBoolean(true);
			output.writeInt(buildingID);
			
		} else {
			
			output.writeBoolean(false);
			
		}

	}

	@Override
	public void readData(DataInputStream input) throws IOException {

		pieceID = input.readInt();
		posX = input.readInt();
		posY = input.readInt();
		damage = input.readInt();
		metadata = input.readInt();
		
		if (input.readBoolean()) {
			
			worldID = input.readInt();
			buildingID = input.readInt();

		} else {
			buildingID = -1;
			
		}
		
		PacketHandler.processPieceUpdate(this);
	
		loaded = true;
		
	}
	
}
