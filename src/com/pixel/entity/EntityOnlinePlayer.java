package com.pixel.entity;

import com.pixel.body.BodyBiped;
import com.pixel.communication.CommunicationClient;
import com.pixel.communication.PlayerManager;
import com.pixel.communication.packet.PacketDamagePlayer;
import com.pixel.world.World;

public class EntityOnlinePlayer extends EntityHuman {

	public String username;
	public int userID;
	
	public EntityOnlinePlayer(int userID, String username, float x, float y) {
		super(x, y, .2F, .2F);
		
		this.username = username;
		body = new BodyBiped(this, "rob");
		PlayerManager.players.put(userID, this);
		this.userID = userID;

	}
	
	public void damage(World w, float damage, Entity damageSource, boolean fromServer) {
		CommunicationClient.addPacket(new PacketDamagePlayer(this, damage));
			
	}
	

}
