package com.pixel.body;

import com.pixel.animation.RelativePositionAnimation;
import com.pixel.animation.RelativePositionEquip;
import com.pixel.animation.RelativePositionImage;
import com.pixel.world.World;

public class RelativeActionPunching extends RelativeAction {

	public RelativeActionPunching(RelativeBody body) {
		super(body, new RelativePositionAnimation(body.human, new RelativePositionImage[]{
				null, 
				null, 
				null, 
				null,
				new RelativePositionImage("resources/entities/rob/front/leftHand.png", 28, 44, new int[]{0, 2, 3, 2, 1, 0}, new int[]{0, 3, 0, -3, -1, 0}), 
				new RelativePositionImage("resources/entities/rob/front/rightHand.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0}, new int[]{-1, -1, 0, 0, 0, 0}),
				null,
				new RelativePositionEquip(body.human, new int[]{-10, -8, -7, -8, -9, -10}, new int[]{-11, -8, -11, -14, -12, -11}, new float[]{1.75F, 1.80F, 1.90F, 1.80F, 1.75F, 1.74F}),
		}, 3, 2), 
		new RelativePositionAnimation(body.human, new RelativePositionImage[]{
				null,
				new RelativePositionEquip(body.human, new int[]{10, 8, 7, 8, 10, 10}, new int[]{-11, -14, -11, -8, -11, -11}, new float[]{1.75F, 1.78F, 1.81F, 1.78F, 1.75F, 1.74F}),
				null,
				null,
				null,
				new RelativePositionImage("resources/entities/rob/back/leftHand.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0}, new int[]{0, 1, 1, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/back/rightHand.png", 28, 44, new int[]{0, -2, -3, -2, 0, 0}, new int[]{0, -3, 0, 3, 0, 0}),
				null,
		}, 3, 2), 
		new RelativePositionAnimation(body.human, new RelativePositionImage[]{
				null,
				null,
				new RelativePositionImage("resources/entities/rob/side/rightHand.png", 24, 42, new int[]{0, -6, -8, -6, 0, 0}, new int[]{0, 0, 0, 0, 0, 0}),
				null,
				null,
				new RelativePositionEquip(body.human, new int[]{-8, -10, -12, -10, -8, -8}, new int[]{-11, -11, -11, -11, -11, -11}, new float[]{1.76F, 1.6F, 1.5F, 1.4F, 1.63F, 1.68F}),
				new RelativePositionImage("resources/entities/rob/side/leftHand.png", 24, 42, new int[]{0, 1, 2, 1, 0, 0}, new int[]{0, 0, 0, 0, 0, 0}),
				null,
		}, 3, 2), 
		new RelativePositionAnimation(body.human, new RelativePositionImage[]{
				null,
				null,
				new RelativePositionImage("resources/entities/rob/side/rightHand.png", 24, 42, new int[]{0, 0, 1, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0}),
				null,
				null,
				new RelativePositionImage("resources/entities/rob/side/leftHand.png", 24, 42, new int[]{0, 4, 6, 4, 0, 0}, new int[]{0, 0, 0, 0, 0, 0}),
				null,
				new RelativePositionEquip(body.human, new int[]{-1, 3, 5, 3, -1, -1}, new int[]{-9, -9, -9, -9, -9, -9}, new float[]{0.44F, 0.50F, 0.70F, 0.6F, 0.5F, 0.4F}),
		}, 3, 2).setFlip(true),
		new RelativePositionAnimation(body.human, new RelativePositionImage[]{
				null,
				null,
				null,
				null,
				new RelativePositionImage("resources/entities/rob/frontSide/leftHand.png", 28, 44, new int[]{0, 0, 0, -1, 0, 0}, new int[]{0, 0, 0, 0, 0, -1}), 
				new RelativePositionImage("resources/entities/rob/frontSide/rightHand.png", 28, 44, new int[]{-1, 0, 1, 0, -2, -1}, new int[]{0, 0, 0, 1, 2, 0}),
				new RelativePositionEquip(body.human, new int[]{-9, -8, -7, -8, -10, -9}, new int[]{-12, -12, -12, -13, -12, -12}, new float[]{0.03F, 0.06F, 0.00F, 1.8F, 1.95F, 0.03F}),
				null,
		}, 3, 2).setFlip(true),
		new RelativePositionAnimation(body.human, new RelativePositionImage[]{
				null,
				null,
				null,
				null,
				new RelativePositionImage("resources/entities/rob/frontSide/leftHand.png", 28, 44, new int[]{0, 3, 7, 3, 2, 0}, new int[]{0, 2, 2, 1, 0, 0}), 
				new RelativePositionImage("resources/entities/rob/frontSide/rightHand.png", 28, 44, new int[]{0, -1, -1, -1, -1, 0}, new int[]{0, 0, 0, 0, 0, 0}),
				null,
				new RelativePositionEquip(body.human, new int[]{-10, -7, -3, -7, -8, -10}, new int[]{-11, -9, -9, -10, -11, -11}, new float[]{1.85F, 1.95F, 0.0F, 1.85F, 1.8F, 1.85F}),
		}, 3, 2),
		new RelativePositionAnimation(body.human, new RelativePositionImage[]{
				null,
				null,
				null,
				new RelativePositionEquip(body.human, new int[]{9, 10, 11, 10, 9, 9}, new int[]{-11, -9, -12, -13, -11, -11}, new float[]{1.8F, 1.7F, 1.65F, 1.8F, 1.84F, 1.8F}),
				new RelativePositionImage("resources/entities/rob/backSide/rightHand.png", 28, 44, new int[]{0, 1, 2, 1, 0, 0}, new int[]{0, 2, -1, -2, 0, 0}),
				null,
				new RelativePositionImage("resources/entities/rob/backSide/leftHand.png", 28, 44, new int[]{0, 0, 0, 0, 1, 0}, new int[]{0, 0, 0, 1, 0, 0}), 
				null,
		}, 3, 2),
		new RelativePositionAnimation(body.human, new RelativePositionImage[]{
				null,
				null,
				null,
				null,
				new RelativePositionImage("resources/entities/rob/backSide/rightHand.png", 28, 44, new int[]{0, -1, 0, 0, 0, 0}, new int[]{0, 0, -1, 0, 0, 0}),
				null,
				new RelativePositionEquip(body.human, new int[]{10, 13, 14, 12, 11, 10}, new int[]{-11, -12, -14, -12, -11, -11}, new float[]{0.39F, 0.43F, 0.38F, 0.34F, 0.3F, 0.2F}),
				new RelativePositionImage("resources/entities/rob/backSide/leftHand.png", 28, 44, new int[]{0, 3, 4, 2, 1, 0}, new int[]{0, -1, -3, -1, 0, 0}), 
		}, 3, 2).setFlip(true));
	}

	public void tick(World w) {

		body.animation.play();
		if (body.human.getPreviousX() == body.human.getX() && body.human.getPreviousY() == body.human.getY()) {
			body.animation.pause("head");
			body.animation.pause("body");
			body.animation.pause("leftFoot");
			body.animation.pause("rightFoot");
			body.animation.pause("shadow");
		} 
		
	}
	
}
