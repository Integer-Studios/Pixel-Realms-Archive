package com.pixel.tile;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.start.PixelRealms;
import com.pixel.tile.TileHill;
import com.pixel.util.Toolkit;
import com.pixel.world.World;

public class Tile {
	
	public Tile(int x, int y, int i, int m, boolean propagate) {
		id = i;
		posX = x;
		posY = y;
		metadata = m;
		
		if (propagate)
			World.propagateTile(this);
		
	}
	
	public void render(GameContainer c, Graphics g, World w) {
		info[id].render(c, g, w, this);
	}
	
	public void tick(World w) {
		info[id].tick(w, this);
		if ((int)w.player.getX() == posX && (int)w.player.getY() == posY) {
			info[id].onPlayerWalkOver(w, this, w.player);
		}
	}

	static {
		Toolkit t = new Toolkit();
		String path = t.getPath();
		info = new TileInfo[]{

				new TileInfo(path+"resources" + PixelRealms.t.separator + "tiles" + PixelRealms.t.separator + "grass.png"), //grass flat 0
				new TileInfo(path+"resources" + PixelRealms.t.separator + "tiles" + PixelRealms.t.separator + "sand" + PixelRealms.t.separator + "sand.png"), //sand flat 1
				new TileRounded(path+"resources" + PixelRealms.t.separator + "tiles" + PixelRealms.t.separator + "sand" + PixelRealms.t.separator + "sand"),//sand rounded 2
				new TileInfo(path+"resources" + PixelRealms.t.separator + "tiles" + PixelRealms.t.separator + "water" + PixelRealms.t.separator + "0.png"), //water flat 3
				new TileRoundedAnimate(path+"resources" + PixelRealms.t.separator + "tiles" + PixelRealms.t.separator + "water" + PixelRealms.t.separator, 2, 20), //water rounded 4
				new TileInfo(path+"resources" + PixelRealms.t.separator + "tiles" + PixelRealms.t.separator + "cobble" + PixelRealms.t.separator + "cobble.png"), //cobble flat 5
				new TileRounded(path+"resources" + PixelRealms.t.separator + "tiles" + PixelRealms.t.separator + "cobble" + PixelRealms.t.separator + "cobble"),//cobble rounded 6
				new TileHill(path+"resources" + PixelRealms.t.separator + "tiles" + PixelRealms.t.separator + "hill" + PixelRealms.t.separator + "hill"),//hill front 7
				new TileInfo(path+"resources" + PixelRealms.t.separator + "tiles" + PixelRealms.t.separator + "black.png"), //black bg 8
				new TileInfo(path+"resources" + PixelRealms.t.separator + "tiles" + PixelRealms.t.separator + "floor.png"), //building floor 9

		};
		
	}
	
	public int id;
	public int posX;
	public int posY;
	public int metadata = -1;
	public static TileInfo[] info;
	
	public int background = 0;
}
