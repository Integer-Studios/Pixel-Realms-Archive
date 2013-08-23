package com.pixel.input;

import org.newdawn.slick.GameContainer;

import com.pixel.gui.GUIButton;
import com.pixel.gui.GUIComponent;
import com.pixel.gui.GUIInventorySlot;
import com.pixel.gui.GUITextBox;
import com.pixel.gui.GUIWindow;
import com.pixel.start.PixelRealms;
import com.pixel.world.World;


public class MouseClickListener {
	
	public static boolean left, right;
	public static boolean pressed = false;
	public static boolean rightClick = false;
	public static boolean onMouseUp = false, onMouseUpCheck = false;
	public static double posX, posY, lastClickedPosX, lastClickedPosY;
	
	public MouseClickListener() {

	}
	
	public static void update(GameContainer gc, int delta) {
		
		if (onMouseUp && !onMouseUpCheck) {
			onMouseUpCheck = true;
		} else {
			onMouseUp = false;
			onMouseUpCheck = false;
		}
		
	}

	public static boolean onMouseUp() {
		return onMouseUp;
	}
	
	public static boolean isPressed() {
		return pressed;
	}
	
	public static float getXWorldMousePosition() {
		float x = (float)posX - World.globalOffsetX;
		x = x/World.tileConstant;
		return x;
	}
	
	public static float getYWorldMousePosition() {
		float y = (float)posY - World.globalOffsetY;
		y = y/World.tileConstant;
		return y;
	}
	
	public static boolean getIsSelected(GUITextBox btn) {
		return (lastClickedPosX > btn.posX && lastClickedPosX < btn.posX+btn.width) && (lastClickedPosY > btn.y && lastClickedPosY < btn.y+btn.height);
	}
	
	public static boolean getIsPressedInButton(GUIButton btn) {
		return (pressed && (posX > btn.posX && posX < btn.posX+btn.width) && (posY > btn.y && posY < btn.y+btn.height));
	}
	
	public static boolean getIsPressedInInventorySlot(GUIInventorySlot btn) {
		return (pressed && (posX > btn.x && posX < btn.x+btn.width) && (posY > btn.y && posY < btn.y+btn.height));
	}
	
	public static boolean getIsPressedInComponent(GUIComponent btn) {
		return (pressed && (posX > btn.x && posX < btn.x+btn.width) && (posY > btn.y && posY < btn.y+btn.height));
	}
	
	public static boolean getIsPressedOutOfWindow(GUIWindow btn) {
		return (pressed && ((posX < btn.x || posX > btn.x+btn.width) || (posY < btn.y || posY > btn.y+btn.height)));
	}

	public static void mouseClicked(int button, int x, int y, int clickCount) {
		posX = x;
		posY = y;
		lastClickedPosX = posX;
		lastClickedPosY = posY;
		if(button == 1) {
			rightClick = true;
		} else {
			rightClick = false;
		}
	}

	public static void mousePressed(int button, int x, int y) {
		pressed = true;
		
		if (World.loaded) {
			
			PixelRealms.world.player.interfaceManager.onMousePressed(x, y, (button == 3));
			
		}
		
		posX = x;
		posY = y;
		lastClickedPosX = posX;
		lastClickedPosY = posY;
		onMouseUp = false;
		if(button == 1) {
			rightClick = true;
		} else {
			rightClick = false;
		}
		
	}
	
	public static void mouseReleased(int button, int x, int y) {

		if (World.loaded) {
			
			PixelRealms.world.player.interfaceManager.onMouseReleased(x, y, (button == 3));
			
		}
		
		pressed = false;
		posX = x;
		posY = y;
		onMouseUp = true;
		if(button == 1) {
			rightClick = true;
		} else {
			rightClick = false;
		}
		
	}


	public static void mouseDragged(int oldX, int oldY, int newX, int newY) {
		posX = newX;
		posY = newY;
		
	}

	public static void mouseMoved(int oldX, int oldY, int newX, int newY) {
		posX = newX;
		posY = newY;		
	}

	public static void mouseWheelMoved(int arg0) {
		// TODO Auto-generated method stub
		
	}

}
