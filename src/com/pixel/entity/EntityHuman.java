package com.pixel.entity;

import javax.sound.sampled.Clip;

import com.pixel.body.RelativeBody;
import com.pixel.item.Item;
import com.pixel.item.ItemStack;
import com.pixel.piece.Piece;

public class EntityHuman extends EntityAlive {

	public boolean isJumping;
	public RelativeBody body;
	public boolean punching;
	public Clip walkingClip;
	public Piece targetPiece;
	public float energy = 100;
	public ItemStack selectedItem = new ItemStack(Item.blank, 0);

	public EntityHuman(float x, float y, float width, float height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isPunching() {
		return punching;
	}

	public void setTargetPiece(Piece p) {
		targetPiece = p;
	}

	public void setEnergy(float energy) {

		this.energy = energy;
		
	}
	
	public void setSelectedItem(ItemStack itemStack) {

		this.selectedItem = itemStack;
		
	}
	
	public ItemStack getSelectedItem() {

		return selectedItem;

	}
	
}
