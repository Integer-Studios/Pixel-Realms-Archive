package com.pixel.frame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.communication.CommunicationClient;
import com.pixel.input.KeyCode;
import com.pixel.input.KeyboardListener;
import com.pixel.start.PixelRealms;
import com.pixel.world.World;

public class PanelWorld extends Panel {
	
	public World world;
	public static Thread clientThread;

	public PanelWorld() {
		CommunicationClient client = new CommunicationClient(PixelRealms.ip);
		clientThread = new Thread(client);
		clientThread.start();     
		world = new World(this);
		PixelRealms.world = world;

		KeyboardListener.clearKeyBindings();
		initializeKeyBindings();
		
	}
	
	public void disinstantiate() {
		world.disinstantiate();
		world = null;
		CommunicationClient.disinstantiate();
		clientThread.interrupt();
		clientThread = null;
		
	}
	
	public void initializeKeyBindings() {
		KeyboardListener.addKeyBinding("Up", KeyCode.KEY_W);
		KeyboardListener.addKeyBinding("Down", KeyCode.KEY_S);
		KeyboardListener.addKeyBinding("Left", KeyCode.KEY_A);
		KeyboardListener.addKeyBinding("Right", KeyCode.KEY_D);
		KeyboardListener.addKeyBinding("Jump", KeyCode.KEY_SPACE);
		KeyboardListener.addKeyBinding("Menu", KeyCode.KEY_ESCAPE);
		KeyboardListener.addKeyBinding("Inventory", KeyCode.KEY_I);
		KeyboardListener.addKeyBinding("Chat", KeyCode.KEY_ENTER);
		KeyboardListener.addKeyBinding("CenterFold", KeyCode.KEY_DOWN);
		KeyboardListener.addKeyBinding("FoldRight", KeyCode.KEY_RIGHT);
		KeyboardListener.addKeyBinding("FoldLeft", KeyCode.KEY_LEFT);
		KeyboardListener.addKeyBinding("HideMenu", KeyCode.KEY_UP);

	}

	public void render(GameContainer c, Graphics g) {
		if (World.loaded)
			world.render(c, g);
	}

}