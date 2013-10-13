package com.pixel.communication;

import java.util.HashMap;

import com.pixel.entity.*;
import com.pixel.world.World;

public class PlayerManager {

	public static String currentPlayer;
	public static int session;
	public static int currentUserID;
	public static boolean kicked;
	public static HashMap<Integer, EntityOnlinePlayer> players = new HashMap<Integer, EntityOnlinePlayer>();
	public static World world;
	
	public static void spawnPlayer(String username, int userID, float x, float y) {
		
		new EntityOnlinePlayer(userID, username, x, y);
		
	}
	
	public static void onPlayerLogout(String username, int userID) {
		
		players.remove(userID);
		
	}
	
	public static void despawnPlayerEntity(int userID) {
		
		players.remove(userID);
		
	}
	
}
