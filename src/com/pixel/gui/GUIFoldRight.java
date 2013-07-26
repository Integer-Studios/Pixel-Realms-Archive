package com.pixel.gui;

import org.lwjgl.opengl.Display;

import com.pixel.entity.EntityPlayer;
import com.pixel.inventory.CraftingBook;

public class GUIFoldRight {
	
	public EntityPlayer player;
	public int originX, originY;
	public GUIComponent window;
	public GUICraftingBook book;
	public GUICraftingSlot craftingSlot;
	public GUIArrowButton backBtn;
	
	public GUIFoldRight(EntityPlayer player) {
		this.player = player;
		originX = Display.getWidth()/2+25;//folds in to +25 folds out to +225
		originY = -305;
		window = new GUIComponent(originX, originY, 200, 350, "resources/gui/interface/foldRight/window.png");
		
		craftingSlot = new GUICraftingSlot(originX+77, originY+30);
		backBtn = new GUIArrowButton(originX+55, originY+41, "back");
		
		book = new GUICraftingBook(originX+10, originY+110, craftingSlot, CraftingBook.defaultBook);

	}
	
	public void tick() {
		if (backBtn.onMouseUp) {
			for (int i = 0; i < craftingSlot.itemsInCrafting.size(); i++) {
				player.giveItem(craftingSlot.itemsInCrafting.get(i));
			}
			craftingSlot.itemsInCrafting.clear();
		}
	}
	
	public void setY(int i) {
		originY = i;
		window.setY(originY);
		
		book.setY(originY+110);

		craftingSlot.setY(originY+30);
		backBtn.setY(originY+41);
	}
	
	public void setX(int i) {
		originX = i;
		window.setX(originX);
		
		book.setX(originX + 10);

		craftingSlot.setX(originX+77);
		backBtn.setX(originX+55);
	}
	
	public void addToGUI() {
		originX = Display.getWidth()/2+25;
		originY = -305;
		window = new GUIComponent(originX, originY, 200, 350, "resources/gui/interface/foldRight/window.png");
		
		craftingSlot = new GUICraftingSlot(originX+77, originY+30);
		backBtn = new GUIArrowButton(originX+55, originY+41, "back");
		
		book = new GUICraftingBook(originX+10, originY+110, craftingSlot, CraftingBook.defaultBook);


		window = (GUIComponent) GUI.addGUIComponent(window);
		book = (GUICraftingBook) GUI.addGUIComponent(book);
		craftingSlot = (GUICraftingSlot) GUI.addGUIComponent(craftingSlot);
		backBtn = (GUIArrowButton) GUI.addGUIComponent(backBtn);
	}

	public void removeFromGUI() {
		GUI.removeGUIComponent(backBtn);
		GUI.removeGUIComponent(craftingSlot);
		GUI.removeGUIComponent(book);
		GUI.removeGUIComponent(window);
	}

}
