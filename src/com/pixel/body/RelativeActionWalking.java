package com.pixel.body;

import com.pixel.animation.RelativePositionAnimation;
import com.pixel.animation.RelativePositionEquip;
import com.pixel.animation.RelativePositionImage;
import com.pixel.world.World;

public class RelativeActionWalking extends RelativeAction {

	public RelativeActionWalking(RelativeBody body) {
		super(body, new RelativePositionAnimation(body.human, new RelativePositionImage[]{
				new RelativePositionImage("resources/entities/rob/front/shadow.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/front/leftFoot.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, -2, -3, -2, 0, 2, 3, 2}), 
				new RelativePositionImage("resources/entities/rob/front/rightFoot.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 2, 3, 2, 0, -2, -3, -2}), 
				new RelativePositionImage("resources/entities/rob/front/body.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/front/leftHand.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 2, 3, 2, 0, -2, -3, -2}), 
				new RelativePositionImage("resources/entities/rob/front/rightHand.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, -2, -3, -2, 0, 2, 3, 2}),
				new RelativePositionImage("resources/entities/rob/front/head.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
				new RelativePositionEquip(body.human, new int[]{-10, -10, -10, -10, -10, -10, -10, -10}, new int[]{-11, -9, -8, -9, -11, -13, -14, -13}, new float[]{1.75F, 1.78F, 1.81F, 1.78F, 1.75F, 1.74F, 1.73F, 1.74F}),
		}, 3, 1), 
		new RelativePositionAnimation(body.human, new RelativePositionImage[]{
				new RelativePositionImage("resources/entities/rob/back/shadow.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionEquip(body.human, new int[]{10, 10, 10, 10, 10, 10, 10, 10}, new int[]{-11, -13, -14, -13, -11, -9, -8, -9}, new float[]{1.75F, 1.78F, 1.81F, 1.78F, 1.75F, 1.74F, 1.73F, 1.74F}),
				new RelativePositionImage("resources/entities/rob/back/leftFoot.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, -2, -3, -2, 0, 2, 3, 2}), 
				new RelativePositionImage("resources/entities/rob/back/rightFoot.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 2, 3, 2, 0, -2, -3, -2}), 
				new RelativePositionImage("resources/entities/rob/back/body.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/back/leftHand.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 2, 3, 2, 0, -2, -3, -2}), 
				new RelativePositionImage("resources/entities/rob/back/rightHand.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, -2, -3, -2, 0, 2, 3, 2}),
				new RelativePositionImage("resources/entities/rob/back/head.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
		}, 3, 1), 
		new RelativePositionAnimation(body.human, new RelativePositionImage[]{
				new RelativePositionImage("resources/entities/rob/side/shadow.png", 24, 42, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/side/rightFoot.png", 24, 42, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, 0, 0, 0, -1, -2, -1, 0}),
				new RelativePositionImage("resources/entities/rob/side/rightHand.png", 24, 42, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, 0, -1, 0, 0, 0, -1, 0}),
				new RelativePositionImage("resources/entities/rob/side/body.png", 24, 42, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/side/leftFoot.png", 24, 42, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{-1, -2, -1, 0, 0, 0, 0, 0}), 
				new RelativePositionEquip(body.human, new int[]{-8, -8, -8, -8, -8, -8, -8, -8}, new int[]{-11, -11, -12, -11, -11, -11, -12, -11}, new float[]{1.76F, 1.7F, 1.65F, 1.6F, 1.63F, 1.68F, 1.71F, 1.73F}),
				new RelativePositionImage("resources/entities/rob/side/leftHand.png", 24, 42, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, 0, -1, 0, 0, 0, -1, 0}), 
				new RelativePositionImage("resources/entities/rob/side/head.png", 24, 42, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
				}, 3, 1), 
		new RelativePositionAnimation(body.human, new RelativePositionImage[]{
				new RelativePositionImage("resources/entities/rob/side/shadow.png", 24, 42, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/side/rightFoot.png", 24, 42, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, 0, 0, 0, -1, -2, -1, 0}),
				new RelativePositionImage("resources/entities/rob/side/rightHand.png", 24, 42, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, 0, -1, 0, 0, 0, -1, 0}),
				new RelativePositionImage("resources/entities/rob/side/body.png", 24, 42, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/side/leftFoot.png", 24, 42, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{-1, -2, -1, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/side/leftHand.png", 24, 42, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, 0, -1, 0, 0, 0, -1, 0}), 
				new RelativePositionEquip(body.human, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{-9, -9, -10, -9, -9, -9, -10, -9}, new float[]{0.44F, 0.43F, 0.44F, 0.45F, 0.48F, 0.51F, 0.48F, 0.45F}),
				new RelativePositionImage("resources/entities/rob/side/head.png", 24, 42, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
				}, 3, 1).setFlip(true),
		new RelativePositionAnimation(body.human, new RelativePositionImage[]{
				new RelativePositionImage("resources/entities/rob/frontSide/shadow.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/frontSide/leftFoot.png", 28, 44, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, 1, 2, 1, 0, -1, -2, -1}), 
				new RelativePositionImage("resources/entities/rob/frontSide/rightFoot.png", 28, 44, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, -1, -2, -1, 0, 1, 2, 1}), 
				new RelativePositionImage("resources/entities/rob/frontSide/body.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/frontSide/leftHand.png", 28, 44, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, -1, -2, -1, 0, 1, 2, 1}), 
				new RelativePositionImage("resources/entities/rob/frontSide/rightHand.png", 28, 44, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, 1, 2, 1, 0, -1, -2, -1}),
				new RelativePositionImage("resources/entities/rob/frontSide/head.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
				new RelativePositionEquip(body.human, new int[]{-7, -8, -9, -8, -7, -6, -5, -6}, new int[]{-12, -11, -10, -11, -12, -13, -14, -13}, new float[]{0.03F, 0.06F, 0.1F, 0.1F, 0.06F, 0.03F, 0.02F, 0.03F}),
				}, 3, 1).setFlip(true),
		new RelativePositionAnimation(body.human, new RelativePositionImage[]{
				new RelativePositionImage("resources/entities/rob/frontSide/shadow.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/frontSide/leftFoot.png", 28, 44, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, 1, 2, 1, 0, -1, -2, -1}), 
				new RelativePositionImage("resources/entities/rob/frontSide/rightFoot.png", 28, 44, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, -1, -2, -1, 0, 1, 2, 1}), 
				new RelativePositionImage("resources/entities/rob/frontSide/body.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/frontSide/leftHand.png", 28, 44, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, -1, -2, -1, 0, 1, 2, 1}), 
				new RelativePositionImage("resources/entities/rob/frontSide/rightHand.png", 28, 44, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, 1, 2, 1, 0, -1, -2, -1}),
				new RelativePositionImage("resources/entities/rob/frontSide/head.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
				new RelativePositionEquip(body.human, new int[]{-10, -11, -12, -11, -10, -9, -8, -9}, new int[]{-11, -13, -14, -13, -11, -9, -8, -9}, new float[]{1.85F, 1.88F, 1.91F, 1.88F, 1.85F, 1.84F, 1.83F, 1.84F}),
				}, 3, 1),
		new RelativePositionAnimation(body.human, new RelativePositionImage[]{
				new RelativePositionImage("resources/entities/rob/backSide/shadow.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/backSide/leftFoot.png", 28, 44, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, -1, -2, -1, 0, 1, 2, 1}), 
				new RelativePositionImage("resources/entities/rob/backSide/rightFoot.png", 28, 44, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, 1, 2, 1, 0, -1, -2, -1}), 
				new RelativePositionEquip(body.human, new int[]{9, 9, 9, 9, 9, 9, 9, 9}, new int[]{-11, -12, -13, -12, -11, -10, -9, -10}, new float[]{1.8F, 1.82F, 1.85F, 1.83F, 1.77F, 1.74F, 1.73F, 1.74F}),
				new RelativePositionImage("resources/entities/rob/backSide/rightHand.png", 28, 44, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, -1, -2, -1, 0, 1, 2, 1}),
				new RelativePositionImage("resources/entities/rob/backSide/body.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/backSide/leftHand.png", 28, 44, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, 1, 2, 1, 0, -1, -2, -1}), 
				new RelativePositionImage("resources/entities/rob/backSide/head.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
		}, 3, 1),
		new RelativePositionAnimation(body.human, new RelativePositionImage[]{
				new RelativePositionImage("resources/entities/rob/backSide/shadow.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/backSide/leftFoot.png", 28, 44, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, 1, 2, 1, 0, -1, -2, -1}), 
				new RelativePositionImage("resources/entities/rob/backSide/rightFoot.png", 28, 44, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, -1, -2, -1, 0, 1, 2, 1}), 
				new RelativePositionImage("resources/entities/rob/backSide/head.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),					
				new RelativePositionImage("resources/entities/rob/backSide/rightHand.png", 28, 44, new int[]{0, -2, -3, -2, -1, 1, 2, 1}, new int[]{0, 1, 2, 1, 0, -1, -2, -1}),
				new RelativePositionImage("resources/entities/rob/backSide/body.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionEquip(body.human, new int[]{10, 10, 10, 9, 9, 10, 10, 10}, new int[]{-11, -12, -13, -12, -11, -10, -9, -10}, new float[]{0.39F, 0.43F, 0.38F, 0.34F, 0.3F, 0.28F, 0.31F, 0.35F}),
				new RelativePositionImage("resources/entities/rob/backSide/leftHand.png", 28, 44, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, -1, -2, -1, 0, 1, 2, 1}), 
		}, 3, 1).setFlip(true));
	}

	public void tick(World w) {
		if (body.human.getX() == body.human.getPreviousX() && body.human.getY() == body.human.getPreviousY()) {
			body.animation.pause();
			//			if (body.human instanceof EntityPlayer)
			//				body.human.walkingClip.stop();
		} else {
			body.animation.play();

//			if (body.human instanceof EntityPlayer) {
//				if (body.human.walkingClip == null) {
//					body.human.walkingClip = Sound.getEffect(Sound.Effect.WALKING_DEFAULT);
//				}
//				if (!body.human.walkingClip.isRunning() && waitToReplay == 0){
//					body.human.walkingClip.setFramePosition(0);
//					body.human.walkingClip.start();
//					waitToReplay = 10;
//				} else if (!body.human.walkingClip.isRunning()){
//					waitToReplay --;
//				}
//			}
		}
	}
	
	public int waitToReplay = 10;


}
//