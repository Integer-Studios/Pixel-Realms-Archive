package com.pixel.communication.packet;

import com.pixel.interior.ConstructionSiteManager;
import com.pixel.interior.InteriorWorld;
import com.pixel.interior.InteriorWorldManager;
import com.pixel.item.Item;
import com.pixel.item.ItemStack;
import com.pixel.piece.Piece;
import com.pixel.piece.PieceConstructionSiteInfo;
import com.pixel.piece.PieceInfo;
import com.pixel.start.PixelRealms;
import com.pixel.util.CoordinateKey;
import com.pixel.world.World;

public class PacketHandler {

	public static void processPlayerUpdate(PacketUpdatePlayer packet) {
		
		System.out.println(packet.username + " is at: " + packet.posX + ", " + packet.posY);
		
	}
	
	public static void processTileUpdate(PacketUpdateTile packet) {
		
		PixelRealms.world.setTile(packet.posX, packet.posY, packet.tileID, packet.metadata);
		
	}

	public static void processPieceUpdate(PacketChangePiece packet) {
		
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
	
	public static void processUpdateInteriorPiece(PacketUpdateInteriorPiece packet) {
		
		if (PixelRealms.world.player.worldID == packet.worldID) {
			
			InteriorWorld w = InteriorWorldManager.interiors.get(packet.worldID);

			w.pieces.replace((packet.y * w.c) + packet.x, new Piece(packet.x, packet.y, packet.pieceID, packet.damage, packet.metadata, false));
			
		}
		
	}

	public static void processUpdatePiece(PacketUpdatePiece packet) {

		if (Piece.info[PixelRealms.world.getPiece(packet.posX, packet.posY)] instanceof PieceConstructionSiteInfo) {

			if (packet.auxiliaryBooleans.get(0))
				ConstructionSiteManager.sites.get(new CoordinateKey(packet.posX, packet.posY)).addItem(packet.auxiliaryIntegers.get(0), packet.auxiliaryIntegers.get(1));
			
		}
		
	}
	
}
