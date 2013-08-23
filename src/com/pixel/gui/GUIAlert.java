package com.pixel.gui;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.communication.CommunicationClient;

public class GUIAlert extends GUIComponentBlank {
	
	public GUIWindow window;
	public GUIComponentText text;
	public GUIButton button;
	public int originX, originY;
	public String callback = "";
	public boolean shouldRemoveFromGUI;

	public GUIAlert(String alert, Color color, String callback) {
		this.callback = callback;
		
		init(alert, color);

	}
	
	public GUIAlert(String alert, Color color) {
		
		init(alert, color);
		
	}
	
	public void init(String alert, Color color) {
		
		originX = Display.getWidth()/2-300;
		originY = Display.getHeight()/2-150;
		window = new GUIWindow(originX, originY, 600, 300);
		text = new GUIComponentText(alert, originX + 50, originY + 50, 40, color);
		button = new GUIButton(originX+200, originY+150, 200, 70, new GUIComponentText("Ok", 90, 8, 35));
		GUI.addGUIComponent(this);
		window = (GUIWindow) GUI.addGUIComponent(window);
		text = (GUIComponentText) GUI.addGUIComponent(text);
		button = (GUIButton) GUI.addGUIComponent(button);	
		
	}
	
	public void removeFromGUI() {
		GUI.removeGUIComponent(this);
		GUI.removeGUIComponent(window);
		GUI.removeGUIComponent(text);
		GUI.removeGUIComponent(button);

	}
	
	public void render(GameContainer c, Graphics g) {
		
		if (shouldRemoveFromGUI) {
			
			removeFromGUI();
			
		}

		if (button.onMouseUp) {
			shouldRemoveFromGUI = true;
			if (!callback.equals("")) {

				if (callback.equals("connect")) {
					
					CommunicationClient.alertReturn();
					
				}
				
				if (callback.equals("disconnect")) {
					
					CommunicationClient.alertReturn();
					
				}
				
				
			}
		} 

	}
}
