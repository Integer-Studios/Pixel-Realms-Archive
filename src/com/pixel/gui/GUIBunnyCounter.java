package com.pixel.gui;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.start.PixelRealms;

public class GUIBunnyCounter extends GUIComponentSet {
	
	public GUIBunnyCounter() {
		
		super(0, 0, 128, 48, new GUIComponent[]{
				new GUIComponent(0, 0, 128, 48, "resources"+PixelRealms.t.separator+"gui"+PixelRealms.t.separator+"kill_bar.png"),
				new GUIComponentText("1234", 67, 3, 30, Color.red)
		});
		
		count = (GUIComponentText) components[1];
		
		int originX = Display.getWidth() - width - 5;
		int originY = Display.getHeight() - height - 28;
	
		this.setPosition(originX, originY);
		
	}
	
	public void render(GameContainer c, Graphics g) {
		super.render(c, g);
		
		int originX = Display.getWidth() - width - 5;
		int originY = Display.getHeight() - height - 28;
	
		this.setPosition(originX, originY);
	}
	
	public void setText(String s) {

		count.setText(s);
		
	}
	
	public void setBunnies(int bunnies) {
		
		setText(bunnies + "");
		
	}
	
	public void addBunny() {
		
		int b = Integer.parseInt(count.text);
		b ++;
		count.setText(b + "");
		
	}
	
	public GUIComponentText count;
	
}
