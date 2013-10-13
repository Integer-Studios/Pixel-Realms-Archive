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
				if (r.overlaps(p.getCollisionBox()) && Piece.info[p.id].isCollectable) {
					
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
		
		if (e.getCollisionBox().overlaps(boxOne)) {
			if (b) {
				if (e.getPreviousX() > boxOne.getX() && e.getPreviousX() < boxOne.getX() + boxOne.getWidth()) {
					int diff;
					if (e.getPreviousY() < e.getY()) {
						diff = -1;
					} else  if (e.getPreviousY() > e.getY()) {
						diff = 1;
					} else {
						diff = 0;
					}
					float borderTop = boxOne.getY();
					float borderBottom = boxOne.getY() + boxOne.getHeight();
					if (Math.abs(e.getPreviousY()-borderTop) < Math.abs(e.getPreviousY()-borderBottom)) {
						if (diff == -1)
							e.setY(e.getPreviousY());
					} else {
						if (diff == 1)
							e.setY(e.getPreviousY());
					}
				} else
				if (e.getPreviousY() > boxOne.getY() && e.getPreviousY() < boxOne.getY() + boxOne.getHeight()) {
					int diff;
					if (e.getPreviousX() < e.getX()) {
						diff = -1;
					} else  if (e.getPreviousX() > e.getX()) {
						diff = 1;
					} else {
						diff = 0;
					}
					float borderLeft = boxOne.getX();
					float borderRight = boxOne.getX() + boxOne.getWidth();
					if (Math.abs(e.getPreviousX()-borderLeft) < Math.abs(e.getPreviousX()-borderRight)) {
						if (diff == -1)
							e.setX(e.getPreviousX());
					} else {
						if (diff == 1)
							e.setX(e.getPreviousX());
					}
				} 
			}
			return true;
		} 
		return false;
		
	}
	
	public static boolean testPieceAgainstEntity(Piece p, Entity e, World w, boolean b) {
		if (e.getCollisionBox().overlaps(p.getCollisionBox())) {
			if (b) {
				if (e.getPreviousX() > p.getCollisionBox().getX() && e.getPreviousX() < p.getCollisionBox().getX() + p.getCollisionBox().getWidth()) {
					int diff;
					if (e.getPreviousY() < e.getY()) {
						diff = -1;
					} else  if (e.getPreviousY() > e.getY()) {
						diff = 1;
					} else {
						diff = 0;
					}
					float borderTop = p.getCollisionBox().getY();
					float borderBottom = p.getCollisionBox().getY() + p.getCollisionBox().getHeight();
					if (Math.abs(e.getPreviousY()-borderTop) < Math.abs(e.getPreviousY()-borderBottom)) {
						if (diff == -1)
							e.setY(e.getPreviousY());
					} else {
						if (diff == 1)
							e.setY(e.getPreviousY());
					}
				} else
				if (e.getPreviousY() > p.getCollisionBox().getY() && e.getPreviousY() < p.getCollisionBox().getY() + p.getCollisionBox().getHeight()) {
					int diff;
					if (e.getPreviousX() < e.getX()) {
						diff = -1;
					} else  if (e.getPreviousX() > e.getX()) {
						diff = 1;
					} else {
						diff = 0;
					}
					float borderLeft = p.getCollisionBox().getX();
					float borderRight = p.getCollisionBox().getX() + p.getCollisionBox().getWidth();
					if (Math.abs(e.getPreviousX()-borderLeft) < Math.abs(e.getPreviousX()-borderRight)) {
						if (diff == -1)
							e.setX(e.getPreviousX());
					} else {
						if (diff == 1)
							e.setX(e.getPreviousX());
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
		if (e.getCollisionBox().overlaps(p.getCollisionBox())) {
			if (e.getPreviousX() > p.getCollisionBox().getX() && e.getPreviousX() < p.getCollisionBox().getX() + p.getCollisionBox().getWidth()) {
				int diff;
				if (e.getPreviousY() < e.getY()) {
					diff = -1;
				} else  if (e.getPreviousY() > e.getY()) {
					diff = 1;
				} else {
					diff = 0;
				}
				float borderTop = p.getCollisionBox().getY();
				float borderBottom = p.getCollisionBox().getY() + p.getCollisionBox().getHeight();
				if (Math.abs(e.getPreviousY()-borderTop) < Math.abs(e.getPreviousY()-borderBottom)) {
					if (diff == -1)
						e.setY(e.getPreviousY());
				} else {
					if (diff == 1)
						e.setY(e.getPreviousY());
				}
			} else
			if (e.getPreviousY() > p.getCollisionBox().getY() && e.getPreviousY() < p.getCollisionBox().getY() + p.getCollisionBox().getHeight()) {
				int diff;
				if (e.getPreviousX() < e.getX()) {
					diff = -1;
				} else  if (e.getPreviousX() > e.getX()) {
					diff = 1;
				} else {
					diff = 0;
				}
				float borderLeft = p.getCollisionBox().getX();
				float borderRight = p.getCollisionBox().getX() + p.getCollisionBox().getWidth();
				if (Math.abs(e.getPreviousX()-borderLeft) < Math.abs(e.getPreviousX()-borderRight)) {
					if (diff == -1)
						e.setX(e.getPreviousX());
				} else {
					if (diff == 1)
						e.setX(e.getPreviousX());
				}
			} 
		}
		return false;
	}

}
