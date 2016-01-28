package com.pixel.communication.packet;

import com.pixel.body.BipedActionKickback;
import com.pixel.communication.PlayerManager;
import com.pixel.entity.EntityAlive;
import com.pixel.interior.ConstructionSiteManager;
import com.pixel.interior.InteriorWorld;
import com.pixel.interior.InteriorWorldManager;
import com.pixel.item.Item;
import com.pixel.item.ItemStack;
import com.pixel.piece.Piece;
import com.pixel.piece.PieceConstructionSiteInfo;
import com.pixel.stage.StageWorld;
import com.pixel.start.MainLoop;
import com.pixel.start.PixelLogger;
import com.pixel.start.PixelRealms;
import com.pixel.util.CoordinateKey;
import com.pixel.world.World;
import com.pixel.world.WorldManager;

public class PacketHandler {

	public static void processPlayerUpdate(PacketUpdatePlayer packet) {
		
		System.out.println(packet.username + " is at: " + packet.posX + ", " + packet.posY);
		
	}
	
	public static void processTileUpdate(PacketUpdateTile packet) {
		
		World.setTile(packet.posX, packet.posY, packet.tileID, packet.metadata);
		
	}

	public static void processPieceUpdate(PacketChangePiece packet) {
		
		WorldManager.getWorld().setPiece(packet.posX, packet.posY, packet.pieceID, packet.damage, packet.metadata, packet.buildingID, packet.worldID);
		
	}
	
	public static void processDamagePiece(PacketDamagePiece packet) {
		
		WorldManager.getWorld().getPieceObject(packet.posX, packet.posY).damage(packet.deltaDamage, WorldManager.getWorld());

	}
	
	public static void processUpdateInventoryContent(PacketUpdateInventoryContent packet) {
		if (packet.inventory == 0) {
			WorldManager.player.inventory.hotbar.serverSetContent(packet.x, packet.y, new ItemStack(Item.getItemForId(packet.itemID), packet.size));
		} else if (packet.inventory == 1) {
			WorldManager.player.inventory.inventoryLeft.serverSetContent(packet.x, packet.y, new ItemStack(Item.getItemForId(packet.itemID), packet.size));
		} else if (packet.inventory == 2) {
			WorldManager.player.inventory.inventoryRight.serverSetContent(packet.x, packet.y, new ItemStack(Item.getItemForId(packet.itemID), packet.size));
		} 
	}
	
	public static void processUpdateInteriorPiece(PacketUpdateInteriorPiece packet) {
		
		if (WorldManager.player.worldID == packet.worldID) {
			
			InteriorWorld w = InteriorWorldManager.interiors.get(packet.worldID);

			w.pieces.replace((packet.y * w.c) + packet.x, new Piece(packet.x, packet.y, packet.pieceID, packet.damage, packet.metadata, packet.lightID, false));
			
		}
		
	}

	public static void processUpdatePiece(PacketUpdatePiece packet) {

		if (Piece.info[WorldManager.getWorld().getPiece(packet.posX, packet.posY)] instanceof PieceConstructionSiteInfo) {

			if (packet.auxiliaryBooleans.get(0))
				ConstructionSiteManager.sites.get(new CoordinateKey(packet.posX, packet.posY)).addItem(packet.auxiliaryIntegers.get(0), packet.auxiliaryIntegers.get(1));
			
		}
		
	}

	public static void processEntityAnimation(PacketEntityAnimation packet) {

		switch (packet.animationID) {
		
		case 0:
			//punch
			if (packet.userID != -1 && packet.player) {

				if (packet.userID != PlayerManager.currentUserID) {

					if (!packet.remove)
						PlayerManager.getPlayer(packet.userID).addPunch();
					else
						PlayerManager.getPlayer(packet.userID).removePunch();

				}
			
			}
			break;
		case 1:
			//kickback
			int power = packet.auxiliaryIntegers.get(0);
			float dirX = packet.auxiliaryFloats.get(0);
			float dirY = packet.auxiliaryFloats.get(1);

			if (packet.userID != -1 && packet.player) {
				if (packet.userID == PlayerManager.currentUserID) {
					
					WorldManager.player.body.addAction(new BipedActionKickback(WorldManager.player.body, power, dirX, dirY));
					
				} else {
					
					PlayerManager.getPlayer(packet.userID).body.addAction(new BipedActionKickback(PlayerManager.getPlayer(packet.userID).body, power, dirX, dirY));
					
				}
			} else {
				
				EntityAlive e = ((EntityAlive) WorldManager.getWorld().getEntity(packet.entityID));
				
				e.body.addAction(new BipedActionKickback(e.body, power, dirX, dirY));
				
			}
			break;
		
		}
		
	
		
	}

	public static void processLoginStage(PacketLoginStage packet) {

		if (packet.stageID == 1) {
			
			StageWorld.finalizeLogin();
			
		}
				
//		if (packet.completion == 1F) {
//
//			switch(packet.stageID) {
//
//			case 0:
//				//World
//				StageWorld.worldLoaded = true;
//				break;
//			case 1:
//				//Player
//				StageWorld.playerLoaded = true;
//				break;
//			case 2:
//				//Inventory
//				PixelLogger.log("Inventory Loaded!");
//				StageWorld.inventoryLoaded = true;
//				break;
//			case 3: 
//				//Players
//				PixelLogger.log("Players Loaded!");
//				StageWorld.playersLoaded = true;
//				break;
//			case 4:
//				//Entities
//				PixelLogger.log("Entities Loaded!");
//				StageWorld.entitiesLoaded = true;
//				break;
//
//			}
//
//		}
		
	}

	public static void handleLoginRequest(PacketLoginRequest packet) {
		
		if (packet.serverID != -1) {
			System.out.println("B");

			((StageWorld) MainLoop.stage).continueLogin(packet);

		} else {
			
			PixelLogger.print("Login Denied by server");
			
		}
		
	}
	
}
