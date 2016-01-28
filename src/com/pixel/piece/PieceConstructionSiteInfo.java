package com.pixel.piece;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.pixel.entity.EntityPlayer;
import com.pixel.interior.Building;
import com.pixel.interior.BuildingInfo;
import com.pixel.start.TextureLoader;
import com.pixel.world.World;
import com.pixel.world.WorldManager;

public class PieceConstructionSiteInfo extends PieceInfo {

	public int buildingID;
	public BuildingInfo building;
	public int stageCount = 5;
	public Image[] stages;

	public PieceConstructionSiteInfo(int buildingID) {
		super(Building.info.get(buildingID).texture + "1.png");
		this.buildingID = buildingID;
		this.building = Building.info.get(buildingID);
		this.texture = building.texture;
		this.maxDamage = building.maxDamage;
		stages = new Image[stageCount];

		// TODO Auto-generated constructor stub
	}
	
	public void onCreated(Piece p) {
		super.onCreated(p);
		
//		ConstructionSiteManager.addSite(new ConstructionSite(p.posX, p.posY, ((PieceConstructionSiteInfo) Piece.info[p.id]).buildingID));
		
	}
	
	public void render(GameContainer c, Graphics g, World w, Piece p) {

		if (stages[0] == null) {
			
			for (int x = 0; x < stageCount; x ++) {

				stages[x] = TextureLoader.load(texture + x +".png");
				
			}
			
		}

		image = stages[p.metadata];
		
		if (image != null && compareImageToStage(p)) {
			image.draw(p.posX*World.tileConstant+WorldManager.getWorld().globalOffsetX, p.posY*World.tileConstant+WorldManager.getWorld().globalOffsetY - ((building.height - 1) * World.tileConstant), World.tileConstant * building.width, World.tileConstant * building.height);
			//			image.draw(building.door.box.getX()*World.tileConstant+World.globalOffsetX ,  building.door.box.getY()*World.tileConstant+World.globalOffsetY, building.door.width, building.door.height);

		} else {

			if (texture != null && texture.length() > 0) {
				image = TextureLoader.load(texture + p.metadata + ".png");
				image.draw(p.posX*World.tileConstant+WorldManager.getWorld().globalOffsetX, p.posY*World.tileConstant+WorldManager.getWorld().globalOffsetY - ((building.height - 1) * World.tileConstant), World.tileConstant * building.width, World.tileConstant * building.height);

				switch(p.metadata) {

				case 1:
					p.redrawCollisionBox(true);
					break;
				case 2:
					p.setSize(0F, 0.4F, 4F, 0.8F);
					p.redrawCollisionBox(true);
					break;
				case 3:
					p.setSize(0F, 0F, 4F, 1F);
					p.redrawCollisionBox(true);
					break;
				case 4:
					p.setSize(0F, -0.4F, 4F, 1.35F);
					p.redrawCollisionBox(true);
					break;
				}


			}

		}

	}
	
	public void onPlayerCollided(World w, Piece p, EntityPlayer player) {

		super.onPlayerCollided(w, p, player);
		
//		if (p.metadata != 0) {
//
//			player.interfaceManager.menuOpenable = true;
//			player.interfaceManager.foldLeft.menuID = 1;
//			player.interfaceManager.menuCoordinate = new CoordinateKey(p.posX, p.posY);
//
//		}
	}

	public boolean compareImageToStage(Piece p) {

		if (image.getTexture().getTextureRef().endsWith(p.metadata + ".png"))
			return true;
		else
			return false;

	}
}
