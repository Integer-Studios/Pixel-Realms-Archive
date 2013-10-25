package com.pixel.util;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.pixel.communication.PlayerManager;
import com.pixel.entity.Entity;
import com.pixel.entity.EntityOnlinePlayer;
import com.pixel.piece.Piece;
import com.pixel.piece.PieceBuilding;
import com.pixel.world.World;

public class CollisionBox {
	
	public CollisionBox() {
	}

	public static boolean testEntititiesInvisible(Entity entity1, Entity entity, World w) {
		
		if (entity.equals(entity1))
			return false;
		
		if (entity.getCollisionBox().overlaps(entity1.getCollisionBox())) {
			
			return true;

		}
		return false;
	}
	
	public static boolean testEntityAgainstBox(Entity e, Rectangle box, World w) {
		
		if (e.getCollisionBox().overlaps(box)) {
			
			return true;
			
		} else {
			
			return false;
			
		}
	
		
	}
	
	public static boolean testEntitiesAgainstPoint(Entity sender, float x, float y, World w) {
		
		for (int b = 0; b < World.entities.size(); b ++) {

			Entity entity = (Entity) World.entities.values().toArray()[b];
			
			if (entity.getCollisionBox().contains(x, y) && !entity.equals(sender))
				return true;

		}
		
		return false;

	}
	
	public static Entity testEntitiesAgainstCollisionBox(Rectangle r, World w) {
		
		for (EntityOnlinePlayer player : PlayerManager.players.values()) {

			if (player.getCollisionBox().overlaps(r)) {

				return player;

			}

		}
		
		for (int b = 0; b < World.entities.size(); b ++) {

			Entity entity = (Entity) World.entities.values().toArray()[b];
			
			if (entity.getCollisionBox().overlaps(r)) {
				return entity;
			}

		}
		
		return null;

	}
	
	public static int testPiecesAgainstCollisionBox(Rectangle r, World w) {
		
		ArrayList<Piece> pieces = new ArrayList<Piece>();
		pieces.addAll(World.pieces.values());
		pieces.addAll(World.buildings.values());
		
		for (Piece p : pieces) {

			if (p != null) {
				if (r.overlaps(p.getCollisionBox()) || p.getCollisionBox().contains(r)) {
					
					if (p instanceof PieceBuilding)
						return -1;
					else
						return (p.posY * World.c) + p.posX;
				}
			}

		}
		
		return -1;

	}
	
	public static boolean testBoxAgainstEntity(Entity e, Rectangle boxOne, World w, boolean b) {
		Rectangle collisionBox = new Rectangle(e.getX()+e.getVelocityX() - (e.width/2), e.getY()+e.getVelocityY() - (e.height/2), e.width, e.height);
		if (collisionBox.overlaps(boxOne)) {
			if (b) {
				if (e.getX() > boxOne.getX() && e.getX() < boxOne.getX() + boxOne.getWidth()) {
					int diff;
					if (e.getY() < e.getY() + e.getVelocityY()) {
						diff = -1;
					} else  if (e.getY() > e.getY() + e.getVelocityY()) {
						diff = 1;
					} else {
						diff = 0;
					}
					float borderTop = boxOne.getY();
					float borderBottom = boxOne.getY() + boxOne.getHeight();
					if (Math.abs(e.getY()-borderTop) < Math.abs(e.getY()-borderBottom)) {
						if (diff == -1)
							e.setVelocityY(0);
					} else {
						if (diff == 1)
							e.setVelocityY(0);
					}
				} else
				if (e.getY() > boxOne.getY() && e.getY() < boxOne.getY() + boxOne.getHeight()) {
					int diff;
					if (e.getX() < e.getX() + e.getVelocityX()) {
						diff = -1;
					} else  if (e.getX() > e.getX() + e.getVelocityX()) {
						diff = 1;
					} else {
						diff = 0;
					}
					float borderLeft = boxOne.getX();
					float borderRight = boxOne.getX() + boxOne.getWidth();
					if (Math.abs(e.getX()-borderLeft) < Math.abs(e.getX()-borderRight)) {
						if (diff == -1)
							e.setVelocityX(0);
					} else {
						if (diff == 1)
							e.setVelocityX(0);
					}
				} 
			}
			return true;
		} 
		return false;
		
	}
	
	public static boolean testPieceAgainstEntity(Piece p, Entity e, World w, boolean b) {
		Rectangle collisionBox = new Rectangle(e.getX()+e.getVelocityX() - (e.width/2), e.getY()+e.getVelocityY() - (e.height/2), e.width, e.height);
		if (collisionBox.overlaps(p.getCollisionBox())) {
			if (b) {
				if (e.getX() > p.getCollisionBox().getX() && e.getX() < p.getCollisionBox().getX() + p.getCollisionBox().getWidth()) {
					int diff;
					if (e.getY() < e.getY()+e.getVelocityY()) {
						diff = -1;
					} else  if (e.getY() > e.getY()+e.getVelocityY()) {
						diff = 1;
					} else {
						diff = 0;
					}
					float borderTop = p.getCollisionBox().getY();
					float borderBottom = p.getCollisionBox().getY() + p.getCollisionBox().getHeight();
					if (Math.abs(e.getY()-borderTop) < Math.abs(e.getY()-borderBottom)) {
						if (diff == -1)
							e.setVelocityY(0);
					} else {
						if (diff == 1)
							e.setVelocityY(0);
					}
				} else
				if (e.getY() > p.getCollisionBox().getY() && e.getY() < p.getCollisionBox().getY() + p.getCollisionBox().getHeight()) {
					int diff;
					if (e.getX() < e.getX() + e.getVelocityX()) {
						diff = -1;
					} else  if (e.getX() > e.getX() + e.getVelocityX()) {
						diff = 1;
					} else {
						diff = 0;
					}
					float borderLeft = p.getCollisionBox().getX();
					float borderRight = p.getCollisionBox().getX() + p.getCollisionBox().getWidth();
					if (Math.abs(e.getX()-borderLeft) < Math.abs(e.getX()-borderRight)) {
						if (diff == -1)
							e.setVelocityX(0);
					} else {
						if (diff == 1)
							e.setVelocityX(0);
					}
				} 
			}
			return true;
		} 
		return false;
	}
	
	public static Entity getEntityAtPoint(Entity sender, float x, float y, World w) {
		
		for (int b = 0; b < World.entities.size(); b ++) {

			Entity entity = (Entity) World.entities.values().toArray()[b];
			
			if (entity.getCollisionBox().contains(x, y) && !entity.equals(sender))
				return entity;

		}
		return null;

	}
	
	public static boolean testEntities(Entity p, Entity e, World w) {
		Rectangle collisionBoxE = new Rectangle(e.getX() + e.getVelocityX() - (e.width/2), e.getY() + e.getVelocityY() - (e.height/2), e.width, e.height);
		Rectangle collisionBoxP = new Rectangle(p.getX() + p.getVelocityX() - (p.width/2), p.getY() + p.getVelocityY() - (p.height/2), p.width, p.height);

		if (collisionBoxE.overlaps(collisionBoxP)) {
			if (e.getX() > p.getCollisionBox().getX() && e.getX() < p.getCollisionBox().getX() + p.getCollisionBox().getWidth()) {
				int diff;
				if (e.getY() < e.getY() + e.getVelocityY()) {
					diff = -1;
				} else  if (e.getY() > e.getY() + e.getVelocityY()) {
					diff = 1;
				} else {
					diff = 0;
				}
				float borderTop = p.getCollisionBox().getY();
				float borderBottom = p.getCollisionBox().getY() + p.getCollisionBox().getHeight();
				if (Math.abs(e.getY()-borderTop) < Math.abs(e.getY()-borderBottom)) {
					if (diff == -1)
						e.setVelocityY(0);
				} else {
					if (diff == 1)
						e.setVelocityY(0);
				}
			} else
			if (e.getY() > p.getCollisionBox().getY() && e.getY() < p.getCollisionBox().getY() + p.getCollisionBox().getHeight()) {
				int diff;
				if (e.getX() < e.getX() + e.getVelocityX()) {
					diff = -1;
				} else  if (e.getX() > e.getX() + e.getVelocityX()) {
					diff = 1;
				} else {
					diff = 0;
				}
				float borderLeft = p.getCollisionBox().getX();
				float borderRight = p.getCollisionBox().getX() + p.getCollisionBox().getWidth();
				if (Math.abs(e.getX()-borderLeft) < Math.abs(e.getX()-borderRight)) {
					if (diff == -1)
						e.setVelocityX(0);
				} else {
					if (diff == 1)
						e.setVelocityX(0);
				}
			} 
		}
		return false;
	}

}
