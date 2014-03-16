package com.pixel.world;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.opengl.renderer.SGL;

public class LightingManager {
	
	public boolean rising, setting;
	float lightValue = 0.15f;
	float defaultNight = 0.15F;
	float defaultDay = 1F;
	float defaultLightChange = 0.001F;

	public LightingManager() {}
	
	public void calcuateLightValue(World w) {
		
		float t = w.getTimeOfDay();
		int min = 0;
		
		if (t < 12000) {
			
			t = t / 1000;
			
			float m = t - ((int) t);
			m = 60 * m;
			min = (int) m;
			
			
		} else {
			
			t = t / 1000;
			
			float m = t - ((int) t);
			m = 60 * m;
			min = (int) m;
			
		}
		
		if ((t >= 6 && t <= 6.9999) || rising) {
			
			handleSunrise(min);
			
		} else if ((t >= 18 && t <= 18.9999) || setting) {
			
			handleSunset(min);
			
		} else {
			
			handleNormal(t, min);
			
		}
		
	}
	
	public void handleNormal(float t, int m) {
		
		if (t > 7 && t < 18) {
			
			lightValue = defaultDay;
			
		} 
		
		if (t < 6 || t > 19) {
			
			lightValue = defaultNight;
			
		}
		
	}
	
	public void handleSunrise(int m) {

		if (m > 15 && !rising && lightValue != defaultDay) {
			
			rising = true;
			
		} else if (rising && lightValue < defaultDay) {
			
			lightValue += defaultLightChange;

		} else if (lightValue >= defaultDay) {
			
			lightValue = defaultDay; 
			rising = false;
			
		}
		
	}
	
	public void handleSunset(int m) {

		if (m > 15 && !setting && lightValue != defaultNight) {
			
			setting = true;
			
		} else if (setting && lightValue > defaultNight) {
			
			lightValue -= defaultLightChange;

		} else if (lightValue <= defaultNight) {
			
			lightValue = defaultNight; 
			setting = false;
			
		}
		
	}
	
	public void render(GameContainer c, Graphics g, World w) {
		
		calcuateLightValue(w);
		
		g.clearAlphaMap();
		g.setDrawMode(Graphics.MODE_ALPHA_MAP);

		GL11.glEnable(SGL.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
		Color color = new Color(0, 0, 0, lightValue);
		g.setColor(color);
		g.fillRect(0, 0, 900, 600);
//		World.alphaImage.setAlpha(lightValue);
		World.alphaImage.draw((w.player.posX*World.tileConstant+World.globalOffsetX) - 200, (w.player.posY*World.tileConstant+World.globalOffsetY) - 200, 400, 400);
		
		g.setDrawMode(Graphics.MODE_ALPHA_BLEND);

		GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_ALPHA,GL11.GL_DST_ALPHA);
		color = new Color(0, 0, 0, 1F);
		g.setColor(color);
		g.fillRect(0, 0, 900, 600);
		
		g.setDrawMode(Graphics.MODE_NORMAL);

	}
	
}
