package com.pixel.gui;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

public class GUIComponentText extends GUIComponent {

	TrueTypeFont font;
	public String text;
	public Color fontColor;
	public float size;
	
	public GUIComponentText(String t, int x, int y, float s) {
		super(x, y, 0, 0,  "");
		text = t;
		size = s;
		fontColor = Color.white;

	}
	
	public GUIComponentText(String t, int x, int y, float s, Color c) {
		super(x, y, 0, 0, "");
		text = t;
		size = s;
		fontColor = c;
	}
	
	public void render(GameContainer c, Graphics g) {	
		
		if (font == null) {
			
			Font awtFont = GUI.font.deriveFont(size);
			font = new TrueTypeFont(awtFont, false);
			
		}
		
		if (text.length() > 0) {
			font.drawString(x, y + (size / 4.8F), text, fontColor);
		}
	}

	public void setText(String t) {
		text = t;
	}
	
}
