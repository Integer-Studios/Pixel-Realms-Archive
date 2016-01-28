package com.pixel.piece;

import com.pixel.entity.Entity;
import com.pixel.entity.EntityPlayer;
import com.pixel.item.Item;
import com.pixel.item.ItemStack;
import com.pixel.sound.PixelEffect;
import com.pixel.sound.PixelSoundManager;
import com.pixel.start.TextureLoader;
import com.pixel.tile.Material;
import com.pixel.world.World;
import com.pixel.world.WorldManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class PieceInfo {
	
	public PieceInfo(String t) {
		texture = t;
		if (texture != null && texture.length() > 0) {
			image = TextureLoader.load(texture);
		}

	}

	public void render(GameContainer c, Graphics g, World w, Piece p) {

		if (image != null) 
			image.draw(p.posX*World.tileConstant+WorldManager.getWorld().globalOffsetX, p.posY*World.tileConstant+WorldManager.getWorld().globalOffsetY, World.tileConstant, World.tileConstant);
		else {
			if (texture != null && texture.length() > 0) {
				image = TextureLoader.load(texture);
				image.draw(p.posX*World.tileConstant+WorldManager.getWorld().globalOffsetX, p.posY*World.tileConstant+WorldManager.getWorld().globalOffsetY, World.tileConstant, World.tileConstant);
			}
		}

	}


	public void tick(World w, Piece p) {

	}

	public void onCreated(Piece p) {
		
	}
	
	public void onEntityCollided(World w, Piece p, Entity entity) {

	}
	
	public void onPlayerCollided(World w, Piece p, EntityPlayer player) { 
			
	}
	
	public void onPlayerHitting(World w, Piece p, EntityPlayer player) {
	
	}
	
	public void onDestroyed(World w, Piece p, EntityPlayer player) {
		if (dropItemStack != null) {
			PixelSoundManager.createEffect(PixelEffect.PICKUP_DEFAULT).start();
			player.giveItem(dropItemStack);
		}
	}
	
	public void setPlayerInInteractionZone(boolean b) {
		playerInInteractionZone = b;
	}
	
	public boolean getPlayerInInteractionZone() {
		return playerInInteractionZone;
	}

	public PieceInfo setShouldCollide(boolean b) {
		shouldCollide = b;
		return this;
	}

	
	public PieceInfo setSize(float x, float y, float w, float h) {
		xOffset = x;
		yOffset = y;
		width = w;
		height = h;
		return this;
	}
	
	public PieceInfo setDropPiece(int p) {
		dropPiece = p;
		return this;
	}
	
	public PieceInfo setDropItemStack(ItemStack i) {
		dropItemStack = i;
		return this;
	}
	
	public ItemStack getDropItemStack() {
		return dropItemStack;
	}
	
	public PieceInfo setMaxDamage(int i) {
		maxDamage = i;	
		return this;
	}
	
	public PieceInfo setIsCollectable(boolean b) {
		isCollectable = b;
		return this;
	}
	
	public PieceInfo setRolloverItem(Item i) {
		rolloverItem = i;
		return this;
	}
	
	public PieceInfo setMaterial(Material m) {
		material = m;
		return this;
	}
	
	public Image image;
	public String texture;
	public boolean shouldCollide = false, isCollectable = true;;
	public boolean playerInInteractionZone;
	public int dropPiece;
	public float width = 0.8F, height = 0.15F, xOffset = 0.1F, yOffset = 0.85F;
	public int maxDamage = 1;
	public ItemStack dropItemStack = null;
	public Item rolloverItem = null;
	public Material material = Material.VEGITATION;
//	public static Clip pickupSound = Sound.getEffect(Sound.PixelEffect.PICKUP_DEFAULT);
}
