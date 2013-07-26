package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pixel.entity.EntityOnlinePlayer;

public class PacketDamagePlayer extends Packet {

	float damage;
	EntityOnlinePlayer player;
	
	public PacketDamagePlayer(EntityOnlinePlayer player, float damage) {
		
		this.id = 13;
		this.player = player;
		this.damage = damage;
		
	}
	
	@Override
	public void writeData(DataOutputStream output) throws IOException {

		output.writeInt(player.userID);
		output.writeFloat(damage);
		
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
