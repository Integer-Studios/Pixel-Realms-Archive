package com.pixel.entity;

import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.badlogic.gdx.math.Rectangle;
import com.pixel.animation.Animation;
import com.pixel.piece.Piece;
import com.pixel.util.CollisionBox;
import com.pixel.world.World;

public class Entity {

	public float posX;
	public float posY;
	protected float prevPosX;
	protected float prevPosY;
	protected Animation animation;
	public boolean painted;
	public float width, height;
	public Rectangle collisionBox;
	public boolean collision;
	public int id;
	public int serverID;
	public int worldID = -1;
	public float health = 10.0F;
	public float floatingOffset = 0.0F;
	@SuppressWarnings("rawtypes")
	private static HashMap<Integer, Class> entityMap = new HashMap<Integer, Class>();
	
	public Entity(float width, float height) {
		
		this.width = width;
		this.height = height;
		collisionBox = new Rectangle(posX - (width/2), posY - (height/2), width, height);
		
	}
	
	public Entity(float x, float y, float width, float height) {
		posX = x;
		posY = y;
		collisionBox = new Rectangle(posX - (width/2), posY - (height/2), width, height);
		this.width = width;
		this.height = height;
		
	}
	
	public float getX() {
		return posX;
	}
	
	public void setX(float x) {
		posX = x;
	}
	
	public float getY() {
		return posY;
	}
	
	public void setY(float y) {
		posY = y;
	}
	
	public float getPreviousX() {
		return prevPosX;
	}
	
	public void setPreviousX(float x) {
		
		this.prevPosX = x;
		
	}
	
	public float getPreviousY() {
		return prevPosY;
	}
	
	public void setPreviousY(float y) {
		
		this.prevPosY = y;
		
	}
	
	public Animation getAnimation() {
		return this.animation;
	}
	
	public Rectangle getCollisionBox() {
		return collisionBox;
	}

	public void setPosition(float x, float y) {
		posX = x;
		posY = y;
	}
	
	public void tick(World w) {
		collisionBox = new Rectangle(posX - (width/2), posY - (height/2), width, height);
//		checkPieceCollision(w, 10);
		prevPosX = posX;
		prevPosY = posY;
	}
	
//	public boolean checkPieceCollision(World w, int r) {
//		int id;
//		Piece piece;
//		for (int x = (int)posX-r; x <= (int)posX+r; x++) {
//			for (int y = (int)posY-r; y <= (int)posY+r; y++) {
//				id = w.getPiece(x, y);
//				if (id != 0 && Piece.info[id].shouldCollide) {
//					piece = w.getPieceObject(x, y);
//					if (CollisionBox.testPieceAgainstEntity(piece, this, w, true)) {
//						Piece.info[id].onEntityCollided(w, piece, this);
//						return true;
//					}
//				}
//			}
//		}
//		return false;
//	}
	
	public void render(GameContainer c, Graphics g, World w) {
		if (animation != null) {
			animation.render(c, g, w);
		}
	}
	
	public boolean isInPreviousPosition() {
		return posX == prevPosX && posY == prevPosY;
	}
	
	public void spawn(int x, int y) {
		posX = x;
		posY = y;
	}
	
	public void onCollidedWithPiece(World w, int x, int y) {
		
	}
	
	public void onOverPiece(World w, int x, int y) {
		
	}
	
	@SuppressWarnings("rawtypes")
	public static Entity getEntity(int id) {
        try
        {
            Class entityClass = (Class)entityMap.get(id);
            return entityClass == null ? null : (Entity)entityClass.newInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Skipping entity with id " + id);
            return null;
        }
    } 
	
	static {
		
		entityMap.put(1, EntityBunny.class);
		
	}
	
	
}
