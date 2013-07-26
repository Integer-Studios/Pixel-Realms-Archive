package com.pixel.gui;

import com.pixel.item.ItemStack;

public class GUIItemStack extends GUIComponentSet {

	public GUIItemStack(int x, int y, ItemStack i) {
		this(x, y, i, new GUIComponent[]{
				new GUIComponent(x, y, 32, 32, i.item.image),
				new GUIComponentText(""+i.size, x+18, y+13, 18)
				});
	}
	
	public GUIItemStack(int x, int y, ItemStack i,  GUIComponent[] g) {
		this(x, y, 32, 32, i, g);
	}
	
	public GUIItemStack(int x, int y, int width, int height, ItemStack i,  GUIComponent[] g) {
		super(x, y, width, height, g);
		components[0].setImage(i.item.image);
		if (i.size == 0) {
			((GUIComponentText)components[1]).setText("");
		} else if (i.size == 1){
			((GUIComponentText)components[1]).setText("");
		} else {
			((GUIComponentText)components[1]).setText(""+i.size);
		}
		itemstack = i;
	}
	
	public void setItemStack(ItemStack i) {
		itemstack = i;
		components[0].setImage(i.item.image);
		if (i.size == 0) {
			((GUIComponentText)components[1]).setText("");
		} else if (i.size == 1){
			((GUIComponentText)components[1]).setText("");
		} else {
			((GUIComponentText)components[1]).setText(""+i.size);
		}
	}
	
	public ItemStack getItemStack() {
		return itemstack;
	}
	
	ItemStack itemstack;

}
