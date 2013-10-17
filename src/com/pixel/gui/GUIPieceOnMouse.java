package com.pixel.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.input.MouseClickListener;
import com.pixel.piece.Piece;
import com.pixel.world.World;

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

		int pieceX = (int) ((MouseClickListener.posX - World.globalOffsetX) / World.tileConstant);
		int pieceY = (int) ((MouseClickListener.posY - World.globalOffsetY) / World.tileConstant);

		image.setAlpha(0.4F);
		setX(pieceX * World.tileConstant + World.globalOffsetX);
		setY(pieceY * World.tileConstant + World.globalOffsetY);
		super.render(c, g);

	}

}
