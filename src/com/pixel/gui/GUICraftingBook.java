package com.pixel.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.inventory.CraftingBook;

public class GUICraftingBook extends GUIComponentSet {
	
	public CraftingBook book;
	public int currentPage;

	public GUICraftingBook(int x, int y, GUICraftingSlot slot, CraftingBook book) {
		super(x, y, 180, 188, new GUIComponent[]{
				new GUIComponent(x, y, 180, 166, "resources/gui/interface/foldRight/book.png"),
				new GUICraftingPage(x, y, slot, book.pages[0]),
				new GUIArrowButton(x+30, y+170, "back"),
				new GUIArrowButton(x+136, y+170, "forward")
		});
		currentPage = 0;
		this.book = book;
	}
	
	public void render(GameContainer c, Graphics g) {
		if (((GUIArrowButton)components[2]).onMouseUp) {
			//back
			if (currentPage - 1 >= 0) {
				currentPage --;
				((GUICraftingPage)components[1]).setPage(book.pages[currentPage]);
			}
		}
		
		if (((GUIArrowButton)components[3]).onMouseUp) {
			//forward
			if (currentPage + 1 < book.pages.length) {
				currentPage ++;
				((GUICraftingPage)components[1]).setPage(book.pages[currentPage]);
			}
		}
		super.render(c, g);
	}

}
