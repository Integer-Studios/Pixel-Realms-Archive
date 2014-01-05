package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pixel.entity.Entity;
import com.pixel.entity.EntityAlive;
import com.pixel.start.PixelRealms;
import com.pixel.world.World;

public class PacketUpdateLivingEntity extends Packet {

	public PacketUpdateLivingEntity() {
		
		this.id = 7;
		
	}
	
	@Override
	public void writeData(DataOutputStream output) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void readData(DataInputStream input) throws IOException {

		float posX = input.readFloat();
		float posY = input.readFloat();
		float health = input.readFloat();
		int id = input.readInt();

		int serverID = input.readInt();

		if (!World.entities.containsKey(serverID)) {
			Entity entity = Entity.getEntity(id);
			entity.serverID = serverID;
			World.propagateEntity(entity);
			
		}

		float curX = World.entities.get(serverID).getX();
		float curY = World.entities.get(serverID).getY();

		World.entities.get(serverID).setPreviousX(curX);
		World.entities.get(serverID).setPreviousY(curY);
		World.entities.get(serverID).setX(posX);
		World.entities.get(serverID).setY(posY);
		((EntityAlive)World.entities.get(serverID)).health = health;
		if (health <= 0) {
			
			((EntityAlive)World.entities.get(serverID)).kill(PixelRealms.world, null);

		}


	}

}
