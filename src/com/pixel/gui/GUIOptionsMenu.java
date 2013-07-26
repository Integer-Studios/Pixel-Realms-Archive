package com.pixel.gui;

import com.pixel.start.PixelRealms;

public class GUIOptionsMenu extends GUIInGameMenu {

	public GUIOptionsMenu() {
		super();
		
		forwardBtn = new GUIKeyBindingButton(originX + 40, originY + 40, "Up");
		forwardLabel = new GUIComponentText("Forward", originX + 120, originY + 50, 35);
		
		backBtn = new GUIKeyBindingButton(originX + 40, originY + 120, "Down");
		backLabel = new GUIComponentText("Backward", originX + 120, originY + 130, 35);
		
		leftBtn = new GUIKeyBindingButton(originX + 40, originY + 200, "Left");
		leftLabel = new GUIComponentText("Left", originX + 120, originY + 210, 35);
		
		rightBtn = new GUIKeyBindingButton(originX + 40, originY + 280, "Right");
		rightLabel = new GUIComponentText("Right", originX + 120, originY + 290, 35);
		
		jumpBtn = new GUIKeyBindingButton(originX + 260, originY + 40, "Jump");
		jumpLabel = new GUIComponentText("Jump", originX + 340, originY + 50, 35);
		
		invBtn = new GUIKeyBindingButton(originX + 260, originY + 120, "Inventory");
		invLabel = new GUIComponentText("Inventory", originX + 340, originY + 130, 35);
		
		backButton = new GUIButton(originX + 420, originY + 290, 140, 70, new GUIComponentText("Back", 45, 8, 35));
	}
	
	public void addToGUI() {
		super.addToGUI();
		forwardBtn = (GUIKeyBindingButton) GUI.addGUIComponent(forwardBtn);
		forwardLabel = (GUIComponentText) GUI.addGUIComponent(forwardLabel);

		backBtn = (GUIKeyBindingButton) GUI.addGUIComponent(backBtn);
		backLabel = (GUIComponentText) GUI.addGUIComponent(backLabel);
		
		leftBtn = (GUIKeyBindingButton) GUI.addGUIComponent(leftBtn);
		leftLabel = (GUIComponentText) GUI.addGUIComponent(leftLabel);

		rightBtn = (GUIKeyBindingButton) GUI.addGUIComponent(rightBtn);
		rightLabel = (GUIComponentText) GUI.addGUIComponent(rightLabel);
		
		jumpBtn = (GUIKeyBindingButton) GUI.addGUIComponent(jumpBtn);
		jumpLabel = (GUIComponentText) GUI.addGUIComponent(jumpLabel);
		
		invBtn = (GUIKeyBindingButton) GUI.addGUIComponent(invBtn);
		invLabel = (GUIComponentText) GUI.addGUIComponent(invLabel);
		
		backButton = (GUIButton) GUI.addGUIComponent(backButton);
	}
	
	public void removeFromGUI() {
		GUI.removeGUIComponent(backButton);
		
		GUI.removeGUIComponent(invLabel);
		GUI.removeGUIComponent(invBtn);
		
		GUI.removeGUIComponent(jumpLabel);
		GUI.removeGUIComponent(jumpBtn);
		
		GUI.removeGUIComponent(rightLabel);
		GUI.removeGUIComponent(rightBtn);
		
		GUI.removeGUIComponent(leftLabel);
		GUI.removeGUIComponent(leftBtn);
		
		GUI.removeGUIComponent(backLabel);
		GUI.removeGUIComponent(backBtn);
		
		GUI.removeGUIComponent(forwardLabel);
		GUI.removeGUIComponent(forwardBtn);
		super.removeFromGUI();
	}
	
	public void tick() {
		
		if (shouldGoBack) {
			shouldGoBack = false;
			removeFromGUI();
			PixelRealms.world.player.interfaceManager.pauseMenu.addToGUI();
		}
		
		if (backButton.onMouseUp) {
			shouldGoBack = true;
		}
		
	}
	
	public GUIKeyBindingButton forwardBtn;
	public GUIComponentText forwardLabel;
	
	public GUIKeyBindingButton backBtn;
	public GUIComponentText backLabel;
	
	public GUIKeyBindingButton leftBtn;
	public GUIComponentText leftLabel;
	
	public GUIKeyBindingButton rightBtn;
	public GUIComponentText rightLabel;
	
	public GUIKeyBindingButton jumpBtn;
	public GUIComponentText jumpLabel;

	public GUIKeyBindingButton invBtn;
	public GUIComponentText invLabel;
	
	public GUIButton backButton;
	
	
	private boolean shouldGoBack;
}
