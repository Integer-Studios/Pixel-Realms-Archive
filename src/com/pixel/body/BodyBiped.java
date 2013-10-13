package com.pixel.body;

import com.pixel.animation.RelativePositionAnimation;
import com.pixel.animation.RelativePositionEquip;
import com.pixel.animation.RelativePositionImage;
import com.pixel.entity.EntityAlive;

public class BodyBiped extends RelativeBody {

	public BodyBiped(EntityAlive entity, String name) {
		super(entity, name,
		new RelativePositionAnimation(entity, new RelativePositionImage[]{
				new RelativePositionImage("resources/entities/"+name+"/front/shadow.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/"+name+"/front/leftFoot.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, -2, -3, -2, 0, 2, 3, 2}), 
				new RelativePositionImage("resources/entities/"+name+"/front/rightFoot.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 2, 3, 2, 0, -2, -3, -2}), 
				new RelativePositionImage("resources/entities/"+name+"/front/body.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/"+name+"/front/leftHand.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 2, 3, 2, 0, -2, -3, -2}), 
				new RelativePositionImage("resources/entities/"+name+"/front/rightHand.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, -2, -3, -2, 0, 2, 3, 2}),
				new RelativePositionImage("resources/entities/"+name+"/front/head.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
				new RelativePositionEquip(entity, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new float[]{0.0F, 0.02F, 0.04F, 0.06F, 0.08F, 0.1F, 0.12F, 0.14F})
				}, 3, 0),
		new RelativePositionAnimation(entity, new RelativePositionImage[]{
				new RelativePositionImage("resources/entities/"+name+"/back/shadow.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionEquip(entity, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new float[]{0.0F, 0.02F, 0.04F, 0.06F, 0.08F, 0.1F, 0.12F, 0.14F}),
				new RelativePositionImage("resources/entities/"+name+"/back/leftFoot.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, -2, -3, -2, 0, 2, 3, 2}), 
				new RelativePositionImage("resources/entities/"+name+"/back/rightFoot.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 2, 3, 2, 0, -2, -3, -2}), 
				new RelativePositionImage("resources/entities/"+name+"/back/body.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/"+name+"/back/leftHand.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 2, 3, 2, 0, -2, -3, -2}), 
				new RelativePositionImage("resources/entities/"+name+"/back/rightHand.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, -2, -3, -2, 0, 2, 3, 2}),
				new RelativePositionImage("resources/entities/"+name+"/back/head.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
				}, 3, 0),
		new RelativePositionAnimation(entity, new RelativePositionImage[]{
				new RelativePositionImage("resources/entities/"+name+"/side/shadow.png", 24, 42, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/"+name+"/side/rightFoot.png", 24, 42, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, 0, 0, 0, -1, -2, -1, 0}),
				new RelativePositionImage("resources/entities/"+name+"/side/rightHand.png", 24, 42, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, 0, -1, 0, 0, 0, -1, 0}),
				new RelativePositionImage("resources/entities/"+name+"/side/body.png", 24, 42, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/"+name+"/side/leftFoot.png", 24, 42, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{-1, -2, -1, 0, 0, 0, 0, 0}), 
				new RelativePositionEquip(entity, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new float[]{0.0F, 0.02F, 0.04F, 0.06F, 0.08F, 0.1F, 0.12F, 0.14F}),
				new RelativePositionImage("resources/entities/"+name+"/side/leftHand.png", 24, 42, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, 0, -1, 0, 0, 0, -1, 0}), 
				new RelativePositionImage("resources/entities/"+name+"/side/head.png", 24, 42, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
				}, 3, 0),
		new RelativePositionAnimation(entity, new RelativePositionImage[]{
				new RelativePositionImage("resources/entities/"+name+"/side/shadow.png", 24, 42, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/"+name+"/side/rightFoot.png", 24, 42, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, 0, 0, 0, -1, -2, -1, 0}),
				new RelativePositionImage("resources/entities/"+name+"/side/rightHand.png", 24, 42, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, 0, -1, 0, 0, 0, -1, 0}),
				new RelativePositionImage("resources/entities/"+name+"/side/body.png", 24, 42, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/"+name+"/side/leftFoot.png", 24, 42, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{-1, -2, -1, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/"+name+"/side/leftHand.png", 24, 42, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, 0, -1, 0, 0, 0, -1, 0}), 
				new RelativePositionImage("resources/entities/"+name+"/side/head.png", 24, 42, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
				new RelativePositionEquip(entity, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{-11, -11, -12, -11, -11, -11, -12, -11}, new float[]{0.44F, 0.43F, 0.44F, 0.45F, 0.48F, 0.51F, 0.48F, 0.45F}),
				}, 3, 0).setFlip(true),
		new RelativePositionAnimation(entity, new RelativePositionImage[]{
				new RelativePositionImage("resources/entities/"+name+"/frontSide/shadow.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/"+name+"/frontSide/leftFoot.png", 28, 44, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, -1, -2, -1, 0, 1, 2, 1}), 
				new RelativePositionImage("resources/entities/"+name+"/frontSide/rightFoot.png", 28, 44, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, 1, 2, 1, 0, -1, -2,-10}), 
				new RelativePositionImage("resources/entities/"+name+"/frontSide/body.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/"+name+"/frontSide/leftHand.png", 28, 44, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, 1, 2, 1, 0, -1, -2, -1}), 
				new RelativePositionImage("resources/entities/"+name+"/frontSide/rightHand.png", 28, 44, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, -1, -2, -1, 0, 1, 2, 1}),
				new RelativePositionEquip(entity, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new float[]{0.0F, 0.02F, 0.04F, 0.06F, 0.08F, 0.1F, 0.12F, 0.14F}),
				new RelativePositionImage("resources/entities/"+name+"/frontSide/head.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
				}, 3, 0).setFlip(true),
		new RelativePositionAnimation(entity, new RelativePositionImage[]{
				new RelativePositionImage("resources/entities/"+name+"/frontSide/shadow.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/"+name+"/frontSide/leftFoot.png", 28, 44, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, 1, 2, 1, 0, -1, -2, -1}), 
				new RelativePositionImage("resources/entities/"+name+"/frontSide/rightFoot.png", 28, 44, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, -1, -2, -1, 0, 1, 2, 1}), 
				new RelativePositionImage("resources/entities/"+name+"/frontSide/body.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/"+name+"/frontSide/leftHand.png", 28, 44, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, -1, -2, -1, 0, 1, 2, 1}), 
				new RelativePositionImage("resources/entities/"+name+"/frontSide/rightHand.png", 28, 44, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, 1, 2, 1, 0, -1, -2, -1}),
				new RelativePositionImage("resources/entities/"+name+"/frontSide/head.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
				new RelativePositionEquip(entity, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new float[]{0.0F, 0.02F, 0.04F, 0.06F, 0.08F, 0.1F, 0.12F, 0.14F}),
				}, 3, 0),
		new RelativePositionAnimation(entity, new RelativePositionImage[]{
				new RelativePositionImage("resources/entities/"+name+"/backSide/shadow.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/"+name+"/backSide/leftFoot.png", 28, 44, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, -1, -2, -1, 0, 1, 2, 1}), 
				new RelativePositionImage("resources/entities/"+name+"/backSide/rightFoot.png", 28, 44, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, 1, 2, 1, 0, -1, -2, -1}), 
				new RelativePositionEquip(entity, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new float[]{0.0F, 0.02F, 0.04F, 0.06F, 0.08F, 0.1F, 0.12F, 0.14F}),
				new RelativePositionImage("resources/entities/"+name+"/backSide/rightHand.png", 28, 44, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, -1, -2, -1, 0, 1, 2, 1}),
				new RelativePositionImage("resources/entities/"+name+"/backSide/body.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/"+name+"/backSide/leftHand.png", 28, 44, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, 1, 2, 1, 0, -1, -2, -1}), 
				new RelativePositionImage("resources/entities/"+name+"/backSide/head.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
				}, 3, 0),
		new RelativePositionAnimation(entity, new RelativePositionImage[]{
				new RelativePositionImage("resources/entities/"+name+"/backSide/shadow.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionImage("resources/entities/"+name+"/backSide/leftFoot.png", 28, 44, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, -1, -2, -1, 0, 1, 2, 1}), 
				new RelativePositionImage("resources/entities/"+name+"/backSide/rightFoot.png", 28, 44, new int[]{0, 1, 2, 1, 0, -1, -2, -1}, new int[]{0, 1, 2, 1, 0, -1, -2, -1}), 
				new RelativePositionImage("resources/entities/"+name+"/backSide/head.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
				new RelativePositionImage("resources/entities/"+name+"/backSide/rightHand.png", 28, 44, new int[]{0, -1, -2, -1, 0, 1, 2, 1}, new int[]{0, -1, -2, -1, 0, 1, 2, 1}),
				new RelativePositionImage("resources/entities/"+name+"/backSide/body.png", 28, 44, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), 
				new RelativePositionEquip(entity, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new float[]{0.0F, 0.02F, 0.04F, 0.06F, 0.08F, 0.1F, 0.12F, 0.14F}),
				new RelativePositionImage("resources/entities/"+name+"/backSide/leftHand.png", 28, 44, new int[]{0, 3, 4, 2, 1, 0}, new int[]{0, -1, -3, -1, 0, 0}), 

		}, 3, 0).setFlip(true));
		addAction(new BipedActionWalking(this));
	}

}
