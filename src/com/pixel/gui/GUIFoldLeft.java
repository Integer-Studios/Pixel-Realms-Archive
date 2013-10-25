package com.pixel.gui;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.pixel.entity.EntityPlayer;
import com.pixel.start.PixelRealms;

public class GUIFoldLeft {
	
	public boolean rob;
	public EntityPlayer player;
	public int originX, originY;
	public GUIComponent window;
	public int menuID = -1;
	
	public GUIFoldLeft(EntityPlayer player) {
		this.player = player;
		originX = Display.getWidth()/2-225;//folds out to -425 folds in to - 225
		originY = -305;
		window = new GUIComponent(originX, originY, 200, 350, "resources/gui/interface/foldLeft/window.png");
		
		initialize();
		
	}
	
	public void initialize() {
		
		GUIComponent leftFoot = new GUIComponent(originX+58, originY+100, 84, 132, "resources/entities/rob/front/leftFoot.png");
		GUIComponent rightFoot = new GUIComponent(originX+58, originY+100, 84, 132, "resources/entities/rob/front/rightFoot.png");
		GUIComponent body = new GUIComponent(originX+58, originY+100, 84, 132, "resources/entities/rob/front/body.png");
		GUIComponent leftHand = new GUIComponent(originX+58, originY+100, 84, 132, "resources/entities/rob/front/leftHand.png");
		GUIComponent rightHand = new GUIComponent(originX+58, originY+100, 84, 132, "resources/entities/rob/front/rightHand.png");
		GUIComponent head = new GUIComponent(originX+58, originY+100, 84, 132, "resources/entities/rob/front/head.png");
		GUIComponent scroll = new GUIComponent(originX+18, originY+30, 164, 58, "resources/gui/interface/foldLeft/scroll.png");
		GUIComponentText name = new GUIComponentText("Rob", originX+85, originY+34, 30, Color.black);
		GUIComponent statsWindow = new GUIComponent(originX+20, originY+250, 160, 64, "resources/gui/interface/foldLeft/statsWindow.png");

		GUIComponentSet rob = new GUIComponentSet(originX, originY, 200, 350, new GUIComponent[]{leftFoot,rightFoot,body,rightHand,leftHand,head,scroll,name,statsWindow});

		player.interfaceManager.leftMenu = rob;
		
		menuID = 0;
		
	}
	
	public void updateMenu(int menu) {

		if (menuID == menu) 
			return;
		
			if (player.interfaceManager.leftMenu != null) {
				
				GUI.removeGUIComponent(player.interfaceManager.leftMenu);
				player.interfaceManager.leftMenu = null;
				
			}
				
			
			switch (menu) {

			case 0:
					initialize();
					break;
			case 1: 

				menuID = menu;
				player.interfaceManager.leftMenu = new GUIFoldConstruction(originX, originY, player.interfaceManager.menuCoordinate.x, player.interfaceManager.menuCoordinate.y, PixelRealms.world.getPiece(player.interfaceManager.menuCoordinate.x, player.interfaceManager.menuCoordinate.y));
				break;
			
			}
			
			player.interfaceManager.menuCoordinate = null;
			player.interfaceManager.leftMenu = (GUIComponentSet) GUI.addGUIComponent(player.interfaceManager.leftMenu);
			player.interfaceManager.reorderLayers();
	}
	
	public void setY(int i) {
		originY = i;
		window.setY(originY);
		
		if (player.interfaceManager.leftMenu != null)
			player.interfaceManager.leftMenu.setY(originY);
		
	}
	
	public void setX(int i) {
		originX = i;
		window.setX(originX);
		if (player.interfaceManager.leftMenu != null)
			player.interfaceManager.leftMenu.setX(originX);

	}
	
	public void addToGUI() {
		originX = Display.getWidth()/2-225;
		originY = -305;
		window = new GUIComponent(originX, originY, 200, 350, "resources/gui/interface/foldLeft/window.png");
		
		initialize();
		
		window = (GUIComponent) GUI.addGUIComponent(window);
		
		player.interfaceManager.leftMenu = (GUIComponentSet) GUI.addGUIComponent(player.interfaceManager.leftMenu);

	}

	public void removeFromGUI() {

		GUI.removeGUIComponent(player.interfaceManager.leftMenu);
		player.interfaceManager.leftMenu = null;
		menuID = -1;
		GUI.removeGUIComponent(window);
	
	}

}
