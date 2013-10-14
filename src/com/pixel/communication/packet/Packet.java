package com.pixel.communication.packet;

import org.newdawn.slick.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;

import com.pixel.communication.CommunicationClient;
import com.pixel.communication.PlayerManager;
import com.pixel.start.PixelRealms;
import com.pixel.world.World;

public abstract class Packet {
	
	public int id;
	public boolean loaded;
	public int userID;
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

			packet.userID = userID;
			packet.id = id;
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
	
	public abstract void writeData(DataOutputStream output) throws IOException;
	
	public abstract void readData(DataInputStream input) throws IOException;

	public static void init() {
		
		packetMap.put(0, PacketBlank.class);
		packetMap.put(1, PacketLogin.class);
		packetMap.put(2, PacketUpdatePlayer.class);
		packetMap.put(3, PacketWorldData.class);
		packetMap.put(4, PacketUpdateTile.class);
		packetMap.put(5, PacketUpdatePiece.class);
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
		
	}
	
}
