package com.pixel.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.input.MouseClickListener;
import com.pixel.interior.Building;
import com.pixel.world.World;
import com.pixel.world.WorldManager;

public class GUIStructureOnMouse extends GUIComponent {

	public int buildingID;
	
	public GUIStructureOnMouse(int x, int y, int buildingID) {
		super(x, y, Building.info.get(buildingID).width * World.tileConstant, Building.info.get(buildingID).height * World.tileConstant, Building.info.get(buildingID).texture + "0.png");

		this.buildingID = buildingID;
		
	}
	
	public void render(GameContainer c, Graphics g) {
		if (image == null) {
			super.render(c,g);
			return;
		}
		image.setAlpha(0.5F);
		int pieceX = (int) ((MouseClickListener.posX - WorldManager.getWorld().globalOffsetX) / World.tileConstant);
		int pieceY = (int) ((MouseClickListener.posY - WorldManager.getWorld().globalOffsetY) / World.tileConstant) -  Building.info.get(buildingID).height;

		setX(pieceX * World.tileConstant + WorldManager.getWorld().globalOffsetX);
		setY(pieceY * World.tileConstant + WorldManager.getWorld().globalOffsetY);
		super.render(c, g);
		
	}

}
