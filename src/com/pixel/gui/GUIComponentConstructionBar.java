package com.pixel.gui;

public class GUIComponentConstructionBar extends GUIComponentSet {

	public String texture;
	
	public GUIComponentConstructionBar(int x, int y, int width, int height, String texture) {
		super(x, y, width, height, new GUIComponent[]{
				new GUIComponent(x + 60, y + 20, width, height, "resources/gui/interface/" + texture + "/background.png"),
				new GUIComponentStretchBar(x + 60, y+22, width, height - 4, "resources/gui/interface/" + texture + "/fill.png", 1.0F),
				new GUIComponent(x + 60, y + 20, width, height, "resources/gui/interface/" + texture + "/frame.png"),
				new GUIComponent(x + 10, y - 12, 48, 48, "resources/pieces/rocks/rock_1.png")

		});
		// TODO Auto-generated constructor stub
	}
	
}
