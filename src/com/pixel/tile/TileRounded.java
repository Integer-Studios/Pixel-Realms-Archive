package com.pixel.tile;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.pixel.start.TextureLoader;
import com.pixel.world.World;
import com.pixel.world.WorldManager;

public class TileRounded extends TileInfo {

	public boolean loaded;
	public Image[] images;
	
	public TileRounded(String t) {
		super(t);
		images = new Image[]{
				TextureLoader.load(texture+"_lrdu.png"),//0
				TextureLoader.load(texture+"_lr.png"),//1
				TextureLoader.load(texture+"_lr.png"),//2
				TextureLoader.load(texture+"_lrd.png"),//3
				TextureLoader.load(texture+"_lrd.png"),//4
				TextureLoader.load(texture+"_lrd.png"),//5
				TextureLoader.load(texture+"_lrd.png"),//6
				TextureLoader.load(texture+"_ld.png"),//7
				TextureLoader.load(texture+"_ld.png"),//8
				TextureLoader.load(texture+"_ld.png"),//9
				TextureLoader.load(texture+"_ld.png"),//10
				TextureLoader.load(texture+"_l.png"),//11
				TextureLoader.load(texture+"_l.png"),//12
				TextureLoader.load(texture+"_l.png"),//13
				TextureLoader.load(texture+"_l.png"),//14
				TextureLoader.load(texture+".png"),//15
		};
		
		 
		images[2].setRotation(270f);
		images[4].setRotation(180f);
		images[5].setRotation(270f);
		images[6].setRotation(90f);
		images[8].setRotation(180f);
		images[9].setRotation(270f);
		images[10].setRotation(90f);
		images[12].setRotation(180f);
		images[13].setRotation(270f);
		images[14].setRotation(90f);
					
		loaded = true;
	}
	
	public void render(GameContainer c, Graphics g, World w, Tile t) {
		
		if (!loaded) {
			images = new Image[]{
					TextureLoader.load(texture+"_lrdu.png"),//0
					TextureLoader.load(texture+"_lr.png"),//1
					TextureLoader.load(texture+"_lr.png"),//2
					TextureLoader.load(texture+"_lrd.png"),//3
					TextureLoader.load(texture+"_lrd.png"),//4
					TextureLoader.load(texture+"_lrd.png"),//5
					TextureLoader.load(texture+"_lrd.png"),//6
					TextureLoader.load(texture+"_ld.png"),//7
					TextureLoader.load(texture+"_ld.png"),//8
					TextureLoader.load(texture+"_ld.png"),//9
					TextureLoader.load(texture+"_ld.png"),//10
					TextureLoader.load(texture+"_l.png"),//11
					TextureLoader.load(texture+"_l.png"),//12
					TextureLoader.load(texture+"_l.png"),//13
					TextureLoader.load(texture+"_l.png"),//14
					TextureLoader.load(texture+".png"),//15
			};
			
			 
			images[2].setRotation(270f);
			images[4].setRotation(180f);
			images[5].setRotation(270f);
			images[6].setRotation(90f);
			images[8].setRotation(180f);
			images[9].setRotation(270f);
			images[10].setRotation(90f);
			images[12].setRotation(180f);
			images[13].setRotation(270f);
			images[14].setRotation(90f);
						
			loaded = true;
		}
		
		for (int i = 0; i < images.length; i++) {
			images[i].setCenterOfRotation(World.tileConstant/2, World.tileConstant/2);
		}
		
				
		boolean l=false,r=false,d=false,u=false;

		try {

			if (t.posX > 0 && WorldManager.getWorld().getTile(t.posX-1, t.posY) != t.id) {
				l = true;
			}
			if (t.posX < WorldManager.getWorld().c -1 && WorldManager.getWorld().getTile(t.posX+1, t.posY) != t.id) {
				r = true;
			}
			if (t.posY < WorldManager.getWorld().c -1 && WorldManager.getWorld().getTile(t.posX, t.posY+1) != t.id) {
				d = true;
			}
			if (t.posY > 0 && WorldManager.getWorld().getTile(t.posX, t.posY-1) != t.id) {
				u = true;
			}

		} catch (NullPointerException e) {
			
			//Ignore, means tile in question is not loaded (rounded tile on edge of loaded map)
			
		}

		if (l) {
			if (r) {
				if (d) {
					if (u) {
						image = images[0];
					} else {
						image = images[3];
					}
				} else if (u) {
					image = images[4];
				} else {
					image = images[1];
				}
			} else if (d) {
				if (u) {
					image = images[6];
				} else {
					image = images[7];
				}
			} else if (u) {
				image = images[10];
			} else {
				image = images[11];
			}
		} else if (r) {
			if (d) {
				if (u) {
					image = images[5];
				} else {
					image = images[9];
				}
			} else if (u) {
				image = images[8];
			} else {
				image = images[12];
			}
		} else if (d) {
			if (u) {
				image = images[2];
			} else {
				image = images[13];
			}
		} else if (u) {
			image = images[14];
		} else {
			image = images[15];
		}
		
		Tile.info[t.background].render(c, g, w, t);
		image.draw(t.posX*World.tileConstant+WorldManager.getWorld().globalOffsetX, t.posY*World.tileConstant+WorldManager.getWorld().globalOffsetY, World.tileConstant, World.tileConstant);
	}
	
	public void setTexture(String texture) {
		this.texture = texture;
	}
	
}
