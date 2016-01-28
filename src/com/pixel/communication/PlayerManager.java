package com.pixel.communication;

import java.util.HashMap;

import com.pixel.entity.*;
import com.pixel.start.PixelRealms;
import com.pixel.world.World;
import com.pixel.world.WorldManager;

public class PlayerManager {

	public static String currentPlayer;
	public static int session;
	public static int currentUserID;
	public static boolean kicked;
	public static HashMap<Integer, Integer> players = new HashMap<Integer, Integer>();
	public static World world;
	public static boolean playerLoggedIn;
	
	public static void spawnPlayer(String username, int userID, float x, float y, int serverID) {
		
		new EntityOnlinePlayer(userID, username, x, y, serverID);
		
	}
	
	public static EntityOnlinePlayer getPlayer(int userID) {
		
		return (EntityOnlinePlayer) WorldManager.getWorld().entities.get(players.get(userID));
		
	}
	
	public static void onPlayerLogout(String username, int userID) {
		
		players.remove(userID);
		
	}
	
	public static void despawnPlayerEntity(int userID) {
		
		players.remove(userID);
		
	}

	public static void updateVisible() {

		for (Integer i : players.values()) {
			 
			EntityOnlinePlayer p = (EntityOnlinePlayer) WorldManager.getWorld().getEntity(i);
			
			if (p.worldID != WorldManager.player.worldID) {
				System.out.println(p.username + " adsad " + p.worldID + " " + WorldManager.player.worldID);
				players.remove(p.userID);
			}
			
		}
		
	}
	
}
