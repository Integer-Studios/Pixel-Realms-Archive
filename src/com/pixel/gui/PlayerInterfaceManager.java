package com.pixel.gui;

import org.lwjgl.opengl.Display;

import com.pixel.entity.EntityPlayer;
import com.pixel.input.KeyboardListener;

public class PlayerInterfaceManager {
	
	public EntityPlayer player;
	
	public GUIBunnyCounter bunnyCounter;
	public GUIChat chat;
	public GUIHotbar hotbarWindow;
	public GUIHealthBar healthBar;
	public GUIHungerBar hungerBar;
	public GUICenterFold centerFold;
	public GUIFoldRight foldRight;
	public GUIFoldLeft foldLeft;
	public GUIPauseMenu pauseMenu;
	public GUIOptionsMenu optionsMenu;
	
	public boolean inputIntercepted;
	public boolean centerSliding, isCenterOpen;
	public boolean leftSliding, isLeftOpen;
	public boolean rightSliding, isRightOpen;
	public boolean menuSliding, isMenuOpen;
	
	public PlayerInterfaceManager(EntityPlayer player) {
		this.player = player;
		bunnyCounter = new GUIBunnyCounter();
		chat = new GUIChat();
		centerFold = new GUICenterFold(player);
		hotbarWindow = new GUIHotbar(player);
		healthBar = new GUIHealthBar(Display.getWidth()/2-225, 75, player);
		hungerBar = new GUIHungerBar(Display.getWidth()/2+60, 75, player);
		foldRight = new GUIFoldRight(player);
		foldLeft = new GUIFoldLeft(player);
		pauseMenu = new GUIPauseMenu();
		optionsMenu = new GUIOptionsMenu();
		
	}
	
	public void initializeInterface() {
		
		GUI.addGUIComponent(chat);
		GUI.addGUIComponent(bunnyCounter);
		foldRight.addToGUI();
		foldLeft.addToGUI();
		centerFold.addToGUI();
		hotbarWindow.addToGUI();
		healthBar = (GUIHealthBar) GUI.addGUIComponent(healthBar);
		hungerBar = (GUIHungerBar) GUI.addGUIComponent(hungerBar);
		
	}
	
	public void onMouseReleased(int x, int y, boolean right) {

		if (pauseMenu.isInGUI && !right) {
			
			pauseMenu.onMouseReleased(x, y);
			
		}
		
	}
	
	public void onMousePressed(int x, int y, boolean right) {
		
		if (pauseMenu.isInGUI && !right) {
			
			pauseMenu.onMousePressed(x, y);
			
		}
		
	}
	
	public void tick() {
		
		if (KeyboardListener.keyBindings.get("Inventory").onKeyUp) {
			if (isMenuOpen) {
				leftSliding = true;
				rightSliding = true;
			} else {
				centerSliding = true;
			}
			menuSliding = true;
		}
		
		if (KeyboardListener.keyBindings.get("CenterFold").onKeyUp) {
			if (!isCenterOpen) {
				centerSliding = true;
			}
		}
		
		if (KeyboardListener.keyBindings.get("FoldLeft").onKeyUp) {
			if (isCenterOpen) {
				leftSliding = true;
			}
		}
		
		if (KeyboardListener.keyBindings.get("FoldRight").onKeyUp) {
			if (isCenterOpen) {
				rightSliding = true;
			}
		}
		
		if (KeyboardListener.keyBindings.get("Menu").onKeyUp && !pauseMenu.isInGUI && !optionsMenu.isInGUI) {
			pauseMenu.addToGUI();
		} else if (KeyboardListener.keyBindings.get("Menu").onKeyUp && pauseMenu.isInGUI) {
			pauseMenu.removeFromGUI();
		} else if (KeyboardListener.keyBindings.get("Menu").onKeyUp && optionsMenu.isInGUI) {
			optionsMenu.removeFromGUI();
		}
		
		if (pauseMenu.isInGUI) 
			pauseMenu.tick();
		if (optionsMenu.isInGUI) 
			optionsMenu.tick();
		
		if (KeyboardListener.keyBindings.get("HideMenu").onKeyUp) {
			if (isCenterOpen && !isLeftOpen && !isRightOpen) {
				centerSliding = true;
			} else if (isCenterOpen && isLeftOpen && !isRightOpen) {
				menuSliding = true;
				leftSliding = true;
			} else if (isCenterOpen && isRightOpen && !isLeftOpen) {
				menuSliding = true;
				rightSliding = true;
			} else if (isMenuOpen) {
				menuSliding = true;
				leftSliding = true;
				rightSliding = true;
			}
		}
		
		if (centerSliding && !isCenterOpen) {
			if (centerFold.originY + 50 <= 70) {
				foldRight.setY(centerFold.originY + 50);
				foldLeft.setY(centerFold.originY + 50);
				centerFold.setY(centerFold.originY + 50);
				healthBar.setY(healthBar.y + 50);
				hungerBar.setY(hungerBar.y + 50);
			} else {
				foldRight.setY(80);
				foldLeft.setY(80);
				centerFold.setY(70);
				healthBar.setY(460);
				hungerBar.setY(460);
				isCenterOpen = true;
				centerSliding = false;
				if (menuSliding && !isMenuOpen) {
					leftSliding = true;
					rightSliding = true;
				}
			}
		} else if (centerSliding && isCenterOpen) {
			if (centerFold.originY - 50 >= -315) {
				foldRight.setY(centerFold.originY - 50);
				foldLeft.setY(centerFold.originY - 50);
				centerFold.setY(centerFold.originY - 50);
				healthBar.setY(healthBar.y - 50);
				hungerBar.setY(hungerBar.y - 50);
			} else {
				foldRight.setY(-305);
				foldLeft.setY(-305);
				centerFold.setY(-315);
				healthBar.setY(75);
				hungerBar.setY(75);
				isCenterOpen = false;
				centerSliding = false;
			}
		}
		
		if (leftSliding && !isLeftOpen) {
			if (foldLeft.originX - 50 >= Display.getWidth()/2-425) {
				foldLeft.setX(foldLeft.originX - 50);
			} else {
				foldLeft.setX(Display.getWidth()/2-425);
				isLeftOpen = true;
				leftSliding = false;
			}
		} else if (leftSliding && isLeftOpen) {
			if (foldLeft.originX + 50 <= Display.getWidth()/2-225) {
				foldLeft.setX(foldLeft.originX + 50);
			} else {
				foldLeft.setX(Display.getWidth()/2-225);
				isLeftOpen = false;
				leftSliding = false;
				if (menuSliding && isMenuOpen) {
					centerSliding = true;
				}
				if (menuSliding && isCenterOpen) {
					centerSliding = true;
				}
			}
		}
		
		if (rightSliding && !isRightOpen) {
			if (foldRight.originX + 50 <= Display.getWidth()/2+225) {
				foldRight.setX(foldRight.originX + 50);
			} else {
				foldRight.setX(Display.getWidth()/2+225);
				isRightOpen = true;
				rightSliding = false;
			}
		} else if (rightSliding && isRightOpen) {
			if (foldRight.originX - 50 >= Display.getWidth()/2+25) {
				foldRight.setX(foldRight.originX - 50);
			} else {
				foldRight.setX(Display.getWidth()/2+25);
				isRightOpen = false;
				rightSliding = false;
				if (menuSliding && isMenuOpen) {
					centerSliding = true;
				}
				if (menuSliding && isCenterOpen) {
					centerSliding = true;
				}
			}
		}
		
		if (menuSliding && !centerSliding && !leftSliding && !rightSliding) {
			if (isMenuOpen) {
				isMenuOpen = false;
			} else {
				isMenuOpen = true;
			}
			menuSliding = false;
		}
		
		if (isCenterOpen && isLeftOpen && isRightOpen) {
			isMenuOpen = true;
		} else {
			isMenuOpen = false;
		}
		
		
		hotbarWindow.tick();
		foldRight.tick();

		if (pauseMenu.isInGUI || optionsMenu.isInGUI || isCenterOpen) {
			
			inputIntercepted = true;
			
		} else {
			
			inputIntercepted = false;
			
		}
		
		
	}
	
	public boolean getIntercept() {
		
		return inputIntercepted;
				
	}

}
