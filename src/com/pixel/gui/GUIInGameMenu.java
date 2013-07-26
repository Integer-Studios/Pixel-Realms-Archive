package com.pixel.gui;

import org.lwjgl.opengl.Display;


public class GUIInGameMenu {
	
	public GUIInGameMenu() {
		originX = Display.getWidth()/2-300;
		originY = Display.getHeight()/2-200;
		window = new GUIWindow(originX, originY, 600, 400);

	}
	
	public void addToGUI() {
		originX = Display.getWidth()/2-300;
		originY = Display.getHeight()/2-200;
		
		window = new GUIWindow(originX, originY, 600, 400);
		
		window = (GUIWindow) GUI.addGUIComponent(window);
		
		isInGUI = true;
	}
	
	public void removeFromGUI() {
		GUI.removeGUIComponent(window);
		
		isInGUI = false;
	}
	
	public void tick() {
		
	}
	
	public GUIWindow window;
	public int originX, originY;
	public boolean isInGUI;
}
