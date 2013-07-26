package com.pixel.gui;

import com.pixel.inventory.InventoryContent;
import com.pixel.item.Item;
import com.pixel.item.ItemStack;

public class GUIFreeInventorySlot extends GUIInventorySlot {

	public GUIFreeInventorySlot(int x, int y, ItemStack is) {
		super(x, y, new InventoryContent(0, 0, is), null);
	}
	
	public void handleItemStackExchange(boolean isRightClick) {

		ItemStack slotStack = ((GUIItemStack)components[1]).getItemStack();
		ItemStack depletedStack = GUIItemStackOnMouse.currentStackOnMouse.getItemStack();
		if (isRightClick) {
			if (GUIItemStackOnMouse.isMouseOccupied() && (slotStack.size == 0)) {
				((GUIItemStack)components[1]).setItemStack(new ItemStack(depletedStack.item, 1));
				GUIItemStackOnMouse.setCurrentItemStack(new ItemStack(depletedStack.item, depletedStack.size-1));
				if (depletedStack.size-1 == 0) {
					GUIItemStackOnMouse.removeFromGUI();
				}
			} else
			if (!GUIItemStackOnMouse.isMouseOccupied() && (slotStack.size != 0 && slotStack.size != 1)) {
				((GUIItemStack)components[1]).setItemStack(new ItemStack(slotStack.item, slotStack.size-slotStack.size/2));
				GUIItemStackOnMouse.addToGUI(new ItemStack(slotStack.item, slotStack.size/2));
			} else
			if (GUIItemStackOnMouse.isMouseOccupied() && (slotStack.size != 0) && (slotStack.item.id == depletedStack.item.id)) {
				((GUIItemStack)components[1]).setItemStack(new ItemStack(slotStack.item, slotStack.size+1));
				GUIItemStackOnMouse.setCurrentItemStack(new ItemStack(depletedStack.item, depletedStack.size-1));
				if (depletedStack.size-1 == 0) {
					GUIItemStackOnMouse.removeFromGUI();
				}
			}
		} else {
			if (GUIItemStackOnMouse.isMouseOccupied()) {
				if (slotStack.item.id == depletedStack.item.id) {
					((GUIItemStack)components[1]).setItemStack(new ItemStack(slotStack.item, slotStack.size + depletedStack.size));
					GUIItemStackOnMouse.removeFromGUI();
				} else {
					((GUIItemStack)components[1]).setItemStack(GUIItemStackOnMouse.currentStackOnMouse.getItemStack());
					GUIItemStackOnMouse.removeFromGUI();
					if (slotStack.size != 0) {
						GUIItemStackOnMouse.addToGUI(slotStack);
					}
				}
			} else {
				GUIItemStackOnMouse.addToGUI(((GUIItemStack)components[1]).getItemStack());
				((GUIItemStack)components[1]).setItemStack(new ItemStack(Item.blank, 0));
			}
		}
		slotStack = ((GUIItemStack)components[1]).getItemStack();
	}

}
