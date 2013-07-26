package com.pixel.piece;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.pixel.world.World;

public class PieceCabinBasic  extends PieceInfo{
	
	public PieceCabinBasic(String t) {
		super(t);
		setSize(0F, -1.2F, 4F, 2.2F);
		maxDamage = 1000;
	}

	public void paint(World w, Piece p, Graphics2D g2) {
		ImageIcon i = new ImageIcon(texture);
		Image img = i.getImage();	
		g2.drawImage(img, p.posX*World.tileConstant+World.globalOffsetX, (p.posY-2)*World.tileConstant+World.globalOffsetY, World.tileConstant*4, World.tileConstant*3, null);
	}

}
