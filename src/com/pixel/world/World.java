package com.pixel.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.communication.CommunicationClient;
import com.pixel.communication.PlayerManager;
import com.pixel.communication.packet.PacketInfoRequest;
import com.pixel.communication.packet.PacketUpdateWorld;
import com.pixel.entity.Entity;
import com.pixel.entity.EntityOnlinePlayer;
import com.pixel.entity.EntityPlayer;
import com.pixel.gui.GUILoadingScreen;
import com.pixel.interior.Building;
import com.pixel.interior.InteriorWorld;
import com.pixel.interior.InteriorWorldManager;
import com.pixel.lighting.PixelLightingManager;
import com.pixel.piece.Piece;
import com.pixel.piece.PieceBuilding;
import com.pixel.player.PlayerMotionManager;
import com.pixel.stage.StageWorld;
import com.pixel.start.PixelRealms;
import com.pixel.tile.Tile;
import com.pixel.util.Toolkit;

public class World {

	public ConcurrentHashMap<Integer, PieceBuilding> buildings = new ConcurrentHashMap<Integer,PieceBuilding>();
	public ConcurrentHashMap<Integer, Entity> entities = new ConcurrentHashMap<Integer,Entity>();
	public Map<Integer, WorldChunk> chunks;
	
	public static boolean loaded;
	public float oldX, oldY;
	public boolean playerReset, playedLogin;
	public int worldSaveCount = 0;
	public int c = 400;
	public int viewDistance = 32;
	public static int tileConstant = 48;
	public static Toolkit t = new Toolkit();
	public int globalOffsetX = 0;
	public int globalOffsetY = 0;
	public static float pieceLayerOffset = 0.85F;
	public float clipConstant = 0.4F;
//	public StageWorld panelWorld;
	public PixelLightingManager lightingManager;
	public int waitCount = 0;
	public long time = 12000;
	public long dayLength = 24000;
	public static boolean interior;
	public InteriorWorld interiorWorld;

	public World(int c) {
		
		this.c = c;
		
		chunks = Collections.synchronizedMap(new LinkedHashMap<Integer, WorldChunk>());

		loaded = false;
		
		lightingManager = new PixelLightingManager();

		globalOffsetX = (int)(Display.getWidth()/2)-(int)(WorldManager.player.getX() * World.tileConstant);
		globalOffsetY = (int)(Display.getHeight()/2)-(int)(WorldManager.player.getY() * World.tileConstant);

	}

	public void setTile(int x, int y, int id) {
		setTile( x,  y,  id, 0);
	}

	public static void setTile(int x, int y, int id, int metadata) {
		new Tile(x, y, id, metadata, true);
	}

	public int getTile(int x, int y) {
		return getChunk(x, y).getTile(x, y).id;
	}

	public Tile getTileObject(int x, int y) {
		return getChunk(x, y).getTile(x, y);
	}
	
	public void setPieceObject(int x, int y, Piece p) {
		getChunk(x, y).setPiece(p);
	}

	public void setPiece(int x, int y, int id) {
		new Piece(x, y, id, true);

	}

	public void setPiece(int x, int y, int id, int damage, int metadata, int buildingID) {

		if (buildingID == -1) {
			new Piece(x, y, id, true);
			getPieceObject(x, y).damage = damage;
			getPieceObject(x, y).metadata = metadata;

		} else if (Building.canBuildingFit(buildingID, x, y)) {
			
			new Piece(x, y, id, true);
			getPieceObject(x, y).damage = damage;
			getPieceObject(x, y).metadata = metadata;

		}


	}
	
	public int getPiece(int x, int y) {
		if (getChunk(x, y).getPiece(x, y) != null)
			return getChunk(x, y).getPiece(x, y).id;
		else
			return 0;
	}

	public Piece getPieceObject(int x, int y) {
		return getChunk(x, y).getPiece(x, y);
	}
	
	public void setPiece(int x, int y, int id, int damage, int metadata, int buildingID, int worldID) {
		
		if (buildingID == -1){
				getChunk(x,y).setPiece(new PieceBuilding(worldID, x, y, buildingID, damage, metadata, -1));
			
		} else  {

				getChunk(x,y).setPiece(new PieceBuilding(worldID, x, y, buildingID, damage, metadata, -1));

		}
		getPieceObject(x,y).damage = damage;
		getPieceObject(x,y).metadata = metadata;

	}
	
	public Entity getEntity(int serverID) {
		return entities.get(serverID);
	}
	
	public void loadInterior(int worldID) {
		InteriorWorld w = InteriorWorldManager.interiors.get(worldID);
		interior = true;
		WorldManager.player.inside = true;
		interiorWorld = w;
		
		oldX = WorldManager.player.getX();
		oldY = WorldManager.player.getY();
		
		globalOffsetX = (int)(Display.getWidth()/2)-(int)(WorldManager.player.getX() * World.tileConstant);
		globalOffsetY = (int)(Display.getHeight()/2)-(int)(WorldManager.player.getY() * World.tileConstant);
		WorldManager.player.teleported = true;
		WorldManager.player.worldID = worldID;
		CommunicationClient.addPacket(new PacketInfoRequest("players"));
		PlayerManager.updateVisible();
	}
	
	public void leaveInterior() {

		interior = false;
		WorldManager.player.inside = false;
		interiorWorld = null;
		WorldManager.player.worldID = -1;
		playerReset = true;
		
		CommunicationClient.addPacket(new PacketUpdateWorld());
		
	}
	
	boolean a = false;

	public void render(GameContainer c, Graphics g) {
		if (!loaded) {
			return;
		} 

//		WorldManager.player.render(c, g, this);
//		WorldManager.player.painted = true;
		WorldManager.player.painted = false;

		synchronized(chunks) {
			for (WorldChunk chunk : chunks.values()) {
				chunk.render(c, g, this);
				if (!WorldManager.player.painted && (chunk.x == ((int)WorldManager.player.getX() >> 4)) &&( chunk.y == ((int)WorldManager.player.getY() >> 4))) {
					WorldManager.player.render(c, g, this);
					WorldManager.player.painted = true;
				}
			}
		}
		a = true;

	}

	public void renderOld(GameContainer c, Graphics g) {
		
		PixelLightingManager.initialize();
		
		if (!loaded) {
			
			return;
			
		} 
		
		if (!interior && playerReset && (WorldManager.player.getX() != oldX || WorldManager.player.getY() != oldY)) {
			
			return;

		}

		loaded = true;
		ArrayList<Entity> entityArray = new ArrayList<Entity>();
		ArrayList<Entity> paintedEntities = new ArrayList<Entity>();

		entityArray.addAll(entities.values());


		for (WorldChunk chunk : chunks.values()) {

//			try {
//				if (!interior) {
//					piecesArray = pieces;
//					tileArray = (Object[]) tiles.values().toArray();
//				} else {
//					piecesArray = interiorWorld.pieces;
//					tileArray = interiorWorld.tiles.values().toArray();
//				}	
//			} catch (NullPointerException e) {
//
//				e.printStackTrace();
//				piecesArray = pieces;
//				tileArray = (Object[]) tiles.values().toArray();
//
//			}

			if (chunk.tiles.size() > 0) {
				//REDUCABLE LOOP
				for (Tile t : chunk.tiles.values()) {
					if (t.posX > getMinXToPaint() && t.posX < getMaxXToPaint() && t.posY > getMinYToPaint() && t.posY < getMaxYToPaint()) {
						t.render(c, g, this);
					}
				}

				WorldManager.player.painted = false;
				for (Piece p : chunk.pieces.values()) {
					if (p != null) {
						if (p.posX > getMinXToPaint() && p.posX < getMaxXToPaint() && p.posY > getMinYToPaint() && p.posY < getMaxYToPaint()) {

							for (int i = 0; i < entityArray.size(); i ++) {

								Entity entity = entityArray.get(i);
								ArrayList<Entity> qualifiedEntities = new ArrayList<Entity>();
								if (p.posY + pieceLayerOffset > entity.getY() && !paintedEntities.contains(entity)) {

									//								entity.render(c, g, this);
									//								paintedEntities.add(entity);
									qualifiedEntities.add(entity);
									//TODO re order these qualified entities based on y coordinates then paint them in order 
								}

								float lowestY = -1F;
								int lowestEntity = -1;
								int paintedQualifiedEntities = 0;

								while (paintedQualifiedEntities < qualifiedEntities.size()) {
									for (int x = 0; x < qualifiedEntities.size(); x++) {
										if (lowestEntity == -1) {
											lowestY = qualifiedEntities.get(x).getY();
											lowestEntity = x;
										} else if (qualifiedEntities.get(x).getY() < lowestY) {
											lowestY = qualifiedEntities.get(x).getY();
											lowestEntity = x;
										}
									}

									qualifiedEntities.get(lowestEntity).render(c, g, this);
									paintedEntities.add(qualifiedEntities.get(lowestEntity));
									paintedQualifiedEntities ++;

								}


							}

							if (p.posY + pieceLayerOffset > WorldManager.player.getY() && !WorldManager.player.painted && (chunk.x == ((int)WorldManager.player.getX() >> 4)) &&( chunk.y == ((int)WorldManager.player.getY() >> 4))) {
								WorldManager.player.render(c, g, this);
								WorldManager.player.painted = true;
							}
							p.render(c, g, this);
						}
					}

				}

			}

			if (!WorldManager.player.painted) {
				WorldManager.player.render(c, g, this);
				WorldManager.player.painted = true;

			}

			lightingManager.render(c, g, this);

		}

	}

	public int getDistance(float x1, float y1, float x2, float y2) {
		return (int)Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
	}
	
	public void tick() {

		time++;

		CommunicationClient.tick();

		//		if (!interior) {
		//			 piecesArray = pieces;
		//			 tileArray = (Object[]) tiles.values().toArray();
		//		} else {
		//			piecesArray = interiorWorld.pieces;
		//			tileArray = (Object[]) interiorWorld.tiles.values().toArray();
		//		}	

		PlayerMotionManager.updatePlayerMotion(WorldManager.player, this);

		for (int i = 0; i < Tile.info.length; i++) {
			Tile.info[i].infoTick(this);
		}
		synchronized(chunks) {

			for (WorldChunk c : chunks.values()) {

				for (Tile t : c.tiles.values()) {

					t.tick(this);

				}

				for (Piece p : c.pieces.values()) {

					if (p != null) {

						if (p.posX > getMinXToPaint() && p.posX < getMaxXToPaint() && p.posY > getMinYToPaint() && p.posY < getMaxYToPaint()) {

							p.tick(this);

						}

					}

				}

			}

		}

		for (int x = 0; x < entities.size(); x ++) {

			((Entity) entities.values().toArray()[x]).tick(this);

		}

		for (int x = 0; x < PlayerManager.players.size(); x ++) {

			((EntityOnlinePlayer)PlayerManager.players.values().toArray()[x]).tick(this);

		}

		WorldManager.player.tick(this);

	}

	public long getTime() {
		return time;
	}
	
	public long getTimeOfDay() {
		return time % dayLength;
	}

	public int getMinXToPaint() {
		return (-1*globalOffsetX/tileConstant) - 2;
	}

	public int getMaxXToPaint() {
		return (-1*globalOffsetX+Display.getWidth())/tileConstant + 2;
	}

	public int getMinYToPaint() {
		return (-1*globalOffsetY/tileConstant) - 2;
	}

	public int getMaxYToPaint() {
		return (-1*globalOffsetY+Display.getHeight())/tileConstant + 2;
	}

	public Piece getNearestPieceWithinRadiusWithID(int id, int x, int y, int radius) {

		int radiusSquared = radius * radius;

		ArrayList<Float[]> trees = new ArrayList<Float[]>();

		for(int b  = -radius; b <= radius; b++) {

			for(int z = -radius; z <= radius; z++) {

				if( (b*b) + (z*z) <= radiusSquared) {

		        	if (z > 0 && b > 0) {

						if (getPiece(b, z) == id) {

							float distance = (float) Math.sqrt((b - x) * (b - x) + (z - y) * (z - y));

							trees.add(new Float[]{b + 0.0F, z + 0.0F, distance});

						}

					}
		        }
		    }
		}

		Float[] top = new Float[]{1F, 1F, (float) (100000*100000)};

		for (int z = 0; z < trees.size(); z ++) {

			Float[] temp = trees.get(z);

			if (temp[2] < top[2]) {

				top = temp;

			}
 			
		}
            
		int xNew = (int) (top[0] + 0);
		int yNew = (int) (top[1] + 0);

		return getPieceObject(xNew, yNew);

	}
	
	public void disinstantiate() {
		
		loaded = false;
		StageWorld.loadingScreenDone = false;
		interior = false;
		
		PlayerManager.players.clear();
		
		entities = new ConcurrentHashMap<Integer,Entity>();;
		chunks = Collections.synchronizedMap(new LinkedHashMap<Integer, WorldChunk>());
	}
	
	public WorldChunk getChunk(Tile tile) {
		
		return getChunk(tile.posX, tile.posY);

	}

	public WorldChunk getChunk(Piece piece) {

		return getChunk(piece.posX, piece.posY);

	}
	
	public WorldChunk getChunk(Entity entity) {
		
		return getChunk(Math.round(entity.getX()), Math.round(entity.getY()));
		
	}

	public WorldChunk getChunk(int x, int y) {
		
		int id = ((y >> 4) * (c >> 4)) + (x >> 4);
		
		if (chunks.containsKey(id)) {
			
			return (chunks.get(id));
			
		} else {
			
			return new WorldChunk(this, (x >> 4), (y >> 4));
			
		}
		
	}

	public void propagatePiece(Piece piece) {

		getChunk(piece).propagatePiece(piece);

	}

	public void propagateTile(Tile tile) {

		getChunk(tile).propagateTile(tile);
		
	}
	
	public void propagateEntity(Entity entity) {

		getChunk(entity).propagateEntity(entity);
		
	}

	public int hash(Piece p) {

		return (p.posY * c) + p.posX;

	}

	public int hash(int x, int y) {

		return (y * c) + x;

	}
	
	public void propagateChunk(WorldChunk chunk) {

		chunks.put((chunk.y * (c >> 4)) + chunk.x, chunk);
		
	}
	
}
