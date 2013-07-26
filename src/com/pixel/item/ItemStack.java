package com.pixel.item;

public class ItemStack {
	
	public ItemStack(Item i, int s) {
		item = i;
		size = s;
	}
	
	public String getTexture() {
		return item.getTexture();
	}

	public Item item;
	public int size, metadata;	
}
