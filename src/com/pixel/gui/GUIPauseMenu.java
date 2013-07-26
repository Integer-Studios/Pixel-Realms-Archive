package com.pixel.gui;

import com.pixel.communication.CommunicationClient;
import com.pixel.communication.packet.PacketLogout;
import com.pixel.start.PixelRealms;

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
	
	public void tick(){
		if (shouldRemoveFromGUI) {
			shouldRemoveFromGUI = false;
			removeFromGUI();
			if (shouldOpenOptionsMenu) {
				shouldOpenOptionsMenu = false;
				PixelRealms.world.player.interfaceManager.optionsMenu.addToGUI();
			}
		}
		
		if (backToGame.onMouseUp) {
			shouldRemoveFromGUI = true;
		} 
		if (quitGame.onMouseUp) {
			CommunicationClient.addPacket(new PacketLogout());

			PixelRealms.world.panelWorld.disinstantiate();
//        	MainFrame.setPanel(new PanelMainMenu(frame));
		}
		if (options.onMouseUp) {
			shouldRemoveFromGUI = true;
			shouldOpenOptionsMenu = true;
		} 
	}

	public GUIButton backToGame, quitGame, options;
	private boolean shouldRemoveFromGUI;
	private boolean shouldOpenOptionsMenu;
}
