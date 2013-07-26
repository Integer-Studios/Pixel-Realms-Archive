package com.pixel.communication.packet;

import org.newdawn.slick.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pixel.communication.ChatMessage;
import com.pixel.communication.PlayerManager;
import com.pixel.start.PixelRealms;

public class PacketChat extends Packet {

	String username;
	String message;
	Color color;
	
	public PacketChat() {
		
		this.id = 8;
		
	}
	
	public PacketChat(String username, String message, Color color) {
		
		this.id = 8;
		this.userID = PlayerManager.currentUserID;
		this.username = username;
		this.message = message;
		this.color = color;
		
	}
	
	public PacketChat(ChatMessage message) {
		
		this.id = 8;
		this.userID = message.userID;
		this.username = message.username;
		this.message =  message.text;
		this.color =  message.color;
		
	}
	
	@Override
	public void writeData(DataOutputStream output) throws IOException {

		Packet.writeString(username, output);
		Packet.writeString(message, output);

		int colorInt = 1;
		if (color == Color.white) {

			colorInt = 1;

		}

		if (color == Color.black) {

			colorInt = 2;

		}

		if (color == Color.red) {

			colorInt = 3;

		}

		if (color == Color.blue) {

			colorInt = 4;

		}
		
		if (color == Color.gray) {

			colorInt = 5;

		}
		
		if (color == Color.green) {

			colorInt = 6;

		}
		
		if (color == Color.yellow) {

			colorInt = 7;

		}
		
		output.writeInt(colorInt);
		
	}

	@Override
	public void readData(DataInputStream input) throws IOException {

		username = Packet.readString(16, input);
		message = Packet.readString(64, input);
		int colorInt = input.readInt();
		
		switch (colorInt) {

		case 1:
			color = Color.white;
			break;
		case 2:
			color = Color.black;
			break;
		case 3:
			color = Color.red;
			break;
		case 4:
			color = Color.blue;
			break;
		case 5:
			color = Color.gray;
			break;
		case 6:
			color = Color.green;
			break;
		case 7:
			color = Color.yellow;
			break;

		}

		if (this.userID != PlayerManager.currentUserID) {
			
			PixelRealms.world.player.interfaceManager.chat.addMessage(new ChatMessage(username, message, color, userID));

		}
		
	}
}
