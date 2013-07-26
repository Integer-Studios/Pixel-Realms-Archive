package com.pixel.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.input.MouseClickListener;
import com.pixel.item.Item;
import com.pixel.item.ItemStack;

public class GUIItemStackOnMouse extends GUIItemStack {

	public GUIItemStackOnMouse(int x, int y, ItemStack is) {
		super(x, y, is);
	}

	public void render(GameContainer c, Graphics g) {
		components[0].setX((int) MouseClickListener.posX - components[0].width/2);
		components[0].setY((int) MouseClickListener.posY - components[0].height/2);
		components[1].setX((int) MouseClickListener.posX+18 - components[0].width/2);
		components[1].setY((int) MouseClickListener.posY+13 - components[0].height/2);
		super.render(c, g);
	}
	
	public static void addToGUI(ItemStack i) {
		currentStackOnMouse.setItemStack(i);
		currentStackOnMouse = (GUIItemStackOnMouse) GUI.addGUIComponent(currentStackOnMouse);
		mouseOccupied = true;
	}
	
	public static void removeFromGUI() {
		if (mouseOccupied) {
			GUI.removeGUIComponent(currentStackOnMouse);
			currentStackOnMouse.setItemStack(new ItemStack(Item.blank, 0));
			mouseOccupied = false;
		}
	}
	
	public static void setCurrentItemStack(ItemStack i) {
		currentStackOnMouse.setItemStack(i);
	}
	
	public static boolean isMouseOccupied() {
		return mouseOccupied;
	}
	
	public static GUIItemStackOnMouse currentStackOnMouse = new GUIItemStackOnMouse(0, 0, new ItemStack(Item.blank, 0));
	public static boolean mouseOccupied = false;
	
}
