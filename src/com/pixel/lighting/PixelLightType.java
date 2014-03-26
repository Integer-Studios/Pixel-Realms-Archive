package com.pixel.lighting;

public enum PixelLightType {

	DEFAULT("alpha.png");

	public String fileName;

	PixelLightType(String file) {

		fileName = file;
		
	}
	
	public int getTypeID() {
		
		switch(this) {
		case DEFAULT:
			return 0;
		default:
			return 0;
		}
 		
	}	
	
	public static PixelLightType getTypeForID(int id) {
		
		switch(id) {
		case 0:
			return PixelLightType.DEFAULT;
		default:
			return PixelLightType.DEFAULT;
		}
 		
	}
	
	
}
