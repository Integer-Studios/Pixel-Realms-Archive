package com.pixel.gui;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class GUINewsFeed extends GUIComponentSet {

	public static Color newsColor = Color.black;
	public static int newsSize = 25;
	
	public GUINewsFeed() {
		super(0, 600 - 30, 900, 30, new GUIComponent[]{
				new GUIComponent(0, 600 - 30, 900, 30, "resources/gui/login/rolling-gray.png"),
				new GUIComponentText("", 1000, 600 - 32, newsSize, newsColor),
				new GUIComponentText("", 1100, 600 - 32, newsSize, newsColor),
				new GUIComponentText("", 1100, 600 - 32, newsSize, newsColor),			
				new GUIComponentText("", 1100, 600 - 32, newsSize, newsColor),
		});		// TODO Auto-generated constructor stub
		
		try {
			URL url = new URL("http://www.pixel-realms.com/news/feed.txt");
			Scanner s = new Scanner(url.openStream());
			
			((GUIComponentText)components[1]).text = "-- " + s.nextLine() + " --";
			((GUIComponentText)components[2]).text = "-- " + s.nextLine() + " --";
			((GUIComponentText)components[3]).text = "-- " + s.nextLine() + " --";
			((GUIComponentText)components[4]).text = "-- " + s.nextLine() + " --";

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void render(GameContainer c, Graphics g) {
		
		super.render(c, g);
		
		int oneLength = (((GUIComponentText)this.components[1]).text.length() * 10);
		int twoLength = (((GUIComponentText)this.components[2]).text.length() * 10);
		int threeLength = (((GUIComponentText)this.components[3]).text.length() * 10);
		int fourLength = (((GUIComponentText)this.components[4]).text.length() * 10);
		
		int space = 70;
		
		if ((components[4].x < (900 - (fourLength + space))) || (components[4].x == 1100 && components[1].x < 1100) || components[1].x == 1000) {

			this.components[1].x --;
			if (components[1].x <= -1 * oneLength) {

				components[1].x = 1100;

			}

		}

		if ((components[1].x < (900 - (oneLength + space)) || (components[1].x == 1100 && components[2].x < 1100))) {

			this.components[2].x --;
			if (components[2].x <= -1 * twoLength) {

				components[2].x = 1100;

			}

		}

		if ((components[2].x < (900 - (twoLength + space)) || (components[2].x == 1100 && components[3].x < 1100))) {

			this.components[3].x --;
			if (components[3].x <= -1 * threeLength) {

				components[3].x = 1100;

			}

		}

		if ((components[3].x < (900 - (threeLength + space)) || (components[3].x == 1100 && components[4].x < 1100))) {

			this.components[4].x --;
			if (components[4].x <= -1 * fourLength) {

				components[4].x = 1100;

			}

		}

	}

}
