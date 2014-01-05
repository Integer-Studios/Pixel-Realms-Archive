package com.pixel.entity;

import javax.sound.sampled.Clip;


import com.pixel.communication.CommunicationClient;
import com.pixel.communication.SaveBunny;
import com.pixel.communication.packet.PacketDamageEntity;
import com.pixel.sound.Sound;
import com.pixel.world.World;

public class EntityBunny extends EntityAnimal {
	
	public EntityBunny() {
		super(.7F, .2F);
		this.id = 1;
		this.setMaxHealth(3.0F);
		this.setBodyPiece(15);
	}
	
	public EntityBunny(float x, float y) {
		super(x, y, .7F, .2F);
		this.id = 1;
		this.setMaxHealth(3.0F);
		this.setBodyPiece(15);
	}
	
	public void damage(World w, float damage, Entity damageSource, boolean fromServer) {
		super.damage(w, damage, damageSource, fromServer);

		CommunicationClient.addPacket(new PacketDamageEntity(this, damage));

		Clip hurt = Sound.getEffect(Sound.Effect.BUNNY_DEATH);
		hurt.setFramePosition(0);
		hurt.start();
		
	}
	
	public void kill(World w, Entity damageSource) {
		super.kill(w,damageSource);
		w.player.onBunnyDeath();
		new SaveBunny().start();
		
	}
	
	public void tick(World w) {
//		for (int x = 0; x < WorldChunkManager.entitiesLoopArray.size(); x ++) {
//			CollisionBox.testEntities(WorldChunkManager.entitiesLoopArray.get(WorldChunkManager.entitiesLoopArray.keySet().toArray()[x]), this, w);
//		}
		super.tick(w);
	}
	
}
