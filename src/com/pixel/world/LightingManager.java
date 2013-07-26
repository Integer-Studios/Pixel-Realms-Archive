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
		
//		g2.setColor(Color.BLACK);
//		Composite c = g2.getComposite();
//		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, lightValue));
//		g2.fillRect(0, 0, w.frame.getWidth(), w.frame.getHeight());
//		g2.setComposite(c);
		
//		BufferedImage bi = new BufferedImage(w.frame.getWidth(), w.frame.getHeight(), BufferedImage.TYPE_INT_ARGB);
//	    Graphics2D big = bi.createGraphics();
//	    
//	    AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.CLEAR, 1f);
//	    
//	    big.setColor(Color.BLACK);
//	    big.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
//	    big.fillRect(0, 0, w.frame.getWidth(), w.frame.getHeight());
//		
//		big.setComposite(ac);
//		big.fill(new Ellipse2D.Double(200, 200, 200, 200));
//		
//		g2.drawImage(bi, null, 0, 0);
//	    
	    
	}
	
	float lightValue = 0.0f;

}
