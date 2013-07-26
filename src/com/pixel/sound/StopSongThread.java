package com.pixel.sound;

import com.pixel.sound.Sound.Music;

public class StopSongThread extends Thread {

	public void run() {
		
		if (Sound.currentSong != Music.OFF) {
			Sound.currentSongStream.drain();
			Sound.currentSongStream.close();
			Sound.currentSong = Music.OFF;
			Sound.song = null;
		}
		
	}
	
}
