package com.pixel.gui;

import com.pixel.input.KeyboardListener;

public class GUIBox extends GUIComponentSet{

	GUIComponentText textComponent;
	
	public GUIBox(int x, int y, int w, int h, GUIComponent[] set) {
		
		super(x, y, w, h, set);
		
		posX = x;
		posY = y;
		width = w;
		height = h;
		KeyboardListener.registerTextBox(this);
		
	}
	
	public void setText(String t) {
		t = t.replace("|", "");
		if (blinkOn) {
			t += "|";
		}
		textComponent.setText(t);
	}
	
	public String getText() {
		return textComponent.text.replace("|", "");
	}
	
	public boolean getPressed() {
		return selected;
	}
	
	public boolean selected;
	public boolean placeHolderIsUnedited = true;
	public int width, height, posX, posY;
	public String text;
	public int overflowLimit = 18;
	
	public int blinkWait = 0;
	public boolean blinkOn = false;
	
}
