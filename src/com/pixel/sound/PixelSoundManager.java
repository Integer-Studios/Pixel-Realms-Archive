package com.pixel.sound;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

import javax.sound.sampled.*;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public class PixelSoundManager {

	protected static SourceDataLine currentSongStream;
	public static Audio song;
	public static Music currentSong = Music.OFF;
	public static ConcurrentHashMap<Integer, PixelSound> sounds = new ConcurrentHashMap<Integer, PixelSound>();
	public static int soundID = 0;
	
	public PixelSoundManager() {


	}

	public enum Music {

		OFF(""),
		SCARBOROUGH_FAIR("scarborough_fair.wav"),
		THE_WITCH("the_witch.wav"),
		LOVE_ME_SEXY("love_me_sexy.wav"),
		ROB_IN_WHITE_SATIN("rws.wav"),
		ADIOS("adios.wav"),
		PEACE_LEAF("peace_leaf.wav");

		String fileName;

		Music(String file) {

			fileName = file;

		}

	}
	
	public static PixelSound createEffect(PixelEffect effect) {
		
		int id = soundID + 1;
		soundID ++;
		PixelSound sound = new PixelSound(effect, id);
		sounds.put(id, sound);
		return sound;
		
	}

	public static Audio getSong() {

		try {
			Audio song;

			song = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("resources/sounds/music/" + currentSong.fileName));

			return song;
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		

	}
	
	public static void startMusic(Music music) {
		
		currentSong = music;
		song = getSong();
		song.playAsMusic(1.0F, 1.0F, false);
		
	}

	public static void stopMusic() {

		currentSong = null;
		song.stop();
		song = null;

	}
	
	public static void update() {
		
		if (!song.isPlaying() && currentSong != Music.OFF) {
			
			onSongEnded(currentSong);
			
		}
		
		for (PixelSound sound : sounds.values()) {
			
			sound.update();
			
		}
		
	}

	public static Music getCurrentSong() {

		return currentSong;

	}

	public static void onSongEnded(Music song) {

		currentSong = Music.OFF;

		if (song == Music.SCARBOROUGH_FAIR)
			song = Music.THE_WITCH;
		else
			song = Music.SCARBOROUGH_FAIR;

		startMusic(song);

	}

}
