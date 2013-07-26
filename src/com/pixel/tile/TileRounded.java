package com.pixel.tile;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import com.pixel.world.World;

public class TileRounded extends TileInfo {

	public TileRounded(String t) {
		super(t);
	}
	
	public void render(GameContainer c, Graphics g, World w, Tile t) {
		
//		boolean l=false,r=false,d=false,u=false;
//		if (t.posX > 0 && w.getTile(t.posX-1, t.posY) != t.id) {
//			l = true;
//		}
//		if (t.posX < World.c -1 && w.getTile(t.posX+1, t.posY) != t.id) {
//			r = true;
//		}
//		if (t.posY < World.c -1 && w.getTile(t.posX, t.posY+1) != t.id) {
//			d = true;
//		}
//		if (t.posY > 0 && w.getTile(t.posX, t.posY-1) != t.id) {
//			u = true;
//		}
//		
//		if (l == t.prevl && r == t.prevr && d == t.prevd && u == t.prevu && t.prevImage != null) {
//			Tile.info[t.background].render(c, g, w, t);
//			g2.drawImage(t.prevImage, t.posX*World.tileConstant+World.globalOffsetX, t.posY*World.tileConstant+World.globalOffsetY, World.tileConstant, World.tileConstant, null);
//		} else {
//			Image img;
//			String text = getType(l,r,d,u);
//			int orientation = getOrientation(l,r,d,u, text);
//			text = texture + text;
//			img = TextureLoader.load(text);
//			img = orientateImage(img, orientation);
//			Tile.info[t.background].paint(w, t, g2);
//			g2.drawImage(img, t.posX*World.tileConstant+World.globalOffsetX, t.posY*World.tileConstant+World.globalOffsetY, World.tileConstant, World.tileConstant, null);
//			t.prevImage = img;
//			t.setPreviousNeighbors(l, r, d, u);
//		}
	}
	
	public void setTexture(String texture) {
		this.texture = texture;
	}
	
//	public Image orientateImage(Image img, int d) {
//
//		if (d == 2) {
//			AffineTransform tx = new AffineTransform();
//
//			// last, width = height and height = width :)
//			tx.translate(img.getHeight() / 2,img.getWidth() / 2);
//			tx.rotate(Math.PI);
//			// first - center image at the origin so rotate works OK
//			tx.translate(-img.getWidth() / 2,-img.getHeight() / 2);
//
//			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
//
//			// new destination image where height = width and width = height.
//			BufferedImage newImage =new BufferedImage(img.getHeight(), img.getWidth(), img.getType());
//			return op.filter(img, newImage);
//		} else
//		
//		if (d == 1) {
//			AffineTransform tx = new AffineTransform();
//
//			// last, width = height and height = width :)
//			tx.translate(img.getHeight() / 2,img.getWidth() / 2);
//			tx.rotate(Math.PI / 2);
//			// first - center image at the origin so rotate works OK
//			tx.translate(-img.getWidth() / 2,-img.getHeight() / 2);
//
//			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
//
//			// new destination image where height = width and width = height.
//			BufferedImage newImage =new BufferedImage(img.getHeight(), img.getWidth(), img.getType());
//			return op.filter(img, newImage);
//		} else
//		
//		if (d == 3) {
//			AffineTransform tx = new AffineTransform();
//
//			// last, width = height and height = width :)
//			tx.translate(img.getHeight() / 2,img.getWidth() / 2);
//			tx.rotate(Math.PI / -2);
//			// first - center image at the origin so rotate works OK
//			tx.translate(-img.getWidth() / 2,-img.getHeight() / 2);
//
//			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
//
//			// new destination image where height = width and width = height.
//			BufferedImage newImage =new BufferedImage(img.getHeight(), img.getWidth(), img.getType());
//			return op.filter(img, newImage);
//		} else
//			
//		{
//			
//			return img;
//			
//		}
//		
//	}
	
	public String getType(boolean l, boolean r, boolean d, boolean u) {
		String text = "";
		if (l) {
			text = "_l";
			if (r) {
				text += "r";
			}
			if (d) {
				text += "d";
			}
			if (u) {
				text += "u";
			}
		} else
		if (r) {
			text = "_l";
			if (l) {
				text += "r";
			}
			if (d) {
				text += "d";
			}
			if (u) {
				text += "u";
			}
		} else
		if (d) {
			text = "_l";
			if (u) {
				text += "r";
			}
			if (l) {
				text += "d";
			}
			if (r) {
				text += "u";
			}
		} else
		if (u) {
			text = "_l";
			if (d) {
				text += "r";
			}
			if (l) {
				text += "d";
			}
			if (r) {
				text += "u";
			}
		}
		
		if (text.equals("_ldu") || text.equals("_lru")) {
			text = "_lrd";
		}
		if (text.equals("_lu")) {
			text = "_ld";
		}
		text += ".png";
		return text;
	}
	
	public int getOrientation(boolean l, boolean r, boolean d, boolean u, String o) {
		int type = 0;
		if (o.equals("_l.png")) {
			type = 0;
		}
		if (o.equals("_ld.png")) {
			type = 1;
		}
		if (o.equals("_lr.png")) {
			type = 1;
		}
		if (o.equals("_lrd.png")) {
			type = 2;
		}
		if (o.equals("_lrdu.png")) {
			type = 3;
		}
		
		
		if (l&&r&&d&&u && type == 3) {
			return 0;
		} else
		if (l&&r&&d && type == 2) {
			return 0;
		} else
		if (l&&r && type == 1) {
			return 0;
		} else
		if (l&&d && type == 1) {
			return 0;
		} else
		if (l && type == 0) {
			return 0;
		} else
			
		if (u&&l&&d && type == 2) {
			return 1;
		} else
		if (u&&d && type == 1) {
			return 1;
		} else
		if (u&&l && type == 1) {
			return 1;
		} else
		if (u && type == 0) {
			return 1;
		} else
		
		if (r&&u&&l && type == 2) {
			return 2;
		} else
		if (r&&u && type == 1) {
			return 2;
		} else
		if (r && type == 0) {
			return 2;
		} else
		
		if (d&&r&&u && type == 2) {
			return 3;
		} else
		if (d&&r && type == 1) {
			return 3;
		} else
		if (d && type == 0) {
			return 3;
		}
		return -1;
	}
		 
}
