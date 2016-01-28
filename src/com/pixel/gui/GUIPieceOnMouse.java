package com.pixel.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.input.MouseClickListener;
import com.pixel.piece.Piece;
import com.pixel.world.World;
import com.pixel.world.WorldManager;

public class GUIPieceOnMouse extends GUIComponent {

	public GUIPieceOnMouse(int x, int y, int pieceID) {
		super(x, y, World.tileConstant, World.tileConstant, Piece.info[pieceID].texture);
		// TODO Auto-generated constructor stub
	}
	
	public void render(GameContainer c, Graphics g) {
		
		if (image == null) {
			
			super.render(c, g);
			return;
			
		}

		int pieceX = (int) ((MouseClickListener.posX - WorldManager.getWorld().globalOffsetX) / World.tileConstant);
		int pieceY = (int) ((MouseClickListener.posY - WorldManager.getWorld().globalOffsetY) / World.tileConstant);

		image.setAlpha(0.4F);
		setX(pieceX * World.tileConstant + WorldManager.getWorld().globalOffsetX);
		setY(pieceY * World.tileConstant + WorldManager.getWorld().globalOffsetY);
		super.render(c, g);

	}

}
