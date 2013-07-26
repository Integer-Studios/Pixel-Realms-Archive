package com.pixel.animation;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.pixel.start.TextureLoader;
import com.pixel.world.World;

public class RelativePositionImageRotatable extends RelativePositionImage {
	
	public float[] rads;

	public RelativePositionImageRotatable(String texture, int width,
			int height, int[] x, int[] y, float[] rads) {
		super(texture, width, height, x, y);
		this.rads = rads;
	}
	
	public void render(GameContainer c, Graphics g, World w, RelativePositionAnimation anim) {
		Image img;
		try {
		img = TextureLoader.load(texture);
		} catch (RuntimeException e) {
			return;
		}
		int /* posx, posY,*/ offsetX, offsetY;
		
		offsetX = 0;
		offsetY = 0 - height;
		if (anim.flip) {
			offsetX = 0 - width;
		}
		
		if (playing) {
			
//			posX = (int)(anim.entity.getX() * World.tileConstant + World.globalOffsetX)  + x[currentFrame];
//			posY = (int)(anim.entity.getY() * World.tileConstant + World.globalOffsetY) + y[currentFrame];
			
//			g2.translate(posX, posY);
//			g2.rotate(Math.PI * rads[currentFrame]);
			img.draw(offsetX, offsetY, width, height);
			
//			g2.rotate(-1 * Math.PI * rads[currentFrame]);
//			g2.translate(-1*posX, -1*posY);
			
			wait++;
			if (wait == anim.speed) {
				wait = 0;
				currentFrame++;
				if (currentFrame == frames) {
					currentFrame = 0;
				}
			}
		} else {
//			posX = (int)(anim.entity.getX() * World.tileConstant + World.globalOffsetX)  + x[0];
//			posY = (int)(anim.entity.getY() * World.tileConstant + World.globalOffsetY) + y[0];
			
//			g2.translate(posX, posY);
//			g2.rotate(Math.PI * rads[0]);
		
			img.draw(offsetX, offsetY, width, height);
		
//			g2.rotate(-1 * Math.PI * rads[0]);
//			g2.translate(-1*posX, -1*posY);
		}
	}

}
