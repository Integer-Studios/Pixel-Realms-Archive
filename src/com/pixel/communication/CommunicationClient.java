package com.pixel.communication;

import org.newdawn.slick.Color;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import com.pixel.communication.packet.Packet;
import com.pixel.gui.GUIAlert;
import com.pixel.start.MainLoop;
import com.pixel.start.PixelRealms;

public class CommunicationClient implements Runnable {

    public static AtomicInteger sent = new AtomicInteger();
    public static AtomicInteger received = new AtomicInteger();
    public String host;
	public Thread writer, reader;

    private volatile static ArrayList<Packet> packetQue = new ArrayList<Packet>();
	private volatile static DataOutputStream output;
	private volatile static DataInputStream input;
	private volatile static boolean running;
	private volatile static Socket socket;

	public CommunicationClient(String host) {
		
		this.host = host;
		
	}
	
	@Override
	public void run() {

		try {
			Packet.init();
			socket = new Socket(host, 25566);
			socket.setTcpNoDelay(true);
			output = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream(), 5120));
			input = new DataInputStream(socket.getInputStream());
			writer = new CommunicationClientWriterThread(this);
			reader = new CommunicationClientReaderThread(this);
			running = true;
			
			writer.start();
			reader.start();
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: " + host + ".");
			System.exit(1);
		} catch (IOException e) {
			
			PixelRealms.loop.displayAlert("The server is not up! Try again later.", Color.black, "connect");
			System.err.println("Couldn't get I/O for the connection to: " + PixelRealms.ip  + ".");
		}

		

	}
	
	public static void tick() {
		
		if (socket != null && !socket.isConnected()) {
			
			PixelRealms.loop.displayAlert("The server is not up! Try again later.", Color.black, "connect");
			System.err.println("Lost the connection to: " + PixelRealms.ip  + ".");
			
		}
		
	}

	private boolean sendPacket() {

		try {

			Packet packet;

			if (packetQue.size() > 0) {
				
				packet = packetQue.get(0);

				if (packet != null) {

					packetQue.remove(0);
					Packet.writePacket(packet, output);
					return true;

				}
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
	
	private Packet readPacket() {
		
		try {
			
			return Packet.readPacket(input);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return null;

	}

	public static void addPacket(Packet packet) {
		
		packetQue.add(packet);
		
	}
	
	public static void sendNetworkPacket(CommunicationClient client) {
		if (isRunning())
			client.sendPacket();
		
	}
	
	public static Packet readNetworkPacket(CommunicationClient client) {
		
		if (isRunning())
			return client.readPacket();
		else
			return null;
	}
	
	public static void alertReturn() {
		
		PixelRealms.world.panelWorld.disinstantiate();
		MainLoop.setPanel(1);
		
	}
	
	public static void disconnect() {

		try {
			
			output.flush();
		    output.close();
			input.close();
			socket.close();
			running = false;

			disinstantiate();

			if (PlayerManager.kicked) {

				new GUIAlert("Someone is logged in to your account!", Color.black, "disconnect");
				System.err.println("Kicked from: " + PixelRealms.ip  + ".");

				PlayerManager.kicked = false;

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	public static void disinstantiate() {
		
		packetQue.clear();
		
	}
	
	public static boolean isRunning() {
		
		return running;
		
	}
	
}
