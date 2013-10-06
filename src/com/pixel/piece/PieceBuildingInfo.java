package com.pixel.piece;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.building.Building;
import com.pixel.communication.CommunicationClient;
import com.pixel.communication.packet.PacketLoadInterior;
import com.pixel.entity.EntityPlayer;
import com.pixel.start.TextureLoader;
import com.pixel.util.CollisionBox;
import com.pixel.world.World;

public class PieceBuildingInfo extends PieceInfo {

	public Building building;
	
	public PieceBuildingInfo(int buildingID) {
		
		super("");
		building = new Building(buildingID);
		this.width = building.width;
		this.height = building.height;
		this.texture = Building.info.get(buildingID).texture;
		setSize(0F, 0F, 4F, 2.2F);
		maxDamage = 1000;
		
	}
	
	public void onCreated(Piece p) {

		
	}
	
	public void onPlayerCollided(World w, Piece p, EntityPlayer player) {

		if (CollisionBox.testBoxAgainstEntity(w.player, building.door.box, w, true) && !player.inside) {
			
			CommunicationClient.addPacket(new PacketLoadInterior(((PieceBuilding) p).worldID));
			
		}
		
	}
	
	public void render(GameContainer c, Graphics g, World w, Piece p) {

		if (image != null) {
			image.draw(p.posX*World.tileConstant+World.globalOffsetX, p.posY*World.tileConstant+World.globalOffsetY, World.tileConstant * building.width, World.tileConstant * building.height);
//			image.draw(building.door.box.getX()*World.tileConstant+World.globalOffsetX ,  building.door.box.getY()*World.tileConstant+World.globalOffsetY, building.door.width, building.door.height);

		} else {
			if (texture != null && texture.length() > 0) {
				image = TextureLoader.load(texture);
				image.draw(p.posX*World.tileConstant+World.globalOffsetX, p.posY*World.tileConstant+World.globalOffsetY, World.tileConstant * building.width, World.tileConstant * building.height);
			}
		}

	}
	
}
