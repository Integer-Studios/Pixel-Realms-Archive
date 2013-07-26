package com.pixel.piece;

import com.badlogic.gdx.math.Rectangle;
import com.pixel.building.Building;

public class PieceBuilding extends Piece {

	public Building building;
	
	public PieceBuilding(int x, int y, int i) {
		super(x, y, 17);
		
		this.building = new Building(i, x, y);
		
		init();
		
	}
	
	public PieceBuilding(int x, int y, int i, int damage, int metadata) {
		super(x, y, 17);
		
		this.building = new Building(i, x, y);
		this.damage = damage;
		this.metadata = metadata;
		
		init();
		
	}
	
	public void init() {
		
		collisionBox = new Rectangle(posX + info[id].xOffset, posY + info[id].yOffset, building.width, building.height);
		
	}

}
