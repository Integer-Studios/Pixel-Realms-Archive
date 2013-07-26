package com.pixel.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.input.MouseClickListener;
import com.pixel.start.PixelRealms;

public class GUIButton extends GUIComponentSet{
	
	public GUIButton(int x, int y, int w, int h, GUIComponentText gct) {
		
		super(x, y, w, h, new GUIComponent[]{
				new GUIComponent(x, y, 20, 20, GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "button" + PixelRealms.t.separator + "top_left.png"),
				new GUIComponent(x+w-20, y, 20, 20, GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "button" + PixelRealms.t.separator + "top_right.png"),
				new GUIComponent(x, y+h-20, 20, 20, GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "button" + PixelRealms.t.separator + "bottom_left.png"),
				new GUIComponent(x+w-20, y+h-20, 20, 20, GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "button" + PixelRealms.t.separator + "bottom_right.png"),
				new GUIComponent(x+20, y, w-40, 20, GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "button" + PixelRealms.t.separator + "top.png"),
				new GUIComponent(x+20, y+h-20, w-40, 20, GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "button" + PixelRealms.t.separator + "bottom.png"),
				new GUIComponent(x+w-20, y+20, 20, h-40, GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "button" + PixelRealms.t.separator + "right.png"),
				new GUIComponent(x, y+20, 20, h-40, GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "button" + PixelRealms.t.separator + "left.png"),
				new GUIComponent(x+20, y+20, w-40, h-40, GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "button" + PixelRealms.t.separator + "center.png"),
				new GUIComponentText(gct.text, x + gct.x, y + gct.y, gct.size),
		});
		posX = x;
		posY = y;
		width = w;
		height = h;
		text = gct.text;
	}
	
	public void setPressed(boolean b) {
		if (pressed && !b) {
			onMouseUp = true;
		} else {
			onMouseUp = false;
		}
		pressed = b;
		if (pressed) {
			components[0].setTexture(GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "button" + PixelRealms.t.separator + "pressed" + PixelRealms.t.separator + "top_left.png");
			components[1].setTexture(GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "button" + PixelRealms.t.separator + "pressed" + PixelRealms.t.separator + "top_right.png");
			components[2].setTexture(GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "button" + PixelRealms.t.separator + "pressed" + PixelRealms.t.separator + "bottom_left.png");
			components[3].setTexture(GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "button" + PixelRealms.t.separator + "pressed" + PixelRealms.t.separator + "bottom_right.png");
			components[4].setTexture(GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "button" + PixelRealms.t.separator + "pressed" + PixelRealms.t.separator + "top.png");
			components[5].setTexture(GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "button" + PixelRealms.t.separator + "pressed" + PixelRealms.t.separator + "bottom.png");
			components[6].setTexture(GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "button" + PixelRealms.t.separator + "pressed" + PixelRealms.t.separator + "right.png");
			components[7].setTexture(GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "button" + PixelRealms.t.separator + "pressed" + PixelRealms.t.separator + "left.png");
			components[8].setTexture(GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "button" + PixelRealms.t.separator + "pressed" + PixelRealms.t.separator + "center.png");
		} else {
			components[0].setTexture(GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "button" + PixelRealms.t.separator + "top_left.png");
			components[1].setTexture(GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "button" + PixelRealms.t.separator + "top_right.png");
			components[2].setTexture(GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "button" + PixelRealms.t.separator + "bottom_left.png");
			components[3].setTexture(GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "button" + PixelRealms.t.separator + "bottom_right.png");
			components[4].setTexture(GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "button" + PixelRealms.t.separator + "top.png");
			components[5].setTexture(GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "button" + PixelRealms.t.separator + "bottom.png");
			components[6].setTexture(GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "button" + PixelRealms.t.separator + "right.png");
			components[7].setTexture(GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "button" + PixelRealms.t.separator + "left.png");
			components[8].setTexture(GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "button" + PixelRealms.t.separator + "center.png");
		}
	}
	
	public void render(GameContainer c, Graphics g) {
		if (pressed) {
			setPressed(MouseClickListener.left);
		} else {
			setPressed(MouseClickListener.getIsPressedInButton(this));
		}
		super.render(c, g);
	}

	
	public boolean getPressed() {
		return pressed;
	}

	public boolean getOnMouseUp() {
		return onMouseUp;
	}
	
	public void setText(String s) {
		((GUIComponentText)components[9]).setText(s);
	}
	
	public void setFontColor(Color c) {
		((GUIComponentText)components[9]).fontColor = c;
	}
	
	public boolean pressed, onMouseUp;
	public int width, height, posX, posY;
	public String text;
}
