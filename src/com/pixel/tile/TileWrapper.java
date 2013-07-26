package com.pixel.tile;

import java.io.Serializable;

public class TileWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int posX, posY;
	public int id;
	
	public TileWrapper(int id, int posX, int posY) {
		
		this.id = id;
		this.posX = posX;
		this.posY = posY;
		
	}
	
}
