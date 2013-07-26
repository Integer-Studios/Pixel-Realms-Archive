package com.pixel.animation;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.pixel.start.PixelRealms;
import com.pixel.start.TextureLoader;
import com.pixel.util.Toolkit;
import com.pixel.world.World;

public class RelativePositionImage {
	
	public RelativePositionImage(String texture, int width, int height, int[] x, int[] y) {
		this.texture = t.getPath() + texture;
		
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;

		frames = x.length;
	}

	public void play() {
		playing = true;
	}
	
	public void pause() {
		playing = false;
	}
	
	public void render(GameContainer c, Graphics g, World w, RelativePositionAnimation anim) {
		int posX, posY;

		if (image == null) {
			
			try {
				this.image = TextureLoader.load(texture);
				this.render(c, g, w, anim);

			} catch (RuntimeException e) {
				this.image = null;
				e.printStackTrace();
				return;
			}
		}
		
		if (playing && image != null) {
			
			posX = (int)(anim.entity.getX() * World.tileConstant + World.globalOffsetX)  + x[currentFrame];
			posY = (int)(anim.entity.getY() * World.tileConstant + World.globalOffsetY) + y[currentFrame];
			
			image.draw(posX - (width / 2), posY - (height), width, height);
			
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
			
			image.draw(posX - (width / 2), posY - (height), width, height);
		} 
		
	}
	
	public Image image;
	public int actionID;
	public String texture;
	public int width, height;
	public int[] x, y;
	public int frames, currentFrame, wait;
	public boolean playing = true;
	public Toolkit t = new Toolkit();
}
