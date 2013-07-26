package com.pixel.item;

import org.newdawn.slick.Image;

import com.pixel.start.PixelRealms;
import com.pixel.start.TextureLoader;
import com.pixel.tile.Material;
import com.pixel.util.Toolkit;

public class Item {
	
	public Item(int i, String s) {
		id = i;
		texture = t.getPath() + "resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "items/" + s;
		image = TextureLoader.load(texture);
		items[id] = this;
		specialtyMaterial = Material.VEGITATION;
		specialtyDamage = 0;
	}
	
	public String getTexture() {
		return texture;
	}
	
	public Item setSpecialtyMaterial(Material m) {
		specialtyMaterial = m;
		return this;
	}
	
	public Item setSpecialtyDamage(int i) {
		specialtyDamage = i;
		return this;
	}
	
	public Item setSpecialty(Material m, int i) {
		specialtyMaterial = m;
		specialtyDamage = i;
		return this;
	}
	
	public Item setDamage(int damage) {
		this.damage = damage;
		return this;
	}
	
	public static Item getItemForId(int id) {
		return items[id];
	}
	
	public static Item[] items = new Item[420];

	public static Item blank = new Item(0, "blank.png");
	public static Item sac = new Item(1, "sac.png");
	public static Item flowerPurple = new Item(2, "flower_purple.png");
	public static Item logPine = new Item(3, "log_pine.png");
	public static Item logApple = new Item(4, "log_apple.png");
	public static Item stumpPine = new Item(5, "stump_pine.png");
	public static Item stumpApple = new Item(6, "stump_apple.png");
	public static Item branchPine = new Item(7, "branch_pine.png");
	public static Item branchApple = new Item(8, "branch_apple.png");
	public static Item rock = new Item(9, "rock.png");
	public static Item bunnyFoot = new Item(10, "rabbit_foot.png");
	public static Item bunnyFur = new Item(11, "rabbit_fur.png");
	public static Item bunnyLeg = new ItemFood(12, "rabbit_leg.png", 10);
	public static Item grass = new Item(13, "grass.png");
	public static Item testSword = new Item(14, "sword.png").setDamage(30);
	public static Item testAxe = new Item(15, "axe.png").setSpecialty(Material.WOOD, 10).setDamage(15);
	public static Item testPick = new Item(16, "pick.png").setSpecialty(Material.STONE, 10).setDamage(20);
	public static Item ragaStaff = new Item(17, "ragaStaff.png").setDamage(45);
	public static Item littleChapsStoreManagementDevice = new Item(18, "little_chaps.png").setDamage(40);
	public static Item goldSword = new Item(19, "goldsword.png");
	public static Item baneOfEmera = new Item(20, "baneOfEmera.png").setDamage(50);
	public static Item sharpenedRock = new Item(21, "sharpenedRock.png").setSpecialty(Material.WOOD, 1).setDamage(4);
	public static Item stick = new Item(22, "stick.png");

	public Image image;
	public int id;
	public String texture;
	public Material specialtyMaterial;
	public int specialtyDamage;
	public int damage;
	public Toolkit t = new Toolkit();
}
