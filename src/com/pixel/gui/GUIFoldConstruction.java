package com.pixel.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.communication.CommunicationClient;
import com.pixel.communication.packet.PacketUpdateConstructionSite;
import com.pixel.interior.Building;
import com.pixel.interior.ConstructionSite;
import com.pixel.interior.ConstructionSiteManager;
import com.pixel.item.Item;
import com.pixel.item.ItemStack;
import com.pixel.piece.PieceConstructionSiteInfo;
import com.pixel.piece.Piece;
import com.pixel.util.CoordinateKey;

public class GUIFoldConstruction extends GUIComponentSet {

	public int siteX, siteY, siteID, buildingID;
	
	public GUIFoldConstruction(int x, int y, int siteX, int siteY, int siteID) {
		super(x, y, 200, 350, new GUIComponent[]{
				new GUIComponentConstructionBar(x, y + 55, 120, 12, "construction"),
				new GUIComponentConstructionBar(x, y + 85, 120, 12, "construction"),
				new GUIComponentConstructionBar(x, y + 115, 120, 12, "construction"),
				new GUIComponentConstructionBar(x, y + 145, 120, 12, "construction"),
				new GUIComponentConstructionBar(x, y + 175, 120, 12, "construction"),
				new GUIComponentConstructionBar(x, y + 205, 120, 12, "construction"),
				new GUIComponentConstructionBar(x, y + 235, 120, 12, "construction"),
				new GUIComponentConstructionBar(x, y + 265, 120, 12, "construction"),
				new GUIComponentText("Cabin", x + 110, y + 15, 30, Color.black),
				new GUIComponent(x + 20, y + 15, 60, 45, Building.info.get(((PieceConstructionSiteInfo) Piece.info[siteID]).buildingID).texture + "0.png")

		});		// TODO Auto-generated constructor stub
		
		this.buildingID = ((PieceConstructionSiteInfo) Piece.info[siteID]).buildingID;
		this.siteID = siteID;
		this.siteX = siteX;
		this.siteY = siteY;
		ConstructionSite site = ConstructionSiteManager.sites.get(new CoordinateKey(siteX, siteY));

		int requirements = Building.info.get(buildingID).requirements.size();

		for (int i = 7; i >= 0; i --) {

			if (i > (requirements - 1)) {

				GUI.removeGUIComponent(components[i]);
				components[i] = new GUIComponentBlank();

			} else {
				
				int id = (Integer) Building.info.get(buildingID).requirements.keySet().toArray()[i];
				
				((GUIComponentConstructionBar) components[i]).setItem(id);
				float percent = Building.info.get(buildingID).requirements.get(id) - site.items.get(id);
				percent = percent / Building.info.get(buildingID).requirements.get(id);
				((GUIComponentConstructionBar) components[i]).setPercent(percent);

			}

		}
		
	}
	
	public void onMouseReleased(int x, int y, boolean right) {

		if (x > this.x && x < this.x + width && y > this.y && y < this.y + height) {

			ConstructionSite site = ConstructionSiteManager.sites.get(new CoordinateKey(siteX, siteY));

			ItemStack stack = GUIItemStackOnMouse.currentStackOnMouse.getItemStack();

			int left = site.addItem(stack.item.id, stack.size);
			
			if (left != -1) {
				
				if (left == 0) {

					GUIItemStackOnMouse.removeFromGUI();

				} else {
					
					GUIItemStackOnMouse.setCurrentItemStack(new ItemStack(Item.items[stack.item.id], left));
					
				}
				CommunicationClient.addPacket(new PacketUpdateConstructionSite(siteX, siteY, stack.item.id, stack.size));
				
			}
			

		}

	}

	public void render(GameContainer c, Graphics g) {
		super.render(c, g);
		int requirements = Building.info.get(buildingID).requirements.size();
		ConstructionSite site = ConstructionSiteManager.sites.get(new CoordinateKey(siteX, siteY));

		for (int i = 7; i >= 0; i --) {

			if (i <= (requirements - 1)) {

				int id = (Integer) Building.info.get(buildingID).requirements.keySet().toArray()[i];
				
				float percent = Building.info.get(buildingID).requirements.get(id) - site.items.get(id);
				percent = percent / Building.info.get(buildingID).requirements.get(id);
				((GUIComponentConstructionBar) components[i]).setPercent(percent);

			}

		}
		
//		CommunicationClient.addPacket(new PacketUpdatePiece(p).addAuxiliaryBoolean(true).addAuxiliaryIntegers(new int[]{9, 1}));
		
	}

}
