package com.pixel.start;

public class PixelLogger {

	public static String prefix = "[Pixel Realms] ";
	public static String debugPrefix = "[Debug] ";
	
	public static void err(String error) {
		
		System.out.println(prefix + error);
		
	}
	
	public static void log(String log) {
		
		System.out.println(prefix + log);

	}
	
	public static void print(String msg) {
		
		System.out.println(prefix + msg);
		
	}
	
	public static void debug(String key, int...is) {
		
		System.out.print(debugPrefix + key + " ");
		
	    for(int i: is) {
	    	
	    	System.out.print(i + " ");
	    	
	    }

	    System.out.println();

	}
	
	public static void debug(String key, float...fs) {
		
		System.out.print(debugPrefix + key + " ");
		
	    for(float f : fs) {
	    	
	    	System.out.print(f + " ");
	    
	    }

	    System.out.println();
	    
	}
	
	public static void debug(String key, String...str) {
		
		System.out.print(debugPrefix + key + " ");
		
	    for(String s : str) {
	    	
	    	System.out.print(s + " ");
	    	
	    }
	    System.out.println();

		
	}
	
}
