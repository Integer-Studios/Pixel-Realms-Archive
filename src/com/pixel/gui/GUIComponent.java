package com.pixel.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.pixel.start.TextureLoader;

public class GUIComponent {
	
	public Image image;
	public int x, y, width, height;
	public String texture;
	public int indexInGUI = -1;
	
	public GUIComponent(int x, int y, int width, int height, String texture) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.texture = texture;
		if (texture.length() > 0) {
			try {
				this.image = TextureLoader.load(texture);
				this.image.setFilter(Image.FILTER_NEAREST);
			} catch (RuntimeException e) {

				this.image = null;
			}

		}

	}

	public GUIComponent(int x, int y, int width, int height, Image image) {
		this.x = x;
		this.y = y;
		this.texture = "";
		this.width = width;
		this.height = height;
		this.image = image;

	}
	
	public void render(GameContainer c, Graphics g) {
		if (image != null) 
			image.draw(x,y, width, height);
		else if (texture.length() > 0 && texture.substring(0,texture.lastIndexOf("/")).length() > 0) {

			try {
				this.image = TextureLoader.load(texture);
				this.image.setFilter(Image.FILTER_NEAREST);
				image.draw(x,y, width, height);
			} catch (RuntimeException e) {

				this.image = null;
			}
			
		}
	
	}
	
	public void onAddedToGUI() {
		
	}
	
	public void setTexture(String s) { 
		texture = s;
		if (texture.length() > 0 && texture.substring(0,texture.lastIndexOf("/")).length() > 0) {
			try {
				image = TextureLoader.load(texture);
				image.setFilter(Image.FILTER_NEAREST);
			} catch (RuntimeException e) {
				image = null;

			}
		}

	}
	
	public void setImage(Image i) {
		
		image = i;
		texture = i.getResourceReference();
		
	}
	
	public String getTexture() { 
		return texture;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setWidth(int w) {
		width = w;
	}
	public void setHeight(int h) {
		height = h;
	}

}
