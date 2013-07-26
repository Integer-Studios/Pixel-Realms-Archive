package com.pixel.gui;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.pixel.entity.EntityPlayer;

public class GUIFoldLeft {
	
	public EntityPlayer player;
	public int originX, originY;
	public GUIComponent window, leftFoot, rightFoot, body, leftHand, rightHand, head, scroll, statsWindow;
	public GUIComponentText name;
	
	public GUIFoldLeft(EntityPlayer player) {
		this.player = player;
		originX = Display.getWidth()/2-225;//folds out to -425 folds in to - 225
		originY = -305;
		window = new GUIComponent(originX, originY, 200, 350, "resources/gui/interface/foldLeft/window.png");
		
		leftFoot = new GUIComponent(originX+58, originY+100, 84, 132, "resources/entities/rob/front/leftFoot.png");
		rightFoot = new GUIComponent(originX+58, originY+100, 84, 132, "resources/entities/rob/front/rightFoot.png");
		body = new GUIComponent(originX+58, originY+100, 84, 132, "resources/entities/rob/front/body.png");
		leftHand = new GUIComponent(originX+58, originY+100, 84, 132, "resources/entities/rob/front/leftHand.png");
		rightHand = new GUIComponent(originX+58, originY+100, 84, 132, "resources/entities/rob/front/rightHand.png");
		head = new GUIComponent(originX+58, originY+100, 84, 132, "resources/entities/rob/front/head.png");
		
		scroll = new GUIComponent(originX+18, originY+30, 164, 58, "resources/gui/interface/foldLeft/scroll.png");
		name = new GUIComponentText("Rob", originX+85, originY+34, 30, Color.black);
		
		statsWindow = new GUIComponent(originX+20, originY+250, 160, 64, "resources/gui/interface/foldLeft/statsWindow.png");

	}
	
	public void setY(int i) {
		originY = i;
		window.setY(originY);
		
		leftFoot.setY(originY+100);
		rightFoot.setY(originY+100);
		body.setY(originY+100);
		leftHand.setY(originY+100);
		rightHand.setY(originY+100);
		head.setY(originY+100);
		
		scroll.setY(originY+30);
		name.setY(originY+34);
		
		statsWindow.setY(originY+250);
	}
	
	public void setX(int i) {
		originX = i;
		window.setX(originX);
		
		leftFoot.setX(originX+58);
		rightFoot.setX(originX+58);
		body.setX(originX+58);
		leftHand.setX(originX+58);
		rightHand.setX(originX+58);
		head.setX(originX+58);
		
		scroll.setX(originX+18);
		name.setX(originX+85);
		
		statsWindow.setX(originX+20);

	}
	
	public void addToGUI() {
		originX = Display.getWidth()/2-225;
		originY = -305;
		window = new GUIComponent(originX, originY, 200, 350, "resources/gui/interface/foldLeft/window.png");
		
		leftFoot = new GUIComponent(originX+58, originY+100, 84, 132, "resources/entities/rob/front/leftFoot.png");
		rightFoot = new GUIComponent(originX+58, originY+100, 84, 132, "resources/entities/rob/front/rightFoot.png");
		body = new GUIComponent(originX+58, originY+100, 84, 132, "resources/entities/rob/front/body.png");
		leftHand = new GUIComponent(originX+58, originY+100, 84, 132, "resources/entities/rob/front/leftHand.png");
		rightHand = new GUIComponent(originX+58, originY+100, 84, 132, "resources/entities/rob/front/rightHand.png");
		head = new GUIComponent(originX+58, originY+100, 84, 132, "resources/entities/rob/front/head.png");

		scroll = new GUIComponent(originX+18, originY+30, 164, 58, "resources/gui/interface/foldLeft/scroll.png");
		name = new GUIComponentText("Rob", originX+85, originY+34, 30, Color.black);
		
		statsWindow = new GUIComponent(originX+20, originY+250, 160, 64, "resources/gui/interface/foldLeft/statsWindow.png");

		window = (GUIComponent) GUI.addGUIComponent(window);
		
		leftFoot = (GUIComponent) GUI.addGUIComponent(leftFoot);
		rightFoot = (GUIComponent) GUI.addGUIComponent(rightFoot);
		body = (GUIComponent) GUI.addGUIComponent(body);
		leftHand = (GUIComponent) GUI.addGUIComponent(leftHand);
		rightHand = (GUIComponent) GUI.addGUIComponent(rightHand);
		head = (GUIComponent) GUI.addGUIComponent(head);
		
		scroll = (GUIComponent) GUI.addGUIComponent(scroll);
		name = (GUIComponentText) GUI.addGUIComponent(name);
		
		statsWindow = (GUIComponent) GUI.addGUIComponent(statsWindow);

	}

	public void removeFromGUI() {
		GUI.removeGUIComponent(statsWindow);

		GUI.removeGUIComponent(name);
		GUI.removeGUIComponent(scroll);
		
		GUI.removeGUIComponent(head);
		GUI.removeGUIComponent(rightHand);
		GUI.removeGUIComponent(leftHand);
		GUI.removeGUIComponent(body);
		GUI.removeGUIComponent(rightFoot);
		GUI.removeGUIComponent(leftFoot);

		GUI.removeGUIComponent(window);
	}

}
