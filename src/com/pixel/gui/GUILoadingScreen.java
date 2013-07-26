package com.pixel.gui;

import org.lwjgl.opengl.Display;

import com.pixel.world.World;

public class GUILoadingScreen extends GUIComponentSet {

	public int frame = 1;
	public int timer = 0;
	
	public GUILoadingScreen () {
		
		super(0, 0, 128, 48, new GUIComponent[]{
				new GUIComponent(0, 0, Display.getWidth(), Display.getHeight(), "resources/gui/multiplayer/loading/0.png")
		});

		this.setPosition(0, 0);

	}

	public void tick () {

		if (World.loaded) {

			World.loadingScreenDone = true;
			GUI.removeGUIComponent(this);

		} 


	}

}
