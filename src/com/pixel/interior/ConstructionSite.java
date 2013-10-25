package com.pixel.interior;

import java.util.HashMap;

public class ConstructionSite {

	public int x, y;
	public int buildingID;
	public HashMap<Integer, Integer> items = new HashMap<Integer, Integer>();	
	
	public ConstructionSite(int x, int y, int buildingID) {
		
		this.x = x;
		this.y = y;
		this.buildingID = buildingID;
		
	}
	
	public void addItem(int id, int amount) {
		
		if (items.containsKey(id)) {
			
			amount += items.get(id);
			items.put(id, amount);
			
		} else {
			
			items.put(id, amount);
			
		}
		
	}
	
	public boolean isCompleted() {
		
		for (Integer id : Building.info.get(buildingID).requirements.keySet()) {
			
			if (!items.containsKey(id)) {
				
				return false;
				
			} else {
				
				if (items.get(id) < Building.info.get(buildingID).requirements.get(id)) {
					
					return false;
					
				}
				
			}
			
		}
		
		return true;
		
	}
	
}
