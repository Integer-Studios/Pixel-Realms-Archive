package com.pixel.communication.packet;

import com.pixel.item.Item;
import com.pixel.item.ItemStack;
import com.pixel.piece.PieceInfo;
import com.pixel.start.PixelRealms;
import com.pixel.world.World;

public class PacketHandler {

	public static void processPlayerUpdate(PacketUpdatePlayer packet) {
		
		System.out.println(packet.username + " is at: " + packet.posX + ", " + packet.posY);
		
	}
	
	public static void processTileUpdate(PacketUpdateTile packet) {
		
		PixelRealms.world.setTile(packet.posX, packet.posY, packet.tileID);
		
	}

	public static void processPieceUpdate(PacketUpdatePiece packet) {
		
		PixelRealms.world.setPiece(packet.posX, packet.posY, packet.pieceID, packet.damage, packet.metadata, packet.buildingID, packet.worldID);
		
	}
	
	public static void processDamagePiece(PacketDamagePiece packet) {
		
		PixelRealms.world.getPieceObject(packet.posX, packet.posY).damage(packet.deltaDamage, PixelRealms.world);

	}
	
	public static void processUpdateInventoryContent(PacketUpdateInventoryContent packet) {
		if (packet.inventory == 0) {
			PixelRealms.world.player.inventory.hotbar.serverSetContent(packet.x, packet.y, new ItemStack(Item.getItemForId(packet.itemID), packet.size));
		} else if (packet.inventory == 1) {
			PixelRealms.world.player.inventory.inventoryLeft.serverSetContent(packet.x, packet.y, new ItemStack(Item.getItemForId(packet.itemID), packet.size));
		} else if (packet.inventory == 2) {
			PixelRealms.world.player.inventory.inventoryRight.serverSetContent(packet.x, packet.y, new ItemStack(Item.getItemForId(packet.itemID), packet.size));
		} 
		if (World.loaded && PixelRealms.world.player.interfaceInitialized) {
			PieceInfo.pickupSound.setFramePosition(0);
			PieceInfo.pickupSound.start();
		}
	}
	
}
