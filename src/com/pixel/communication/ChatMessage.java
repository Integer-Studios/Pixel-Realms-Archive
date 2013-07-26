package com.pixel.communication;

import org.newdawn.slick.Color;

import com.pixel.gui.GUIChat;

public class ChatMessage {

	public String username;
	public int userID;
	public Color color;
	public String text;
	public boolean shown;
	public int time;
	
	public ChatMessage(String username, String text, Color color, int userID) { 
		
		this.username = username;
		this.text = text;
		this.color = color;
		this.userID = userID;
		
	}
	
	public ChatMessage(String text, int userID) { 
		
		this.text = text;
		this.userID = userID;
		this.username = PlayerManager.players.get(userID).username;
		this.color = GUIChat.defaultColor;
		
	}
	
}
