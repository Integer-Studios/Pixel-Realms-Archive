package com.pixel.player;

import com.pixel.entity.EntityPlayer;
import com.pixel.inventory.Inventory;

public class PlayerInventory {
	
	public EntityPlayer player;
	public Inventory hotbar, inventoryLeft, inventoryRight;

	public PlayerInventory(EntityPlayer player) {
		this.player = player;
		hotbar = new Inventory(player, 10, 1, 0);
		inventoryLeft = new Inventory(player, 3, 6, 1);
		inventoryRight = new Inventory(player, 3, 6, 2);
	}
	

}
