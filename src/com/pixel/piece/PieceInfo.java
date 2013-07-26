package com.pixel.piece;

import com.pixel.entity.Entity;
import com.pixel.entity.EntityPlayer;
import com.pixel.frame.MainFrame;
import com.pixel.item.Item;
import com.pixel.item.ItemStack;
import com.pixel.sound.Sound;
import com.pixel.start.TextureLoader;
import com.pixel.tile.Material;
import com.pixel.world.World;

import javax.sound.sampled.Clip;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class PieceInfo {
	
	public PieceInfo(String t) {
		texture = t;
	}
	
	public void render(GameContainer c, Graphics g, World w, Piece p) {

		if (image != null) 
			image.draw(p.posX*World.tileConstant+World.globalOffsetX, p.posY*World.tileConstant+World.globalOffsetY, World.tileConstant, World.tileConstant);
		else {
			if (texture != null && texture.length() > 0) {
				image = TextureLoader.load(texture);
				image.draw(p.posX*World.tileConstant+World.globalOffsetX, p.posY*World.tileConstant+World.globalOffsetY, World.tileConstant, World.tileConstant);
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
//		if (dropItemStack != null) {
//			pickupSound.setFramePosition(0);
//			pickupSound.start();
//			player.giveItem(dropItemStack);
//		}
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
	
	public void onMouseOver() {
		if (rolloverItem != null) {
			MainFrame.setCursorToItem(rolloverItem);
		} else {
			MainFrame.setCursorDefault();
		}
	}
	
	public PieceInfo setMaterial(Material m) {
		material = m;
		return this;
	}
	
	public Image image;
	public String texture;
	public boolean shouldCollide, isCollectable = true;;
	public boolean playerInInteractionZone;
	public int dropPiece;
	public float width = 0.8F, height = 0.15F, xOffset = 0.1F, yOffset = 0.85F;
	public int maxDamage = 1;
	public ItemStack dropItemStack = null;
	public Item rolloverItem = null;
	public Material material = Material.VEGITATION;
	public static Clip pickupSound = Sound.getEffect(Sound.Effect.PICKUP_DEFAULT);
}
