package com.pixel.gui;

import java.util.concurrent.ConcurrentHashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.inventory.Inventory;
import com.pixel.util.CoordinateKey;

public class GUIInventory extends GUIComponentSet {

	public ConcurrentHashMap<CoordinateKey, GUIInventorySlot> slots = new ConcurrentHashMap<CoordinateKey, GUIInventorySlot>();
	
	public GUIInventory(int x, int y, Inventory i) {
		super(x, y, i.getWidth()*49, i.getHeight()*49, new GUIComponent[]{});
		inventory = i;
		createSlots();
	}

	public void createSlots() {
		for (int x = 0; x < inventory.getWidth(); x++) {
			for (int y = 0; y < inventory.getHeight(); y++) {
				slots.put(new CoordinateKey(x, y), new GUIInventorySlot(this.x + x*49, this.y + y*49, inventory.getContent(x, y), inventory));
			}
		}
	}
	
	public void updateSlot(int x, int y) {
		
		slots.put(new CoordinateKey(x, y), new GUIInventorySlot(this.x + x*49, this.y + y*49, inventory.getContent(x, y), inventory));
		
	}
	
	public void render(GameContainer c, Graphics g) {
		super.render(c, g);
		for (GUIInventorySlot slot : slots.values()) {
			slot.render(c, g);
		}
	}
	
	public void setInventoryAndRepaint(Inventory i) {
		inventory = i;
		createSlots();
	}
	
	public void setInventory(Inventory i) {
		inventory = i;
	}
	
	@Override 
	public void setY(int y) {
		
		int deltaY = y - this.y;
		for (GUIInventorySlot slot : slots.values()) {
			slot.setY(slot.y+deltaY);
		}
		this.y = y;
		
	}
	
	@Override 
	public void setX(int x) {
		
		int deltaX = x - this.x;
		for (GUIInventorySlot slot : slots.values()) {
			slot.setX(slot.x+deltaX);
		}
		this.x = x;
		
	}

	public Inventory inventory;
}
