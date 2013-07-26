package com.pixel.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.input.KeyboardListener;

public class GUIChatTextBox extends GUIBox {
	
	public GUIChatTextBox(int x, int y, int w, int h, GUIComponentText gct) {
		super(x, y, w, h, new GUIComponent[]{
				new GUIComponentText(gct.text, x + gct.x, y + gct.y, gct.size, Color.white)
		});
		posX = x;
		posY = y;
		width = w;
		height = h;
		text = gct.text;
		textComponent = (GUIComponentText) components[0];
		overflowLimit = 64;

		KeyboardListener.registerTextBox(this);
	}
	
	public void render(GameContainer c, Graphics g) {
		if (selected) {
			if (placeHolderIsUnedited) {
				setText("");
				placeHolderIsUnedited = false;
			}
			KeyboardListener.setSelectedTextBox(this);
			if (blinkWait == 0) {
				if (blinkOn) {
					setText(getText().replace("|", ""));
					blinkOn = false;
				} else {
					setText(getText()+"|");
					blinkOn = true;
				}
				blinkWait = 10;
			}
			blinkWait--;
		} else {
			setText(getText().replace("|", ""));
			blinkOn = false;
			blinkWait = 0;
		}
		super.render(c, g);
	}

}
