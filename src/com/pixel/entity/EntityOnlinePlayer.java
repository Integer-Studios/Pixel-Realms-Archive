package com.pixel.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.communication.CommunicationClient;
import com.pixel.communication.PlayerManager;
import com.pixel.communication.packet.PacketDamagePlayer;
import com.pixel.player.RelativeBody;
import com.pixel.world.World;

public class EntityOnlinePlayer extends EntityHuman {

	public String username;
	public int userID;
	
	public EntityOnlinePlayer(int userID, String username, float x, float y) {
		super(x, y, .2F, .2F);
		
		this.username = username;
		body = new RelativeBody(this);
		PlayerManager.players.put(userID, this);
		this.userID = userID;

	}
	
	public void tick(World w) {
		
		if (body != null) {
			body.tick(w);
		}
		prevPosX = posX;
		prevPosY = posY;
		
		super.tick(w);
		
	}
	
	public void damage(World w, float damage, Entity damageSource) {
		CommunicationClient.addPacket(new PacketDamagePlayer(this, damage));
			
	}
	
	public void render(GameContainer c, Graphics g, World w) {
		super.render(c, g, w);
		if (body != null)
			body.render(c, g, w);
	}
	

}
