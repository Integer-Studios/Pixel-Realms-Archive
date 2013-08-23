package com.pixel.piece;

import com.badlogic.gdx.math.Rectangle;
import com.pixel.building.Building;

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
		super(x, y, 17, true);
		
		this.worldID = worldID;
		this.building = new Building(worldID, i, x, y);
		this.damage = damage;
		this.metadata = metadata;
		
		init();
		
	}
	
	public void init() {
		
		collisionBox = new Rectangle(posX + info[id].xOffset, posY + info[id].yOffset, building.width, building.height);
		
	}

}
