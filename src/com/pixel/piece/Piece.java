package com.pixel.piece;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.badlogic.gdx.math.Rectangle;
import com.pixel.communication.CommunicationClient;
import com.pixel.communication.packet.PacketDamagePiece;
import com.pixel.input.MouseClickListener;
import com.pixel.item.Item;
import com.pixel.item.ItemStack;
import com.pixel.player.PlayerMotionManager;
import com.pixel.sound.PixelEffect;
import com.pixel.sound.PixelSoundManager;
import com.pixel.tile.Material;
import com.pixel.util.CollisionBox;
import com.pixel.util.Toolkit;
import com.pixel.world.World;
import com.pixel.world.WorldManager;

public class Piece {
	
	public Piece(int x, int y, int i, boolean propagate) {
		id = i;
		posX = x;
		posY = y;
		damage = info[id].maxDamage;
		info[id].onCreated(this);
		collisionBox = new Rectangle(posX + info[id].xOffset, posY + info[id].yOffset, info[id].width, info[id].height);
	
		if (propagate)
			WorldManager.propagatePiece(this);
	
	}
	
	public Piece(int x, int y, int i, int damage, int metadata, int lightID, boolean propagate) {
		id = i;
		posX = x;
		posY = y;
		this.damage = damage;
		this.metadata = metadata;
		this.lightID = lightID;
		info[id].onCreated(this);
		collisionBox = new Rectangle(posX + info[id].xOffset, posY + info[id].yOffset, info[id].width, info[id].height);

		if (propagate)
			WorldManager.propagatePiece(this);
		
	}
	
	public void render(GameContainer c, Graphics g, World w) {
		info[id].render(c, g, w, this);
	}
	
	public void tick(World w) {
		info[id].tick(w, this);
		if (info[id].shouldCollide) {
			boolean flag = false;
			if (playerInCollidedPosition && !WorldManager.player.isMoving()) {
				info[id].setPlayerInInteractionZone(true);
				flag = true;
			} else if (playerInCollidedPosition && WorldManager.player.isMoving()) {
				flag = false;
			}
			if (CollisionBox.testPieceAgainstEntity(this, WorldManager.player, w, true)) {
				if (playerInCollidedPosition) {
					info[id].setPlayerInInteractionZone(true);
				} else {
					info[id].onPlayerCollided(w, this, WorldManager.player);
					PlayerMotionManager.onCollidedWithPiece(WorldManager.player, posX, posY);
				}
				playerInCollidedPosition= true;
				flag = true;
			}
//			for (int x = 0; x < World.entities.size(); x++) {
//				if (CollisionBox.testPieceAgainstEntity(this, (Entity) World.entities.values().toArray()[x], w, true)) {
//					info[id].onEntityCollided(w, this,(Entity) World.entities.values().toArray()[x]);
//				}
//				
//			}
			playerInCollidedPosition = flag;
			info[id].setPlayerInInteractionZone(playerInCollidedPosition);
		} else {
			if (CollisionBox.testPieceAgainstEntity(this, WorldManager.player, w, false)) {
				info[id].onPlayerCollided(w, this, WorldManager.player);
				WorldManager.player.onOverPiece(w, posX, posY);
			}
//			try {
//				for (int x = 0; x < World.entities.size(); x++) {
//					if (CollisionBox.testPieceAgainstEntity(this, (Entity) World.entities.values().toArray()[x], w, false)) {
//						info[id].onEntityCollided(w, this,(Entity) World.entities.values().toArray()[x]);
//					}
//
//				} 
//			} catch (NullPointerException e) {
//
//
//				
//			}
		}
		
		if ((int)MouseClickListener.getXWorldMousePosition() == posX && (int)MouseClickListener.getYWorldMousePosition() == posY) {

			if (MouseClickListener.onMouseUp && id != 0) {
				WorldManager.player.setTargetPiece(this);
			}
			
		}
		
	}
		
	public void damage(int i, World w) {
		
		if (id == 0) 
			return;
		
		info[id].onPlayerHitting(w, this, WorldManager.player);
		damage -= i;
		
		PixelSoundManager.createEffect(PixelEffect.HIT_DEFAULT).start();
		
		if (damage < 0)
			damage = 0;
		
		CommunicationClient.addPacket(new PacketDamagePiece(posX, posY, i, 0));
	}
	
	public void damageWithItem(int i, World w, Item item) {
		if (item.specialtyMaterial == info[id].material) {
			i += item.specialtyDamage;
		}
		damage(i, w);
	}
	
	public Rectangle getCollisionBox() {
		return collisionBox;
	}
	
	static {
		Toolkit t = new Toolkit();
		String path = t.getPath();
		info = new PieceInfo[]{
				new PieceBlank().setIsCollectable(false),//0
				new PieceInfo(path+"resources/pieces/vegitation/grass_1.png").setIsCollectable(false),//1
				new PieceInfo(path+"resources/pieces/vegitation/grass_2.png").setIsCollectable(false),//2
				new PieceInfo(path+"resources/pieces/vegitation/flower_1.png").setDropItemStack(new ItemStack(Item.flowerPurple, 1)),//3
				new PieceInfo(path+"resources/pieces/rocks/rock_1.png").setShouldCollide(true).setDropItemStack(new ItemStack(Item.rock, 1)),//4
				new PieceTree(path+"resources/pieces/trees/pine/tree.png", 7).setShouldCollide(true).setMaxDamage(100).setDropPiece(6).setMaterial(Material.WOOD),//5
				new PieceDoubleHeight(path+"resources/pieces/trees/pine/stump.png").setShouldCollide(true).setMaxDamage(120).setDropItemStack(new ItemStack(Item.stumpPine, 1)).setMaterial(Material.WOOD),//6
				new PieceDoubleWidth(path+"resources/pieces/trees/pine/down.png").setShouldCollide(true).setDropPiece(8).setDropItemStack(new ItemStack(Item.branchPine, 3)),//7
				new PieceDoubleWidth(path+"resources/pieces/trees/pine/logs.png").setShouldCollide(true).setMaxDamage(20).setDropItemStack(new ItemStack(Item.logPine, 3)).setMaterial(Material.WOOD),//8
				new PieceInfo(path+"resources/pieces/rocks/rock_2.png").setShouldCollide(true).setSize(0.0F, 0.7F, 0.9F, 0.2F).setDropItemStack(new ItemStack(Item.rock, 2)).setMaterial(Material.STONE).setMaxDamage(50),//9
				new PieceTree(path+"resources/pieces/trees/apple/tree.png", 12).setShouldCollide(true).setMaxDamage(100).setDropPiece(11).setMaterial(Material.WOOD),//10
				new PieceDoubleHeight(path+"resources/pieces/trees/apple/stump.png").setShouldCollide(true).setMaxDamage(120).setDropItemStack(new ItemStack(Item.stumpApple, 1)).setMaterial(Material.WOOD),//11
				new PieceDoubleWidth(path+"resources/pieces/trees/apple/down.png").setShouldCollide(true).setDropPiece(13).setDropItemStack(new ItemStack(Item.branchApple, 3)),//12
				new PieceDoubleWidth(path+"resources/pieces/trees/apple/logs.png").setShouldCollide(true).setMaxDamage(20).setDropItemStack(new ItemStack(Item.logApple, 3)).setMaterial(Material.WOOD),//13
				new PieceCabinBasic(path+"resources/pieces/buildings/cabin_1.png").setShouldCollide(true),//14
				new PieceMultiItem(path+"resources/pieces/bodies/dead_bunny.png", new ItemStack[] {new ItemStack(Item.bunnyFoot, 1), new ItemStack(Item.bunnyLeg, 1), new ItemStack(Item.bunnyFur, 1)}),//15
				new PieceTripleHeight(path+"resources/pieces/trees/abyssal-fur.png").setShouldCollide(true).setMaxDamage(-1).setIsCollectable(false), //16
				new PieceBuildingInfo(0).setShouldCollide(true).setSize(0F, -0.4F, 4F, 1.35F),//17
				new PieceInfo(path+"resources/pieces/walls/top.png").setIsCollectable(false).setShouldCollide(true).setSize(0F, 0.1F, 1F, 1F),//18
				new PieceInfo(path+"resources/pieces/walls/left.png").setIsCollectable(false).setShouldCollide(true).setSize(-0.2F, 0F, 1.4F, 1F),//19
				new PieceInfo(path+"resources/pieces/walls/right.png").setIsCollectable(false).setShouldCollide(true).setSize(-0.2F, 0F, 1.4F, 1F),//20
				new PieceInfo(path+"resources/pieces/walls/top_left.png").setIsCollectable(false).setShouldCollide(true).setSize(0F, 0F, 1F, 1F),//21
				new PieceInfo(path+"resources/pieces/walls/top_right.png").setIsCollectable(false).setShouldCollide(true).setSize(0F, 0F, 1F, 1F),//22
				new PieceInfo(path+"resources/pieces/walls/bottom_left.png").setIsCollectable(false).setShouldCollide(true).setSize(0F, 0F, 1F, 1F),//23
				new PieceInfo(path+"resources/pieces/walls/bottom_right.png").setIsCollectable(false).setShouldCollide(true).setSize(0F, 0F, 1F, 1F),//24
				new PieceDoor(path+"resources/pieces/walls/door.png").setIsCollectable(false).setShouldCollide(true).setSize(0F, 0.1F, 1F, 1F),//25
				new PieceConstructionSiteInfo(0).setShouldCollide(true).setSize(0F, 0.4F, 4F, 0.8F),//26
				new PieceSac(path+"resources/pieces/other/sac.png").setShouldCollide(true).setDropItemStack(new ItemStack(Item.flowerPurple, 1)),//27
				new PieceSpawner(path+"resources/pieces/bodies/dead_bunny.png").setShouldCollide(false),//28
		};
		
	}
	
	public void redrawCollisionBox(boolean defaultBox) {
		
		if (defaultBox){
			
			collisionBox = new Rectangle(posX + info[id].xOffset, posY + info[id].yOffset, info[id].width, info[id].height);
			xOffset = info[id].xOffset;
			yOffset = info[id].yOffset;
			width = info[id].width;
			height = info[id].height;

			
		} else {
			
			collisionBox = new Rectangle(posX + xOffset, posY + yOffset, width, height);
			
		}
		 
	}
	
	public boolean canBeDamaged(Item item) {
		if (info[id].material == Material.VEGITATION) {
			return true;
		} else
		if (info[id].material == item.specialtyMaterial) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void setSize(float xOffset, float yOffset, float width, float height) {
		
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.width = width;
		this.height = height;
		
	}
	
	public float width = 0.8F, height = 0.15F, xOffset = 0.1F, yOffset = 0.85F;
	public int id;
	public int metadata;
	public int posX;
	public int posY;
	public int damage = 10;
	public int lightID;
	public boolean playerInCollidedPosition;
	public Rectangle collisionBox;
	public static PieceInfo[] info;
	
}
