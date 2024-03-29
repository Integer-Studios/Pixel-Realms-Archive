package com.pixel.tile;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.pixel.entity.EntityPlayer;
import com.pixel.start.TextureLoader;
import com.pixel.world.World;
import com.pixel.world.WorldManager;

public class TileInfo {
	
	public static int test = 0;
	
	public TileInfo(String t) {
		texture = t;
		if (texture != null && texture.length() > 0 && texture.endsWith("png")) {
		image = TextureLoader.load(texture);
		}
	}
	
	public void render(GameContainer c, Graphics g, World w, Tile t) {

		if (image != null)  {
			image.draw(t.posX*World.tileConstant+WorldManager.getWorld().globalOffsetX, t.posY*World.tileConstant+WorldManager.getWorld().globalOffsetY, World.tileConstant, World.tileConstant);
		} else {
			image = TextureLoader.load(texture);
			image.draw(t.posX*World.tileConstant+WorldManager.getWorld().globalOffsetX, t.posY*World.tileConstant+WorldManager.getWorld().globalOffsetY, World.tileConstant, World.tileConstant);
	
		}

	}
	
	public void tick(World w, Tile t) {
		
	}
	
	public void infoTick(World world) {
		
	}
	
	public void onPlayerWalkOver(World w, Tile t, EntityPlayer player) {
		

	}
	
	public Image image;
	public String texture;


}
