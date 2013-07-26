package com.pixel.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class GUIHealthHeart extends GUIComponent {
	
	public int health = 5;
	
	public GUIHealthHeart(int x, int y, int health) {
		super(x, y, 14, 12, "resources/gui/interface/heart/5.png");
		this.health = health;
	}
	
	public void damage(int i) {
		health -= i;
		if (health < 0) {
			health = 0;
		}
	}
	
	public void setHealth(int i) {
		health = i;
	}
	
	public void resetHealth() {
		setHealth(5);
	}
	
	public void render(GameContainer c, Graphics g) {
		setTexture("resources/gui/interface/heart/" + health + ".png");
		super.render(c, g);
	}

}
