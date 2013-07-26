package com.pixel.piece;

import java.io.Serializable;

public class PieceWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int id, posX, posY, damage, metadata;
	
	public PieceWrapper (int id, int posX, int posY, int damage, int metadata) {
		
		this.id = id;
		this.posX = posX;
		this.posY = posY;
		this.damage = damage;
		this.metadata = metadata;
		
	}
	
}
