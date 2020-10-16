package no.hvl.dat100ptc.oppgave1;

import no.hvl.dat100ptc.TODO;

public class GPSPoint {

	private int tid;
	private double lat;
	private double lon;
	private double ele;
		
	public GPSPoint(int time, double latitude, double longitude, double elevation) {

		tid = time;
		lat = latitude;
		lon = longitude;
		ele = elevation;
		
	}

	public int getTime() {
		
		return tid;
		
	}

	public void setTime(int time) {
		
		tid = time;
		
	}

	public double getLatitude() {
		
		return lat;
		
	}

	public void setLatitude(double latitude) {
		
		lat = latitude;
		
	}

	public double getLongitude() {
		
		return lon;
		
	}

	public void setLongitude(double longitude) {
		
		lon = longitude;
		
	}

	public double getElevation() {
		
		return ele;
		
	}

	public void setElevation(double elevation) {
		
		ele = elevation;
		
	}
	
	public String toString() {
		
		String str;
		
		str = tid + " (" + lat + "," + lon + ") " + ele + "\n";
		
		return str;
		
	}
}
