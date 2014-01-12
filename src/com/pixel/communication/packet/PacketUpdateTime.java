package com.pixel.communication.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Timestamp;

import com.pixel.start.PixelRealms;

public class PacketUpdateTime extends Packet {

	public long time;
	public long timeBetween;
	public long timestamp;

	public PacketUpdateTime() {}
	
	@Override
	public void writeData(DataOutputStream output) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void readData(DataInputStream input) throws IOException {

		time = input.readLong();
		timeBetween = input.readLong();
		timestamp = input.readLong();
		
		java.util.Date date= new java.util.Date();
		long currentTimestamp = new Timestamp(date.getTime()).getTime();
		
		if ((currentTimestamp - timestamp) >= timeBetween) {
			
			int t = (int) ((currentTimestamp - timestamp) / timeBetween);
			
			time += t;
			
		}
		
		PixelRealms.world.time = time;

	}

}
