package com.pixel.entity;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.badlogic.gdx.math.Rectangle;
import com.pixel.communication.CommunicationClient;
import com.pixel.communication.GetBunnies;
import com.pixel.communication.PlayerManager;
import com.pixel.communication.packet.PacketUpdatePlayer;
import com.pixel.communication.packet.PacketUpdateWorld;
import com.pixel.gui.GUIHotbar;
import com.pixel.gui.PlayerInterfaceManager;
import com.pixel.input.KeyboardListener;
import com.pixel.input.MouseClickListener;
import com.pixel.inventory.Inventory;
import com.pixel.item.ItemFood;
import com.pixel.item.ItemStack;
import com.pixel.piece.PieceInfo;
import com.pixel.player.PlayerInventory;
import com.pixel.player.PlayerMotionManager;
import com.pixel.player.RelativeActionPunching;
import com.pixel.player.RelativeBody;
import com.pixel.start.PixelRealms;
import com.pixel.util.CollisionBox;
import com.pixel.world.World;

public class EntityPlayer extends EntityHuman {
	
	public float damage = 1.0F;
	public float hitRadius = 0.5F;
	public float reachRadius = 0.5F;
	public float speed = 0.07F;
	public int previousMaxX;
	public int previousMaxY;
	public int previousMinX;
	public int previousMinY;
	public int loadedX, loadedY;
	public int bunnies;
	public PlayerInterfaceManager interfaceManager;
	public PlayerInventory inventory;
	public boolean updated = true;
	public boolean punchEnacted = false;
	public int jumpWait;
	public int punchingIndex;
	public boolean interfaceInitialized;

	public EntityPlayer(int x, int y) {
		super(x, y, .2F, .2F);
		body = new RelativeBody(this);
		inventory = new PlayerInventory(this);
		loadedX = x;
		loadedY = y;
		interfaceManager = new PlayerInterfaceManager(this);
		new GetBunnies(this).start();
	}
	
	public void tick(World w) {
				
//		w.setTile(Math.round(posX), Math.round(posY), 6);
		if (World.loaded) {
			
			interfaceManager.tick();
			
		}
		if ((Math.sqrt((loadedX - posX)*(loadedX - posX) + (loadedY - posY)*(loadedY - posY)) >= 25) && updated) {
			
			updated = false;
			CommunicationClient.addPacket(new PacketUpdateWorld());
			
		}
		
		if ((PixelRealms.world.getPiece((int)MouseClickListener.getXWorldMousePosition(), (int)MouseClickListener.getYWorldMousePosition())) == 0) {

			targetPiece = null;
			
		}
		
		if (MouseClickListener.isPressed() && !MouseClickListener.rightClick && punchingIndex == 0 && !punching && !punchEnacted && !interfaceManager.getIntercept()) {
			punchingIndex = body.addAction(new RelativeActionPunching(body));
			punching = true;
			
		} else if (!MouseClickListener.isPressed() && !MouseClickListener.rightClick && punchingIndex != 0 && punchEnacted) {
			body.removeAction(punchingIndex);
			punchingIndex = 0;
			punchEnacted = false;
			punching = false;
		}
		
		if (MouseClickListener.onMouseUp && MouseClickListener.rightClick) {
			if (selectedItem.item instanceof ItemFood && eatFood((ItemFood) selectedItem.item)) {
				inventory.hotbar.depleteContent(GUIHotbar.selectedSlot.x, GUIHotbar.selectedSlot.y, 1);
			}
		}
		
		if (isJumping) {
			updateJumpOffset();
		} else {
			floatingOffset = 0.0F;
		}

		testEntityCollisions(w);

		body.tick(w);
		
		CommunicationClient.addPacket(new PacketUpdatePlayer(PlayerManager.currentPlayer, this.getX(), this.getY(), health, satisfaction, energy, this.selectedItem));
		
		prevPosX = posX;
		prevPosY = posY;

		super.tick(w);
	}
	
	public boolean eatFood(ItemFood i) {
		if (satisfaction< 100) {
			setSatisfaction(satisfaction+i.food);
			if (satisfaction > 100) {
				setSatisfaction(100);
			}
			return true;
		}
		return false;
	}

	public void updateJumpOffset() {
		if (jumpWait == 1) {
			if (floatingOffset == 0.0F) {
				floatingOffset = -0.01F;
			} else
			if (floatingOffset == -0.01F) {
				floatingOffset = 0.1F;
			} else
			if (floatingOffset == 0.1F) {
				floatingOffset = 0.17F;
			} else
			if (floatingOffset == 0.17F) {
				floatingOffset = 0.2F;
			} else
			if (floatingOffset == 0.2F) {
				floatingOffset = 0.22F;
			} else
			if (floatingOffset == 0.22F) {
				floatingOffset = 0.19F;
			} else
			if (floatingOffset == 0.19F) {
				floatingOffset = 0.16F;
			} else
			if (floatingOffset == 0.16F) {
				floatingOffset = 0.11F;
			} else
			if (floatingOffset == 0.11F) {
				floatingOffset = 0.0F;
			}
			jumpWait = 0;
		} else {
			jumpWait ++;
		}
	}
	
	public boolean testEntityCollisions(World w) {
		for (int x = 0; x < World.entities.size(); x ++) {
			if (CollisionBox.testEntities((Entity) World.entities.values().toArray()[x], this, w)) {
				return true;
			}
		}
		return false;
	}
	
	public void enactPunch(World w) {
		float x = MouseClickListener.getXWorldMousePosition();
		float y = MouseClickListener.getYWorldMousePosition();
		
		float x1,x2,y1,y2;
		if (x - hitRadius < posX - reachRadius) {
			x1 = posX - reachRadius;
		} else {
			x1 = x - hitRadius;
		}
		if (x + hitRadius > posX + width + reachRadius) {
			x2 = posX + width + reachRadius;
		} else {
			x2 = x + hitRadius;
		}
		if (y - hitRadius < posY - reachRadius) {
			y1 = posY - reachRadius;
		} else {
			y1 = y - hitRadius;
		}
		if (y + hitRadius > posY + height + reachRadius) {
			y2 = posY + height + reachRadius;
		} else {
			y2 = y + hitRadius;
		}

		Rectangle clickScope = new Rectangle(x1, y1, x2-x1, y2-y1);

		Entity e = CollisionBox.testEntitiesAgainstCollisionBox(clickScope, w);

		int tempDamage = 1;
		
		if (e != null && e instanceof EntityAlive) {
			if (selectedItem != null && selectedItem.item.damage != 0)
				tempDamage += selectedItem.item.damage;
			
			EntityAlive entityAlive = (EntityAlive) e;
			if (entityAlive instanceof EntityOnlinePlayer)
				((EntityOnlinePlayer) entityAlive).damage(w, tempDamage, this);
			else 
				entityAlive.damage(w, tempDamage, this);

		} else {
			int i = CollisionBox.testPiecesAgainstCollisionBox(clickScope, w);
			if (i != -1) {
				if (World.pieces[i].canBeDamaged(selectedItem.item))
					World.pieces[i].damageWithItem(tempDamage, w, selectedItem.item);
			}
		}
	}

	public void updatePlayerPosition(World w) {
		float tempSpeed = speed;
		
		if ((KeyboardListener.keyBindings.get("Left").pressed && KeyboardListener.keyBindings.get("Up").pressed) || (KeyboardListener.keyBindings.get("Right").pressed && KeyboardListener.keyBindings.get("Up").pressed) || (KeyboardListener.keyBindings.get("Left").pressed && KeyboardListener.keyBindings.get("Down").pressed) || (KeyboardListener.keyBindings.get("Right").pressed && KeyboardListener.keyBindings.get("Down").pressed)) {

			tempSpeed = tempSpeed / (float)Math.sqrt(2D);

		} 
		if (KeyboardListener.keyBindings.get("Left").pressed) {
			setX(posX - tempSpeed);
		} 
		if (KeyboardListener.keyBindings.get("Right").pressed) {
			setX(posX + tempSpeed);
		} 
		if (KeyboardListener.keyBindings.get("Up").pressed) {
			setY(posY - tempSpeed);
		} 
		if (KeyboardListener.keyBindings.get("Down").pressed) {
			setY(posY + tempSpeed);
		}
		
		World.globalOffsetX = (int)(Display.getWidth()/2)-(int)(posX * World.tileConstant);
		World.globalOffsetY = (int)(Display.getHeight()/2)-(int)(posY * World.tileConstant);
	
	}

	public void render(GameContainer c, Graphics g, World w) {
		super.render(c, g, w);
		if (World.loadingScreenDone && !interfaceInitialized) {
			
			interfaceInitialized = true;
			interfaceManager.initializeInterface();
			PieceInfo.pickupSound.setFramePosition(0);
			PieceInfo.pickupSound.start();
			
		}
		body.render(c, g, w);
	}

	public Inventory getLeftInventory() {
		return inventory.inventoryLeft;
	}
	
	public Inventory getRightInventory() {
		return inventory.inventoryRight;
	}
	
	public Inventory getHotbar() {
		return inventory.hotbar;
	}
	
	public boolean giveItem(ItemStack is) {
		if (!inventory.hotbar.addItem(is.item, is.size)) {
			if (!inventory.inventoryLeft.addItem(is.item, is.size)) {
				if (!inventory.inventoryRight.addItem(is.item, is.size)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void onBunnyDeath() {
		
		bunnies ++;
		interfaceManager.bunnyCounter.addBunny();
		
	}
	
	public void onCollidedWithPiece(World w, int x, int y) {
		PlayerMotionManager.clearTarget();
	}

}