package com.pixel.interior;

import java.util.HashMap;

import com.pixel.util.CoordinateKey;

public class ConstructionSiteManager {

	public static HashMap<CoordinateKey, ConstructionSite> sites = new HashMap<CoordinateKey, ConstructionSite>();

	public static void addSite(ConstructionSite site) {

		sites.put(new CoordinateKey(site.x, site.y), site);
		
	}
	
}
