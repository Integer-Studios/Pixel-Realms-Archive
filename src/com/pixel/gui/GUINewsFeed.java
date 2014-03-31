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
	public boolean reloaded = false;
	public String feedURL;
	public String defaultMessage;
	public int lineCount;
	
	public GUINewsFeed(String feed, String defaultMessage) {
		super(0, 600 - 30, 900, 30, new GUIComponent[]{
				new GUIComponent(0, 600 - 30, 900, 30, "resources/gui/login/rolling-gray.png"),
				new GUIComponentText("", 1000, 600 - 32, newsSize, newsColor),
				new GUIComponentText("", 1100, 600 - 32, newsSize, newsColor),
				new GUIComponentText("", 1100, 600 - 32, newsSize, newsColor),			
				new GUIComponentText("", 1100, 600 - 32, newsSize, newsColor),
				new GUIComponentText("", 1100, 600 - 32, newsSize, newsColor),
				new GUIComponentText("", 1100, 600 - 32, newsSize, newsColor),
				new GUIComponentText("", 1100, 600 - 32, newsSize, newsColor),			
				new GUIComponentText("", 1100, 600 - 32, newsSize, newsColor),
		});		// TODO Auto-generated constructor stub
		
		this.feedURL = feed;
		this.defaultMessage = defaultMessage;
		
		loadFeed();
		
	}
	
	public void render(GameContainer c, Graphics g) {
		
		super.render(c, g);
		
		int[] lengths = new int[8];
		
		for (int a = 1; a <= 8; a ++) {
			
			lengths[a - 1] = (((GUIComponentText)this.components[a]).text.length() * 10);
			
		}
		
		int space = 70;
		
		for (int a = 1; a <= lineCount; a ++) {

			if (a == 1) {
				int tempLineCount = lineCount;
				if (lineCount == 1) 
					tempLineCount = 2;
				if ((components[tempLineCount].x < (900 - (lengths[tempLineCount - 1] + space))) || (components[tempLineCount].x == 1100 && components[a].x < 1100) || components[a].x == 1000) {
					this.components[a].x --;
					if (components[a].x <= -1 * lengths[a - 1]) {

						if (lineCount == 1)
							components[a].x = 1000;
						else
							components[a].x = 1100;

					}
				}
			} else {
				
				if ((components[a - 1].x < (900 - (lengths[a - 2] + space))) || (components[a - 1].x == 1100 && components[a].x < 1100) || components[a].x == 1000) {

					this.components[a].x --;
					if (components[a].x <= -1 * lengths[a - 1]) {

						components[a].x = 1100;

					}
				}
				
			}

		}
		
//		loadFeed();
		
	}
	
	public void loadFeed() {
		
		new FeedLoader(this).start();
		
	}

}

class FeedLoader extends Thread {
	
	GUINewsFeed news;
	
	public FeedLoader(GUINewsFeed news) {

		this.news = news;

	}

	public void run() {

		try {
			URL url = new URL(news.feedURL);
			Scanner s = new Scanner(url.openStream());

			int lineCount = 1;

			while (s.hasNextLine() && lineCount <= 8) {

				((GUIComponentText)news.components[lineCount]).text = "-- " + s.nextLine() + " --";
				if (s.hasNextLine())
				lineCount ++;

			}
			
			for (int x = lineCount + 1; x <= 8; x ++) {
				
				((GUIComponentText)news.components[x]).text = "";
				
			}
			
			news.lineCount = lineCount;

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
