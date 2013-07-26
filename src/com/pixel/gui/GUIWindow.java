package com.pixel.gui;

import com.pixel.start.PixelRealms;

public class GUIWindow extends GUIComponentSet {

	public GUIWindow(int x, int y, int w, int h) {
		super(x, y, w, h, new GUIComponent[]{
				new GUIComponent(x, y, 20, 20, GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "window" + PixelRealms.t.separator + "top_left.png"),
				new GUIComponent(x+w-20, y, 20, 20, GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "window" + PixelRealms.t.separator + "top_right.png"),
				new GUIComponent(x, y+h-20, 20, 20, GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "window" + PixelRealms.t.separator + "bottom_left.png"),
				new GUIComponent(x+w-20, y+h-20, 20, 20, GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "window" + PixelRealms.t.separator + "bottom_right.png"),
				new GUIComponent(x+20, y, w-40, 20, GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "window" + PixelRealms.t.separator + "top.png"),
				new GUIComponent(x+20, y+h-20, w-40, 20, GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "window" + PixelRealms.t.separator + "bottom.png"),
				new GUIComponent(x+w-20, y+20, 20, h-40, GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "window" + PixelRealms.t.separator + "right.png"),
				new GUIComponent(x, y+20, 20, h-40, GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "window" + PixelRealms.t.separator + "left.png"),
				new GUIComponent(x+20, y+20, w-40, h-40, GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "window" + PixelRealms.t.separator + "center.png"),

		});
	}

}
