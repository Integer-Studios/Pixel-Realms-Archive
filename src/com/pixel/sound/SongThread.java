package com.pixel.sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import com.pixel.start.PixelRealms;
import com.pixel.util.Toolkit;

public class SongThread extends Thread {

	Sound.Music song;
	
	public SongThread(Sound.Music song) {
		
		this.song = song;
		
	}
	
	public void run() {
		
		Sound.currentSong = song;

		Toolkit t = new Toolkit();
		String path = t.getPath();
		int BUFFER_SIZE = 128000;
	    File soundFile = null;
	    AudioInputStream audioStream = null;
	    AudioFormat audioFormat = null;
	    
		try {
			soundFile = new File(path + "resources" + PixelRealms.t.separator + "sounds" + PixelRealms.t.separator + "music" + PixelRealms.t.separator + "" + song.fileName);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		try {
			audioStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (Exception e){
			e.printStackTrace();
			System.exit(1);
		}

		audioFormat = audioStream.getFormat();

		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		try {
			Sound.currentSongStream = (SourceDataLine) AudioSystem.getLine(info);
			Sound.currentSongStream.open(audioFormat);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		FloatControl volume = (FloatControl) Sound.currentSongStream.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(-20);
        Sound.currentSongStream.start();

		int nBytesRead = 0;
		byte[] abData = new byte[BUFFER_SIZE];
		while (nBytesRead != -1) {
			try {
				nBytesRead = audioStream.read(abData, 0, abData.length);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (nBytesRead >= 0) {
				@SuppressWarnings("unused")
				int nBytesWritten = Sound.currentSongStream.write(abData, 0, nBytesRead);
			}
		}

		Sound.currentSongStream.drain();
		Sound.currentSongStream.close();
		
		Sound.onSongEnded(Sound.currentSong);
	
		
	}
	
}
