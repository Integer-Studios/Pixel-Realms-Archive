package com.pixel.util;

public class CoordinateKey {

	public final int x;
	public final int y;

	public CoordinateKey(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CoordinateKey)) return false;
		CoordinateKey key = (CoordinateKey) o;
		return x == key.x && y == key.y;
	}

	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + y;
		return result;
	}

}
