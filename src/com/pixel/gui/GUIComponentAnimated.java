package com.pixel.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.animation.AnimationGUI;

public class GUIComponentAnimated extends GUIComponent {

	public GUIComponentAnimated(AnimationGUI a) {
		super(0, 0, 0, 0, "");
		animation = a;
	}
	
	public void render(GameContainer c, Graphics g) {
		animation.render(c, g);
	}
	
	public AnimationGUI animation;

}
