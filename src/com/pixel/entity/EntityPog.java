package com.pixel.entity;

import com.pixel.body.BodyBiped;

public class EntityPog extends EntityMonster {

	public EntityPog() {
		super(.9F, .2F);
		this.id = 2;
		this.setMaxHealth(3.0F);
		body = new BodyBiped(this, "pog");
	}
	
	public EntityPog(float x, float y) {
		super(x, y, .9F, .2F);
		this.id = 2;
		this.setMaxHealth(3.0F);
		body = new BodyBiped(this, "pog");
	}

}
