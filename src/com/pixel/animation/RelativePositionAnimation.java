package com.pixel.animation;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.entity.Entity;
import com.pixel.world.World;

public class RelativePositionAnimation {
	
	public ArrayList<String> pausedImages = new ArrayList<String>();
	
	public RelativePositionAnimation(RelativePositionAnimation a, int action) {
		this.entity = a.entity;
		this.images = a.images;
		this.speed = a.speed;
		this.action = action;
		
	}
	
	public RelativePositionAnimation(Entity entity, RelativePositionImage[] images, int speed, int action) {
		this.entity = entity;
		this.images = images;
		this.speed = speed;
		this.action = action;
		
		for (int i = 0; i < images.length; i++) {
			if (images[i] != null)
				images[i].actionID = action;
		}
		
	}

	public void pause() {
	
		for (int i = 0; i < images.length; i++) {
			images[i].pause();
		}
		
	}
	
	public void play() {
		
		for (int i = 0; i < images.length; i++) {
			images[i].play();
		}
		
	}
	
	public void pause(String s) {
		
		for (int i = 0; i < images.length; i++) {
			if (images[i].texture.contains(s)) {
				images[i].pause();
			}
		}
		
	}
	
	public void play(String s) {
		
		for (int i = 0; i < images.length; i++) {
			if (images[i].texture.contains(s)) {
				images[i].play();
			}
		}
		
	}
	
	public void render(GameContainer c, Graphics g, World w) {

		for (int i = 0; i < images.length; i++) {
			images[i].render(c, g, w, this);
		}
		
		if (flip && !flipped) {
			
			for (int i = 0; i < images.length; i++) {
				if (images[i].image != null)
					images[i].image = images[i].image.getFlippedCopy(true, false);
			}
			flipped = true;
			
		} else if (!flip && flipped) {
			
			for (int i = 0; i < images.length; i++) {
				if (images[i].image != null)
					images[i].image = images[i].image.getFlippedCopy(false, false);
			}
			flipped = false;
			
		}

	}
	
	public RelativePositionAnimation setFlip(boolean b) {
		flip = b;
		return this;
	}
	
	private BufferedImage flipImage(BufferedImage image) {
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate( - image.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx,
		AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		image = op.filter(image, null);
		return image;
	}
	
	public BufferedImage readImage(String file) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(file));
		} catch (IOException e) {
			return null;
		}
		if (flip) {
			image = flipImage(image);
		}
		return image;
	}
	
	public static boolean stringContainsItemFromList(String inputString, ArrayList<String> items)
	{
	    for(int i =0; i < items.size(); i++)
	    {
	        if(inputString.contains(items.get(i)))
	        {
	            return true;
	        }
	    }
	    return false;
	}

	public int action;
	public Entity entity;
	public RelativePositionImage[] images;
	public int speed;
	public boolean flip = false;
	public boolean flipped = false;

//				if (!stringContainsItemFromList(images[i].texture, pausedImages)) {
//


}
