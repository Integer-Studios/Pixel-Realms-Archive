package com.pixel.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class GUIComponentSet extends GUIComponent {
	
	public GUIComponentSet(int x, int y, int width, int height, GUIComponent[] c) {
		super(x, y, width, height, "");
		components = c;
	}
	
	public void onAddedToGUI() {
		for (int i = 0; i < components.length; i++) {
			components[i].onAddedToGUI();
		}
	}
	
	public void render(GameContainer c, Graphics g) {
		for (int i = 0; i < components.length; i++) {
			components[i].render(c, g);
		}
	}
	
	
	public void setX(int x) {
		int deltaX = x - this.x;
		for (int i = 0; i < components.length; i++) {
			components[i].setX(components[i].x+deltaX);
		}
		this.x = x;
	}
	
	public void setY(int y) {
		int deltaY = y - this.y;
		for (int i = 0; i < components.length; i++) {
			components[i].setY(components[i].y+deltaY);
		}
		this.y = y;
	}
	
	public void setPosition(int x, int y) {
		int deltaY = y - this.y;
		int deltaX = x - this.x;
		for (int i = 0; i < components.length; i++) {
			components[i].setPosition(components[i].x + deltaX, components[i].y + deltaY);
		}
		this.x = x;
		this.y = y;
	}
	
	public GUIComponent[] components;
	public int indexInGUI = -1;
}
