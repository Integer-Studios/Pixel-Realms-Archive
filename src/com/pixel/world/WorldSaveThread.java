package com.pixel.world;

import java.io.IOException;


public class WorldSaveThread extends Thread {

	public void run() {
		
		try {
			saveWorld();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(5L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("[Lunumia Client] World Saved.");
		
	}
	
	public void saveWorld() throws IOException {
		
//		System.out.println("[Lunumia Client] World saving...");
//		
//		ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("player.dat"));
//		outputStream.writeObject(new PlayerWrapper(Lunumia.world.player.getX(), Lunumia.world.player.getY(), Lunumia.world.player.health));
//		outputStream.close();
//		
//		outputStream = new ObjectOutputStream(new FileOutputStream("tiles.dat"));
//        ArrayList<TileWrapper> tilesToWrite = new ArrayList<TileWrapper>();
//        
//		for (int x = 0; x < Lunumia.world.tiles.length; x ++) {
//			
//			tilesToWrite.add(new TileWrapper(Lunumia.world.tiles[x].id, Lunumia.world.tiles[x].posX, Lunumia.world.tiles[x].posY));
//			
//		}
//	
//		outputStream.writeObject(tilesToWrite);
//		outputStream.close();
//
//		outputStream = new ObjectOutputStream(new FileOutputStream("pieces.dat"));
//        ArrayList<PieceWrapper> piecesToWrite = new ArrayList<PieceWrapper>();
//		
//		for (int x = 0; x < Lunumia.world.pieces.length; x ++) {
//
//			piecesToWrite.add(new PieceWrapper(Lunumia.world.pieces[x].id, Lunumia.world.pieces[x].posX, Lunumia.world.pieces[x].posY, Lunumia.world.pieces[x].damage, Lunumia.world.pieces[x].metadata));
//
//		}
//		
//		outputStream.writeObject(piecesToWrite);
//		outputStream.close();
//		
//		outputStream = new ObjectOutputStream(new FileOutputStream("entities.dat"));
//        ArrayList<EntityWrapper> entitiesToWrite = new ArrayList<EntityWrapper>();
//		
//		for (int x = 0; x < Lunumia.world.entities.size(); x ++) {
//
//			entitiesToWrite.add(new EntityWrapper(Lunumia.world.entities.get(x).id, Lunumia.world.entities.get(x).getX(), Lunumia.world.entities.get(x).getY(), Lunumia.world.entities.get(x).health));
//
//		}
//		
//		outputStream.writeObject(entitiesToWrite);
//		outputStream.close();

	}
}
