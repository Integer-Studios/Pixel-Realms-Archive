package com.pixel.inventory;

import com.pixel.item.ItemStack;

public class CraftingPage {
	
	public ItemStack item;
	public String name;
	public ItemStack[] itemstacks;
	
	public CraftingPage(ItemStack item, String name, ItemStack[] itemstacks) {
		this.item = item;
		this.name = name;
		this.itemstacks = itemstacks;
	}

}
