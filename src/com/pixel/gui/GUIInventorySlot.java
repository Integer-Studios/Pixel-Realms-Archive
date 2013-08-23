package com.pixel.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.input.MouseClickListener;
import com.pixel.inventory.Inventory;
import com.pixel.inventory.InventoryContent;
import com.pixel.item.Item;
import com.pixel.item.ItemStack;

public class GUIInventorySlot extends GUIComponentSet {

	public GUIInventorySlot(int x, int y, InventoryContent c, Inventory i) {
		super(x, y, 45, 45, new GUIComponent[]{
				new GUIComponent(x, y, 45, 45, PlayerInterfaceManager.slotImage),
				new GUIItemStack(x+6, y+6, c.itemstack)
		});
		content = c;
		inventory = i;
	}
	
	public void setItem(InventoryContent content, Inventory i) {
		
		((GUIItemStack) components[1]).setItemStack(content.itemstack);
		
		this.content = content;
		this.inventory = i;
		
	}
	
	public void render(GameContainer c, Graphics g) {
		setPressed(MouseClickListener.getIsPressedInInventorySlot(this));
		super.render(c, g);
	}
	
	public void setItem(ItemStack itemstack) {
		
		this.content = new InventoryContent(0, 0, itemstack);
		
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
		inventory.setContent(content.x, content.y, slotStack);
	}

	public boolean getPressed() {
		return pressed;
	}
	
	public boolean getOnMouseUp() {
		return onMouseUp;
	}

	public boolean pressed, onMouseUp;
	public InventoryContent content;
	public Inventory inventory;
}
