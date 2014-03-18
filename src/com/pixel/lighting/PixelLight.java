package com.pixel.lighting;

import org.newdawn.slick.Image;

public class PixelLight {

	public PixelLightType type;
	public float posX, posY;
	public int width, height, id;
	
	public PixelLight(float x, float y, int width, int height, PixelLightType type) {
		
		this.posX = x;
		this.posY = y;
		this.width = width;
		this.height = height;
		this.type = type;
		
		PixelLightingManager.propagateLight(this);
		
	}
	
	public Image getImage() {
		
		return PixelLightingManager.getImageForType(type);
		
	}
	
}
