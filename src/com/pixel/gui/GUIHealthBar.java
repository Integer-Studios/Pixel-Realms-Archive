package com.pixel.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.entity.EntityAlive;

public class GUIHealthBar extends GUIComponentSet {
	
	public EntityAlive entity;

	public GUIHealthBar(int x, int y, EntityAlive entity) {
		super(x, y, 170, 8, new GUIComponent[]{
				new GUIHealthHeart(x, y, 5),
				new GUIHealthHeart(x+17, y, 5),
				new GUIHealthHeart(x+34, y, 5),
				new GUIHealthHeart(x+51, y, 5),
				new GUIHealthHeart(x+68, y, 5),
				new GUIHealthHeart(x+85, y, 5),
				new GUIHealthHeart(x+102, y, 5),
				new GUIHealthHeart(x+119, y, 5),
				new GUIHealthHeart(x+136, y, 5),
				new GUIHealthHeart(x+153, y, 5),
		});
		this.entity = entity;
	}
	
	public void render(GameContainer c, Graphics g) {
		int tempHealth = (int)entity.getHealth();
		if (tempHealth % 2 != 0) {
			tempHealth ++;
		}
		tempHealth = tempHealth / 2;
		for (int i = 0; i < components.length; i++) {
			if (tempHealth >= 5) {
				((GUIHealthHeart)components[i]).setHealth(5);
				tempHealth -= 5;
			} else {
				((GUIHealthHeart)components[i]).setHealth(tempHealth);
				tempHealth = 0;
			}
		}
		super.render(c, g);
	}

}
