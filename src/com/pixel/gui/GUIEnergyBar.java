package com.pixel.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.entity.EntityHuman;

public class GUIEnergyBar extends GUIComponentSet {
	
	public EntityHuman human;

	public GUIEnergyBar(int x, int y, EntityHuman human) {
		super(x, y, 476, 12, new GUIComponent[]{
				new GUIComponent(x, y, 476, 12, "resources/gui/interface/energyBar/background.png"),
				new GUIComponentStretchBar(x, y+2, 476, 8, "resources/gui/interface/energyBar/fill.png", 1.0F),
				new GUIComponent(x, y, 476, 12, "resources/gui/interface/energyBar/frame.png")
		});
		this.human = human;
	}
	
	public float energyToFloat(float i) {
		return ((float)i)/100;
	}
	
	public void render(GameContainer c, Graphics g) {
		((GUIComponentStretchBar)components[1]).setPercent(energyToFloat(human.energy));
		super.render(c, g);
	}

}
