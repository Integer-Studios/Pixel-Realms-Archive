package com.pixel.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.awt.event.KeyEvent;

import com.pixel.input.KeyBinding;
import com.pixel.input.KeyboardListener;

public class GUIKeyBindingButton extends GUIButton {

	public GUIKeyBindingButton(int x, int y, String s) {
		super(x, y, 70, 70, new GUIComponentText("", 27, 10, 35));
		name = s;
	}
	
	public void render(GameContainer c, Graphics g) {
		if (!isSelected) {
			setText(KeyEvent.getKeyText(((KeyBinding) KeyboardListener.keyBindings.get(name)).binding));
			if (onMouseUp) {
				KeyboardListener.setCurrentKeyBindingButton(this);
				setText("?");
				setFontColor(Color.red);
				isSelected = true;
			}
		}
		super.render(c, g);
	}
	
	public void deselect() {
		setText(KeyEvent.getKeyText(((KeyBinding) KeyboardListener.keyBindings.get(name)).binding));
		setFontColor(Color.white);
		isSelected = false;
	}
	
	public void deselectWithKeyCode(int i) {
		((KeyBinding) KeyboardListener.keyBindings.get(name)).setBinding(i);
		setText(KeyEvent.getKeyText(((KeyBinding) KeyboardListener.keyBindings.get(name)).binding));
		setFontColor(Color.white);
		isSelected = false;
	}

	public String name;
	public boolean isSelected = false;
}
