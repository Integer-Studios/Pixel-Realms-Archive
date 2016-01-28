package com.pixel.util;

import java.util.HashMap;

public class Metadata {

	public HashMap<Integer, MetadataObject> objects;
	
	public Metadata() {
		
		objects = new HashMap<Integer, MetadataObject>();
		
	}
	
	public Metadata addString(int i, String...str) {
		
		for(String s : str) {
	    	
			new MetadataObject(this, i, s);

		}

		return this;

	}

	public Metadata addInteger(int ix, int...is) {

		for(int i : is) {

			new MetadataObject(this, ix, i);

		}

		return this;

	} 

	public Metadata addFloat(int i, float...fs) {

		for(float f : fs) {

			new MetadataObject(this, i, f);

		}		

		return this;

	}

	public Metadata addBoolean(int i, boolean...bs) {

		for(boolean b : bs) {

			new MetadataObject(this, i, b);

		}		
		
		return this;
		
	}
	
	public class MetadataObject {
		
		public int type;
		
		public String s;
		public boolean b;
		public int i;
		public float f;
		
		
		public MetadataObject(String s) {
			
			this.s = s;
			type = 0;

		}

		public MetadataObject(int i) {

			this.i = i;
			type = 1;

		}

		public MetadataObject(float f) {

			this.f = f;
			type = 2;

		}


		public MetadataObject(boolean b) {

			this.b = b;
			type = 3;
			
		}
		
		public MetadataObject(Metadata m, int id, String s) {
			
			this.s = s;
			type = 0;
			propagate(m, id);

		}

		public MetadataObject(Metadata m, int id, int i) {

			this.i = i;
			type = 1;
			propagate(m, id);

		}

		public MetadataObject(Metadata m, int id, float f) {

			this.f = f;
			type = 2;
			propagate(m, id);

		}


		public MetadataObject(Metadata m, int id, boolean b) {

			this.b = b;
			type = 3;
			propagate(m, id);
			
		}
		
		public void propagate(Metadata m, int id) {
			
			m.objects.put(id, this);
			
		}

	}
	
}
