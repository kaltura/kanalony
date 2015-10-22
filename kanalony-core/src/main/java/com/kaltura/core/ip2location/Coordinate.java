package com.kaltura.core.ip2location;

public class Coordinate {
	
	protected String name;
	protected float longitude;
	protected float latitude;
	
	public Coordinate(String name, float latitude, float longitude) {
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String country) {
		this.name = country;
	}
	
	public float getLongitude() {
		return longitude;
	}
	
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
	public float getLatitude() {
		return latitude;
	}
	
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
		
}
