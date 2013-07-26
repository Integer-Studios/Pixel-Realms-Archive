package com.pixel.gui;

public class GUIComponentStretchBar extends GUIComponent {
	
	public float percent = 1.0F;
	public int totalWidth;

	public GUIComponentStretchBar(int x, int y, int width, int height,
			String texture, float percent) {
		super(x, y, (int)(width*percent), height, texture);
		totalWidth = width;
	}
	
	public void setPercent(float f) {
		percent = f;
		width = (int)(totalWidth*percent);
	}

}
