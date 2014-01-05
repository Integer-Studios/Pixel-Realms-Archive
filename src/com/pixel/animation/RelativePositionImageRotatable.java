package com.pixel.animation;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.item.Item;
import com.pixel.start.PixelRealms;
import com.pixel.world.World;

public class RelativePositionImageRotatable extends RelativePositionImage {
	
	public float[] rads;
	public int itemID = -1;
	public boolean flipped = true;

	public RelativePositionImageRotatable(int itemID, int width,
			int height, int[] x, int[] y, float[] rads) {
		super("", width, height, x, y);
		this.itemID = itemID;
		this.rads = rads;
	}
	
	public void render(GameContainer c, Graphics g, World w, RelativePositionAnimation anim) {
		int posX, posY;

		if (image == null) {
			
			try {
				this.image = Item.items[itemID].image.copy();
				this.render(c, g, w, anim);

			} catch (RuntimeException e) {
				this.image = null;
				e.printStackTrace();
				return;
			}
		} 
		
		if (anim.flip && !flipped) {
			System.out.println("flipped");
			flipped = true;
			this.image = image.getFlippedCopy(true, false);
		}

		int offsetX, offsetY;
		
		offsetX = 0;
		offsetY = 0 - height;
		if (anim.flip) {
			offsetX = 0 - width;
		}
		
		if (playing && image != null) {
			
			posX = (int)(anim.entity.getX() * World.tileConstant + World.globalOffsetX)  + x[currentFrame];
			posY = (int)(anim.entity.getY() * World.tileConstant + World.globalOffsetY) + y[currentFrame];
			
			
			image.setCenterOfRotation(offsetX*-1, offsetY*-1);
			image.setRotation((float) Math.toDegrees(Math.PI * rads[currentFrame]));
			
			image.draw(posX+offsetX, posY+offsetY, width, height);
			
			wait++;
			if (wait == anim.speed) {
				wait = 0;
				currentFrame++;

				if (currentFrame != 0 && currentFrame != frames && actionID == 2) {
					
					if (PixelRealms.world.player.punchEnacted && PixelRealms.world.player.punching) {
						PixelRealms.world.player.punchEnacted = false;
					}
					
				}
				
				if (currentFrame == frames) {
					if (actionID == 2) {
						
						//punch image
						if (!PixelRealms.world.player.punchEnacted && PixelRealms.world.player.punching) {
							PixelRealms.world.player.enactPunch(w);
							PixelRealms.world.player.punchEnacted = true;
						}
						
					}
					currentFrame = 0;
				}
			}
		} else if (image != null) {
			posX = (int)(anim.entity.getX() * World.tileConstant + World.globalOffsetX)  + x[0];
			posY = (int)(anim.entity.getY() * World.tileConstant + World.globalOffsetY) + y[0];
			
			
			image.setCenterOfRotation(offsetX*-1, offsetY*-1);
			image.setRotation((float) Math.toDegrees(Math.PI * rads[0]));
			
			image.draw(posX+offsetX, posY+offsetY, width, height);
		} 
		
	}
	
	public void setItemID(int itemID) {
		
		this.itemID = itemID;
		
		if (this.itemID != -1)
			flipped = false;
					
		try {
			this.image = Item.items[itemID].image.copy();

		} catch (RuntimeException e) {
			this.image = null;
			e.printStackTrace();
			return;
		}
		
	}
	

}
