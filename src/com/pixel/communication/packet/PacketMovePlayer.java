package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pixel.communication.PlayerManager;
import com.pixel.entity.EntityAlive;

public class PacketMovePlayer extends Packet {

	EntityAlive entity;
	int userID;
	boolean n, w, e, s;
	int speed;
	
	public PacketMovePlayer() {
		this.id = 16;
	}
	
	public PacketMovePlayer(boolean n, boolean w, boolean e, boolean s) {
		
		this.id = 16;
		this.userID = PlayerManager.currentUserID;
		this.n = n;
		this.w = w;
		this.e = e;
		this.s = s;
		
	}
	
	public void writeData(DataOutputStream output) throws IOException {

		output.writeInt(userID);
		output.writeBoolean(n);
		output.writeBoolean(w);
		output.writeBoolean(e);
		output.writeBoolean(s);

		
	}

	public void readData(DataInputStream input) throws IOException {

		userID = input.readInt();
		n = input.readBoolean();
		w = input.readBoolean();
		e = input.readBoolean();
		s = input.readBoolean();
		speed = input.readInt();
		
		if (PlayerManager.currentUserID != userID) {
			
			if (PlayerManager.players.containsKey(userID)) {
				
				PlayerManager.players.get(userID).n = n;
				PlayerManager.players.get(userID).w = w;
				PlayerManager.players.get(userID).e = e;
				PlayerManager.players.get(userID).s = s;
				PlayerManager.players.get(userID).speed = speed;


			}
			
		}

	}

}
