package com.pixel.lighting;

import org.newdawn.slick.Image;

import com.pixel.entity.Entity;
import com.pixel.piece.Piece;
import com.pixel.tile.Tile;

public class PixelLight {

	public PixelLightType type;
	public float posX, posY;
	public int width, height, id;
	
	public PixelLight(float x, float y, int width, int height, PixelLightType type, Entity entity) {

		this.posX = x;
		this.posY = y;
		this.width = width;
		this.height = height;
		this.type = type;

		PixelLightingManager.proposeLight(this, entity);

	}

	public PixelLight(float x, float y, int width, int height, PixelLightType type, Piece piece) {

		this.posX = x;
		this.posY = y;
		this.width = width;
		this.height = height;
		this.type = type;

		PixelLightingManager.proposeLight(this, piece);

	}

	public PixelLight(float x, float y, int width, int height, PixelLightType type, Tile tile) {
		
		this.posX = x;
		this.posY = y;
		this.width = width;
		this.height = height;
		this.type = type;
		
		PixelLightingManager.proposeLight(this, tile);
		
	}
	
	public PixelLight(int id, float x, float y, int width, int height, PixelLightType type) {
		
		this.id = id;
		this.posX = x;
		this.posY = y;
		this.width = width;
		this.height = height;
		this.type = type;
		
		PixelLightingManager.lights.put(id, this);
		
	}
	
	public Image getImage() {
		
		return PixelLightingManager.getImageForType(type);
		
	}
	
}
