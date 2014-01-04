package com.pixel.start;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import com.pixel.sound.SongThread;
import com.pixel.sound.Sound;
import com.pixel.sound.Sound.Music;
import com.pixel.util.Toolkit;
import com.pixel.world.World;

public class PixelRealms {

	public static World world;
	public static boolean server;
	public static Toolkit t;
	public static String ip;
	public static int port = 25570;
	public static boolean loggedIn = false;
	public static MainLoop loop;
	
	public static void main(String[] args) {
		System.out.println("-- Pixel Realms V1.0 --");
		
		port = 25571;
		server = false;

		t = new Toolkit();

		try {
			initializeLWJGL();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		new MainFrame().createFrame();

		new Sound();


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

	public static void playSong(Sound.Music song) {

		if (Sound.currentSong != Music.PEACE_LEAF) {
			Sound.stopSong();

			Sound.song = new SongThread(song);
			Sound.song.start();
		}
		
	}

}
