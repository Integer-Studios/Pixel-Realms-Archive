package com.pixel.util;

public class SHScript {
	
	public SHScript(String d) {
		dir = d;
	}
	
	public void addLines(String[] ss) {
		for (int i = 0; i < ss.length; i++) {
			s+=ss[i]+"\n";
		}
	}
	
	public void addLine(String ss) {
		s+=ss+"\n";
	}
	
	public String read() {
		return s;
	}
	
	public String s = "";
	public String dir;
}
