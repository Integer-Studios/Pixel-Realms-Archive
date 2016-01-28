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
import com.pixel.world.WorldManager;

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
		}

		boolean l=false,r=false,d=false,u=false;
		int lO = -1;
		int rO = -1;
		int dO = -1;
		int uO = -1;
		if (t.posX > 0 && (WorldManager.getWorld().getTile(t.posX-1, t.posY) == t.id)) {
			l = true;
			lO = WorldManager.getWorld().getTileObject(t.posX-1, t.posY).metadata;
		}
		if (t.posX < WorldManager.getWorld().c -1 && (WorldManager.getWorld().getTile(t.posX+1, t.posY) == t.id)) {
			r = true;
			rO = WorldManager.getWorld().getTileObject(t.posX+1, t.posY).metadata;
		}
		if (t.posY < WorldManager.getWorld().c -1 && (WorldManager.getWorld().getTile(t.posX, t.posY+1) == t.id)) {
			d = true;
			dO = WorldManager.getWorld().getTileObject(t.posX, t.posY+1).metadata;
		}
		if (t.posY > 0 && (WorldManager.getWorld().getTile(t.posX, t.posY-1) == t.id)) {
			u = true;
			uO = WorldManager.getWorld().getTileObject(t.posX, t.posY-1).metadata;
		}
		
		//if statement
		if (l) {
			if (r) {
				if (d) {
					if (u) {//lrdu
						image = images[t.metadata];//unknown
					} else {//lrd
						if (dO == 1 || dO == 2) {
							if (lO == 5 || lO == 11 || lO == 2 || rO == 2 || rO == 12 || rO == 6) {
								image = images[2];//top
								t.metadata = 2;
							} else {
								image = images[1];//bottom wall
								t.metadata = 1;
							}
						} else {
							image = images[t.metadata];//unknown
						}
					}
				} else if (u) {//lru
					if (uO == 1 || uO == 2 || uO == 0) {
						if (lO == 5 || lO == 11 || lO == 2 || rO == 2 || rO == 12 || rO == 6) {
							image = images[2];//top
							t.metadata = 2;
						} else {
							image = images[1];//bottom wall
							t.metadata = 1;
						}
					} else {
						image = images[t.metadata];//unknown
					}
				} else {//lr
					//check for cliff
					//check direction facing
					if (lO == 5 || lO == 11 || lO == 2 || rO == 2 || rO == 12 || rO == 6) {
						image = images[2];//top
						t.metadata = 2;
					} else {
						image = images[1];//bottom wall
						t.metadata = 1;
					}
				}
			} else if (d) {
				if (u) {//ldu
					image = images[t.metadata];//unknown
				} else {//ld
					if (dO == 1) {
						if (lO == 5 || lO == 11 || lO == 2) {
							image = images[2];//top wall
							t.metadata = 2;
						} else {
							image = images[10];//right ending
							t.metadata = 10;
						}
					} else {
						image = images[6];//top right corner
						t.metadata = 6;
					}
				}
			} else if (u) {//lu
				image = images[8];//bottom right corner
				t.metadata = 8;
			} else {//l
				//check direction facing
				if (lO == 5 || lO == 11 || lO == 2) {
					image = images[2];//top wall
					t.metadata = 2;
				} else {
					image = images[10];//right ending
					t.metadata = 10;
				}
			}
		} else if (r) {
			if (d) {
				if (u) {//rdu
					image = images[t.metadata];//unknown
				} else {//rd
					if (dO == 1) {
						if (rO == 2 || rO == 6 || rO == 12) {
							image = images[2];//top
							t.metadata = 2;
						} else {
							image = images[9];//left ending
							t.metadata = 9;
						}
					} else {
						image = images[5];//top left corner
						t.metadata = 5;
					}
				}
			} else if (u) {//ru
				image = images[7];//bottom left corner
				t.metadata = 7;
			} else {//r
				//check direction facing
				if (rO == 2 || rO == 6 || rO == 12) {
					image = images[2];//top
					t.metadata = 2;
				} else {
					image = images[9];//left ending
					t.metadata = 9;
				}
			}
		} else if (d) {
			if (u) {//du
				//check direction facing
				if (dO == 8 || dO == 10 || dO == 4 || dO == 12 || uO == 14 || uO == 6 || uO == 4 || uO == 2) {
					image = images[4];//right wall
					t.metadata = 4;
				} else {
					image = images[3];//left wall
					t.metadata = 3;
				}
			} else {//d
				//check direction facing
				if (dO == 1 || dO == 2) {
					image = images[0];//hill
					t.metadata = 0;
				} else if (dO == 8 || dO == 10 || dO == 4 || dO == 12) {
					image = images[12];//top right ending
					t.metadata = 12;
				} else {
					image = images[11];//top left ending
					t.metadata = 11;
				}
			}
		} else if (u) {//u
			//check direction facing
			if (uO == 6 || uO == 4 || uO == 14) {
				image = images[14];//bottom right ending
				t.metadata = 14;
			} else {
				image = images[13];//bottom left ending
				t.metadata = 13;
			}
		} else {//none
			image = images[0];
			t.metadata = 0;
		}
		
		Tile.info[t.background].render(c, g, w, t);
		image.draw(t.posX*World.tileConstant+WorldManager.getWorld().globalOffsetX, t.posY*World.tileConstant+WorldManager.getWorld().globalOffsetY, World.tileConstant, World.tileConstant);
		
		

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
