package com.pixel.piece;

import com.pixel.entity.EntityPlayer;
import com.pixel.item.ItemStack;
import com.pixel.world.World;

public class PieceMultiItem extends PieceInfo {

	public PieceMultiItem(String t, ItemStack[] i) {
		super(t);
		dropItems = i;
	}
	
	public PieceInfo setDropItemStacks(ItemStack[] i) {
		dropItems = i;
		return this;
	}
	
	public ItemStack[] getDropItemStacks() {
		return dropItems;
	}
	
	public void onDestroyed(World w, Piece p, EntityPlayer player) {
		for (int i = 0; i < dropItems.length; i++) {
			dropItemStack = dropItems[i];
			if (dropItemStack != null) {
//				pickupSound.setFramePosition(0);
//				pickupSound.start();
				player.giveItem(dropItemStack);
			}
		}
	}
	
	public ItemStack[] dropItems;

}
