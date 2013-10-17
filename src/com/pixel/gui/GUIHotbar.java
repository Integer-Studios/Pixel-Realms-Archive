package com.pixel.gui;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Image;

import com.pixel.entity.EntityPlayer;
import com.pixel.sound.Sound;
import com.pixel.start.PixelRealms;
import com.pixel.start.TextureLoader;
import com.pixel.util.CoordinateKey;

public class GUIHotbar {
	
	public EntityPlayer player;
	public GUIComponent window;
	public GUIInventory inventory;
	public GUIEnergyBar energyBar;
	public Image selectedSlotImage;
	public static Image slotImage;
	public static CoordinateKey selectedSlot;
	public int originX, originY;

	public GUIHotbar(EntityPlayer player) {
		
		this.player = player;
		originX = Display.getWidth()/2-250;
		originY = 0;
		selectedSlot = new CoordinateKey(0, 0);
		slotImage = TextureLoader.load("resources/gui/inventory/slot.png");
		selectedSlotImage = TextureLoader.load("resources/gui/inventory/selectedSlot.png");
		window = new GUIComponent(originX, originY, 500, 70, "resources/gui/interface/hotbar/window.png");
		inventory = new GUIInventory(originX+6, originY+5, player.getHotbar());
		energyBar = new GUIEnergyBar(originX+12, originY+52, player);

	}

	public void tick() {
			((GUIInventorySlot) inventory.slots.get(selectedSlot)).texture = ("resources/gui/inventory/selectedSlot.png");
	}

	public void addToGUI() {
		originX = Display.getWidth()/2-250;
		originY = 0;
		
		window = new GUIComponent(originX, originY, 500, 70, "resources/gui/interface/hotbar/window.png");
		window = (GUIComponent) GUI.addGUIComponent(window);
		inventory = (GUIInventory) GUI.addGUIComponent(inventory);
		energyBar = (GUIEnergyBar) GUI.addGUIComponent(energyBar);
		
	}
	
	public void selectSlot(int x, int y) {
		
		((GUIInventorySlot) inventory.slots.get(selectedSlot)).components[0].setImage(slotImage);
		
		selectedSlot = new CoordinateKey(x, y);
		((GUIInventorySlot) inventory.slots.get(selectedSlot)).components[0].setImage(selectedSlotImage);

		if (((GUIItemStack) ((GUIInventorySlot) inventory.slots.get(selectedSlot)).components[1]).itemstack != null) {
			int id = ((GUIItemStack) ((GUIInventorySlot) inventory.slots.get(selectedSlot)).components[1]).itemstack.item.id;
			if (id == 7 || id == 8) {
				
				if (Sound.currentSong != Sound.Music.PEACE_LEAF)
					PixelRealms.playSong(Sound.Music.PEACE_LEAF);
				
				
			} else if (Sound.currentSong == Sound.Music.PEACE_LEAF){
				
				Sound.stopSong();
				
			}
			PixelRealms.world.player.setSelectedItem(((GUIItemStack) ((GUIInventorySlot) inventory.slots.get(selectedSlot)).components[1]).itemstack);
		} else {
			
			PixelRealms.world.player.setSelectedItem(null);

		}
	}
	
	public void removeFromGUI() {
		GUI.removeGUIComponent(energyBar);
		GUI.removeGUIComponent(inventory);
		GUI.removeGUIComponent(window);
		
	}
	

}
