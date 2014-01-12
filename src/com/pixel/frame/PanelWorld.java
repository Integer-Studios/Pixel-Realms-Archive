package com.pixel.frame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.communication.CommunicationClient;
import com.pixel.communication.PlayerManager;
import com.pixel.communication.packet.PacketLogin;
import com.pixel.gui.GUI;
import com.pixel.gui.GUILoadingScreen;
import com.pixel.input.KeyCode;
import com.pixel.input.KeyboardListener;
import com.pixel.sound.PixelEffect;
import com.pixel.sound.PixelSoundManager;
import com.pixel.start.PixelLogger;
import com.pixel.start.PixelRealms;
import com.pixel.world.World;

public class PanelWorld extends Panel {
	
	public World world;
	public static Thread clientThread;
	
	public static boolean loadingScreenDone, rendered;
	public static boolean inventoryLoaded;
	public static boolean playerLoaded;
	public static boolean worldLoaded;
	public static boolean loginRebound;
	public static boolean loaded;
	public static boolean playersLoaded;
	public static boolean entitiesLoaded;

	public PanelWorld() {}
	
	public PanelWorld init() {
		
		World.loadingScreen = new GUILoadingScreen();
		GUI.addGUIComponent(World.loadingScreen);
		
		CommunicationClient client = new CommunicationClient(PixelRealms.ip, PixelRealms.port);
		clientThread = new Thread(client);
		clientThread.start();     
		world = new World(this);
		PixelRealms.world = world;
		
        CommunicationClient.addPacket(new PacketLogin(PlayerManager.currentPlayer, PlayerManager.session));

		KeyboardListener.clearKeyBindings();
		initializeKeyBindings();
		
		return this;
	}
	
	public void disinstantiate() {
		World.loadingScreen = null;
		world.disinstantiate();
		world = null;
		CommunicationClient.disinstantiate();
		clientThread.interrupt();
		clientThread = null;
		
		loadingScreenDone = false;
		inventoryLoaded = false;
		worldLoaded = false;
		playersLoaded = false;
		entitiesLoaded = false;
		inventoryLoaded = false;
		loginRebound = false;
		playerLoaded = false;
		loaded = false;
		
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
		KeyboardListener.addKeyBinding("E", KeyCode.KEY_E);
	}
	
	public void update (GameContainer c, int delta) {
		
		if (loaded) {
			
			PixelRealms.world.tick();
			
		}

		if (worldLoaded && playerLoaded && loginRebound && inventoryLoaded && playersLoaded && entitiesLoaded) {
			
			if (!loadingScreenDone) {
				
				PixelLogger.log("User: " + PlayerManager.currentPlayer + " has logged in with id: " + PlayerManager.currentUserID);
				
				GUI.removeGUIComponent(World.loadingScreen);
				PanelWorld.loadingScreenDone = true;

				PixelRealms.loggedIn = true;
				PixelRealms.world.player.updated = true;
				loaded = true;

			} 
			
		} 
		
	}

	public void render(GameContainer c, Graphics g) {

		if (loaded) {

			world.render(c, g);

			if (!rendered) {
				
				rendered = true;
				PixelSoundManager.createEffect(PixelEffect.LOGIN).start();
				
			}
			
		}
		
	}

}