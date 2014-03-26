package com.pixel.lighting;

import java.util.HashMap;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.opengl.renderer.SGL;

import com.pixel.communication.CommunicationClient;
import com.pixel.communication.packet.PacketLight;
import com.pixel.entity.Entity;
import com.pixel.piece.Piece;
import com.pixel.start.PixelLogger;
import com.pixel.start.TextureLoader;
import com.pixel.tile.Tile;
import com.pixel.world.World;

public class PixelLightingManager {
	
	public boolean rising, setting;
	float lightValue = 0.15f;
	float defaultNight = 0.15F;
	float defaultDay = 1F;
	float defaultLightChange = 0.001F;
	public static Image alphaImage;
	public static Image foregroundImage;
	public static HashMap<String, Image> alphaImages = new HashMap<String, Image>();
	public static HashMap<Integer, PixelLightProposal> proposedLights = new HashMap<Integer, PixelLightProposal>();
	public static HashMap<Integer, PixelLight> lights = new HashMap<Integer, PixelLight>();
	
	public PixelLightingManager() {}
	
	public static void propagateLight(PixelLight light) {
		
		int id = lights.size() + 1;
		while (lights.containsKey(id)) {
			
			id ++;
			
		}
		
		light.id = id;
		lights.put(id, light);
		
	}
	
	public static PixelLight getLight(int id) {
		
		return lights.get(id);
		
	}
	
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

		for (PixelLight light : lights.values()) {
			
			light.getImage().draw((light.posX * World.tileConstant + World.globalOffsetX) - (light.width/2), (light.posY * World.tileConstant + World.globalOffsetY) - (light.height / 2), light.width, light.height);
			
		}
//		alphaImage.draw((w.player.posX*World.tileConstant+World.globalOffsetX) - 200, (w.player.posY*World.tileConstant+World.globalOffsetY) - 200, 400, 400);
		
		g.setDrawMode(Graphics.MODE_ALPHA_BLEND);

		GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_ALPHA,GL11.GL_DST_ALPHA);
		color = new Color(0, 0, 0, 1F);
		g.setColor(color);
		g.fillRect(0, 0, 900, 600);
		
		g.setDrawMode(Graphics.MODE_NORMAL);

	}
	
	public static Image getImageForType(PixelLightType type) {
		
		Image i = null;
		if (alphaImages.containsKey(type.fileName)) {
			
			i = alphaImages.get(type.fileName);
			if (i != null) {
				
				return i;
				
			} else {
				
				alphaImages.put(type.fileName, TextureLoader.load(type.fileName));
				i = alphaImages.get(type.fileName);

			}
			
		} else {
			
			alphaImages.put(type.fileName, TextureLoader.load(type.fileName));
			i = alphaImages.get(type.fileName);

		}
			
		return i;
		
	}

	public static void initialize() {
		
		if (alphaImage == null) {
			
			alphaImage = TextureLoader.load("resources/alpha.png");
			
		}
		
		if (alphaImages.get(PixelLightType.DEFAULT.fileName) == null) {
			
			alphaImages.put(PixelLightType.DEFAULT.fileName, TextureLoader.load("resources/" + PixelLightType.DEFAULT.fileName));
			
		}
				
		if (foregroundImage == null) {
			
			foregroundImage = TextureLoader.load("resources/foreground.png");
			
		}		
		
	}

	public static void proposeLight(PixelLight pixelLight, Piece piece) {

		int proposalID = proposedLights.size() + 1;
		proposedLights.put(proposalID, new PixelLightProposal(pixelLight, piece));
		CommunicationClient.addPacket(new PacketLight(pixelLight, proposalID));
		
	}
	
	public static void proposeLight(PixelLight pixelLight, Entity entity) {

		int proposalID = proposedLights.size() + 1;
		proposedLights.put(proposalID, new PixelLightProposal(pixelLight, entity));
		CommunicationClient.addPacket(new PacketLight(pixelLight, proposalID));
		
	}
	
	public static void proposeLight(PixelLight pixelLight, Tile tile) {

		int proposalID = proposedLights.size() + 1;
		proposedLights.put(proposalID, new PixelLightProposal(pixelLight, tile));
		CommunicationClient.addPacket(new PacketLight(pixelLight, proposalID));
		
	}

	public static void confirmProposal(int proposalID, PixelLight p) {

		if (proposedLights.containsKey(proposalID)) 
			proposedLights.get(proposalID).confirm(p);
		else
			PixelLogger.log("Skipping proposal: " + proposalID);
		
	}
	
}
