package com.pixel.animation;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.entity.EntityHuman;
import com.pixel.world.World;

public class RelativePositionEquip extends RelativePositionImageRotatable {
	
	public EntityHuman human;

	public RelativePositionEquip(EntityHuman human, int[] x, int[] y, float[] rads) {
		super("", 16, 16, x, y, rads);
		this.human = human;
	}
	
	public void render(GameContainer c, Graphics g, World w, RelativePositionAnimation anim) {
		texture = human.getSelectedItem().item.texture;
		super.render(c, g, w, anim);
	}

}
