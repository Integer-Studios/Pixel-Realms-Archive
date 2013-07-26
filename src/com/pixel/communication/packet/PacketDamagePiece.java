package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketDamagePiece extends Packet {
	
	public int posX, posY, deltaDamage, damageSource;
	
	public PacketDamagePiece() {}
	
	public PacketDamagePiece(int x, int y, int deltaDamage, int damageSource) {
		this.id = 10;
		this.posX = x;
		this.posY = y;
		this.deltaDamage = deltaDamage;
		this.damageSource = damageSource;
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {

		output.writeInt(posX);
		output.writeInt(posY);
		output.writeInt(deltaDamage);
		output.writeInt(damageSource);

	}

	@Override
	public void readData(DataInputStream input) throws IOException {

		posX = input.readInt();
		posY = input.readInt();
		deltaDamage = input.readInt();
		damageSource = input.readInt();

		PacketHandler.processDamagePiece(this);
		
	}

}
