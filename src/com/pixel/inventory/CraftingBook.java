package com.pixel.inventory;

import com.pixel.item.Item;
import com.pixel.item.ItemStack;

public class CraftingBook {
	
	public CraftingPage[] pages;
	
	public CraftingBook(CraftingPage[] pages) {
		this.pages = pages;
	}
	
	public static CraftingBook defaultBook = new CraftingBook(new CraftingPage[]{
			new CraftingPage(new ItemStack(Item.sharpenedRock, 1), "Sharp Rock", new ItemStack[]{
				new ItemStack(Item.rock, 1),
			}),
			new CraftingPage(new ItemStack(Item.stick, 10), "Stick", new ItemStack[]{
				new ItemStack(Item.logPine, 1),
			}),
			new CraftingPage(new ItemStack(Item.testSword, 1), "Stone Sword", new ItemStack[]{
				new ItemStack(Item.rock, 2),
				new ItemStack(Item.stick, 1)
			}),
			new CraftingPage(new ItemStack(Item.testPick, 1), "Stone Pick", new ItemStack[]{
				new ItemStack(Item.rock, 3),
				new ItemStack(Item.stick, 1),
			}),
			new CraftingPage(new ItemStack(Item.testAxe, 1), "Stone Axe", new ItemStack[]{
				new ItemStack(Item.rock, 3),
				new ItemStack(Item.stick, 1)
			}),
			new CraftingPage(new ItemStack(Item.cabinCornerstone, 1), "Cornerstone", new ItemStack[]{
				new ItemStack(Item.rock, 1),
			}),
			
	});

}
