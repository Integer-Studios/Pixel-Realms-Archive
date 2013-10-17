package com.pixel.body;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.animation.RelativePositionAnimation;
import com.pixel.entity.EntityAlive;
import com.pixel.world.World;

public class RelativeBody {
	
	public RelativeBody(EntityAlive human, String name, RelativePositionAnimation f, RelativePositionAnimation b, RelativePositionAnimation l, RelativePositionAnimation r, RelativePositionAnimation fl, RelativePositionAnimation fr, RelativePositionAnimation bl, RelativePositionAnimation br) {
		this.entity = human;
		this.front = f;
		this.back = b;
		this.left = l;
		this.right = r;
		this.frontLeft = fl;
		this.frontRight = fr;
		this.backLeft = bl;
		this.backRight = br;
		this.name = name;
		this.animation = this.front;
	}
	
	public EntityAlive entity;
	public RelativePositionAnimation animation, front, back, left, right, frontLeft, frontRight, backLeft, backRight;
	public int orientation = 0;
	public ArrayList<RelativeAction> actions = new ArrayList<RelativeAction>();
	public String name;
	
	public int addAction(RelativeAction a) {
		actions.add(a);
		reOverlayActions();
		reOrientAnimation();
		return actions.size()-1;
	}
	
	public void removeAction(int i) {
		actions.remove(i);
		reOverlayActions();
		reOrientAnimation();
	}

	public void reOverlayActions() {
		actions.get(0).overwriteWithAction();
		for (int i = 1; i < actions.size(); i++) {
			actions.get(i).addRelativeAction();
		}
	}
	
	public void reOrientAnimation() {
		switch (orientation) {
		case 0: setToFrontAnimation(); break;
		case 1: setToBackAnimation(); break;
		case 2: setToLeftAnimation(); break;
		case 3: setToRightAnimation(); break;
		case 4: setToFrontLeftAnimation(); break;
		case 5: setToFrontRightAnimation(); break;
		case 6: setToBackLeftAnimation(); break;
		case 7: setToBackRightAnimation(); break;
		default: break;
		}
	}

	public void tick(World w) {
		if (entity.getVelocityX() < 0) {
			if (entity.getVelocityY() < 0) {
				setToBackLeftAnimation();
			} else if (entity.getVelocityY() > 0) {
				setToFrontLeftAnimation();
			} else {
				setToLeftAnimation();
			}
		} else if (entity.getVelocityX() > 0) {
			if (entity.getVelocityY() < 0) {
				setToBackRightAnimation();
			} else if (entity.getVelocityY() > 0) {
				setToFrontRightAnimation();
			} else {
				setToRightAnimation();
			}
		} else if (entity.getVelocityY() < 0) {
			setToBackAnimation();
		} else if (entity.getVelocityY() > 0) {
			setToFrontAnimation();
		}
		for (int i = 0; i < actions.size(); i++) {
			actions.get(i).tick(w);
		}
	}

	
	public void lookToMouse() {
//		int x = Math.round(MouseClickListener.getXWorldMousePosition());
//		int y = Math.round(MouseClickListener.getYWorldMousePosition());
//		if (x < human.getX()) {
////			if (y < human.getY()) {
////				setToBackLeftAnimation();
////			} else if (y > human.getY()) {
////				setToFrontLeftAnimation();
////			} else {
//				setToLeftAnimation();
////			}
//		} else if (x > human.getX()){
////			if (y < human.getY()) {
////				setToBackRightAnimation();
////			} else if (y > human.getY()) {
////				setToFrontRightAnimation();
////			} else {
//				setToRightAnimation();
////			}
//		} 
//		if (y < human.getY()) {
//			setToBackAnimation();
//		} else if (y > human.getY()){
//			setToFrontAnimation();
//		}
	}
	
	public void setToFrontAnimation() {
		animation = front;
		orientation = 0;
	}
	
	public void setToBackAnimation() {
		animation = back;
		orientation = 1;
	}
	
	public void setToLeftAnimation() {
		animation = left;
		orientation = 2;
	}
	
	public void setToRightAnimation() {
		animation = right;
		orientation = 3;
	}
	
	public void setToFrontLeftAnimation() {
		animation = frontLeft;
		orientation = 4;
	}
	
	public void setToFrontRightAnimation() {
		animation = frontRight;
		orientation = 5;
	}
	
	public void setToBackLeftAnimation() {
		animation = backLeft;
		orientation = 6;
	}
	
	public void setToBackRightAnimation() {
		animation = backRight;
		orientation = 7;
	}
	
	public void render(GameContainer c, Graphics g, World w) {
		animation.render(c, g, w);
	}

}
