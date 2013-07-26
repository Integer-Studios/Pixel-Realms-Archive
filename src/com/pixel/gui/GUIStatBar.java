package com.pixel.gui;

public class GUIStatBar extends GUIComponent {
	
	public GUIStatBar(int x, int y, int width, int height, String texture) {
		super(x, y, width, height, GUI.t.getPath()+"gui/statBar.png");
		int coeff = width/15;
		backGroundBar = new GUIComponent(x+coeff, (int)((width-(coeff*2))*(percent)), width, height, texture);
	}
	
	public void onAddedToGUI() {
		GUI.addGUIComponent(backGroundBar);
	}
	
	public float getPercent() {
		return percent;
	}
	
	public void setPercent(float f) {
		percent = f;
		backGroundBar.setWidth((int)((width-16)*(percent)));
	}
	
	public GUIComponent backGroundBar;
	public float percent = 0.5F;

}
