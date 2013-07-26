package com.pixel.communication;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.pixel.entity.EntityPlayer;

public class GetBunnies extends Thread {

	EntityPlayer player;
	
	public GetBunnies(EntityPlayer player) {
		
		this.player = player;
		
	}
	
	public void run() {
		try {
			String urlParameters = "";

			urlParameters = "id=" + PlayerManager.currentUserID + "&request=get";

			String request = "http://pixel-realms.com/backend/bunny_query.php";
			URL url = new URL(request); 
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false); 
			connection.setRequestMethod("POST"); 
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
			connection.setRequestProperty("charset", "utf-8");
			connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
			//			connection.setRequestProperty("Authorization", "Basic " + new String(encodedAuthorization));
			connection.setUseCaches (false);

			DataOutputStream wr = new DataOutputStream(connection.getOutputStream ());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			InputStream input = connection.getInputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(input));

			StringBuilder sb = new StringBuilder();

			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			} 
			
			player.bunnies = Integer.parseInt(sb.toString());
			player.interfaceManager.bunnyCounter.setBunnies(player.bunnies);

		} catch (Exception e) {

			if (!(e instanceof NumberFormatException))
				e.printStackTrace();

		}

	}

}
