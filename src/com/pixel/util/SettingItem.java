package com.pixel.util;

import java.io.IOException;

public class SettingItem {
	
	public SettingItem(String path) {
		file = new TextFile(path);
		fi = new FileItem(file.path);
	}
	
	public boolean create() {
		try {
			return fi.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void addKey(String key, String value) {
		if (!fi.exists())
			create();
		file.newLine(key + "=" + value);
	}
	
	public void addKey(String key, boolean value) {
		if (!fi.exists())
			create();
		String valueStr = "";
		if (value)
			valueStr = "true";
		if (!value)
			valueStr = "false";
		file.newLine(key + "=" + valueStr);
	}
	
	public void addKey(String key, int value) {
		if (!fi.exists())
			create();
		file.newLine(key + "=" + value);
	}
	
	public String loadStringForKey(String key) {
		if (!fi.exists())
			create();
		TextIndex ti = file.findFirst(key);
		String line = file.readLine(ti.line);
		String delims = "[=]+";
 		String[] split = line.split(delims);
 		if (split.length > 0)
 			return split[1];
 		else
 			return null;
	}
	
	public int loadIntForKey(String key) {
		if (!fi.exists())
			create();
		TextIndex ti = file.findFirst(key);
		String line = file.readLine(ti.line);
		String delims = "[=]+";
 		String[] split = line.split(delims);
 		if (split.length > 0)
 			return Integer.parseInt(split[1]);
 		else
 			return 0;
	}
	
	public boolean loadBoolForKey(String key) {
		if (!fi.exists())
			create();
		TextIndex ti = file.findFirst(key);
		String line = file.readLine(ti.line);
		String delims = "[=]+";
 		String[] split = line.split(delims);
 		if (split.length > 0) {
 			if (split[1].equals("true"))
 				return true;
 			else
 				return false;
 		}
 		else
 			return false;
	}
	
	public TextFile file;
	public FileItem fi;
}
