package com.pixel.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.entity.EntityAlive;

public class GUIHungerBar extends GUIComponentSet {
	
	public EntityAlive entity;

	public GUIHungerBar(int x, int y, EntityAlive entity) {
		super(x, y, 170, 8, new GUIComponent[]{
				new GUIHungerApple(x, y, 5),
				new GUIHungerApple(x+17, y, 5),
				new GUIHungerApple(x+34, y, 5),
				new GUIHungerApple(x+51, y, 5),
				new GUIHungerApple(x+68, y, 5),
				new GUIHungerApple(x+85, y, 5),
				new GUIHungerApple(x+102, y, 5),
				new GUIHungerApple(x+119, y, 5),
				new GUIHungerApple(x+136, y, 5),
				new GUIHungerApple(x+153, y, 5),
		});
		this.entity = entity;
	}
	
	public void render(GameContainer c, Graphics g) {
		int tempSatisfaction = (int)entity.getSatisfaction();
		if (tempSatisfaction % 2 != 0) {
			tempSatisfaction ++;
		}
		tempSatisfaction = tempSatisfaction / 2;
		for (int i = 0; i < components.length; i++) {
			if (tempSatisfaction >= 5) {
				((GUIHungerApple)components[i]).setSatisfaction(5);
				tempSatisfaction -= 5;
			} else {
				((GUIHungerApple)components[i]).setSatisfaction(tempSatisfaction);
				tempSatisfaction = 0;
			}
		}
		super.render(c, g);
	}

}
