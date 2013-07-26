package com.pixel.communication;

public class SaveBunny extends Thread {

	public void run() {
		try {
//			String urlParameters = "";
//
//			urlParameters = "id=" + PlayerManager.currentUserID + "&request=add";
//
//			String request = "http://pixel-realms.com/backend/bunny_query.php";
//			URL url = new URL(request); 
//			HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
//			connection.setDoOutput(true);
//			connection.setDoInput(true);
//			connection.setInstanceFollowRedirects(false); 
//			connection.setRequestMethod("POST"); 
//			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
//			connection.setRequestProperty("charset", "utf-8");
//			connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
//			//			connection.setRequestProperty("Authorization", "Basic " + new String(encodedAuthorization));
//			connection.setUseCaches (false);
//
//			DataOutputStream wr = new DataOutputStream(connection.getOutputStream ());
//			wr.writeBytes(urlParameters);
//			wr.flush();
//			wr.close();
//
//			InputStream input = connection.getInputStream();
//
//			BufferedReader br = new BufferedReader(new InputStreamReader(input));
//
//			StringBuilder sb = new StringBuilder();
//
//			String line;
//			while ((line = br.readLine()) != null) {
//				sb.append(line);
//			} 
		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
