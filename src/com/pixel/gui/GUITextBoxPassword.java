package com.pixel.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.input.MouseClickListener;

public class GUITextBoxPassword extends GUITextBox {

	public GUITextBoxPassword(int x, int y, int w, int h, GUIComponentText gct) {
		super(x, y, w, h, gct);
		hiddenText = "";
	}
	
	public String hideText(String t) {
		hiddenText = t;
		String s = "";
		for (int i = 0; i < hiddenText.length(); i++) {
			s += "*";
		}
		return s;
	}
	
	public void setEdited() {
		
		placeHolderIsUnedited = false;
		
	}
	
	public void render(GameContainer c, Graphics g) {
		selected = MouseClickListener.getIsSelected(this);
		if (selected) {
			if (placeHolderIsUnedited) {
				setText("");
				((GUIComponentText)components[9]).y += 7;
				placeHolderIsUnedited = false;
			}
		}
		super.render(c, g);
	}
	
	public void setText(String t) {
		if (placeHolderIsUnedited) {
			super.setText(t);
		} else {
			if (blinkOn) {
				((GUIComponentText)components[9]).setText(hideText(t.replace("|", ""))+"'");
			} else {
				((GUIComponentText)components[9]).setText(hideText(t.replace("|", "")));
			}
		}
	}
	
	public String getText() {
		if(placeHolderIsUnedited) {
			return super.getText();
		} else {
			return hiddenText;
		}
	}
	

	public String hiddenText;
}
