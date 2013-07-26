package com.pixel.gui;


import com.pixel.inventory.Inventory;

public class GUIInventory extends GUIComponentSet {

	public GUIInventory(int x, int y, Inventory i) {
		super(x, y, i.getWidth()*49, i.getHeight()*49, new GUIComponent[]{});
		inventory = i;
		components = createSlots();
	}

	public GUIComponent[] createSlots() {
		GUIComponent[] ret = new GUIComponent[inventory.getWidth()*inventory.getHeight()];
		int i = 0;
		for (int x = 0; x < inventory.getWidth(); x++) {
			for (int y = 0; y < inventory.getHeight(); y++) {
				ret[i] = new GUIInventorySlot(this.x + x*49, this.y + y*49, inventory.getContent(x, y), inventory);
				i++;
			}
		}
		return ret;
	}
	
	public void setInventory(Inventory i) {
		inventory = i;
		components = createSlots();
	}

	public Inventory inventory;
}
