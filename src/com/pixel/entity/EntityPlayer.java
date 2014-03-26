package com.pixel.entity;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.badlogic.gdx.math.Rectangle;
import com.pixel.body.BodyBiped;
import com.pixel.body.BipedActionPunching;
import com.pixel.communication.CommunicationClient;
import com.pixel.communication.GetBunnies;
import com.pixel.communication.packet.PacketEntityAnimation;
import com.pixel.communication.packet.PacketUpdateInteriorPiece;
import com.pixel.communication.packet.PacketChangePiece;
import com.pixel.communication.packet.PacketUpdateWorld;
import com.pixel.frame.PanelWorld;
import com.pixel.gui.GUI;
import com.pixel.gui.GUIHotbar;
import com.pixel.gui.GUIPieceOnMouse;
import com.pixel.gui.GUIStructureOnMouse;
import com.pixel.gui.PlayerInterfaceManager;
import com.pixel.input.MouseClickListener;
import com.pixel.interior.Building;
import com.pixel.inventory.Inventory;
import com.pixel.item.Item;
import com.pixel.item.ItemFood;
import com.pixel.item.ItemStack;
import com.pixel.lighting.PixelLight;
import com.pixel.lighting.PixelLightType;
import com.pixel.lighting.PixelLightingManager;
import com.pixel.piece.Piece;
import com.pixel.player.PlayerInventory;
import com.pixel.player.PlayerMotionManager;
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
	public boolean teleported;
	public PlayerInterfaceManager interfaceManager;
	public PlayerInventory inventory;
	public boolean updated = true;
	public boolean punchEnacted = false;
	public int jumpWait;
	public int punchingIndex;
	public boolean interfaceInitialized;
	public boolean inside;
	public boolean door;
	public int currentlySelectedInterior;
	public float doorX, doorY;
	public Piece collidedPiece;
	
	public EntityPlayer(int x, int y) {
		super(x, y, .2F, .2F);
		body = new BodyBiped(this, "rob");
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
		if (!inside && (Math.sqrt((loadedX - posX)*(loadedX - posX) + (loadedY - posY)*(loadedY - posY)) >= 25) && updated) {
			
			updated = false;
			CommunicationClient.addPacket(new PacketUpdateWorld());

		}

		try {
			if ((PixelRealms.world.getPiece((int)MouseClickListener.getXWorldMousePosition(), (int)MouseClickListener.getYWorldMousePosition())) == 0) {

				targetPiece = null;

			}
		} catch (Exception e){


		}
		
		if (MouseClickListener.isPressed() && !MouseClickListener.rightClick && punchingIndex == 0 && !punching && !punchEnacted && !interfaceManager.getIntercept()) {
			punchingIndex = body.addAction(new BipedActionPunching(body));
			CommunicationClient.addPacket(new PacketEntityAnimation(0, false));
			punching = true;
			
		} else if (!MouseClickListener.isPressed() && !MouseClickListener.rightClick && punchingIndex != 0 && punchEnacted) {
			body.removeAction(punchingIndex);
			CommunicationClient.addPacket(new PacketEntityAnimation(0, true));
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
		
		PlayerMotionManager.checkMovement(this);

		super.tick(w);
		
		World.globalOffsetX = (int)(Display.getWidth()/2)-(int)(posX * World.tileConstant);
		World.globalOffsetY = (int)(Display.getHeight()/2)-(int)(posY * World.tileConstant);
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
		
		Item.items[selectedItem.item.id].onSwing(this);

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
				((EntityOnlinePlayer) entityAlive).damage(w, tempDamage, this, false);
			else 
				entityAlive.damage(w, tempDamage, this, false);

		} else {
			Piece p = CollisionBox.testPiecesAgainstCollisionBox(clickScope, w);
			if (p != null) {
				if (p.canBeDamaged(selectedItem.item))
					p.damageWithItem(tempDamage, w, selectedItem.item);
			}
		}
	}

	public void testLight() {
		
		if (lightID == -1) 
			new PixelLight(posX, posY, 400, 400, PixelLightType.DEFAULT, this);
	
	}

	public void render(GameContainer c, Graphics g, World w) {
		
		if (lightID != -1) {
			
			PixelLightingManager.lights.get(lightID).posX = posX;
			PixelLightingManager.lights.get(lightID).posY = posY;

		}
		
		if (PanelWorld.loadingScreenDone && !interfaceInitialized) {
			
			interfaceInitialized = true;
			interfaceManager.initializeInterface();
			
		}
		super.render(c, g, w);
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

	public void setSelectedItem(ItemStack itemStack) {
		super.setSelectedItem(itemStack);

		if (itemStack != null) {

			if (this.selectedItem.item.id == 23) {

				interfaceManager.structureOnMouse = new GUIStructureOnMouse((int)MouseClickListener.posX, (int)MouseClickListener.posY, 0);
				GUI.addGUIComponent(interfaceManager.structureOnMouse);

			} else if (interfaceManager.structureOnMouse != null) {

				GUI.removeGUIComponent(interfaceManager.structureOnMouse);
				interfaceManager.structureOnMouse = null;

			}

			if (this.selectedItem.item.pieceID != 0) {

				if (interfaceManager.pieceOnMouse != null)
					GUI.removeGUIComponent(interfaceManager.pieceOnMouse);

				interfaceManager.pieceOnMouse = new GUIPieceOnMouse((int)MouseClickListener.posX, (int)MouseClickListener.posY, this.selectedItem.item.pieceID);
				GUI.addGUIComponent(interfaceManager.pieceOnMouse);

			} else if (interfaceManager.pieceOnMouse != null) {

				GUI.removeGUIComponent(interfaceManager.pieceOnMouse);
				interfaceManager.pieceOnMouse = null;

			}
			
		} else {
			
			if (interfaceManager.structureOnMouse != null) {

				GUI.removeGUIComponent(interfaceManager.structureOnMouse);
				interfaceManager.structureOnMouse = null;

			}
			
			if (interfaceManager.pieceOnMouse != null) {

				GUI.removeGUIComponent(interfaceManager.pieceOnMouse);
				interfaceManager.pieceOnMouse = null;

			}
			
		}


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

	public void onMouseReleased(int x, int y, int button) {

		int posX = (int)MouseClickListener.getXWorldMousePosition();
		int posY = (int)MouseClickListener.getYWorldMousePosition();
		if (button == 1) {
			
			if (PixelRealms.world.getPiece(posX, posY) == 0 && selectedItem.item.pieceID != 0) {
				
				if (worldID != -1) 
					CommunicationClient.addPacket(new PacketUpdateInteriorPiece(worldID, new Piece(posX, posY, selectedItem.item.pieceID, false)));
				else
					CommunicationClient.addPacket(new PacketChangePiece(new Piece(posX, posY, selectedItem.item.pieceID, false)));
		
				inventory.hotbar.depleteContent(GUIHotbar.selectedSlot.x, GUIHotbar.selectedSlot.y, 1);

			} else {
				
				if (selectedItem.item.id == 23) {
					
					posX = (int) ((MouseClickListener.posX - World.globalOffsetX) / World.tileConstant);
					posY = (int) ((MouseClickListener.posY - World.globalOffsetY) / World.tileConstant) - 1;
					
					if (Building.canBuildingFit(0, (int)posX, (int)posY)) {
						CommunicationClient.addPacket(new PacketChangePiece(26, 0, (int)posX, (int)posY, 1));
						
					}

					inventory.hotbar.depleteContent(GUIHotbar.selectedSlot.x, GUIHotbar.selectedSlot.y, 1);
					
				}
				
			}
			
		}
		
	}


}