package com.pixel.sound;

public enum PixelEffect {

	WALKING_DEFAULT("walking_default.wav"),
	PUNCHING_DEFAULT("punch.wav"),
	HIT_DEFAULT("hit.wav"),
	PICKUP_DEFAULT("pickup.wav"),
	BUNNY_DEATH("bunny_death.wav"),
	LOGIN("login.wav");

	String fileName;

	PixelEffect(String file) {

		fileName = file;

	}
	
}
