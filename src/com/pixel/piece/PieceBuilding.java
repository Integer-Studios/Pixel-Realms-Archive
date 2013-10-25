package com.pixel.piece;

import com.badlogic.gdx.math.Rectangle;
import com.pixel.interior.Building;
import com.pixel.world.World;

public class PieceBuilding extends Piece {

	public Building building;
	public int worldID;
	
	public PieceBuilding(int worldID, int x, int y, int i) {
		super(x, y, 17, true);
		
		this.worldID = worldID;
		this.building = new Building(worldID, i, x, y);
		
		init();
		
	}
	
	public PieceBuilding(int worldID, int x, int y, int i, int damage, int metadata) {
		super(x, y, 17, false);
		this.worldID = worldID;
		this.building = new Building(worldID, i, x, y);
		this.damage = damage;
		this.metadata = metadata;
		
		
		World.propagatePiece(this);
		init();
		
	}
	
	public void tick(World w) {
		super.tick(w);
//		if (CollisionBox.testEntityAgainstBox(w.player, building.door.box, w)) {
			
//			System.out.println("A");
//			CommunicationClient.addPacket(new PacketLoadInterior(((PieceBuilding) p).worldID));
			
//		}		
	}
	
	public void init() {
		
		collisionBox = new Rectangle(posX + info[id].xOffset, posY + info[id].yOffset, info[id].width, info[id].height);
		
	}

}
