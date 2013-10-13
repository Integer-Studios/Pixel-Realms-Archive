package com.pixel.animation;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.entity.EntityAlive;
import com.pixel.entity.EntityHuman;
import com.pixel.world.World;

public class RelativePositionEquip extends RelativePositionImageRotatable {
	
	public EntityAlive human;

	public RelativePositionEquip(EntityAlive human, int id, int[] x, int[] y, float[] rads) {
		super(-1, 16, 16, x, y, rads);
		this.human = human;
		setItemID(id);
	}
	
	public RelativePositionEquip(EntityAlive human, int[] x, int[] y, float[] rads) {
		super(-1, 16, 16, x, y, rads);
		this.human = human;
		setItemID(0);
	}
	
	
	public void render(GameContainer c, Graphics g, World w, RelativePositionAnimation anim) {

		if (human instanceof EntityHuman && ((EntityHuman)human).getSelectedItem().item.id != itemID) {

			setItemID(((EntityHuman)human).getSelectedItem().item.id);

		}
		super.render(c, g, w, anim);
	}

}
