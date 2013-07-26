package com.pixel.player;

import org.lwjgl.opengl.Display;

import com.pixel.entity.EntityPlayer;
import com.pixel.input.KeyboardListener;
import com.pixel.world.World;

public class PlayerMotionManager {
	
	public PlayerMotionManager() {
		
	}
	
	public static float targetPosX, targetPosY;
	public static boolean hasTarget = false;
	
	public static void setTargetPosition(float x, float y) {
		targetPosX = x;
		targetPosY = y;
		hasTarget = true;
	}
	
	public static void updatePlayerMotion(EntityPlayer player, World world) {
		float tempSpeed = player.speed;
		
		if ((KeyboardListener.keyBindings.get("Left").pressed && KeyboardListener.keyBindings.get("Up").pressed) || (KeyboardListener.keyBindings.get("Right").pressed && KeyboardListener.keyBindings.get("Up").pressed) || (KeyboardListener.keyBindings.get("Left").pressed && KeyboardListener.keyBindings.get("Down").pressed) || (KeyboardListener.keyBindings.get("Right").pressed && KeyboardListener.keyBindings.get("Down").pressed)) {
			tempSpeed = tempSpeed / (float)Math.sqrt(2D);
		} 
		
		if (KeyboardListener.keyBindings.get("Left").pressed) {
			player.setX(player.getX() - tempSpeed);
			clearTarget();
		} 
		if (KeyboardListener.keyBindings.get("Right").pressed) {
			player.setX(player.getX() + tempSpeed);
			clearTarget();
		} 
		if (KeyboardListener.keyBindings.get("Up").pressed) {
			player.setY(player.getY() - tempSpeed);
			clearTarget();
		} 
		if (KeyboardListener.keyBindings.get("Down").pressed) {
			player.setY(player.getY() + tempSpeed);
			clearTarget();
		}
		
		if (hasTarget) {
			
			if (targetPosX != player.getX()) {
				movePlayerXAtSpeed(player, getPlayerSpeedX(player));
			}
			if (targetPosY != player.getY()) {
				movePlayerYAtSpeed(player, getPlayerSpeedY(player));
			}
			
			if (targetPosX == player.getX() && targetPosY == player.getY()) {
				clearTarget();
			}
		}
		
		World.globalOffsetX = (int)(Display.getWidth()/2)-(int)(player.getX() * World.tileConstant);
		World.globalOffsetY = (int)(Display.getHeight()/2)-(int)(player.getY() * World.tileConstant);

	}

	private static void movePlayerXAtSpeed(EntityPlayer player, float speed) {
		if (targetPosX > player.getX()) {
			if (targetPosX <= player.getX() + speed) {
				player.setX(targetPosX);
			} else {
				player.setX(player.getX() + speed);
			}
		} else if (targetPosX < player.getX()) {
			if (targetPosX >= player.getX() - speed) {
				player.setX(targetPosX);
			} else {
				player.setX(player.getX() - speed);
			}
		}
	}
	
	private static void movePlayerYAtSpeed(EntityPlayer player, float speed) {
		if (targetPosY > player.getY()) {
			if (targetPosY <= player.getY() + speed) {
				player.setY(targetPosY);
			} else {
				player.setY(player.getY() + speed);
			}
		} else if (targetPosY < player.getY()) {
			if (targetPosY >= player.getY() - speed) {
				player.setY(targetPosY);
			} else {
				player.setY(player.getY() - speed);
			}
		}
	}
	
	private static float getPlayerSpeedX(EntityPlayer player) {
		float deltaY = Math.abs(targetPosY - player.getY());
		float deltaX = Math.abs(targetPosX - player.getX());
		double theta = Math.atan(deltaY/deltaX);
		float speed = player.speed * (float)Math.cos(theta);
		return speed;
	}
	
	private static float getPlayerSpeedY(EntityPlayer player) {
		float deltaY = Math.abs(targetPosY - player.getY());
		float deltaX = Math.abs(targetPosX - player.getX());
		double theta = Math.atan(deltaY/deltaX);
		float speed = player.speed * (float)Math.sin(theta);
		return speed;
	}
	
	public static void clearTarget() {
		hasTarget = false;
	}
}
