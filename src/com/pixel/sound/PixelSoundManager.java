package com.pixel.sound;

import java.util.concurrent.ConcurrentHashMap;

import javax.sound.sampled.*;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import com.pixel.util.Toolkit;

public class PixelSoundManager {

	protected static SourceDataLine currentSongStream;
	public static Sound song;
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
	
	public static PixelSound createEffect(PixelEffect effect, float volume) {
		
		int id = soundID + 1;
		soundID ++;
		PixelSound sound = new PixelSound(effect, id, volume);
		sounds.put(id, sound);
		return sound;
		
	}

	public static Sound getSong() {

		Sound song;
		Toolkit t = new Toolkit();
		try {
			song = new Sound(t.getPath() + "resources/sounds/music/" + currentSong.fileName);
			return song;
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
		

	}
	
	public static void startMusic(Music music) {
		
		currentSong = music;
		song = getSong();
		song.play(1.0F, 0.15F);
		
	}

	public static void stopMusic() {

		currentSong = Music.OFF;
		song.stop();
		song = null;

	}
	
	public static void update() {
		
		if (song != null && !song.playing() && currentSong != Music.OFF) {
			
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
