package com.pixel.world;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class LightingManager {
	
	public LightingManager() {
		
	}

	public void render(GameContainer c, Graphics g, World w) {
		float f;
		if (w.getTimeOfDay() < w.dayLength - w.getTimeOfDay()) {
			//morning
			f = (w.dayLength/2) - w.getTimeOfDay();
			f = f / w.dayLength;
			f = f / 1000;
			lightValue -= f;
		} else if (w.getTimeOfDay() > w.dayLength - w.getTimeOfDay()) {
			//afternoon
			f = (w.dayLength/2) - (w.dayLength - w.getTimeOfDay());
			f = f / w.dayLength;
			f = f / 1000;
			lightValue += f;
		} else {
			//noon
			lightValue = 0.0f;
		}
		
		if (lightValue >= 0.9) {
			
			lightValue = 0.9f;
			
		} else if (lightValue <= 0) {
			
			lightValue = 0;

		}

//		g.clearAlphaMap();
//		g.setDrawMode(Graphics.MODE_ALPHA_MAP);
//
//		GL11.glEnable(SGL.GL_BLEND);
//		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
//
//		g.setDrawMode(Graphics.MODE_ALPHA_BLEND);
//
//		GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_ALPHA,GL11.GL_DST_ALPHA);
//
//		g.setColor(Color.black);
//		g.fillRect(0, 0, 900, 600);
		
		//http://www.2shared.com/complete/LEs7kHys/LightTest.html
		
	}
	
	float lightValue = 0.0f;

}
