package com.pixel.inventory;

import com.pixel.item.ItemStack;

public class InventoryContent {
	
	public InventoryContent(int x, int y, ItemStack i) {
		this.x = x;
		this.y = y;
		itemstack = i;
	}

	public int x, y;
	public ItemStack itemstack;
}
