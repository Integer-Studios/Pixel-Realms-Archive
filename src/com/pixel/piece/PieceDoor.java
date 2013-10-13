package com.pixel.piece;

import com.pixel.entity.EntityPlayer;
import com.pixel.world.World;

public class PieceDoor extends PieceInfo {

	public PieceDoor(String t) {
		super(t);
		// TODO Auto-generated constructor stub
	}

	public void onPlayerCollided(World w, Piece p, EntityPlayer player) {

		if (player.inside) {
			
			player.doorX = player.getX();
			player.doorY = player.getY();
			player.door = true;
			
		}
		
	}

}
