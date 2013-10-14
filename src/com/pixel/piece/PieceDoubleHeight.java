package com.pixel.piece;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.start.TextureLoader;
import com.pixel.world.World;

public class PieceDoubleHeight extends PieceInfo{
	
	public PieceDoubleHeight(String t) {
		super(t);
		setSize(0.15F, 0.8F, 0.7F, 0.1F);
	}

	public void render(GameContainer c, Graphics g, World w, Piece p) {

		if (image != null) {
			image.draw(p.posX*World.tileConstant+World.globalOffsetX, p.posY*World.tileConstant-World.tileConstant+World.globalOffsetY, World.tileConstant, World.tileConstant*2);
		} else {
			if (texture != null && texture.length() > 0) {
				image = TextureLoader.load(texture);
				image.draw(p.posX*World.tileConstant+World.globalOffsetX, p.posY*World.tileConstant-World.tileConstant+World.globalOffsetY, World.tileConstant, World.tileConstant*2);
			}
		}

	}
	
}
