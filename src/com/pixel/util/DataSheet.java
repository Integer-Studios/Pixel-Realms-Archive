package com.pixel.util;

import java.io.IOException;

public class DataSheet {
	
	public DataSheet(String p) {
		path = p;
		sheet = new TextFile(path);
	}

	public boolean createNewDataSheet() {
		try {
			FileItem f = new FileItem(path);
			if (f.exists()) {
				return false;
			}
			if (f.createNewFile())
				return true;
			else
				return false;
		} catch (IOException e) {
			return false;
		}
	}
	
	public void clearDataSheet() {
		sheet.write("");
	}
	
	public void clearItem(String s) {
		TextIndex t = sheet.findFirst("*@*\""+s+"\"*@*");
		if (t != null)
			sheet.deleteLine(t.line);
	}
	
	public void crearPrefix(String s) {
		TextIndex[] tA = sheet.find("*@*\""+s);
		for (int i = 0; i < tA.length; i++) {
			if (sheet.readLine(tA[i].line).startsWith("*@*\""+s))
				sheet.deleteLine(tA[i].line);
		}
	}
	
	public boolean containsItem(String s) {
		TextIndex t = sheet.findFirst("*@*\""+s+"\"*@*");
		return t != null;
	}
	
	public void writeInt(int i, String s) {
		sheet.newLine("*@*\""+s+"\"*@*" + i);
	}
	
	public void writeFloat(float i, String s) {
		sheet.newLine("*@*\""+s+"\"*@*" + i);
	}
	
	public void writeLong(long i, String s) {
		sheet.newLine("*@*\""+s+"\"*@*" + i);
	}
	
	public void writeString(String i, String s) {
		sheet.newLine("*@*\""+s+"\"*@*" + i);
	}
	
	public void writeBoolean(boolean i, String s) {
		sheet.newLine("*@*\""+s+"\"*@*" + i);
	}
	
	public void writeIntArray(int[] iA, String s) {
		for (int i = 0; i < iA.length; i++) {
			sheet.newLine("*@*\"" + s + "\"*@*" + iA[i]);
		}
	}
	
	public void writeFloatArray(float[] iA, String s) {
		for (int i = 0; i < iA.length; i++) {
			sheet.newLine("*@*\"" + s + "\"*@*" + iA[i]);
		}
	}
	
	public void writeLongArray(long[] iA, String s) {
		for (int i = 0; i < iA.length; i++) {
			sheet.newLine("*@*\"" + s + "\"*@*" + iA[i]);
		}
	}
	
	public void writeStringArray(String[] iA, String s) {
		for (int i = 0; i < iA.length; i++) {
			sheet.newLine("*@*\"" + s + "\"*@*" + iA[i]);
		}
	}
	
	public void writeBooleanArray(boolean[] iA, String s) {
		for (int i = 0; i < iA.length; i++) {
			sheet.newLine("*@*\"" + s + "\"*@*" + iA[i]);
		}
	}
	
	public int readInt(String s) {
		TextIndex t = sheet.findFirst("*@*\""+s+"\"*@*");
		s = sheet.readLine(t.line);
		s = s.substring(s.lastIndexOf("\"*@*")+4);
		return Integer.parseInt(s);
	}
	
	public float readFloat(String s) {
		TextIndex t = sheet.findFirst("*@*\""+s+"\"*@*");
		s = sheet.readLine(t.line);
		s = s.substring(s.lastIndexOf("\"*@*")+4);
		return Float.parseFloat(s);
	}
	
	public long readLong(String s) {
		TextIndex t = sheet.findFirst("*@*\""+s+"\"*@*");
		s = sheet.readLine(t.line);
		s = s.substring(s.lastIndexOf("\"*@*")+4);
		return Long.parseLong(s);
	}
	
	public String readString(String s) {
		TextIndex t = sheet.findFirst("*@*\""+s+"\"*@*");
		s = sheet.readLine(t.line);
		s = s.substring(s.lastIndexOf("\"*@*")+4);
		return s;
	}
	
	public boolean readBoolean(String s) {
		TextIndex t = sheet.findFirst("*@*\""+s+"\"*@*");
		s = sheet.readLine(t.line);
		s = s.substring(s.lastIndexOf("\"*@*")+4);
		return Boolean.parseBoolean(s);
	}
	
	public int[] readIntArray(String s) {
		TextIndex[] tA = sheet.find("*@*\""+s+"\"*@*");
		int[] iA = new int[tA.length];
		for (int i = 0; i < tA.length; i++) {
			s = sheet.readLine(tA[i].line);
			s = s.substring(s.lastIndexOf("\"*@*")+4);
			iA[i] = Integer.parseInt(s);
		}
		return iA;
	}
	
	public float[] readFloatArray(String s) {
		TextIndex[] tA = sheet.find("*@*\""+s+"\"*@*");
		float[] iA = new float[tA.length];
		for (int i = 0; i < tA.length; i++) {
			s = sheet.readLine(tA[i].line);
			s = s.substring(s.lastIndexOf("\"*@*")+4);
			iA[i] = Float.parseFloat(s);
		}
		return iA;
	}
	
	public long[] readLongArray(String s) {
		TextIndex[] tA = sheet.find("*@*\""+s+"\"*@*");
		long[] iA = new long[tA.length];
		for (int i = 0; i < tA.length; i++) {
			s = sheet.readLine(tA[i].line);
			s = s.substring(s.lastIndexOf("\"*@*")+4);
			iA[i] = Long.parseLong(s);
		}
		return iA;
	}
	
	public String[] readStringArray(String s) {
		TextIndex[] tA = sheet.find("*@*\""+s+"\"*@*");
		String[] iA = new String[tA.length];
		for (int i = 0; i < tA.length; i++) {
			s = sheet.readLine(tA[i].line);
			s = s.substring(s.lastIndexOf("\"*@*")+4);
			iA[i] = s;
		}
		return iA;
	}
	
	public boolean[] readBooleanArray(String s) {
		TextIndex[] tA = sheet.find("*@*\""+s+"\"*@*");
		boolean[] iA = new boolean[tA.length];
		for (int i = 0; i < tA.length; i++) {
			s = sheet.readLine(tA[i].line);
			s = s.substring(s.lastIndexOf("\"*@*")+4);
			iA[i] = Boolean.parseBoolean(s);
		}
		return iA;
	}

	public String path;
	public TextFile sheet;
}
