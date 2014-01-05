package com.pixel.body;

import com.pixel.animation.RelativePositionAnimation;
import com.pixel.animation.RelativePositionImage;
import com.pixel.world.World;

public class BipedActionKickback extends BipedAction {
	
	public int power;
	public float dirX, dirY;
	private boolean started;

	public BipedActionKickback(RelativeBody body, int power, float dirX, float dirY) {
		super(body, new RelativePositionAnimation(body.entity, new RelativePositionImage[]{
				null, 
				null, 
				null, 
				null,
				null, 
				null,
				null,
				null,
		}, 3, 2), 
		new RelativePositionAnimation(body.entity, new RelativePositionImage[]{
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
		}, 3, 2), 
		new RelativePositionAnimation(body.entity, new RelativePositionImage[]{
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
		}, 3, 2), 
		new RelativePositionAnimation(body.entity, new RelativePositionImage[]{
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
		}, 3, 2).setFlip(true),
		new RelativePositionAnimation(body.entity, new RelativePositionImage[]{
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
		}, 3, 2).setFlip(true),
		new RelativePositionAnimation(body.entity, new RelativePositionImage[]{
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
		}, 3, 2),
		new RelativePositionAnimation(body.entity, new RelativePositionImage[]{
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
		}, 3, 2),
		new RelativePositionAnimation(body.entity, new RelativePositionImage[]{
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
		}, 3, 2).setFlip(true));
		
		this.power = power;
	}

	public void tick(World w) {

//		body.animation.play();
		
		if (!started) {
			
			
			
		}
		
	}
	
}
