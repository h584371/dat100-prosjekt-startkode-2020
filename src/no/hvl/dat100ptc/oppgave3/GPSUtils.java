package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;
		
		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		
		return min;
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {
		
		int lengde = gpspoints.length;
		double latTab[] = new double[lengde];
		int i = 0;
		while (i < gpspoints.length) {
			latTab[i] = gpspoints[i].getLatitude();
			i++;
			
		}
		return latTab;
		
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		int lengde = gpspoints.length;
		double lonTab[] = new double[lengde];
		int i = 0;
		while (i < gpspoints.length) {
			lonTab[i] = gpspoints[i].getLongitude();
			i++;
		}
		return lonTab;
		
	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;

		latitude1 = gpspoint1.getLatitude();
		latitude2 = gpspoint2.getLatitude();
		longitude1 = gpspoint1.getLongitude();
		longitude2 = gpspoint2.getLongitude();
		
		double deltaLat = toRadians(latitude2 - latitude1);
		double deltaLon = toRadians(longitude2 - longitude1);
		double a = pow(sin(deltaLat / 2.0), 2)
				+ cos(toRadians(latitude1)) * cos(toRadians(latitude2)) * pow(sin(deltaLon / 2.0), 2);
		double c = 2.0 * atan2(sqrt(a), sqrt(1-a));
		d = R * c;
		return d;

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;

		secs = gpspoint2.getTime() - gpspoint1.getTime();
		
		speed = (distance(gpspoint1, gpspoint2) / 1000) / (secs / 3600.0);
		
		return speed;

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";
		
		int h = secs / 3600;
		int m = (secs % 3600) / 60;
		int s = secs % 60;

		String time = String.format("%02d:%02d:%02d", h, m, s);
		timestr = "  " + time;
		return timestr;

	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;
		
		double r = Math.round(d * 100.0) / 100.0;
		str = Double.toString(r);
		while (str.length() < 10) {
			str = " " + str;
		}
		return str;
	}
}
