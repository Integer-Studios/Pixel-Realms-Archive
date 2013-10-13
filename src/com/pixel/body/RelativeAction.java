package com.pixel.body;


import com.pixel.animation.RelativePositionAnimation;
import com.pixel.world.World;

public class RelativeAction {
	public RelativeAction(RelativeBody body, RelativePositionAnimation front, RelativePositionAnimation back, RelativePositionAnimation left, RelativePositionAnimation right, RelativePositionAnimation frontLeft, RelativePositionAnimation frontRight, RelativePositionAnimation backLeft, RelativePositionAnimation backRight) {
		this.front = front;
		this.back = back;
		this.left = left;
		this.right = right;
		this.frontLeft = frontLeft;
		this.frontRight = frontRight;
		this.backLeft = backLeft;
		this.backRight = backRight;
		this.body = body;
		this.originals = new RelativePositionAnimation[]{front, back, left, right, frontLeft, frontRight, backLeft, backRight};
	}
	
	public void addRelativeAction() {
		for (int i = 0; i < body.front.images.length; i++) {
			if (this.front.images[i] != null) {
				body.front.images[i] = front.images[i];
			}
		}
		for (int i = 0; i < body.back.images.length; i++) {
			if (this.back.images[i] != null) {
				body.back.images[i] = back.images[i];
			}
		}
		for (int i = 0; i < body.left.images.length; i++) {
			if (this.left.images[i] != null) {
				body.left.images[i] = left.images[i];
			}
		}
		for (int i = 0; i < body.right.images.length; i++) {
			if (this.right.images[i] != null) {
				body.right.images[i] = right.images[i];
			}
		}
		for (int i = 0; i < body.frontLeft.images.length; i++) {
			if (this.frontLeft.images[i] != null) {
				body.frontLeft.images[i] = frontLeft.images[i];
			}
		}
		for (int i = 0; i < body.frontRight.images.length; i++) {
			if (this.frontRight.images[i] != null) {
				body.frontRight.images[i] = frontRight.images[i];
			}
		}
		for (int i = 0; i < body.backLeft.images.length; i++) {
			if (this.backLeft.images[i] != null) {
				body.backLeft.images[i] = backLeft.images[i];
			}
		}
		for (int i = 0; i < body.backRight.images.length; i++) {
			if (this.backRight.images[i] != null) {
				body.backRight.images[i] = backRight.images[i];
			}
		}
	}
	
	public void overwriteWithAction() {
		body.front = originals[0];
		body.back = originals[1];
		body.left = originals[2];
		body.right = originals[3];
		body.frontLeft = originals[4];
		body.frontRight = originals[5];
		body.backLeft = originals[6];
		body.backRight = originals[7];

	}
	
	public void tick(World w) {
		
	}
	
	public RelativePositionAnimation front, back, left, right, frontLeft, frontRight, backLeft, backRight;
	public RelativePositionAnimation[] originals;
	public RelativeBody body;
	
}
