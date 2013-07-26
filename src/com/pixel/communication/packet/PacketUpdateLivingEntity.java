package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pixel.entity.Entity;
import com.pixel.entity.EntityAlive;
import com.pixel.entity.EntityAnimal;
import com.pixel.entity.ai.Herd;
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
		
		if (input.readBoolean()) {
			
			int herdID = input.readInt();
			int serverID = input.readInt();
			
			if (!World.herds.containsKey(herdID)) {
				
				new Herd((EntityAnimal) Entity.getEntity(id), herdID, serverID);
				World.herds.get(herdID).entities.get(serverID).setPreviousX(posX);
				World.herds.get(herdID).entities.get(serverID).setPreviousY(posY);

			} else if (!World.herds.get(herdID).entities.containsKey(serverID)) {
				
				World.herds.get(herdID).entities.put(serverID, (EntityAnimal) Entity.getEntity(id));
				World.herds.get(herdID).entities.get(serverID).serverID = serverID;
				World.herds.get(herdID).entities.get(serverID).setPreviousX(posX);
				World.herds.get(herdID).entities.get(serverID).setPreviousY(posY);
				
			}
			
			EntityAnimal animal = World.herds.get(herdID).entities.get(serverID);
			animal.herdID = herdID;
			animal.serverID = serverID;
			float curX = animal.getX();
			float curY = animal.getY();
			animal.setPreviousX(curX);
			animal.setPreviousY(curY);
			animal.setPosition(posX, posY);
			animal.health = health;
			
			if (animal.health <= 0) {
				
				animal.kill(PixelRealms.world, null);
				
			}
			
		} else {

			int serverID = input.readInt();

			if (!World.entities.containsKey(serverID)) 
				return;

			float curX = World.entities.get(serverID).getX();
			float curY = World.entities.get(serverID).getY();

			World.entities.get(serverID).setPreviousX(curX);
			World.entities.get(serverID).setPreviousY(curY);
			World.entities.get(serverID).setX(posX);
			World.entities.get(serverID).setY(posY);
			World.entities.get(serverID).health = health;
			if (health <= 0) {

				((EntityAlive)World.entities.get(serverID)).kill(PixelRealms.world, null);

			}

		}


	}

}
