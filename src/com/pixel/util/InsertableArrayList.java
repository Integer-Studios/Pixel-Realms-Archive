package com.pixel.util;

import java.util.ArrayList;

public class InsertableArrayList {
	
	public InsertableArrayList() {
	}
	
	public static ArrayList<Object> insertAtIndex(ArrayList<Object> arrayList, int index, Object o) {
		for (int i = arrayList.size()-1; i >= index; i--) {
			arrayList.set(i+1, arrayList.get(i));
		}
		arrayList.set(index, o);
		return arrayList;
	}
	
	public static ArrayList<Object> moveToIndex(ArrayList<Object> arrayList,int index, int newIndex) {
		Object o = arrayList.get(index);
		if (newIndex-index < 0) {//moving higher
			for (int i = index+1; i >= newIndex; i++) {
				arrayList.set(i-1, arrayList.get(i));
			}
			arrayList.set(newIndex, o);
		} else if (newIndex-index < 0) {//moving lower
			for (int i = index-1; i >= newIndex; i--) {
				arrayList.set(i+1, arrayList.get(i));
			}
			arrayList.set(newIndex, o);
		}
		return arrayList;
	}
	
}
