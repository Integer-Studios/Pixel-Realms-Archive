package com.pixel.piece;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.start.TextureLoader;
import com.pixel.world.World;
import com.pixel.world.WorldManager;

public class PieceDoubleWidth extends PieceInfo{
	
	public PieceDoubleWidth(String t) {
		super(t);
		setSize(0.1F, 0.8F, 1.4F, 0.1F);
	}
	
	public PieceDoubleWidth(String t, int i) {
		super(t);
		maxDamage = i;
		setSize(0.1F, 0.8F, 1.4F, 0.1F);
	}
	
	public void render(GameContainer c, Graphics g, World w, Piece p) {

		if (image != null) 
			image.draw(p.posX*World.tileConstant+WorldManager.getWorld().globalOffsetX, p.posY*World.tileConstant+WorldManager.getWorld().globalOffsetY, World.tileConstant*2, World.tileConstant);
		else {
			if (texture != null && texture.length() > 0) {
				image = TextureLoader.load(texture);
				image.draw(p.posX*World.tileConstant+WorldManager.getWorld().globalOffsetX, p.posY*World.tileConstant+WorldManager.getWorld().globalOffsetY, World.tileConstant*2, World.tileConstant);
			}
		}

	}

}
