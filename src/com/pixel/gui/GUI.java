package com.pixel.gui;

import java.awt.Font;
import java.io.InputStream;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.util.ResourceLoader;

import com.pixel.input.KeyboardListener;
import com.pixel.util.Toolkit;

public class GUI {
	
	public static void init() {
		
		font = getCustomFont();

	}
	
	public static Font getCustomFont() {
		
		Font font;
		Font awtFont = new Font("Times New Roman", Font.BOLD, 24);

		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream("resources/fonts/MixSerifCondense.ttf");

			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			font = awtFont2;
		} catch (Exception e) {
			font = awtFont;
			e.printStackTrace();
		}	
		
		return font;
	}
	
	public static void render(GameContainer c, Graphics g) {
		for (int i = 0; i < components.size(); i++) {
			components.get(i).render(c, g);
		}
	}

	public static GUIComponent addGUIComponent(GUIComponent e) {
		components.add(e);

		e.onAddedToGUI();
		e.indexInGUI = components.size()-1;
		return e;
	}
	
	public static void removeGUIComponent(GUIComponent e) {
		components.remove(e.indexInGUI);
		components.add(e.indexInGUI, new GUIComponentBlank());
	}
	
	public static void clear() {

		components = new ArrayList<GUIComponent>();
		KeyboardListener.textBoxes.clear();
		KeyboardListener.selectedTextBox = null;
	}
	
	public static void disinstantiate() {
		
		clear();
		
	}
	
	public static Font font;
	public static ArrayList<GUIComponent> components = new ArrayList<GUIComponent>();
	public static Toolkit t = new Toolkit();

}
