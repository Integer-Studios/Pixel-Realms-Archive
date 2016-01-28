package com.pixel.gui;

import com.pixel.communication.CommunicationClient;
import com.pixel.communication.packet.PacketLogout;
import com.pixel.start.MainLoop;
import com.pixel.start.PixelRealms;
import com.pixel.world.WorldManager;

public class GUIPauseMenu extends GUIInGameMenu {
	
	public GUIPauseMenu() {
		super();
		
		backToGame = new GUIButton(originX+150, originY+65, 300, 70, new GUIComponentText("Back To Game", 70, 8, 35));
		quitGame = new GUIButton(originX+150, originY+165, 300, 70, new GUIComponentText("Quit to Main Menu", 45, 8, 35));
		options = new GUIButton(originX+150, originY+265, 300, 70, new GUIComponentText("Options", 115, 8, 35));
	}
	
	public void addToGUI() {
		super.addToGUI();
		backToGame = (GUIButton) GUI.addGUIComponent(backToGame);
		quitGame = (GUIButton) GUI.addGUIComponent(quitGame);
		options = (GUIButton) GUI.addGUIComponent(options);
	}
	
	public void removeFromGUI() {
		GUI.removeGUIComponent(options);
		GUI.removeGUIComponent(quitGame);
		GUI.removeGUIComponent(backToGame);

		super.removeFromGUI();
	}
	
	public void onMouseReleased(int posX, int posY) {
		
		if (quitGame.pressed) {
			quitGame.setPressed(false);
			CommunicationClient.addPacket(new PacketLogout());

//			WorldManager.getWorld().panelWorld.disinstantiate();
			MainLoop.setPanel(1);
		}
		if (backToGame.pressed) {
			backToGame.setPressed(false);
			shouldRemoveFromGUI = true;
		}
		if (options.pressed) {
			options.setPressed(false);
			shouldRemoveFromGUI = true;
			shouldOpenOptionsMenu = true;
		}
		
	}
	
	public void onMousePressed(int posX, int posY) {
		
		GUIButton btn = quitGame;
		if ((posX > btn.posX && posX < btn.posX+btn.width) && (posY > btn.y && posY < btn.y+btn.height)) {
			quitGame.setPressed(true);
		}
		btn = backToGame;
		if ((posX > btn.posX && posX < btn.posX+btn.width) && (posY > btn.y && posY < btn.y+btn.height)) {
			backToGame.setPressed(true);
		}
		btn = options;
		if ((posX > btn.posX && posX < btn.posX+btn.width) && (posY > btn.y && posY < btn.y+btn.height)) {
			options.setPressed(true);
		}
		
	}
	
	public void tick(){
		if (shouldRemoveFromGUI) {
			shouldRemoveFromGUI = false;
			removeFromGUI();
			if (shouldOpenOptionsMenu) {
				shouldOpenOptionsMenu = false;
				WorldManager.player.interfaceManager.optionsMenu.addToGUI();
			}
		}
		
	}

	public GUIButton backToGame, quitGame, options;
	private boolean shouldRemoveFromGUI;
	private boolean shouldOpenOptionsMenu;
}
