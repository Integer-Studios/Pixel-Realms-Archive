package com.pixel.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.pixel.input.MouseClickListener;
import com.pixel.start.TextureLoader;

public class GUIArrowButton extends GUIComponent {

	public boolean pressed, onMouseUp;
	public Image down, up;
	public String direction;

	public GUIArrowButton(int x, int y, String dir) {
		super(x, y, 14, 22, "resources/gui/interface/foldRight/"+dir+"Btn.png");
		direction = dir;
		
		down = TextureLoader.load("resources/gui/interface/foldRight/"+direction+"BtnDown.png");
		up = TextureLoader.load("resources/gui/interface/foldRight/"+direction+"Btn.png");
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
			image = down;
		} else {
			image = up;
		}
	}

}
