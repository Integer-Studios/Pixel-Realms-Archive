package com.pixel.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.body.RelativeBody;
import com.pixel.communication.CommunicationClient;
import com.pixel.communication.packet.PacketDamageEntity;
import com.pixel.sound.PixelEffect;
import com.pixel.sound.PixelSoundManager;
import com.pixel.world.World;

public class EntityAlive extends Entity {
	
	public float satisfaction;
	public int bodyID = -1;
	public float speed;
	public float health = 10.0F;
	
	public RelativeBody body;

	public EntityAlive(float width, float height) {
		super(width, height);
		body = null;
	}
	
	public EntityAlive(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	public void tick(World w) {
		if (body != null) {
			body.tick(w);
		}
		super.tick(w);
	}
	
	public void render(GameContainer c, Graphics g, World w) {
		if (body != null)
			body.render(c, g, w);
		super.render(c, g, w);
	}
	
	public void setMaxHealth(float health) {
		
		this.health = health;
		
	}
	
	public void setHealth(float health) {
		
		this.health = health;
		
	}
	
	public void setBodyPiece(int i) {
		bodyID = i;
	}
	
	public float getHealth() {
		
		return health;
		
	}
	
	public float getSatisfaction() {
		return satisfaction;
	}
	
	
	public void setSatisfaction(float satisfaction) {

		this.satisfaction = satisfaction;
		
	}
	
	public void damage(World w, float damage, Entity damageSource, boolean fromServer) {
		if (!fromServer) {
			PixelSoundManager.createEffect(PixelEffect.PUNCHING_DEFAULT).start();
			CommunicationClient.addPacket(new PacketDamageEntity(this, damage));
		} else {
			System.out.println("damaged: " + damage);

			this.health -= damage;
			System.out.println("health: " + health);
			if (this.health <= 0.0F) {
				this.kill(w, damageSource);
				
			}
		}
	}
	
	public void update(EntityAlive entity) {
		
		this.posX = entity.getX();
		this.posY = entity.getY();
		this.health = entity.health;
		
	}
	
	public void kill(World w, Entity damageSource) {
		PixelSoundManager.createEffect(PixelEffect.BUNNY_DEATH).start();
		health = 0;
	}
	
	public void spawnBody(World w) {

		int newX = Math.round(posX);
		int newY = Math.round(posY);
		
		if (bodyID != -1) {
			
			w.setPiece(newX, newY, bodyID);
			
		}
		
	}
	
}
