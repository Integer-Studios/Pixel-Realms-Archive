package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;

import com.pixel.gui.GUIFoldConstruction;
import com.pixel.interior.Building;
import com.pixel.interior.ConstructionSite;
import com.pixel.interior.ConstructionSiteManager;
import com.pixel.start.PixelRealms;
import com.pixel.util.CoordinateKey;

public class PacketUpdateConstructionSite extends Packet {

	public int siteX, siteY, buildingID, itemID, amount;
	public HashMap<Integer, Integer> resources = new HashMap<Integer, Integer>();
	
	public PacketUpdateConstructionSite() {
		
		this.id = 21;
		
	}
	
	public PacketUpdateConstructionSite(int siteX, int siteY, int itemID, int amount) {
		
		this.id = 21;
		this.siteX = siteX;
		this.siteY = siteY;
		this.itemID = itemID; 
		this.amount = amount;

		
	}
	
	@Override
	public void writeData(DataOutputStream output) throws IOException {

		output.writeInt(siteX);
		output.writeInt(siteY);
		output.writeInt(itemID);
		output.writeInt(amount);

	}

	@Override
	public void readData(DataInputStream input) throws IOException {

		this.siteX = input.readInt();
		this.siteY = input.readInt();
		this.buildingID = input.readInt();
		
		if (!ConstructionSiteManager.sites.containsKey(new CoordinateKey(siteX, siteY))) 
			ConstructionSiteManager.addSite(new ConstructionSite(siteX, siteY, buildingID));
		
		int itemAmount = input.readInt();
		
		if (itemAmount == -1) {
			
			if (PixelRealms.world.player.interfaceManager.foldLeft.menuID == 1) {
				
				GUIFoldConstruction fold = (GUIFoldConstruction) PixelRealms.world.player.interfaceManager.leftMenu;
				
				if (fold.siteX == siteX && fold.siteY == siteY) {
					
					PixelRealms.world.player.interfaceManager.foldLeft.updateMenu(0);
					
				}
				
			}
			
			ConstructionSiteManager.sites.remove(new CoordinateKey(siteX, siteY));
			return;
		}
		
		for (int x = 0; x < itemAmount; x ++) {
			int id = input.readInt();
			int amount = input.readInt();
//			amount = Building.info.get(buildingID).requirements.get(id) - amount;
			ConstructionSiteManager.sites.get(new CoordinateKey(siteX, siteY)).setItem(id, amount);
			
			
		}
		
	}

}
