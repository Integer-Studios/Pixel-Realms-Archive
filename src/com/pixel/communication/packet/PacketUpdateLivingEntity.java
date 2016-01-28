package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pixel.communication.PlayerManager;
import com.pixel.entity.Entity;
import com.pixel.entity.EntityAlive;
import com.pixel.item.Item;
import com.pixel.item.ItemStack;
import com.pixel.util.Metadata;
import com.pixel.world.WorldManager;

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

		int serverID = input.readInt();
		float posX = input.readFloat();
		float posY = input.readFloat();
		float health = input.readFloat();
		float satisfaction = input.readFloat();
		float energy = input.readFloat();
		int worldID = input.readInt();
		int id = input.readInt();

		System.out.println("B");

		int amt = input.readInt();
		Metadata m = new Metadata();
		for (int x = 0; x < amt; x ++) {
			
			int type = input.readInt();
			
			switch (type) {
			
			case 0:
				int objID = input.readInt();
				String s = Packet.readString(16, input);
				m.addString(objID, s);
				break;
			case 1:
				int objID1 = input.readInt();
				int i = input.readInt();
				m.addInteger(objID1, i);
				break;
			case 2:
				int objID2 = input.readInt();
				float f = input.readFloat();
				m.addFloat(objID2, f);
				break;
			case 3: 
				int objID3 = input.readInt();
				boolean b = input.readBoolean();
				m.addBoolean(objID3, b);
				break;
			
			}
			
		}	
		
		if (m.objects.get(0).b) {
			
			//Player
			int userID = m.objects.get(1).i;
			String username = m.objects.get(2).s;
			int itemID = -1;
			int itemAmount = -1;
			if (m.objects.containsKey(3)) {
				
				itemID = m.objects.get(3).i;
				itemAmount = m.objects.get(4).i;
			
			}
			
			if (userID == PlayerManager.currentUserID) {
				
				WorldManager.getWorld().playerReset = false;
				WorldManager.player.teleported = true;
				WorldManager.player.setPosition(posX,  posY);
				WorldManager.player.setHealth(health);
				WorldManager.player.setSatisfaction(satisfaction);
				WorldManager.player.setEnergy(energy);
				WorldManager.player.worldID = worldID;
				
			}
			
			if (PlayerManager.players.containsKey(userID) && WorldManager.getWorld().entities.containsKey(serverID)) {
				
				
				
			} else if (PlayerManager.players.containsKey(userID)) {
				
				PlayerManager.players.remove(userID);
				PlayerManager.spawnPlayer(username, userID, posX, posY, serverID);
				
			} else if (WorldManager.getWorld().entities.containsKey(serverID)){
				
				System.out.println("[Pixel Realms] Unable to load player (Entity ID exists)");
				
			} else {
				
				PlayerManager.spawnPlayer(username, userID, posX, posY, serverID);
				
			}
			
			if (m.objects.containsKey(3)) {
				
				PlayerManager.getPlayer(userID).setSelectedItem(new ItemStack(Item.getItemForId(itemID), itemAmount));
				
			}

//			int posYint = (int)posY;
//			WorldManager.getWorld()Chunk chunk = WorldManager.getWorld().getChunk(Math.round(posX), Math.round(posY));
//			PixelLogger.debug("PacketWorldManager.getWorld()Data Entities PosY:", posY, posYint);
//			//9.8, 9; 9.3, 9; 9.0, 9; 8.9, 8;
//			if ((posY -(float)posYint) < WorldManager.getWorld().pieceLayerOffset) {
//				ChunkEntityRenderGroup group = (ChunkEntityRenderGroup)chunk.renderGroups.get((2*posYint)+1);
//				group.objects.put(group.objects.size(), new ChunkRenderObject(chunk, 2, id));
//				group.reorder(chunk);
//				chunk.renderGroups.put((2*posYint)+1, group);
//			} else {
//				ChunkEntityRenderGroup group = (ChunkEntityRenderGroup)chunk.renderGroups.get((2*(posYint-1))+1);
//				group.objects.put(group.objects.size(), new ChunkRenderObject(chunk, 2, id));
//				group.reorder(chunk);
//				chunk.renderGroups.put((2*(posYint-1))+1, group);
//			}
			
			
		} else {
			
			float curX = WorldManager.getWorld().entities.get(serverID).getX();
			float curY = WorldManager.getWorld().entities.get(serverID).getY();
			WorldManager.getWorld().entities.get(serverID).setPreviousX(curX);
			WorldManager.getWorld().entities.get(serverID).setPreviousY(curY);
			
			if (health <= 0) {
				
				((EntityAlive)WorldManager.getWorld().entities.get(serverID)).kill(WorldManager.getWorld(), null);

			}
			
		}
		
		((EntityAlive)WorldManager.getWorld().entities.get(serverID)).setHealth(health);
		((EntityAlive)WorldManager.getWorld().entities.get(serverID)).setSatisfaction(satisfaction);
		((EntityAlive)WorldManager.getWorld().entities.get(serverID)).setEnergy(energy);
		WorldManager.getWorld().entities.get(serverID).setX(posX);
		WorldManager.getWorld().entities.get(serverID).setY(posY);
		WorldManager.getWorld().entities.get(serverID).metadata = m;
		
		if (!WorldManager.getWorld().entities.containsKey(serverID)) {
			Entity entity = Entity.getEntity(id);
			entity.serverID = serverID;
			WorldManager.getWorld().propagateEntity(entity);
			
		}


		
		


	}

}
