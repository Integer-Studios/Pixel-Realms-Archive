package com.pixel.gui;

import com.pixel.item.ItemStack;

public class GUIItemStackPreview extends GUIComponentSet {

	public ItemStack itemstack;
	
	public GUIItemStackPreview(int x, int y, ItemStack itemstack) {
		super(x, y, 48, 48, new GUIComponent[]{
			new GUIComponent(x, y, 48, 48, itemstack.getTexture()),
			new GUIComponentText("" + itemstack.size, x+26, y+26, 30)
		});
		this.itemstack = itemstack;

		if (itemstack.size <= 1) {
			((GUIComponentText)components[1]).setText("");
		}
		
	}

}
