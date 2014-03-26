package com.pixel.gui;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.input.MouseClickListener;
import com.pixel.item.Item;
import com.pixel.item.ItemStack;

public class GUICraftingSlot extends GUIComponent {
	
	public boolean pressed, onMouseUp;
	public ArrayList<ItemStack> itemsInCrafting = new ArrayList<ItemStack>();

	public GUICraftingSlot(int x, int y) {
		super(x, y, 45, 45, "resources/gui/inventory/slot.png");
	}
	
	public void render(GameContainer c, Graphics g) {
		setPressed(MouseClickListener.getIsPressedInComponent(this));
		super.render(c, g);
	}
	
	public void setPressed(boolean b) {
		if (pressed && !b) {
			onMouseUp = true;
			handleItemStackExchange(MouseClickListener.rightClick);
		} else {
			onMouseUp = false;
		}
		pressed = b;
	}
	
	public void drainSlotForItemStack(ItemStack is) {
		for (int i = 0; i < itemsInCrafting.size(); i++) {
			if (itemsInCrafting.get(i).item.id == is.item.id) {
				System.out.println(itemsInCrafting.size());
				if (itemsInCrafting.get(i).size > is.size) {
					itemsInCrafting.set(i, new ItemStack(is.item, itemsInCrafting.get(i).size - is.size));
				} else {
					itemsInCrafting.set(i, new ItemStack(Item.blank, 0));
				}
				return;
			}
		}
	}
	
	
	public void handleItemStackExchange(boolean isRightClick) {
		ItemStack slotStack = new ItemStack(Item.blank, 0);
		ItemStack depletedStack = GUIItemStackOnMouse.currentStackOnMouse.getItemStack();
		if (isRightClick) {
			if (GUIItemStackOnMouse.isMouseOccupied() && (slotStack.size == 0)) {
//				((GUIItemStack)components[1]).setItemStack(new ItemStack(depletedStack.item, 1));
				addItemStackToCrafting(new ItemStack(depletedStack.item, 1));
				GUIItemStackOnMouse.setCurrentItemStack(new ItemStack(depletedStack.item, depletedStack.size-1));
				if (depletedStack.size-1 == 0) {
					GUIItemStackOnMouse.removeFromGUI();
				}
			} else
			if (!GUIItemStackOnMouse.isMouseOccupied() && (slotStack.size != 0 && slotStack.size != 1)) {
//				((GUIItemStack)components[1]).setItemStack(new ItemStack(slotStack.item, slotStack.size-slotStack.size/2));
				addItemStackToCrafting(new ItemStack(slotStack.item, slotStack.size-slotStack.size/2));
				GUIItemStackOnMouse.addToGUI(new ItemStack(slotStack.item, slotStack.size/2));
			} else
			if (GUIItemStackOnMouse.isMouseOccupied() && (slotStack.size != 0) && (slotStack.item.id == depletedStack.item.id)) {
//				((GUIItemStack)components[1]).setItemStack(new ItemStack(slotStack.item, slotStack.size+1));
				addItemStackToCrafting(new ItemStack(slotStack.item, slotStack.size+1));
				GUIItemStackOnMouse.setCurrentItemStack(new ItemStack(depletedStack.item, depletedStack.size-1));
				if (depletedStack.size-1 == 0) {
					GUIItemStackOnMouse.removeFromGUI();
				}
			}
		} else {
			if (GUIItemStackOnMouse.isMouseOccupied()) {
				if (slotStack.item.id == depletedStack.item.id) {
//					((GUIItemStack)components[1]).setItemStack(new ItemStack(slotStack.item, slotStack.size + depletedStack.size));
					addItemStackToCrafting(new ItemStack(slotStack.item, slotStack.size + depletedStack.size));
					GUIItemStackOnMouse.removeFromGUI();
				} else {
//					((GUIItemStack)components[1]).setItemStack(GUIItemStackOnMouse.currentStackOnMouse.getItemStack());
					addItemStackToCrafting(GUIItemStackOnMouse.currentStackOnMouse.getItemStack());
					GUIItemStackOnMouse.removeFromGUI();
					if (slotStack.size != 0) {
						GUIItemStackOnMouse.addToGUI(slotStack);
					}
				}
			}
		}
	}
	
	public void addItemStackToCrafting(ItemStack is) {
		for (int i = 0; i < itemsInCrafting.size(); i++) {
			if (itemsInCrafting.get(i).item.id == is.item.id) {
				itemsInCrafting.set(i, new ItemStack(is.item, is.size + itemsInCrafting.get(i).size));
				return;
			}
		}
		itemsInCrafting.add(is);
	}

}
