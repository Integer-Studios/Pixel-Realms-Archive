package com.pixel.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class GUIHungerApple extends GUIComponent {
	
	public int satisfaction = 5;
	
	public GUIHungerApple(int x, int y, int satisfaction) {
		super(x, y, 10, 12, "resources/gui/interface/apple/5.png");
		this.satisfaction = satisfaction;
	}
	
	public void damage(int i) {
		satisfaction -= i;
		if (satisfaction < 0) {
			satisfaction = 0;
		}
	}
	
	public void setSatisfaction(int i) {
		satisfaction = i;
	}
	
	public void resetSatisfaction() {
		setSatisfaction(5);
	}
	
	public void render(GameContainer c, Graphics g) {
		
		if (!image.getResourceReference().contains(satisfaction + ".png"))
			setTexture("resources/gui/interface/apple/" + satisfaction + ".png");
		super.render(c, g);
	}

}
