package com.pixel.start;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import com.pixel.util.Toolkit;
import com.pixel.world.World;

public class PixelRealms {

	public static Toolkit t;
	private static String ip;
	public static int port = 25565;
	public static MainLoop loop;
	
	public static void main(String[] args) {
		System.out.println("-- Pixel Realms V1.0 --");
		
		t = new Toolkit();

		try {
			initializeLWJGL();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String getIP() {
		
		return ip;
		
	}
	
	public static void initializeLWJGL() throws SlickException {
		
		loop = new MainLoop("Pixel Realms");
		
		AppGameContainer container = new AppGameContainer(loop);
        container.setDisplayMode(900, 600, false);
        container.setVSync(true);                     // Turn VSync
        container.setMaximumLogicUpdateInterval(35); // Max. 200 miliseconds can pass
        container.setMinimumLogicUpdateInterval(25);

		container.start();
		
		
	}

	public static void setIP(String ip) {
		PixelRealms.ip = ip;
	}

}
