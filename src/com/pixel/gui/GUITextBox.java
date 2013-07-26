package com.pixel.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.input.KeyboardListener;
import com.pixel.input.MouseClickListener;
import com.pixel.start.PixelRealms;

public class GUITextBox extends GUIBox{
	
	public GUITextBox(int x, int y, int w, int h, GUIComponentText gct) {
		super(x, y, w, h, new GUIComponent[]{
				new GUIComponent(x, y, 20, 20, GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "text_box" + PixelRealms.t.separator + "top_left.png"),
				new GUIComponent(x+w-20, y, 20, 20, GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "text_box" + PixelRealms.t.separator + "top_right.png"),
				new GUIComponent(x, y+h-20, 20, 20, GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "text_box" + PixelRealms.t.separator + "bottom_left.png"),
				new GUIComponent(x+w-20, y+h-20, 20, 20, GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "text_box" + PixelRealms.t.separator + "bottom_right.png"),
				new GUIComponent(x+20, y, w-40, 20, GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "text_box" + PixelRealms.t.separator + "top.png"),
				new GUIComponent(x+20, y+h-20, w-40, 20, GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "text_box" + PixelRealms.t.separator + "bottom.png"),
				new GUIComponent(x+w-20, y+20, 20, h-40, GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "text_box" + PixelRealms.t.separator + "right.png"),
				new GUIComponent(x, y+20, 20, h-40, GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "text_box" + PixelRealms.t.separator + "left.png"),
				new GUIComponent(x+20, y+20, w-40, h-40, GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "text_box" + PixelRealms.t.separator + "center.png"),
				new GUIComponentText(gct.text, x + gct.x, y + gct.y, gct.size, Color.black)
		});
		text = gct.text;
		textComponent = (GUIComponentText) components[9];
		
	}
	
	public void render(GameContainer c, Graphics g) {
			selected = MouseClickListener.getIsSelected(this);
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
				blinkWait = 50;
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
