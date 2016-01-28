package com.pixel.stage;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.communication.CommunicationClient;
import com.pixel.communication.PlayerManager;
import com.pixel.communication.packet.PacketLoadPlayer;
import com.pixel.communication.packet.PacketLogin;
import com.pixel.communication.packet.PacketLoginRequest;
import com.pixel.communication.packet.PacketWorldData;
import com.pixel.gui.GUI;
import com.pixel.gui.GUILoadingScreen;
import com.pixel.input.KeyCode;
import com.pixel.input.KeyboardListener;
import com.pixel.sound.PixelEffect;
import com.pixel.sound.PixelSoundManager;
import com.pixel.start.PixelLogger;
import com.pixel.start.PixelRealms;
import com.pixel.world.WorldManager;

public class StageWorld extends Stage {
	
	public static Thread clientThread;
	
	public static boolean loadingScreenDone, rendered;
	public static boolean loggedIn, finalized;
	public static GUILoadingScreen loadingScreen;

	public StageWorld() {}
	
	public StageWorld init() {
		
//		loadingScreen = new GUILoadingScreen();
//		GUI.addGUIComponent(loadingScreen);
		
		CommunicationClient client = new CommunicationClient(PixelRealms.getIP(), PixelRealms.port);
		clientThread = new Thread(client);
		clientThread.start();     
		WorldManager.init(this);
		
        CommunicationClient.addPacket(new PacketLoginRequest(PlayerManager.currentUserID, PlayerManager.session, PlayerManager.currentPlayer));

		KeyboardListener.clearKeyBindings();
		initializeKeyBindings();
		
		return this;

	}
	
	public void requestFinalization() {
		
        CommunicationClient.addPacket(new PacketLogin(PlayerManager.currentUserID));
		
	}
	
	public static void finalizeLogin() {

		finalized = true;
		
	}
	
	public void continueLogin(PacketLoginRequest packet) {
		
	      CommunicationClient.addPacket(new PacketLoadPlayer(PlayerManager.currentPlayer));
	      CommunicationClient.addPacket(new PacketWorldData());
		
	}
	
	public void disinstantiate() {
		loadingScreen = null;
		WorldManager.disinstantiate();
		CommunicationClient.disinstantiate();
		clientThread.interrupt();
		clientThread = null;
		
		loadingScreenDone = false;
		
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
		
		if (WorldManager.worldLoaded && WorldManager.playerLoaded && !loggedIn) {
			
			finalizeLogin();
			loggedIn = true;
			
		}
		
		if (finalized) {
			
			WorldManager.getWorld().tick();
			
		}
		
		if (WorldManager.playerLoaded && WorldManager.worldLoaded && PlayerManager.playerLoggedIn) {
			
			if (!loadingScreenDone) {
				
				PixelLogger.log("User: " + PlayerManager.currentPlayer + " has logged in with id: " + PlayerManager.currentUserID);
				
				GUI.removeGUIComponent(loadingScreen);
				StageWorld.loadingScreenDone = true;

				WorldManager.player.updated = true;
				finalized = true;

			} 
			
		} 
		
	}

	public void render(GameContainer c, Graphics g) {

		if (finalized) {

			WorldManager.getWorld().render(c, g);

			if (!rendered) {
				
				rendered = true;
				PixelSoundManager.createEffect(PixelEffect.LOGIN).start();
				
			}
			
		}
		
	}

}