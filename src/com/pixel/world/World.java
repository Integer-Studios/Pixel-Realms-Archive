package com.pixel.world;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import com.pixel.communication.CommunicationClient;
import com.pixel.communication.PlayerManager;
import com.pixel.communication.packet.PacketInfoRequest;
import com.pixel.communication.packet.PacketLogin;
import com.pixel.communication.packet.PacketUpdateTile;
import com.pixel.communication.packet.PacketUpdateWorld;
import com.pixel.effects.Particle;
import com.pixel.entity.Entity;
import com.pixel.entity.EntityOnlinePlayer;
import com.pixel.entity.EntityPlayer;
import com.pixel.frame.PanelWorld;
import com.pixel.gui.GUILoadingScreen;
import com.pixel.piece.Piece;
import com.pixel.piece.PieceBuilding;
import com.pixel.player.PlayerMotionManager;
import com.pixel.sound.Sound;
import com.pixel.sound.Sound.Music;
import com.pixel.start.PixelRealms;
import com.pixel.tile.Tile;
import com.pixel.util.Toolkit;

public class World {

	public static ConcurrentHashMap<Integer, Tile> tiles = new ConcurrentHashMap<Integer,Tile>();
	public static ConcurrentHashMap<Integer, PieceBuilding> buildings = new ConcurrentHashMap<Integer,PieceBuilding>();
	public static ConcurrentMap<Integer, Piece> pieces;
	public static ConcurrentHashMap<Integer, Entity> entities = new ConcurrentHashMap<Integer,Entity>();
//	public static ConcurrentHashMap<Integer, Herd> herds = new ConcurrentHashMap<Integer,Herd>();
	public static ConcurrentHashMap<Integer, Tile> tilesLocked = new ConcurrentHashMap<Integer,Tile>();
	public static ConcurrentHashMap<Integer, PieceBuilding> buildingsLocked = new ConcurrentHashMap<Integer,PieceBuilding>();
	public static ConcurrentMap<Integer, Piece> piecesLocked;
	public static ConcurrentHashMap<Integer, Entity> entitiesLocked = new ConcurrentHashMap<Integer,Entity>();

	public static int derp = 0x01;
	public static int derp1 = 0x02;

	
	public static boolean loaded, loadingScreenDone, removeLoadingScreen, locked;
	public float oldX, oldY;
	public boolean playerReset;
	public int worldSaveCount = 0;
	public ArrayList<Particle> particles = new ArrayList<Particle>();
	public static int c = 400;
	public int viewDistance = 32;
	public static int tileConstant = 48;
	public static Toolkit t = new Toolkit();
	public static int globalOffsetX = 0;
	public static int globalOffsetY = 0;
	public EntityPlayer player;
	public float pieceLayerOffset = 0.85F;
	public float clipConstant = 0.4F;
	public PanelWorld panelWorld;
	public static GUILoadingScreen loadingScreen;
	public LightingManager lightingManager;
	public long time = 12000;
	public long dayLength = 24000;
	public static boolean interior;
	public InteriorWorld interiorWorld;
	
	public World(PanelWorld p) {
		PixelRealms.world = this;

		panelWorld = p;
		pieces = new ConcurrentLinkedHashMap.Builder<Integer, Piece>().maximumWeightedCapacity(10000000).build();
		loaded = false;
		player = new EntityPlayer(200, 200);
		
		CommunicationClient.addPacket(new PacketLogin(PlayerManager.currentPlayer, PlayerManager.session));

		pieces.clear();
//		herds.clear();
		tiles.clear();
		entities.clear();

//		for (int y = 0; y < World.c; y++) {
//
//			for (int x = 0; x < World.c; x ++) {
//
//				pieces.put((y * c) + x, new Piece(x, y, 0, false));
//
//			}
//
//		}

		lightingManager = new LightingManager();

		globalOffsetX = (int)(Display.getWidth()/2)-(int)(player.getX() * World.tileConstant);
		globalOffsetY = (int)(Display.getHeight()/2)-(int)(player.getY() * World.tileConstant);


	}

	public void setPlayer(EntityPlayer player) {

		this.player = player;
		
	}
	
	public void setTile(int x, int y, int id) {
		setTile( x,  y,  id, 0);
	}

	public void setTile(int x, int y, int id, int metadata) {
		tiles.put((y * c) + x, new Tile(x, y, id, metadata, true));
		CommunicationClient.addPacket(new PacketUpdateTile(id, x, y));
	}

	public int getTile(int x, int y) {
		if (tiles.get((y * c) + x) != null)
			return tiles.get((y * c) + x).id;
		else
			return 0;
	}

	public Tile getTileObject(int x, int y) {
		return tiles.get((y * c) + x);
	}
	
	public void setPieceObject(int x, int y, Piece p) {
		if (pieces.containsKey(hash(p)))
			pieces.replace(hash(p), p);
		else
			pieces.put(hash(p), p);
	}

	public void setPiece(int x, int y, int id) {
		if (pieces.containsKey(hash(x, y)))
			pieces.replace(hash(x, y), new Piece(x, y, id, true));
		else
			pieces.put(hash(x, y), new Piece(x, y, id, true));

	}

	public void setPiece(int x, int y, int id, int damage, int metadata, int buildingID, int worldID) {
		
		if (buildingID == -1){
			if (pieces.containsKey(hash(x, y)))
				pieces.replace(hash(x, y), new Piece(x, y, id, true));
			else
				pieces.put(hash(x, y), new Piece(x, y, id, true));
			
		} else  {

			if (pieces.containsKey(hash(x, y)))
				pieces.replace(hash(x, y), new PieceBuilding(worldID, x, y, buildingID, damage, metadata));
			else
				pieces.put(hash(x, y), new PieceBuilding(worldID, x, y, buildingID, damage, metadata));

		}
		pieces.get((y * c) + x).damage = damage;
		pieces.get((y * c) + x).metadata = metadata;

	}
	
	public int getPiece(int x, int y) {
		if (pieces.containsKey((y * c) + x))
			return pieces.get((y * c) + x).id;
		else
			return 0;
	}

	public Piece getPieceObject(int x, int y) {
		return pieces.get((y * c) + x);
	}

	public void spawnParticle(Particle p) {
		particles.add(p);
	}
	
	public static Entity getEntity(int serverID) {
		return entities.get(serverID);
	}
	
	public void loadInterior(int worldID) {
		InteriorWorld w = InteriorWorldManager.interiors.get(worldID);
		interior = true;
		player.inside = true;
		interiorWorld = w;
		
//		tiles.clear();
//		pieces.clear();
//		entities.clear();
//		
//		tiles = w.tiles;
//		pieces = w.pieces;
//		entities = w.entities;
		
		oldX = player.getX();
		oldY = player.getY();
		
		World.globalOffsetX = (int)(Display.getWidth()/2)-(int)(player.getX() * World.tileConstant);
		World.globalOffsetY = (int)(Display.getHeight()/2)-(int)(player.getY() * World.tileConstant);
		player.teleported = true;
		player.worldID = worldID;
		CommunicationClient.addPacket(new PacketInfoRequest("players"));
		PlayerManager.updateVisible();
	}
	
	public void leaveInterior() {

		interior = false;
		player.inside = false;
		interiorWorld = null;
		player.worldID = -1;
		playerReset = true;
		
		CommunicationClient.addPacket(new PacketUpdateWorld());
		
	}

	public void render(GameContainer c, Graphics g) {
		
		if (!loadingScreenDone)
			loadingScreen.tick();

		if (!loaded) {
			
			return;
			
		}
		
		if (!interior && playerReset && (player.getX() != oldX || player.getY() != oldY)) {
			
			return;
			
		}

		loaded = true;
		ArrayList<Entity> entityArray = new ArrayList<Entity>();
		ArrayList<Entity> paintedEntities = new ArrayList<Entity>();
		Object[] tileArray;
		ConcurrentMap<Integer, Piece> piecesArray;
		
		if (locked) {

		

			entityArray.addAll(entitiesLocked.values());

			entityArray.addAll(PlayerManager.players.values());
			

			try {
				if (!interior) {
					piecesArray = piecesLocked;
					tileArray = (Object[]) tilesLocked.values().toArray();
				} else {
					piecesArray = interiorWorld.pieces;
					tileArray = interiorWorld.tiles.values().toArray();
				}	
			} catch (NullPointerException e) {

				e.printStackTrace();
				piecesArray = piecesLocked;
				tileArray = (Object[]) tilesLocked.values().toArray();

			}

		} else {
			
			
			entityArray.addAll(entities.values());

			entityArray.addAll(PlayerManager.players.values());
			
			try {
				if (!interior) {
					piecesArray = pieces;
					tileArray = (Object[]) tiles.values().toArray();
				} else {
					piecesArray = interiorWorld.pieces;
					tileArray = interiorWorld.tiles.values().toArray();
				}	
			} catch (NullPointerException e) {

				e.printStackTrace();
				piecesArray = pieces;
				tileArray = (Object[]) tiles.values().toArray();

			}

			
		}
		
		if (tileArray.length > 0) {
			//REDUCABLE LOOP
			for (int x = 0; x < tileArray.length; x++) {
				if ((((Tile) tileArray[x]).posX > getMinXToPaint() && ((Tile) tileArray[x]).posX < getMaxXToPaint() && ((Tile) tileArray[x]).posY > getMinYToPaint() && ((Tile) tileArray[x]).posY < getMaxYToPaint())) {
					((Tile) tileArray[x]).render(c, g, this);
				}
			}
			player.painted = false;
			for (Piece p : piecesArray.values()) {
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

						if (p.posY + pieceLayerOffset > player.getY() && !player.painted) {
							player.render(c, g, this);
							player.painted = true;
						}
						p.render(c, g, this);
					}
				}
			}
			if (!player.painted) {
				player.render(c, g, this);
				player.painted = true;
			}
			
			for (int y = 0; y < particles.size(); y ++) {
				particles.get(y).render(c, g, this);
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
		
		Object[] tileArray;
		ConcurrentMap<Integer, Piece> piecesArray;
		if (!interior) {
			 piecesArray = pieces;
			 tileArray = (Object[]) tiles.values().toArray();
		} else {
			piecesArray = interiorWorld.pieces;
			tileArray = (Object[]) interiorWorld.tiles.values().toArray();
		}	
		
		PlayerMotionManager.updatePlayerMotion(player, this);
		
		for (int i = 0; i < Tile.info.length; i++) {
			Tile.info[i].infoTick(this);
		}

		for (int x = 0; x < tileArray.length; x ++) {
			if (getDistance(player.posX, player.posY, ((Tile) tileArray[x]).posX, ((Tile) tileArray[x]).posY) < 10) {

				((Tile) tileArray[x]).tick(this);
			}
			
		}


		for (Piece p : piecesArray.values()) {
				if (p != null) {
					if (p.posX > getMinXToPaint() && p.posX < getMaxXToPaint() && p.posY > getMinYToPaint() && p.posY < getMaxYToPaint()) {

						p.tick(this);

					}
			} else 
				break;

		}
				
		for (int x = 0; x < entities.size(); x ++) {

			((Entity) entities.values().toArray()[x]).tick(this);

		}
		
		
		for (int x = 0; x < PlayerManager.players.size(); x ++) {

			((EntityOnlinePlayer)PlayerManager.players.values().toArray()[x]).tick(this);

		}

		//REDUCABLE LOOP
		for (int x = 0; x < particles.size(); x ++) {
			particles.get(x).tick(this, x);
		}
		
		player.tick(this);
		
		int play = 0 + (int)(Math.random() * ((10000 - 0) + 1));
		
		if (play == 1 && Sound.getCurrentSong() == Sound.Music.OFF) {
			
			PixelRealms.playSong(Music.ROB_IN_WHITE_SATIN);
			
		}

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

						if (this.getPiece(b, z) == id) {

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

		return this.getPieceObject(xNew, yNew);

	}
	
	public void disinstantiate() {
		
		loaded = false;
		loadingScreenDone = false;
		interior = false;
		
//		player.walkingClip.close();
		
		PlayerManager.players.clear();
		
		entities = new ConcurrentHashMap<Integer,Entity>();;
		tiles = new ConcurrentHashMap<Integer,Tile>();
		pieces = new ConcurrentLinkedHashMap.Builder<Integer, Piece>().maximumWeightedCapacity(10000000).build();
		
	}
	
	public static void propagatePiece(Piece piece) {

		if (!interior)
			if (pieces.containsKey(hash(piece)))
				pieces.replace(hash(piece), piece);
			else
				pieces.put(hash(piece), piece);
	}
	
	public static void propagateTile(Tile tile) {

		tiles.put((tile.posY * c) + tile.posX, tile);
		
	}
	
	public static void propagateEntity(Entity entity) {

		entities.put(entity.serverID, entity);
		
	}

	public static int hash(Piece p) {

		return (p.posY * c) + p.posX;

	}

	public static int hash(int x, int y) {

		return (y * c) + x;

	}
	
	public static void holdTillLoad() {

		tiles.clear();
		pieces.clear();
		entities.clear();
		buildings.clear();
		
//		tilesLocked = tiles;
//		piecesLocked = pieces;
//		entitiesLocked = entities;
//		buildingsLocked = buildings;
		
//		locked = true;
		
		
	}

	public static void release() {

//		tilesLocked.clear();
//		piecesLocked.clear();
//		entitiesLocked.clear();
//		buildingsLocked.clear();
		
//		locked = false;
		
	}

}
