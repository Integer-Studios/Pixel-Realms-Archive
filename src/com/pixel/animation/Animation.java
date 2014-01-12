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
import org.newdawn.slick.Image;

import com.pixel.start.TextureLoader;
import com.pixel.util.Toolkit;
import com.pixel.world.World;

public class Animation {
	
	public ArrayList<Image> loadedImages = new ArrayList<Image>();
	
	public Animation(String p, float x, float y, int w, int h, int s, int sp, int sh) {
		path = p;
		posX = x;
		posY = y;
		width = w;
		height = h;
		size = s;
		speed = sp;
		shadow = sh;
		images = createImageArray(p);
		
		
		
	}
	
	private String[] createImageArray(String path) {
		String[] ret = new String[size];
		for (int i = 0; i < size; i++) {
			ret[i] = t.getPath() + path + i + ".png";
			loadedImages.add(TextureLoader.loadImage(ret[i]));
		}
		return ret;
	}
	
	public void tick() {
		animationCompleted = false;
		count ++;
		if (count == speed) {
			count = 0;
			currentFrame++;
			if (currentFrame == size) {
				currentFrame = 0;
				animationCompleted = true;
			}
		}
	}
	
	public void render(GameContainer c, Graphics g, World w) {
		if (paused) {
			Image image = loadedImages.get(0);
			image.draw((int)(posX * World.tileConstant + World.globalOffsetX) - (width / 2), (int)(posY * World.tileConstant + World.globalOffsetY - ((height) - (shadow * 4))), width, height);
			
		} else {
			tick();
			Image image = loadedImages.get(currentFrame);
			if (image == null) {
				image = loadedImages.get(lastWorkingFrame);
			} else {
				lastWorkingFrame = currentFrame;
			}
			image.draw((int)(posX * World.tileConstant + World.globalOffsetX) - (width / 2), (int)(posY * World.tileConstant + World.globalOffsetY - ((height) - (shadow * 4))), width, height);
		}
	}
	
	public void pause() {
		paused = true;
	}
	
	public void play() {
		paused = false;
	}
	
	public void setFlip(boolean b) {
		flip = b;
	}
	
	public void setPath(String p, int s, int sh) {
		size = s;
		shadow = sh;
		images = createImageArray(p);
	}
	
	public void setPath(String p) {
		path = p;
		images = createImageArray(p);
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
	
	private BufferedImage flipImage(BufferedImage image) {
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate( - image.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx,
		AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		image = op.filter(image, null);
		return image;
	}
	
	public String getPath() {
		return path;
	}

	public float posX, posY;
	public int width, height, size, speed, shadow, count, currentFrame, lastWorkingFrame;
	public String[] images;
	public String path;
	public boolean paused, flip, animationCompleted;
	public Toolkit t = new Toolkit();
}
