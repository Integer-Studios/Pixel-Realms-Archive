package com.pixel.frame;

import com.pixel.item.Item;

public class MainFrame {
	
	public MainFrame() {

	}

	public void createFrame() {
//		if (!System.getProperty("os.name").startsWith("Windows"))
//	    System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Pixel Realms");
//
//		frame = new JFrame("Pixel Realms");
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize(900, 600);
//		frame.setLocationRelativeTo(null);
//		frame.setResizable(false);
//		currentCursor = "resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "cursors" + PixelRealms.t.separator + "cursor_default(act).png";
//
//		BufferedImage cursorImg = readImage(currentCursor);
//
//		// Create a new blank cursor.
//		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
//		    cursorImg, new Point(0, 0), "default cursor");
//		frame.getContentPane().setCursor(blankCursor);
//		panel = new PanelLogin(frame);
//		frame.addKeyListener(new KeyboardListener());
//		MouseClickListener mcl = new MouseClickListener(panel);
//		frame.addMouseListener(mcl);
//		frame.addMouseMotionListener(mcl);
//		frame.add(panel);
//		frame.setVisible(true);
		
	}
	
	public static void setCursorDefault() {
//		if (!currentCursor.equals("resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "cursors" + PixelRealms.t.separator + "cursor_default(act).png"))
//		{
//			currentCursor = "resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "cursors" + PixelRealms.t.separator + "cursor_default(act).png";
//			BufferedImage cursorImg = readImage(currentCursor);
//			Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
//					cursorImg, new Point(0, 0), "default cursor");
//			frame.getContentPane().setCursor(blankCursor);
//		}
	}
	
	public static void setCursor(String s) {
//		if (!currentCursor.equals(s)) {
//			currentCursor = s;
//			BufferedImage cursorImg = readImage(currentCursor);

//			Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
//					cursorImg, new Point(0, 0), "default cursor");
//			frame.getContentPane().setCursor(blankCursor);
//		}
	}
	
	public static void setCursorToItem(Item i) {
		setCursor(i.texture);
	}
	
	public static String currentCursor = "";

}
