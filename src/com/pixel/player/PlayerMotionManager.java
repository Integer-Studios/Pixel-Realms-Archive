package com.pixel.player;


import com.pixel.communication.CommunicationClient;
import com.pixel.communication.packet.PacketMoveLivingEntity;
import com.pixel.communication.packet.PacketMovePlayerDeprecated;
import com.pixel.entity.EntityPlayer;
import com.pixel.input.KeyboardListener;
import com.pixel.world.World;

public class PlayerMotionManager {
	
	public PlayerMotionManager() {
		
	}
	
	public static float targetPosX, targetPosY;
	public static boolean hasTarget = false;
	public static float changeX, changeY, prevChangeX, prevChangeY;
	
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
		
		if (KeyboardListener.keyBindings.get("Left").pressed && !KeyboardListener.keyBindings.get("Right").pressed) {
			player.setVelocityX(-1*tempSpeed);
		} else if (KeyboardListener.keyBindings.get("Right").pressed && !KeyboardListener.keyBindings.get("Left").pressed) {
			player.setVelocityX(tempSpeed);
		} else {
			player.setVelocityX(0);
		}
		
		if (KeyboardListener.keyBindings.get("Down").pressed && !KeyboardListener.keyBindings.get("Up").pressed) {
			player.setVelocityY(tempSpeed);
		} else if (KeyboardListener.keyBindings.get("Up").pressed && !KeyboardListener.keyBindings.get("Down").pressed) {
			player.setVelocityY(-1*tempSpeed);
		} else {
			player.setVelocityY(0);
		}
//		
//		if (KeyboardListener.keyBindings.get("Left").pressed) {
//			player.setX(player.getX() - tempSpeed);
//			clearTarget();
//		} 
//			
//		if (KeyboardListener.keyBindings.get("Right").pressed) {
//			player.setX(player.getX() + tempSpeed);
//			clearTarget();
//		}  
//		
//		if (KeyboardListener.keyBindings.get("Up").pressed) {
//			player.setY(player.getY() - tempSpeed);
//			clearTarget();
//		}
//			
//		if (KeyboardListener.keyBindings.get("Down").pressed) {
//			player.setY(player.getY() + tempSpeed);
//			clearTarget();
//		}
		
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
		
		if (Math.abs(player.getX() - player.doorX) >= .9 || Math.abs(player.getY() - player.doorY) >= .9) {
			player.door = false;
		
		}

	}
	
	public static int teleportCount = 0;
	
	public static void checkMovement(EntityPlayer player) {
				
		if (player.getVelocityX() >= .8 && player.getVelocityY() >= .8)
			player.door = false;
		
		if ((player.getVelocityX() != player.getPreviousVelocityX() || player.getVelocityY() != player.getPreviousVelocityY()) && !player.teleported) {
			CommunicationClient.addPacket(new PacketMoveLivingEntity(player));
		} else if (player.teleported && teleportCount == 1) {
			
			player.teleported = false;
			teleportCount = 0;
			
		} else if (player.teleported) {
			
			teleportCount ++;
			
		}
		
//		prevChangeX = changeX;
//		prevChangeY = changeY;
//		

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
	
	public static void onCollidedWithPiece(EntityPlayer player, int x, int y) {
		checkMovement(player);
	}
}
