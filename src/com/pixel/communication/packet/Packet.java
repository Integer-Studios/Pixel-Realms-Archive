package com.pixel.communication.packet;

import org.newdawn.slick.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.pixel.communication.CommunicationClient;
import com.pixel.communication.PlayerManager;
import com.pixel.start.PixelRealms;
import com.pixel.world.World;

public abstract class Packet {
	
	public int id;
	public boolean loaded;
	public int userID;
	public ArrayList<Float> auxiliaryFloats = new ArrayList<Float>();
	public ArrayList<Integer> auxiliaryIntegers = new ArrayList<Integer>();
	public ArrayList<Boolean> auxiliaryBooleans = new ArrayList<Boolean>();
	public ArrayList<String> auxiliaryStrings = new ArrayList<String>();
	
	@SuppressWarnings("rawtypes")
	private static HashMap<Integer, Class> packetMap = new HashMap<Integer, Class>();
	
	public static void writePacket(Packet packet, DataOutputStream output) {
		if (((packet.id != 1 && packet.id != 3) && !PixelRealms.loggedIn) || (packet.id == 2 && !World.loaded)) {
			
			return;
			
		} 
		
		packet.userID = PlayerManager.currentUserID;
		try {
			output.writeInt(packet.id);
			output.writeInt(PlayerManager.currentUserID);
			packet.writeAuxiliaryVariables(output);
			packet.writeData(output);
			output.flush();

			if (packet.id == 9) {
				
				CommunicationClient.disconnect();
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static Packet readPacket(DataInputStream input) {
		
		try {
			
			int id = input.readInt();
				
			int userID = input.readInt();

			Packet packet = getPacket(id);

			if (packet == null) 
				System.out.println("[Pixel Realms] Skipping packet with id: " + id);
			
			packet.userID = userID;
			packet.id = id;
			packet.readAuxiliaryVariables(input);
			packet.readData(input);
			packet.loaded = true;

			return packet;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			if (CommunicationClient.isRunning()) {
				CommunicationClient.disconnect();
				PixelRealms.loop.displayAlert("The server is not up! Try again later.", Color.black, "connect");
				System.err.println("Client lost the connection to: " + PixelRealms.ip  + ".");
				
			}

		} 
		
		return null;
		
	}
	
	public static void writeString(String string, DataOutputStream output) throws IOException {
        if (string.length() > 32767)
        {
            throw new IOException("String too big");
        }
        else
        {
            output.writeShort(string.length());
            output.writeChars(string);
        }
    }

    public static String readString(int length, DataInputStream input) throws IOException {
       
    	short lengthRead = input.readShort();

        if (lengthRead > length)
        {
            throw new IOException("Received string length longer than maximum allowed (" + lengthRead + " > " + length + ")");
        }
        else if (lengthRead < 0)
        {
            throw new IOException("Received string length is less than zero! Weird string!");
        }
        else
        {
            StringBuilder builder = new StringBuilder();

            for (int x = 0; x < lengthRead; ++x)
            {
            	builder.append(input.readChar());
            }

            return builder.toString();
        }
    }
    
    @SuppressWarnings("rawtypes")
	public static Packet getPacket(int id) {
        try
        {
            Class packetClass = (Class)packetMap.get(id);
            	
            if (id == 14) {
            	
            	PacketLoadInterior l = (PacketLoadInterior)packetClass.newInstance();
            	
            	return l;
            	
            }
            
            return packetClass == null ? null : (Packet)packetClass.newInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Skipping packet with id " + id);
            return null;
        }
    }
    
    public void writeAuxiliaryVariables(DataOutputStream output) throws IOException {
		
		output.writeInt(auxiliaryFloats.size());
		output.writeInt(auxiliaryIntegers.size());
		output.writeInt(auxiliaryBooleans.size());
		output.writeInt(auxiliaryStrings.size());

		for (Float f : auxiliaryFloats) {

			output.writeFloat(f);

		}

		for (Integer i : auxiliaryIntegers) {

			output.writeInt(i);

		}
		
		for (Boolean b : auxiliaryBooleans) {

			output.writeBoolean(b);

		}

		for (String s : auxiliaryStrings) {

			output.writeInt(s.length());
			writeString(s, output);

		}
		
	}
	
	public void readAuxiliaryVariables(DataInputStream input) throws IOException {
		
		int floats = input.readInt();
		int ints = input.readInt();
		int booleans = input.readInt();
		int strings = input.readInt();
		
		for (int x = 0; x < floats; x ++) {

			auxiliaryFloats.add(input.readFloat());

		}

		for (int x = 0; x < ints; x ++) {

			auxiliaryIntegers.add(input.readInt());

		}

		for (int x = 0; x < booleans; x ++) {

			auxiliaryBooleans.add(input.readBoolean());

		}

		for (int x = 0; x < strings; x ++) {
			
			int length = input.readInt();
			auxiliaryStrings.add(readString(length, input));

		}


	}

	public Packet addAuxiliaryFloat(float f) {

		auxiliaryFloats.add(f);
		return this;

	}

	public Packet addAuxiliaryInteger(Integer integer) {

		auxiliaryIntegers.add(integer);
		return this;

	}

	public Packet addAuxiliaryBoolean(Boolean bool) {

		auxiliaryBooleans.add(bool);
		return this;

	}
	
	public Packet addAuxiliaryString(String string) {

		auxiliaryStrings.add(string);
		return this;

	}

	public Packet addAuxiliaryFloats(float[] floats) {

		for (int x = 0; x < floats.length; x ++) {
			
			auxiliaryFloats.add(floats[x]);
		
		}
			
		return this;

	}
	
	public Packet addAuxiliaryIntegers(int[] integers) {

		for (int x = 0; x < integers.length; x ++) {
			
			auxiliaryIntegers.add(integers[x]);
		
		}
		return this;

	}
	
	public Packet addAuxiliaryBooleans(Boolean[] booleans) {

		for (int x = 0; x < booleans.length; x ++) {
			
			auxiliaryBooleans.add(booleans[x]);
		
		}
		return this;

	}
	
	public Packet addAuxiliaryStrings(String[] strings) {

		for (int x = 0; x < strings.length; x ++) {
			
			auxiliaryStrings.add(strings[x]);
		
		}
		return this;

	}
	
	public abstract void writeData(DataOutputStream output) throws IOException;
	
	public abstract void readData(DataInputStream input) throws IOException;

	public static void init() {
		
		packetMap.put(0, PacketBlank.class);
		packetMap.put(1, PacketLogin.class);
		packetMap.put(2, PacketUpdatePlayer.class);
		packetMap.put(3, PacketWorldData.class);
		packetMap.put(4, PacketUpdateTile.class);
		packetMap.put(5, PacketChangePiece.class);
		packetMap.put(6, PacketUpdateWorld.class);
		packetMap.put(7, PacketUpdateLivingEntity.class);
		packetMap.put(8, PacketChat.class);
		packetMap.put(9, PacketLogout.class);
		packetMap.put(10, PacketDamagePiece.class);
		packetMap.put(11, PacketUpdateInventoryContent.class);
		packetMap.put(12, PacketDamageEntity.class);
		packetMap.put(13, PacketDamagePlayer.class);
		packetMap.put(14, PacketLoadInterior.class);
		packetMap.put(15, PacketMoveLivingEntity.class);
		packetMap.put(16, PacketMovePlayer.class);
		packetMap.put(17, PacketInfoRequest.class);
		packetMap.put(18, PacketLoadPlayer.class);
		packetMap.put(19, PacketUpdateInteriorPiece.class);
		packetMap.put(20, PacketUpdatePiece.class);
		packetMap.put(21, PacketUpdateConstructionSite.class);
		packetMap.put(22, PacketEntityAnimation.class);
		packetMap.put(23, PacketUpdateTime.class);
		packetMap.put(24, PacketLoginStage.class);

	}
	
}
