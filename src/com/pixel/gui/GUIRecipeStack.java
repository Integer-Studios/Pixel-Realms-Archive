package com.pixel.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.item.Item;
import com.pixel.item.ItemStack;

public class GUIRecipeStack extends GUIItemStack {
	
	public Item item;
	public int current, needed;

	public GUIRecipeStack(int x, int y, Item i, int current, int needed) {
		super(x, y, 16, 16, new ItemStack(i, 1), new GUIComponent[]{
				new GUIComponent(x, y, 16, 16, Item.items[i.id].image),
				new GUIComponentText(current + "/" + needed, x+0, y+0, 12)
				});
		if (current == 0 && needed == 0) {
			((GUIComponentText)components[1]).setText("");
		} else {
			((GUIComponentText)components[1]).setText(current + "/" + needed);
		}
		
		item = i;
		this.current = current;
		this.needed = needed;
	}
	
	public void setRecipe(Item i, int current, int needed) {
		
		this.item = i;
		this.current = current;
		this.needed = needed;
		components[0].image = Item.items[i.id].image;
		((GUIComponentText)this.components[1]).setText(current + "/" + needed);
		
	}
	
	public void render(GameContainer c, Graphics g) {
		if (current == 0 && needed == 0) {
			((GUIComponentText)components[1]).setText("");
		} else {
			((GUIComponentText)components[1]).setText(current + "/" + needed);
		}
		super.render(c, g);
	}

}
