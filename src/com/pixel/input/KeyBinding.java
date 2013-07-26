package com.pixel.input;

public class KeyBinding {
	
	public KeyBinding(int b) {
		binding = b;
	}
	
	public void setBinding(int i) {
		binding = i;
	}
	
	public void onPressed(int code) {
		if (code == binding) {
			pressed = true;
			onKeyDown = true;
		}
	}
	
	public void onReleased(int code) {
		if (code == binding) {
			pressed = false;
			onKeyUp = true;
		}
	}
	
	public void tick() {
		if (onKeyDown && !keyDownCheck) {
			keyDownCheck = true;
		} else if (onKeyDown && keyDownCheck) {
			onKeyDown = false;
			keyDownCheck = false;
		}
		
		if (onKeyUp && !keyUpCheck) {
			keyUpCheck = true;
		} else if (onKeyUp && keyUpCheck) {
			onKeyUp = false;
			keyUpCheck = false;
		}
	}
	
	public int binding;
	public boolean onKeyDown, pressed, onKeyUp;
	private boolean keyDownCheck, keyUpCheck;

}
