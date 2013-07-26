package com.pixel.inventory;

import java.util.ArrayList;

import com.pixel.communication.CommunicationClient;
import com.pixel.communication.packet.PacketUpdateInventoryContent;
import com.pixel.entity.EntityPlayer;
import com.pixel.item.Item;
import com.pixel.item.ItemStack;

public class Inventory {
	
	public Inventory(EntityPlayer p, int w, int h, int id) {
		this(p, w, h, new ArrayList<InventoryContent>());
		this.id = id;
	}
	
	public Inventory(EntityPlayer p, int w, int h, ArrayList<InventoryContent> ic) {
		width = w;
		height = h;
		content = ic;
		player = p;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public InventoryContent getContent(int x, int y) {
		for (int i = 0; i < content.size(); i++) {
			if (content.get(i).x == x && content.get(i).y == y) {
				return content.get(i);
			}
		}
		return new InventoryContent(x, y, new ItemStack(Item.blank, 0));
	}
	
	public void setGUIContent() {
		if (id == 0) {
			player.interfaceManager.hotbarWindow.inventory.setInventory(this);
		} else
		if (id == 1) {
			player.interfaceManager.centerFold.leftInv.setInventory(this);
		} else
		if (id == 2) {
			player.interfaceManager.centerFold.rightInv.setInventory(this);
		}
	}
	
	public void setContent(int x, int y, ItemStack itemstack) {
		for (int i = 0; i < content.size(); i++) {
			if (content.get(i).x == x && content.get(i).y == y) {
				content.set(i, new InventoryContent(x, y, itemstack));
//				setGUIContent();
				CommunicationClient.addPacket(new PacketUpdateInventoryContent(x,y,itemstack.item.id, itemstack.size, id));
				return;
			}
		}
		content.add(new InventoryContent(x, y, itemstack));
//		setGUIContent();
		CommunicationClient.addPacket(new PacketUpdateInventoryContent(x,y,itemstack.item.id, itemstack.size, id));
	}
	
	public void depleteContent(int x, int y, int amount) {
		ItemStack is = getContent(x, y).itemstack;
		if (is.size-amount > 0)
			setContent(x, y, new ItemStack(is.item, is.size-amount));
		else
			setContent(x, y, new ItemStack(Item.blank, 0));
	}
	
	public void serverSetContent(int x, int y, ItemStack itemstack) {
		for (int i = 0; i < content.size(); i++) {
			if (content.get(i).x == x && content.get(i).y == y) {
				content.set(i, new InventoryContent(x, y, itemstack));
				setGUIContent();
				return;
			}
		}
		content.add(new InventoryContent(x, y, itemstack));
		setGUIContent();
	}
	
	public boolean addItem(Item i, int s) {
		if (updateNextCoords(new ItemStack(i, s))) {
			ItemStack its = getContent(nextX, nextY).itemstack;
			its.size = its.size + s;
			setContent(nextX, nextY, its);
			return true;
		} else if (nextX != -1) {
			setContent(nextX, nextY, new ItemStack(i, s));
			return true;
		} else {
			return false;
		}
	}
	
	public boolean updateNextCoords(ItemStack i) {
		nextX = 0;
		nextY = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (getContent(x,y).itemstack.item.id == i.item.id) {
					nextX = x;
					nextY = y;
					return true;
				}

			}
		}
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (getContent(x,y).itemstack.item.id == 0) {
					nextX = x;
					nextY = y;
					return false;
				}
			}
		}
		nextX = -1;
		nextY = -1;
		return false;
	}

	public int nextX, nextY;
	public int width, height;
	public ArrayList<InventoryContent> content;
	public EntityPlayer player;
	public int id;

}
