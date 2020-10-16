package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;



import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 8+00;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		return MAPXSIZE / (Math.abs(maxlon - minlon)); 

	}

	// antall y-pixels per breddegrad
	public double ystep() {
		
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		
		return MAPYSIZE / (Math.abs(maxlat - minlat));
	}

	public void showRouteMap(int ybase) {
		
		int RADIUS = 4;
		int x;
		int y;
		int startX = 0;
		int startY = 0;
		double elevation = 0.0;
		
		setColor (0, 255, 0);
		
		for (int i = 0; i < gpspoints.length; i++) {
			
			x = MARGIN + (int) ((gpspoints[i].getLongitude() - GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints))) * xstep());
			y = ybase - (int) ((gpspoints[i].getLatitude() - GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints))) * ystep());
			
			if(i < 1) {
				startX = x;
				startY = y;
				
				fillCircle(startX, startY, RADIUS);
			} else {
				
				if (gpspoints[i].getElevation() > elevation) {
					setColor(255, 0, 0);
				} else if (gpspoints[i].getElevation() < elevation) {
					setColor(0, 255, 0);
				}
				elevation = gpspoints[i].getElevation();
				
				drawLine(startX, startY, x, y);
				startX = x;
				startY = y;
				
				
				fillCircle(x, y, RADIUS);
			}
		}
	}

		public void showStatistics() {

			int TEXTDISTANCE = 20;
			int WEIGHT = 80;

			setColor(0,0,0);
			setFont("Courier",12);
		
			String text[] = {	"Total time",
								"Total distance",
								"Total Elevation",
								"Max speed",
								"Average speed",
								"Energy" };
		
			String statistics[] = { GPSUtils.formatTime(gpscomputer.totalTime()) + " ",
									GPSUtils.formatDouble(gpscomputer.totalDistance()/1000) + " km ",
									GPSUtils.formatDouble(gpscomputer.totalElevation()) + " m ",
									GPSUtils.formatDouble(gpscomputer.maxSpeed()) + " km/t ",
									GPSUtils.formatDouble(gpscomputer.averageSpeed()) + " km/t ",
									GPSUtils.formatDouble(gpscomputer.totalKcal(WEIGHT)) + " kcal "};
		
			for (int i = 0; i < statistics.length; i++) {
				drawString(text[i], TEXTDISTANCE, TEXTDISTANCE + i*TEXTDISTANCE);
				drawString("       :" + statistics[i], TEXTDISTANCE*5, TEXTDISTANCE + i*TEXTDISTANCE);
			}
			
		}
}

