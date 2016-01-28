package com.pixel.piece;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.entity.EntityPlayer;
import com.pixel.interior.Building;
import com.pixel.interior.InteriorWorldManager;
import com.pixel.start.TextureLoader;
import com.pixel.util.CollisionBox;
import com.pixel.world.World;
import com.pixel.world.WorldManager;

public class PieceBuildingInfo extends PieceInfo {

	public Building building;
	
	public PieceBuildingInfo(int buildingID) {
		
		super("");
		building = new Building(buildingID);
		this.texture = Building.info.get(buildingID).texture;
		maxDamage = 1000;
		
	}
	
	public void onCreated(Piece p) {


	}

	public void onPlayerCollided(World w, Piece p, EntityPlayer player) {
		Building b = ((PieceBuilding) p).building;

		if (CollisionBox.testBoxAgainstEntity(WorldManager.player, InteriorWorldManager.doors.get(b.worldID).box, w, true) && !player.inside) {
			player.door = true;
			player.currentlySelectedInterior = ((PieceBuilding) p).building.worldID;
			player.doorX = player.getX();
			player.doorY = player.getY();

		}

	}

	public void render(GameContainer c, Graphics g, World w, Piece piece) {

		PieceBuilding p = (PieceBuilding) piece;
		
		if (image != null) {
			image.draw(p.posX*World.tileConstant+WorldManager.getWorld().globalOffsetX, p.posY*World.tileConstant+WorldManager.getWorld().globalOffsetY - ((building.height - 1) * World.tileConstant), World.tileConstant * building.width, World.tileConstant * building.height);
//			image.draw(building.door.box.getX()*World.tileConstant+World.globalOffsetX ,  building.door.box.getY()*World.tileConstant+World.globalOffsetY, building.door.width, building.door.height);

		} else {
			
			if (texture != null && texture.length() > 0) {
				image = TextureLoader.load(texture + "0.png");
				image.draw(p.posX*World.tileConstant+WorldManager.getWorld().globalOffsetX, p.posY*World.tileConstant+WorldManager.getWorld().globalOffsetY - ((building.height - 1) * World.tileConstant), World.tileConstant * building.width, World.tileConstant * building.height);
				
			}

		}

	}
	
	public void setCollision() {
		
		if (building.id == 0) 
			setSize(0F, 0.0F, 1F, 1.0F);
		
	}
	
}
