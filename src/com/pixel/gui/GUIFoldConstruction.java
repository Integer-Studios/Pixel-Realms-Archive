package com.pixel.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.communication.CommunicationClient;
import com.pixel.communication.packet.PacketUpdatePiece;
import com.pixel.piece.Piece;
import com.pixel.start.PixelRealms;

public class GUIFoldConstruction extends GUIComponentSet {

	public int siteX, siteY;
	
	public GUIFoldConstruction(int x, int y, int siteX, int siteY) {
		super(x, y, 200, 350, new GUIComponent[]{
				new GUIComponentConstructionBar(x, y + 60, 120, 12, "construction"),
				new GUIComponentConstructionBar(x, y + 90, 120, 12, "construction"),
				new GUIComponentConstructionBar(x, y + 120, 120, 12, "construction"),
				new GUIComponentConstructionBar(x, y + 150, 120, 12, "construction"),
				new GUIComponentConstructionBar(x, y + 180, 120, 12, "construction"),
				new GUIComponentConstructionBar(x, y + 210, 120, 12, "construction"),
				new GUIComponentConstructionBar(x, y + 240, 120, 12, "construction"),
				new GUIComponentConstructionBar(x, y + 270, 120, 12, "construction"),
				new GUIComponentText("", x + 90, y + 5, 40, Color.black)

		});		// TODO Auto-generated constructor stub
		
		this.siteX = siteX;
		this.siteY = siteY;
		
	}
	
	public void render(GameContainer c, Graphics g) {
		super.render(c, g);
		
		Piece p = PixelRealms.world.getPieceObject(siteX, siteY);
		
		CommunicationClient.addPacket(new PacketUpdatePiece(p).addAuxiliaryBoolean(true).addAuxiliaryIntegers(new int[]{1, 1}));
		
	}

}
