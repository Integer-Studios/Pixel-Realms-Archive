package com.pixel.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.inventory.CraftingPage;
import com.pixel.item.Item;

public class GUICraftingPage extends GUIComponentSet {
	
	public GUICraftingSlot slot;
	public GUIItemStackPreview itemImage;
	public GUIInventorySlot itemSlot;
	public CraftingPage page;
	boolean satisfied = false;
	
	public GUICraftingPage(int x, int y, GUICraftingSlot slot, CraftingPage page) {
		super(x, y, 180, 166, new GUIComponent[]{
				new GUIItemStackPreview(x+105, y+30, page.item),
				new GUIComponentText(page.name, x+103, y+85, 20, Color.black),
				new GUIRecipeSlot(x+17, y+12),
				new GUIRecipeSlot(x+47, y+12),
				new GUIRecipeSlot(x+17, y+42),
				new GUIRecipeSlot(x+47, y+42),
				new GUIRecipeSlot(x+17, y+72),
				new GUIRecipeSlot(x+47, y+72),
				new GUIRecipeSlot(x+17, y+102),
				new GUIRecipeSlot(x+47, y+102),
		});
		
		this.slot = slot;
		this.page = page;
		itemImage = new GUIItemStackPreview(x+105, y+30, page.item);
		itemSlot = new GUIFreeInventorySlot(x+105, y+30, page.item);

		for (int i = 0; i < page.itemstacks.length; i++) {
			((GUIRecipeSlot)components[i+2]).setRecipe(page.itemstacks[i].item, 0, page.itemstacks[i].size);
		}
		
	}
	
	public void setPage(CraftingPage page) {
		this.page = page;
		((GUIItemStackPreview)this.components[0]).setItem(page.item);
		((GUIComponentText)this.components[1]).setText(page.name);
		
		itemImage.setItem(page.item);
		itemSlot.setItem(page.item);

		for (int i = 0; i < page.itemstacks.length; i++) {
			((GUIRecipeSlot)components[i+2]).setRecipe(page.itemstacks[i].item, 0, page.itemstacks[i].size);
		}
		for (int i = page.itemstacks.length + 2; i < this.components.length; i++) {
			((GUIRecipeSlot)components[i]).setRecipe(Item.blank, 0, 0);
		}
		
	}
	
	public void render(GameContainer c, Graphics g) {
		
		for (int i = 0; i < slot.itemsInCrafting.size(); i++) {
			((GUIRecipeSlot)components[2]).applyStack(slot.itemsInCrafting.get(i));
			((GUIRecipeSlot)components[3]).applyStack(slot.itemsInCrafting.get(i));
			((GUIRecipeSlot)components[4]).applyStack(slot.itemsInCrafting.get(i));
			((GUIRecipeSlot)components[5]).applyStack(slot.itemsInCrafting.get(i));
			((GUIRecipeSlot)components[6]).applyStack(slot.itemsInCrafting.get(i));
			((GUIRecipeSlot)components[7]).applyStack(slot.itemsInCrafting.get(i));
			((GUIRecipeSlot)components[8]).applyStack(slot.itemsInCrafting.get(i));
			((GUIRecipeSlot)components[9]).applyStack(slot.itemsInCrafting.get(i));
		}
		
		
		if (satisfied) {
			itemImage = new GUIItemStackPreview(x+105, y+30, page.item);
		} else {
			itemSlot = new GUIFreeInventorySlot(x+105, y+30, page.item);
		}
		
		
		satisfied = true;
		for (int i = 2; i < components.length; i++) {
			if (!((GUIRecipeSlot)components[i]).isSatisfied) {
				satisfied = false;
			}
		}
		
		
		if (satisfied) {
			components[0] = itemSlot;
			if (((GUIItemStack)itemSlot.components[1]).itemstack.size == 0) {
				for (int i = 2; i < components.length; i++) {
					if (((GUIRecipeSlot)components[i]).getItemStack().item.id != 0) {
						slot.drainSlotForItemStack(((GUIRecipeSlot)components[i]).getItemStack());
					}
				}
				satisfied = false;
			}
		} else {
			components[0] = itemImage;
		}
		
		
		super.render(c, g);
	}

}
