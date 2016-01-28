package com.pixel.start;


import javax.swing.JFrame;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;

import com.pixel.gui.GUI;
import com.pixel.gui.GUIAlert;
import com.pixel.gui.GUINewsFeed;
import com.pixel.input.KeyboardListener;
import com.pixel.input.MouseClickListener;
import com.pixel.piece.Piece;
import com.pixel.sound.PixelSoundManager;
import com.pixel.stage.Stage;
import com.pixel.stage.StageLogin;
import com.pixel.stage.StageMainMenu;
import com.pixel.stage.StageWorld;
import com.pixel.tile.Tile;
import com.pixel.world.World;

public class MainLoop extends BasicGame implements KeyListener, MouseListener {
	
	public static boolean left, right;
	public JFrame frame;
	public World world;
	
	final double GAME_HERTZ = 30.0;
    final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
    final int MAX_UPDATES_BEFORE_RENDER = 5;
    double lastUpdateTime;
    double lastRenderTime;
    public boolean running = true;
    public boolean leftButton;
    public boolean rightButton;
    public static GUINewsFeed newsFeed;
    public static Stage stage;
    public static boolean init;
    
    public static int fps = 60;
    
    final double TARGET_FPS = 60;
    final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;
    
	public MainLoop(String title) throws SlickException {
		
		super(title);
		
	}

	@Override
	public void render(GameContainer c, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		
		double now = System.nanoTime();

		if (!init) {
			
			PixelLogger.log(Tile.info.length + " tiles.");
			PixelLogger.log(Piece.info.length + " pieces.");
			init = true;
			
		}
		
		stage.render(c, g);
		GUI.render(c, g);
		
		now = System.nanoTime();

		lastRenderTime = now;
	}

	@Override
	public void init(GameContainer c) throws SlickException {
		// TODO Auto-generated method stub
		GUI.init();
		newsFeed = new GUINewsFeed("http://server.integerstudios.com/pixel/feed.txt", "No News");
		stage = new StageLogin();

	}

	@Override
	public void update(GameContainer c, int delta) throws SlickException {
		// TODO Auto-generated method stub
		stage.update(c, delta);
		MouseClickListener.update(c, delta);
		KeyboardListener.update(c, delta);
		PixelSoundManager.update();

	}

	@Override
	public void keyPressed(int code, char v) {
		
		KeyboardListener.keyPressed(code, v);
		
	}
	
	@Override
	public void keyReleased(int code, char v) {
		
		KeyboardListener.keyReleased(code, v);
		
	}
	
	public void displayAlert(String message, Color color, String command) {
		
		new GUIAlert(message, color, command);
		
	}
	
	public static void setPanel(int id) {
		
		GUI.clear();
		switch (id) {
		case 0:
			stage = new StageLogin();
			break;
		case 1:
			stage = new StageMainMenu();
			break;
		case 2:
			stage = new StageWorld().init();
			break;
		
		}
		
	}
	
	@Override
	public void mouseWheelMoved(int change) {
		
		MouseClickListener.mouseWheelMoved(change);
		
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount){
		
		MouseClickListener.mouseClicked(button, x, y, clickCount);
		
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		
		MouseClickListener.mousePressed(button, x, y);
		
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		
		MouseClickListener.mouseReleased(button, x, y);
		
	}

	@Override
	public void mouseMoved(int oldX, int oldY, int newX, int newY) {
		
		MouseClickListener.mouseMoved(oldX, oldY, newX, newY);		
		
	}

	@Override
	public void mouseDragged(int oldX, int oldY, int newX, int newY) {
		
		MouseClickListener.mouseDragged(oldX, oldY, newX, newY);		
		
	}
	
}
