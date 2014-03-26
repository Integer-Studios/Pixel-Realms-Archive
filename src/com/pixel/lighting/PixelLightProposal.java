package com.pixel.lighting;

import com.pixel.entity.Entity;
import com.pixel.piece.Piece;
import com.pixel.tile.Tile;

public class PixelLightProposal {
	
	public PixelLight light;
	public Entity entity;
	public Piece piece;
	public Tile tile;
	
	public PixelLightProposal(PixelLight light, Entity entity) {
		
		this.light = light;
		this.entity = entity;
		
	}
	
	public PixelLightProposal(PixelLight light, Piece piece) {
		
		this.light = light;
		this.piece = piece;

	}

	public PixelLightProposal(PixelLight light, Tile tile) {

		this.light = light;
		this.tile = tile;

	}
	
	public void confirm(PixelLight light) {
		
		if (tile != null) {
			
			tile.lightID = light.id;
			
		}
		
		if (piece != null) {
			
			piece.lightID = light.id;
			
		}
		
		if (entity != null) {
			
			entity.lightID = light.id;
			
		}
		
	}

}
