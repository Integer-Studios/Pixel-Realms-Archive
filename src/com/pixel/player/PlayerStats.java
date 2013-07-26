package com.pixel.player;

import com.pixel.entity.EntityPlayer;
import com.pixel.gui.GUI;
import com.pixel.gui.GUIStatBar;
import com.pixel.util.Toolkit;

public class PlayerStats {
	
	public PlayerStats(EntityPlayer ep) {
		player = ep;
		honorBar = new GUIStatBar(60, 0, 120, 20, t.getPath()+"gui/stats/blue.png");
		staminaBar = new GUIStatBar(60, 20, 120, 20, t.getPath()+"gui/stats/green.png");
		healthBar = new GUIStatBar(60, 40, 120, 20, t.getPath()+"gui/stats/red.png");
		GUI.addGUIComponent(healthBar);
		GUI.addGUIComponent(staminaBar);
		GUI.addGUIComponent(honorBar);
		
		//example
		healthBar.setPercent(1.0F);
		staminaBar.setPercent(1.0F);
		honorBar.setPercent(0.5F);
	}
	
	public boolean editHealth(int i) {
		health += i;
		boolean flag = true;
		if (health > 30) {
			health = 30;
			flag = false;
		}
		if (health < 0) {
			health = 0;
			flag = false;
		}
		float f = (float)health/30F;
		healthBar.setPercent(f);
		return flag;
	}
	
	public boolean editStamina(int i) {
		stamina += i;
		boolean flag = true;
		if (stamina > 500) {
			stamina = 500;
			flag = false;
		}
		if (stamina < 0) {
			stamina = 0;
			flag = false;
		}
		float f = (float)stamina/500F;
		staminaBar.setPercent(f);
		return flag;
	}

	public boolean editHonor(int i) {
		honor += i;
		boolean flag = true;
		if (honor > 1000) {
			honor = 1000;
			flag = false;
		}
		if (honor < 0) {
			honor = 0;
			flag = false;
		}
		float f = (float)honor/1000F;
		honorBar.setPercent(f);
		return flag;
	}
	
	public EntityPlayer player;
	public Toolkit t = new Toolkit();
	public GUIStatBar healthBar, staminaBar, honorBar;
	public int health = 30;
	public int stamina = 300;
	public int honor = 500;
}
