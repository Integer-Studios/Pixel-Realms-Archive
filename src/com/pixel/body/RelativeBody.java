package com.pixel.body;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.animation.RelativePositionAnimation;
import com.pixel.animation.RelativePositionEquip;
import com.pixel.animation.RelativePositionImage;
import com.pixel.entity.EntityHuman;
import com.pixel.world.World;

public class RelativeBody {
	
	public RelativeBody(EntityHuman human) {
		this.human = human;
		this.front = new RelativePositionAnimation(human, new RelativePositionImage[]{
				new RelativePositionImage("resources/entities/rob/front/shadow.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/front/leftFoot.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, -2, -3, -2, 0, 2, 3, 2}), 
				new RelativePositionImage("resources/entities/rob/front/rightFoot.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 2, 3, 2, 0, -2, -3, -2}), 
				new RelativePositionImage("resources/entities/rob/front/body.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/front/leftHand.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 2, 3, 2, 0, -2, -3, -2}), 
				new RelativePositionImage("resources/entities/rob/front/rightHand.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, -2, -3, -2, 0, 2, 3, 2}),
				new RelativePositionImage("resources/entities/rob/front/head.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
				new RelativePositionEquip(human, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new float[]{0.0F, 0.02F, 0.04F, 0.06F, 0.08F, 0.1F, 0.12F, 0.14F})
				}, 3, 0);
		this.back = new RelativePositionAnimation(human, new RelativePositionImage[]{
				new RelativePositionImage("resources/entities/rob/back/shadow.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionEquip(human, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new float[]{0.0F, 0.02F, 0.04F, 0.06F, 0.08F, 0.1F, 0.12F, 0.14F}),
				new RelativePositionImage("resources/entities/rob/back/leftFoot.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, -2, -3, -2, 0, 2, 3, 2}), 
				new RelativePositionImage("resources/entities/rob/back/rightFoot.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 2, 3, 2, 0, -2, -3, -2}), 
				new RelativePositionImage("resources/entities/rob/back/body.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/back/leftHand.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 2, 3, 2, 0, -2, -3, -2}), 
				new RelativePositionImage("resources/entities/rob/back/rightHand.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, -2, -3, -2, 0, 2, 3, 2}),
				new RelativePositionImage("resources/entities/rob/back/head.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
				}, 3, 0);
		this.left = new RelativePositionAnimation(human, new RelativePositionImage[]{
				new RelativePositionImage("resources/entities/rob/side/shadow.png", 24, 42, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/side/rightFoot.png", 24, 42, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, 0, 0, 0, -1, -2, -1, 0}),
				new RelativePositionImage("resources/entities/rob/side/rightHand.png", 24, 42, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, 0, -1, 0, 0, 0, -1, 0}),
				new RelativePositionImage("resources/entities/rob/side/body.png", 24, 42, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/side/leftFoot.png", 24, 42, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{-1, -2, -1, 0, 0, 0, 0, 0}), 
				new RelativePositionEquip(human, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new float[]{0.0F, 0.02F, 0.04F, 0.06F, 0.08F, 0.1F, 0.12F, 0.14F}),
				new RelativePositionImage("resources/entities/rob/side/leftHand.png", 24, 42, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, 0, -1, 0, 0, 0, -1, 0}), 
				new RelativePositionImage("resources/entities/rob/side/head.png", 24, 42, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
				}, 3, 0);
		this.right = new RelativePositionAnimation(human, new RelativePositionImage[]{
				new RelativePositionImage("resources/entities/rob/side/shadow.png", 24, 42, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/side/rightFoot.png", 24, 42, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, 0, 0, 0, -1, -2, -1, 0}),
				new RelativePositionImage("resources/entities/rob/side/rightHand.png", 24, 42, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, 0, -1, 0, 0, 0, -1, 0}),
				new RelativePositionImage("resources/entities/rob/side/body.png", 24, 42, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/side/leftFoot.png", 24, 42, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{-1, -2, -1, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/side/leftHand.png", 24, 42, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, 0, -1, 0, 0, 0, -1, 0}), 
				new RelativePositionImage("resources/entities/rob/side/head.png", 24, 42, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
				new RelativePositionEquip(human, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{-11, -11, -12, -11, -11, -11, -12, -11}, new float[]{0.44F, 0.43F, 0.44F, 0.45F, 0.48F, 0.51F, 0.48F, 0.45F}),
				}, 3, 0).setFlip(true);
		this.frontLeft = new RelativePositionAnimation(human, new RelativePositionImage[]{
				new RelativePositionImage("resources/entities/rob/frontSide/shadow.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/frontSide/leftFoot.png", 28, 44, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, -1, -2, -1, 0, 1, 2, 1}), 
				new RelativePositionImage("resources/entities/rob/frontSide/rightFoot.png", 28, 44, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, 1, 2, 1, 0, -1, -2,-10}), 
				new RelativePositionImage("resources/entities/rob/frontSide/body.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/frontSide/leftHand.png", 28, 44, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, 1, 2, 1, 0, -1, -2, -1}), 
				new RelativePositionImage("resources/entities/rob/frontSide/rightHand.png", 28, 44, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, -1, -2, -1, 0, 1, 2, 1}),
				new RelativePositionEquip(human, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new float[]{0.0F, 0.02F, 0.04F, 0.06F, 0.08F, 0.1F, 0.12F, 0.14F}),
				new RelativePositionImage("resources/entities/rob/frontSide/head.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
				}, 3, 0).setFlip(true);
		this.frontRight = new RelativePositionAnimation(human, new RelativePositionImage[]{
				new RelativePositionImage("resources/entities/rob/frontSide/shadow.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/frontSide/leftFoot.png", 28, 44, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, 1, 2, 1, 0, -1, -2, -1}), 
				new RelativePositionImage("resources/entities/rob/frontSide/rightFoot.png", 28, 44, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, -1, -2, -1, 0, 1, 2, 1}), 
				new RelativePositionImage("resources/entities/rob/frontSide/body.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/frontSide/leftHand.png", 28, 44, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, -1, -2, -1, 0, 1, 2, 1}), 
				new RelativePositionImage("resources/entities/rob/frontSide/rightHand.png", 28, 44, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, 1, 2, 1, 0, -1, -2, -1}),
				new RelativePositionImage("resources/entities/rob/frontSide/head.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
				new RelativePositionEquip(human, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new float[]{0.0F, 0.02F, 0.04F, 0.06F, 0.08F, 0.1F, 0.12F, 0.14F}),
				}, 3, 0);
		this.backLeft = new RelativePositionAnimation(human, new RelativePositionImage[]{
				new RelativePositionImage("resources/entities/rob/backSide/shadow.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/backSide/leftFoot.png", 28, 44, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, -1, -2, -1, 0, 1, 2, 1}), 
				new RelativePositionImage("resources/entities/rob/backSide/rightFoot.png", 28, 44, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, 1, 2, 1, 0, -1, -2, -1}), 
				new RelativePositionEquip(human, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new float[]{0.0F, 0.02F, 0.04F, 0.06F, 0.08F, 0.1F, 0.12F, 0.14F}),
				new RelativePositionImage("resources/entities/rob/backSide/rightHand.png", 28, 44, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, -1, -2, -1, 0, 1, 2, 1}),
				new RelativePositionImage("resources/entities/rob/backSide/body.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/backSide/leftHand.png", 28, 44, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, 1, 2, 1, 0, -1, -2, -1}), 
				new RelativePositionImage("resources/entities/rob/backSide/head.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
				}, 3, 0);
		this.backRight = new RelativePositionAnimation(human, new RelativePositionImage[]{
				new RelativePositionImage("resources/entities/rob/backSide/shadow.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/backSide/leftFoot.png", 28, 44, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, -1, -2, -1, 0, 1, 2, 1}), 
				new RelativePositionImage("resources/entities/rob/backSide/rightFoot.png", 28, 44, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, 1, 2, 1, 0, -1, -2, -1}), 
				new RelativePositionImage("resources/entities/rob/backSide/head.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
				new RelativePositionImage("resources/entities/rob/backSide/rightHand.png", 28, 44, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, -1, -2, -1, 0, 1, 2, 1}),
				new RelativePositionImage("resources/entities/rob/backSide/body.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionEquip(human, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new float[]{0.0F, 0.02F, 0.04F, 0.06F, 0.08F, 0.1F, 0.12F, 0.14F}),
				new RelativePositionImage("resources/entities/rob/backSide/leftHand.png", 28, 44, new int[]{0, 3, 4, 2, 1, 0}, new int[]{0, -1, -3, -1, 0, 0}), 

		}, 3, 0).setFlip(true);
		addAction(new RelativeActionWalking(this));
		this.animation = this.front;
	}
	
	public EntityHuman human;
	public RelativePositionAnimation animation, front, back, left, right, frontLeft, frontRight, backLeft, backRight;
	public int orientation = 0;
	public ArrayList<RelativeAction> actions = new ArrayList<RelativeAction>();
	
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
		if (human.getX() - human.getPreviousX() < 0) {
			if (human.getY() - human.getPreviousY() < 0) {
				setToBackLeftAnimation();
			} else if (human.getPreviousY() - human.getY() < 0) {
				setToFrontLeftAnimation();
			} else {
				setToLeftAnimation();
			}
		} else if (human.getPreviousX() - human.getX() < 0) {
			if (human.getY() - human.getPreviousY() < 0) {
				setToBackRightAnimation();
			} else if (human.getPreviousY() - human.getY() < 0) {
				setToFrontRightAnimation();
			} else {
				setToRightAnimation();
			}
		} else if (human.getY() - human.getPreviousY() < 0) {
			setToBackAnimation();
		} else if (human.getPreviousY() - human.getY() < 0) {
			setToFrontAnimation();
		}
		for (int i = 0; i < actions.size(); i++) {
			actions.get(i).tick(w);
		}
		if (human.isPunching()) {
			lookToMouse();
		}
	}
	
	public void selectItem(int itemID) {
		
		
		
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
