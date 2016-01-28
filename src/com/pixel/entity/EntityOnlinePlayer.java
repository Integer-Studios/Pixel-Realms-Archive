package com.pixel.entity;

import com.pixel.body.BipedActionPunching;
import com.pixel.body.BodyBiped;
import com.pixel.communication.CommunicationClient;
import com.pixel.communication.PlayerManager;
import com.pixel.communication.packet.PacketDamagePlayer;
import com.pixel.world.World;
import com.pixel.world.WorldManager;

public class EntityOnlinePlayer extends EntityHuman {

	public String username;
	public int userID;
	public int punchingIndex;
	
	public EntityOnlinePlayer(int userID, String username, float x, float y, int serverID) {
		super(x, y, .2F, .2F);
		
		this.username = username;
		body = new BodyBiped(this, "rob");
		WorldManager.getWorld().entities.put(serverID, this);
		PlayerManager.players.put(userID, serverID);
		this.userID = userID;

	}
	
	public void damage(World w, float damage, Entity damageSource, boolean fromServer) {
		CommunicationClient.addPacket(new PacketDamagePlayer(this, damage));
			
	}

	public void addPunch() {
		// TODO Auto-generated method stub
		punchingIndex = body.addAction(new BipedActionPunching(body));
		
	}
	
	public void removePunch() {
		// TODO Auto-generated method stub
		body.removeAction(punchingIndex);
		
	}
	

}
