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
		
		for (Integer id : Building.info.get(buildingID).requirements.keySet()) {

			items.put(id, Building.info.get(buildingID).requirements.get(id));
			
		}
		
	}
	
	public int addItem(int id, int amount) {
		
		if (items.containsKey(id)) {
			
			if (amount <= items.get(id)) {
				amount = items.get(id) - amount;
				items.put(id, amount);
			} else {
				
				amount -= items.get(id);
				items.put(id, 0);
				return amount;
				
			}

		} else {
			
			return -1;
			
		}
		
		System.out.println(isCompleted());
		return 0;
		
	}
	
	public boolean isCompleted() {
		
		for (Integer id : Building.info.get(buildingID).requirements.keySet()) {
			
			if (!items.containsKey(id)) {
				
				return false;
				
			} else {
				
				if (items.get(id) != 0) {
					
					return false;
					
				}
				
			}
			
		}
		
		return true;
		
	}
	
}
