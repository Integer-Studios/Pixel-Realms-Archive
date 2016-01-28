package com.pixel.gui;

import org.lwjgl.opengl.Display;

import com.pixel.entity.EntityPlayer;

public class GUICenterFold {
	
	public EntityPlayer player;
	public GUIComponent window, decor;
	public GUIInventory leftInv, rightInv;
	public int originX, originY;
	
	public GUICenterFold(EntityPlayer player) {
		this.player = player;
		originX = Display.getWidth()/2-225;
		originY = -315;
//		window = new GUIComponent(originX, originY, 450, 400, "resources/gui/interface/centerFold/window.png");
//		leftInv = new GUIInventory(originX + 45, originY + 45, player.getLeftInventory());
//		rightInv = new GUIInventory(originX + 258, originY + 45, player.getRightInventory());
//		decor = new GUIComponent(originX+211, originY+70, 28, 250, "resources/gui/interface/centerFold/decor.png");
	}
	
	public void setY(int i) {
		originY = i;
		window.setY(originY);
		leftInv.setY(originY + 45);
		rightInv.setY(originY + 45);
		decor.setY(originY + 70);
	}
	
	public void addToGUI() {
		originX = Display.getWidth()/2-225;
		originY = -315;
		window = new GUIComponent(originX, originY, 450, 400, "resources/gui/interface/centerFold/window.png");
		leftInv = new GUIInventory(originX + 45, originY + 45, player.getLeftInventory());
		rightInv = new GUIInventory(originX + 258, originY + 45, player.getRightInventory());
		decor = new GUIComponent(originX+211, originY+70, 28, 250, "resources/gui/interface/centerFold/decor.png");
		
		window = (GUIComponent) GUI.addGUIComponent(window);
		leftInv = (GUIInventory) GUI.addGUIComponent(leftInv);
		rightInv = (GUIInventory) GUI.addGUIComponent(rightInv);
		decor = (GUIComponent) GUI.addGUIComponent(decor);
		
	}

	public void removeFromGUI() {
		GUI.removeGUIComponent(decor);
		GUI.removeGUIComponent(rightInv);
		GUI.removeGUIComponent(leftInv);
		GUI.removeGUIComponent(window);
	}

	public void moveToFront() {

		window = GUI.moveToFront(window);
		leftInv = (GUIInventory) GUI.moveToFront(leftInv);
		rightInv = (GUIInventory) GUI.moveToFront(rightInv);
		decor = GUI.moveToFront(decor);

	}
	
}
