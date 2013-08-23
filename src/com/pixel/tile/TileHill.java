package com.pixel.tile;
//
//import java.awt.Graphics2D;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.pixel.start.TextureLoader;
import com.pixel.world.World;

public class TileHill extends TileInfo {

	public boolean loaded;
	public Image[] images;
	public int orientation = 0;
	
	public TileHill(String t) {
		super(t);
	}

	public void render(GameContainer c, Graphics g, World w, Tile t) {
		
		if (!loaded) {
			images = new Image[]{
					TextureLoader.load(texture+".png"),//0
					TextureLoader.load(texture+"_lr_d.png"),//1
					TextureLoader.load(texture+"_lr_u.png"),//2
					TextureLoader.load(texture+"_du_l.png"),//3
					TextureLoader.load(texture+"_du_r.png"),//4
					TextureLoader.load(texture+"_rd.png"),//5
					TextureLoader.load(texture+"_ld.png"),//6
					TextureLoader.load(texture+"_ru.png"),//7
					TextureLoader.load(texture+"_lu.png"),//8
					TextureLoader.load(texture+"_r_d.png"),//9
					TextureLoader.load(texture+"_l_d.png"),//10
					TextureLoader.load(texture+"_d_l.png"),//11
					TextureLoader.load(texture+"_d_r.png"),//12
					TextureLoader.load(texture+"_u_l.png"),//13
					TextureLoader.load(texture+"_u_r.png"),//14
					TextureLoader.load(texture+"_lru.png"),//15
					TextureLoader.load(texture+"_lrd.png"),//16
					TextureLoader.load(texture+"_lrdu.png"),//17
			};
						
			loaded = true;
			t.metadata = 0;
		}

		boolean l=false,r=false,d=false,u=false;
		int lO = -1;
		int rO = -1;
		int dO = -1;
		int uO = -1;
		if (t.posX > 0 && (w.getTile(t.posX-1, t.posY) == t.id)) {
			l = true;
			lO = w.getTileObject(t.posX-1, t.posY).metadata;
		}
		if (t.posX < World.c -1 && (w.getTile(t.posX+1, t.posY) == t.id)) {
			r = true;
			rO = w.getTileObject(t.posX+1, t.posY).metadata;
		}
		if (t.posY < World.c -1 && (w.getTile(t.posX, t.posY+1) == t.id)) {
			d = true;
			dO = w.getTileObject(t.posX, t.posY+1).metadata;
		}
		if (t.posY > 0 && (w.getTile(t.posX, t.posY-1) == t.id)) {
			u = true;
			uO = w.getTileObject(t.posX, t.posY-1).metadata;
		}
		
		//if statement
		if (l) {
			if (r) {
				if (d) {
					if (u) {
						image = images[17];//unknown
					} else {
						image = images[17];//unknown
					}
				} else if (u) {
					image = images[17];//unknown
				} else {
					//check for cliff
					//check direction facing
					image = images[1];//bottom wall
				}
			} else if (d) {
				if (u) {
					image = images[17];//unknown
				} else {
					image = images[6];//top right corner
				}
			} else if (u) {
				image = images[8];//bottom right corner
			} else {
				//check direction facing
				image = images[10];//right ending
			}
		} else if (r) {
			if (d) {
				if (u) {
					image = images[17];//unknown
				} else {
					image = images[5];//top left corner
				}
			} else if (u) {
				image = images[7];//bottom left corner
			} else {
				//check direction facing
				image = images[9];//left ending
			}
		} else if (d) {
			if (u) {
				//check direction facing
				image = images[3];//left wall
			} else {
				//check direction facing
				image = images[11];//top ending
			}
		} else if (u) {
			//check direction facing
			image = images[13];//bottom ending
		} else {
			image = images[0];
		}
		
		Tile.info[t.background].render(c, g, w, t);
		image.draw(t.posX*World.tileConstant+World.globalOffsetX, t.posY*World.tileConstant+World.globalOffsetY, World.tileConstant, World.tileConstant);
		
		

//		if (l == t.prevl && r == t.prevr && d == t.prevd && u == t.prevu && t.prevImage != null) {
//			Tile.info[t.background].paint(w, t, g2);
//			g2.drawImage(t.prevImage, t.posX*World.tileConstant+World.globalOffsetX, t.posY*World.tileConstant+World.globalOffsetY, World.tileConstant, World.tileConstant, null);
//		} else {
//			BufferedImage img;
//			String text = getType(l,r,d,u);
//			text = texture + text;
//			img = readImage(text);
//			Tile.info[t.background].paint(w, t, g2);
//			g2.drawImage(img, t.posX*World.tileConstant+World.globalOffsetX, t.posY*World.tileConstant+World.globalOffsetY, World.tileConstant, World.tileConstant, null);
//			t.prevImage = img;
//			t.setPreviousNeighbors(l, r, d, u);
//		}
		
	}
	
//	public String getType(boolean l, boolean r, boolean d, boolean u) {
//		if (front) {
//			if (l && d) {
//				return "_ld.png";
//			} else if (r && d) {
//				return "_rd.png";
//			} else if (l && u) {
//				return "_lu.png";
//			} else if (r && u) {
//				return "_ru.png";
//			} else if (l && r) {
//				return "_lr_d.png";
//			} else if (l && r && u) {
//				return "_lru.png";
//			} else if (l && r && d) {
//				return "_lrd.png";
//			} else if (l && r && d && u) {
//				return "_lrdu.png";
//			} else if (d && u) {
//				return "_du_r.png";
//			} else if (d) {
//				return "_d_r.png";
//			} else if (u) {
//				return "_u_r.png";
//			} else if (l) {
//				return "_l_d.png";
//			} else if (r) {
//				return "_r_d.png";
//			}
//		} else {
//			if (l && d) {
//				return "_ld.png";
//			} else if (r && d) {
//				return "_rd.png";
//			} else if (l && u) {
//				return "_lu.png";
//			} else if (r && u) {
//				return "_ru.png";
//			} else if (l && r) {
//				return "_lr_u.png";
//			} else if (l && r && u) {
//				return "_lru.png";
//			} else if (l && r && d) {
//				return "_lrd.png";
//			} else if (l && r && d && u) {
//				return "_lrdu.png";
//			} else if (d && u) {
//				return "_du_l.png";
//			} else if (d) {
//				return "_d_l.png";
//			} else if (u) {
//				return "_u_l.png";
//			} else if (l) {
//				return "_lr_u.png";
//			} else if (r) {
//				return "_lr_u.png";
//			}
//		}
//		return "_lrdu.png";
//	}


}
