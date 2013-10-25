package com.pixel.interior;

import java.util.HashMap;

import com.pixel.start.PixelRealms;

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
	
	public void setItem(int id, int amount) {
		
		items.put(id, amount);
		PixelRealms.world.getPieceObject(x, y).metadata = calculateMetadata();

	}
	
	public int addItem(int id, int amount) {
		
		System.out.println("A " + id + " " + amount);
		
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
		
		PixelRealms.world.getPieceObject(x, y).metadata = calculateMetadata();
		
		return 0;
		
	}
	
	public int calculateMetadata() {
		
		int total = 0;
		int amount = 0;
		
		for (Integer amt : Building.info.get(buildingID).requirements.values()) {
			
			total += amt;
			
		}
		
		for (Integer amt : items.values()) {
			
			amount += amt;
			
		}
		
		amount = total - amount;
		
		float percentage = (float) amount / (float) total;
		
		if (percentage <= .25) {
			
			return 1;
			
		} else if (percentage <= .5) {
			
			return 2;
			
		} else if (percentage <= .75) {
			
			return 3;
			
		} else if (percentage <= .99) {
			
			return 4;
			
		} else
			return 1;
		
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
