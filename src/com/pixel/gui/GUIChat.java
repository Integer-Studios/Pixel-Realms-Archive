package com.pixel.gui;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

import com.pixel.communication.ChatMessage;
import com.pixel.communication.CommunicationClient;
import com.pixel.communication.PlayerManager;
import com.pixel.communication.packet.PacketChat;
import com.pixel.start.PixelRealms;

public class GUIChat extends GUIComponentSet {
	
	public GUIComponentChatText[] lines = new GUIComponentChatText[5];
	public GUIChatTextBox box;
	public ArrayList<ChatMessage> messages = new ArrayList<ChatMessage>();
	public ArrayList<ChatMessage> shown = new ArrayList<ChatMessage>();

	public static Color defaultColor = Color.white;
	
	public GUIChat() {
		super(0, 0, 128, 100, new GUIComponent[]{
//				new GUIComponent(0, 0, 128, 48, ""),
				new GUIComponentChatText("", 20, 20, 25, Color.white),
				new GUIComponentChatText("", 20, 50, 25, Color.white),
				new GUIComponentChatText("", 20, 80, 25, Color.white),
				new GUIComponentChatText("", 20, 110, 25, Color.white),
				new GUIComponentChatText("", 20, 140, 25, Color.white),
				new GUIChatTextBox(20, 170, 300, 35, new GUIComponentText("", 0, 0, 25))

		});
		
		lines[0] = (GUIComponentChatText) components[4];
		lines[1] = (GUIComponentChatText) components[3];
		lines[2] = (GUIComponentChatText) components[2];
		lines[3] = (GUIComponentChatText) components[1];
		lines[4] = (GUIComponentChatText) components[0];
		box = (GUIChatTextBox) components[5];
		
				// TODO Auto-generated constructor stub
	}
	
	public void render(GameContainer c, Graphics g) {

		int originX = Display.getWidth() - width - 780;
		int originY = Display.getHeight() - height - 130;

		for (int x = 0; x < lines.length; x ++) {

			if (lines[x].message != null) {
				if (box.getPressed()) {

					lines[x].setText(lines[x].message.username + ": " + lines[x].message.text);
					lines[x].fontColor = (lines[x].message.color);

				} else if (!lines[x].text.isEmpty()) {

					lines[x].message.time ++;

					if (lines[x].message.time > 300) {

						lines[x].setText("");
						lines[x].message.shown = true;

					}

				}
			}

		}
	
		this.setPosition(originX, originY);
		super.render(c, g);

	}

	public void send() {

		if (!box.getText().isEmpty()) {
			
			String message = box.getText();
			box.setText("");
			
			if (message.equals("/l")) {
				
				PixelRealms.world.leaveInterior();
				return;
				
			}
			
			if (!message.startsWith("/"))
				addMessage(new ChatMessage(PlayerManager.currentPlayer, message, defaultColor, PlayerManager.currentUserID));
			CommunicationClient.addPacket(new PacketChat(new ChatMessage(PlayerManager.currentPlayer, message, defaultColor, PlayerManager.currentUserID)));
			
		}
		
	}
	
	public void addMessage(ChatMessage msg) {
		
		reorder(msg);
		
	}
	
	public void reorder(ChatMessage msg) {

		
		lines[4].setMessage(lines[3].message);
		lines[3].setMessage(lines[2].message);
		lines[2].setMessage(lines[1].message);
		lines[1].setMessage(lines[0].message);
		lines[0].setMessage(msg);
		
	}
	
	public void setLine(int line, String text, Color color) {
		
		lines[line].fontColor = color;
		lines[line].setText(text);
		
	}
	
}
