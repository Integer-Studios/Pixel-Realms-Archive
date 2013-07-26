package com.pixel.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.input.MouseClickListener;

public class GUIArrowButton extends GUIComponent {

	public boolean pressed, onMouseUp;
	public String direction;

	public GUIArrowButton(int x, int y, String dir) {
		super(x, y, 14, 22, "resources/gui/interface/foldRight/"+dir+"Btn.png");
		direction = dir;
	}
	
	public void render(GameContainer c, Graphics g) {
		setPressed(MouseClickListener.getIsPressedInComponent(this));
		super.render(c, g);
	}
	
	public void setPressed(boolean b) {
		if (pressed && !b) {
			onMouseUp = true;
		} else {
			onMouseUp = false;
		}
		pressed = b;
		
		if (b) {
			texture = "resources/gui/interface/foldRight/"+direction+"BtnDown.png";
		} else {
			texture = "resources/gui/interface/foldRight/"+direction+"Btn.png";
		}
	}

}
