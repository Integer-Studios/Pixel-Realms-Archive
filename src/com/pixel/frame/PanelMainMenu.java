package com.pixel.frame;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.animation.AnimationGUI;
import com.pixel.gui.GUI;
import com.pixel.gui.GUIButton;
import com.pixel.gui.GUIComponentAnimated;
import com.pixel.gui.GUIComponentText;
import com.pixel.start.MainLoop;
import com.pixel.start.PixelRealms;

public class PanelMainMenu extends Panel {
	
	
	public PanelMainMenu() {
		width = -1;
		updateRenderSize();
	}

	public void update(GameContainer c, int delta) {
		
	}

	public void render(GameContainer c, Graphics g) {
		updateRenderSize();
		if (news.getOnMouseUp()) {

			try {
				java.awt.Desktop.getDesktop().browse(new URI("http://pixel-realms.com/"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} 
		
		if (multiPlayer.getOnMouseUp()) {
			MainLoop.setPanel(2);
			PixelRealms.server = true;

		}
		
	}
	
	public void updateRenderSize() {
		if (width != Display.getWidth() || height != Display.getHeight()) {
			GUI.clear();

			news = new GUIButton(144, Display.getHeight()-150, 170, 70, new GUIComponentText("News", 58, 8, 35));
			multiPlayer = new GUIButton(Display.getWidth()/2-85, Display.getHeight()-150, 170, 70, new GUIComponentText("Play", 60, 8, 35));
			options = new GUIButton(Display.getWidth()-310, Display.getHeight()-150, 170, 70, new GUIComponentText("Options", 45, 5, 40));
			GUI.addGUIComponent(new GUIComponentAnimated(new AnimationGUI("resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "home_screen" + PixelRealms.t.separator + "", 0, 0, Display.getWidth(), Display.getHeight(), 5, 9)));
			news = (GUIButton) GUI.addGUIComponent(news);
			multiPlayer = (GUIButton) GUI.addGUIComponent(multiPlayer);
			options = (GUIButton) GUI.addGUIComponent(options);
			GUI.addGUIComponent(MainLoop.newsFeed);
			width = Display.getWidth();
			height = Display.getHeight();
		}
	}
	
	public GUIButton news, multiPlayer, options;
	public int width, height;

}