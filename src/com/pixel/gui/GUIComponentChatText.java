package com.pixel.gui;

import org.newdawn.slick.Color;

import com.pixel.communication.ChatMessage;

public class GUIComponentChatText extends GUIComponentText {

	public ChatMessage message;
	
	public GUIComponentChatText(String t, int x, int y, int s) {
		super(t, x, y, s);

	}

	public GUIComponentChatText(String t, int x, int y, int s, Color c) {
		super(t, x, y, s, c);

	}
	
	public void setMessage(ChatMessage message) {

		if (message != null) {
			this.message = message;
			if (!message.shown) {
				this.setText(message.username + ": " + message.text);
				this.fontColor = message.color;
			}
		}

	}
	
}
