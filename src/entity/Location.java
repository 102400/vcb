package entity;

public class Location {
	
	private int locationId;
	private String name;
	private int userId;
	private double n;
	private double e;
	
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public double getN() {
		return n;
	}
	public void setN(double n) {
		this.n = n;
	}
	public double getE() {
		return e;
	}
	public void setE(double e) {
		this.e = e;
	}

}
