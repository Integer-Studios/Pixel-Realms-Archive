package com.pixel.sound;

import java.io.*;
import java.net.URL;

import javax.sound.sampled.*;

import com.pixel.start.PixelRealms;
import com.pixel.util.Toolkit;

public class Sound {

	protected static SourceDataLine currentSongStream;
	public static SongThread song;
	public static Music currentSong = Music.OFF;

	public Sound() {


	}

	public enum Effect {

		WALKING_DEFAULT("walking_default.wav"),
		PUNCHING_DEFAULT("punch.wav"),
		PICKUP_DEFAULT("pickup.wav"),
		BUNNY_DEATH("bunny_death.wav");

		String fileName;

		Effect(String file) {

			fileName = file;

		}
	} 

	public enum Music {

		OFF(""),
		SCARBOROUGH_FAIR("scarborough_fair.wav"),
		THE_WITCH("the_witch.wav"),
		LOVE_ME_SEXY("love_me_sexy.wav"),
		ROB_IN_WHITE_SATIN("rws.wav"),
		PEACE_LEAF("peace_leaf.wav");

		String fileName;

		Music(String file) {

			fileName = file;

		}


	}

	public static Clip getEffect(Effect effect) {

		Toolkit t = new Toolkit();
		String path = t.getPath();
		Clip clip = null;

		try {
			// Use URL (instead of File) to read from disk and JAR.
			URL url = new URL("file:" + PixelRealms.t.separator + "" + PixelRealms.t.separator + "" + path + "resources" + PixelRealms.t.separator + "sounds" + PixelRealms.t.separator + "effects" + PixelRealms.t.separator + "" + effect.fileName);
			// Set up an audio input stream piped from the sound file.
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
			// Get a clip resource.
			clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioInputStream);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}

		if (clip.isRunning())
			clip.stop();   // Stop the player if it is still running
		clip.setFramePosition(0); // rewind to the beginning


		FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		volume.setValue(-10);

		return clip;

	}

	public static void stopSong() {

		new StopSongThread().start();
		

	}

	public static Music getCurrentSong() {

		return currentSong;

	}

	public static void onSongEnded(Music song) {

		currentSong = Music.OFF;

		//	if (song == Music.SCARBOROUGH_FAIR)
		//		song = Music.THE_WITCH;
		//	else
		//		song = Music.SCARBOROUGH_FAIR;
		//	
		//	playSong(song);

	}

}
