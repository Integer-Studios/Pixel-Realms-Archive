package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pixel.piece.Piece;
import com.pixel.piece.PieceBuilding;

public class PacketUpdatePiece extends Packet {

	public int pieceID, posX, posY, damage, metadata, buildingID, worldID;
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

	@Override
	public void writeData(DataOutputStream output) throws IOException {

		output.writeInt(pieceID);
		output.writeInt(posX);
		output.writeInt(posY);
		output.writeInt(damage);
		output.writeInt(metadata);
		
		if (piece instanceof PieceBuilding) {
			
			output.writeBoolean(true);
			output.writeInt(((PieceBuilding) piece).building.worldID);
			output.writeInt(((PieceBuilding) piece).building.id);
			
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
