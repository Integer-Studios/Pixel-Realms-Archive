package com.pixel.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.item.Item;
import com.pixel.item.ItemStack;

public class GUIRecipeSlot extends GUIComponentSet {
	
	public boolean isSatisfied = false;
	public int current, needed;
	public boolean hasAppliableItemStack = false;
	public ItemStack itemstack;

	public GUIRecipeSlot(int x, int y, Item i, int current, int needed) {
		super(x, y, 26, 26, new GUIComponent[]{
				new GUIComponent(x, y, 26, 26, "resources/gui/interface/foldRight/recipeSlot.png"),
				new GUIRecipeStack(x+5, y+5, i, current, needed),
		});
		this.current = current;
		this.needed = needed;
		itemstack = new ItemStack(i, needed);
	}
	
	public GUIRecipeSlot(int x, int y) {
		super(x, y, 26, 26, new GUIComponent[]{
				new GUIComponent(x, y, 26, 26, "resources/gui/interface/foldRight/recipeSlot.png"),
				new GUIRecipeStack(x+5, y+5, Item.blank, 0, 0),
		});
		this.current = 0;
		this.needed = 0;
		itemstack = new ItemStack(Item.blank, 0);
	}
	
	public void setRecipe(Item i, int current, int needed) {
		components[1] = new GUIRecipeStack(x+5, y+5, i, current, needed);
		this.current = current;
		this.needed = needed;
		itemstack = new ItemStack(i, needed);
	}
	
	public ItemStack getItemStack() {
		return itemstack;
	}
	
	public void render(GameContainer c, Graphics g) {
		if (!hasAppliableItemStack) {
			current = 0;
			((GUIRecipeStack)components[1]).current = 0;
		}
		if (current >= needed) {
			isSatisfied = true;
		} else {
			isSatisfied = false;
		}
		hasAppliableItemStack = false;
		super.render(c, g);
	}
	
	public boolean applyStack(ItemStack is) {
		if (is.item.id == ((GUIRecipeStack)components[1]).item.id) {
			((GUIRecipeStack)components[1]).current = is.size;
			current = is.size;
			hasAppliableItemStack = true;
			return true;
		}
		return false;
	}

}
