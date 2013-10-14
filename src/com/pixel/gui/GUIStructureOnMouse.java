package com.pixel.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.building.Building;
import com.pixel.input.MouseClickListener;
import com.pixel.world.World;

public class GUIStructureOnMouse extends GUIComponent {

	public GUIStructureOnMouse(int x, int y, int buildingID) {
		super(x, y, Building.info.get(buildingID).width * World.tileConstant, Building.info.get(buildingID).height * World.tileConstant, Building.info.get(buildingID).texture + "0.png");

	}
	
	public void render(GameContainer c, Graphics g) {
		image.setAlpha(0.5F);
		setX((int) MouseClickListener.posX - 8/* - width/2*/);
		setY((int) MouseClickListener.posY - height + 3/*- height/2*/);
		super.render(c, g);
		
	}

}
