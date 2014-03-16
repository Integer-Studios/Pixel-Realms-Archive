package com.pixel.frame;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

import org.lwjgl.opengl.Display;

import com.pixel.communication.PlayerManager;
import com.pixel.gui.GUI;
import com.pixel.gui.GUIButton;
import com.pixel.gui.GUIComponent;
import com.pixel.gui.GUIComponentText;
import com.pixel.gui.GUITextBox;
import com.pixel.gui.GUITextBoxPassword;
import com.pixel.sound.PixelSoundManager;
import com.pixel.start.MainLoop;
import com.pixel.start.PixelRealms;
import com.pixel.util.FileItem;
import com.pixel.util.Toolkit;

public class PanelLogin extends Panel {
	
	Toolkit t;
	String basePath, separator;
	String[] login;
	
	public PanelLogin() {
		width = -1;
		updateRenderSize();

		String operatingSystem = System.getProperty("os.name");
		boolean unix = !(operatingSystem.startsWith("Windows"));
		t = new Toolkit();

		PixelRealms.ip = "127.0.0.1";


		switch (PixelRealms.port) {
		
		case 25570:
			System.out.println("[Pixel Realms] Currently connected to the Joint Development Server");
			break;
		case 25571:
			System.out.println("[Pixel Realms] Currently connected to Jakes Development Server");
			break;
		case 25572:
			System.out.println("[Pixel Realms] Currently connected to Quinn's Development Server");
			break;
		}
		
		System.out.println("[Pixel Realms] Current Server IP: " + PixelRealms.ip + ":" + PixelRealms.port);

		
		if (unix) {
		
			basePath = t.shortEndPath(t.getHomeFolder()) + "/Library/Application Support/Pixel Realms/";
			separator = "/";
			
		} else {
		
			basePath = System.getenv("APPDATA") + "\\PixelRealms\\";
			separator = "\\";

		}		
		
		if (new FileItem(basePath + "login.pxr").exists())
			 login = (String[]) t.load(basePath + "login.pxr");
		else
			login = new String[0];
		
		if (login.length > 1) {
			
			username.setText(login[0]);
			password.setText(password.hideText(login[1]));
			password.setEdited();
			
		}
		
		PixelSoundManager.startMusic(PixelSoundManager.Music.ROB_IN_WHITE_SATIN);

	}
	
	public void render(GameContainer c, Graphics g) {
		updateRenderSize();
		super.render(c, g);

//		if (KeyboardListener.keyBindings.get("HideMenu").onKeyUp) {
			
			//slide away login
			
//		}
		
		if (loginBtn.getOnMouseUp()) {
			try {
				tryLogin(username.getText(), password.getText());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//FOR WHEN Y'ALL AINT GOT NO INTERNET  
				//avertLogin(username.getText(), password.getText());
			}
		}
		
	}
	
	public void updateRenderSize() {
		if (width != Display.getWidth() || height != Display.getHeight()) {
			GUI.clear();
			loginBtn = new GUIButton((Display.getWidth()/2 - 85) + 10, (Display.getHeight()/2-60)+125, 170, 70, new GUIComponentText("Login", 55, 8, 35));
			username = new GUITextBox((Display.getWidth()/2 - 135), (Display.getHeight()/2-60)-35, 270, 70, new GUIComponentText("username", 16, 10, 35));
			password = new GUITextBoxPassword((Display.getWidth()/2 - 135), (Display.getHeight()/2-60)+40, 270, 70, new GUIComponentText("password", 16, 10, 35));
			errorField = new GUIComponentText("", Display.getWidth()/2-120, Display.getHeight()/2-140, 30, Color.red);
			
			GUI.addGUIComponent(new GUIComponent(0, 0, Display.getWidth(), Display.getHeight(), GUI.t.getPath()+"resources" + PixelRealms.t.separator + "gui" + PixelRealms.t.separator + "login.png"));
			loginBtn = (GUIButton) GUI.addGUIComponent(loginBtn);
			username = (GUITextBox) GUI.addGUIComponent(username);
			password = (GUITextBoxPassword) GUI.addGUIComponent(password);
			errorField = (GUIComponentText) GUI.addGUIComponent(errorField);
			if (!GUI.components.contains(MainLoop.newsFeed))
			GUI.addGUIComponent(MainLoop.newsFeed);
			
			width = Display.getWidth();
			height = Display.getHeight();
		}
	}
	
	public void avertLogin(String ign, String password) {
    	System.out.println("[Pixel Realms] Unknown Host, averting login...");
        PlayerManager.currentPlayer = "unverified";
    	PlayerManager.currentUserID = -1;
//    	MainFrame.setPanel(new PanelMainMenu(frame));
    	System.out.println("[Pixel Realms] Current User: " + PlayerManager.currentPlayer);
	}
	
	public void tryLogin(String ign, String password) throws Exception {

		MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
 
        byte byteData[] = md.digest();
 
        //convert the byte to hex format method 1
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
        	buffer.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        password = buffer.toString();

        //		 byte[] encodedAuthorization = org.apache.commons.codec.binary.Base64.encodeBase64(authentication.getBytes());
        String urlParameters = "";

        urlParameters = "ign=" + ign + "&password=" + password;

        String request = "http://pixel-realms.com/game_login.php";
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

        String responseString = sb.toString();
    	FileItem loginFile = new FileItem(basePath + "login.pxr");

        if (responseString.contains("LOGIN_SUCCESS")) {

        	PlayerManager.session = Integer.parseInt(responseString.split("!!NEMATTE!!")[3]);
        	PlayerManager.currentPlayer = responseString.split("!!NEMATTE!!")[2];
        	PlayerManager.currentUserID = Integer.parseInt(responseString.split("!!NEMATTE!!")[1]);
        	MainLoop.setPanel(1);
        	//        	MainFrame.setPanel(new PanelMainMenu(frame));
        	System.out.println("[Pixel Realms] Current User: " + PlayerManager.currentPlayer);
        	String[] login = new String[]{ign, this.password.getText()};

        	t.save(basePath + "login.pxr", login);

        } else if (responseString.contains("PASSWORD_INCORRECT")) {
        	errorField.setText("Sorry, password's worng");
        	login = null;
        	errorField.setPosition(Display.getWidth()/2-120, Display.getHeight()/2-140);
        	if (loginFile.exists())
        		loginFile.delete();
        	
        } else if (responseString.contains("USERNAME_INCORRECT")) {
        	errorField.setText("Wrong username, that's embarrassing");
        	errorField.setPosition(Display.getWidth()/2-190, Display.getHeight()/2-140);
        	if (loginFile.exists())
        		loginFile.delete();
        } else if (responseString.contains("UNVERIFIED")) {
        	errorField.setText("Sorry, that account isn't verified.");
        	errorField.setPosition(Display.getWidth()/2-190, Display.getHeight()/2-140);
        }
	}

	public GUIButton loginBtn;
	public GUITextBox username;
	public GUITextBoxPassword password;
	public GUIComponentText errorField;
	public int width, height;

}