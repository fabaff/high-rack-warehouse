package calculation;

import location.*;

/**
 * The operating unit is moving inside the gap in the direction of the 
 * y axis and its beam is only able to move in the direction of the z axis.
 * The superposition of both moving vectors let the operating unit move
 * inclined in side the yz area of the grid.
 */

public class Movement {
	private double xSpeed = 2; // Only relevant for the delivery
	private double ySpeed = 2; // Speed in y axis
	private double zSpeed = 2; // Speed in x axis
	private double lSpeed = 1; // Loading speed
	private double acceleration = 0.5f;
	private double deceleration = 0.5f;
	
	
	/**
	 * Time for a complete loading cycle (from the loading zone to the bin)
	 * and back to standby location.
	 * 
	 * @return the time consumption for a complete loading cycle
	 */
	public double cTime(Location location, String binID1, String binID2)
	{
		// Loading time
		double ptime = pTime();
		// Traveling time
		double mtime = mTime(location, binID1, binID2);
		// Placing time
		double gtime = gTime();
		
		double cTime = ptime + mtime + gtime + mtime;
		return cTime;	
	}

	/**
	 * Calculates the moving time between two given positions inside the grid.
	 * 
	 * @param ySpeed 		
	 * @param zSpeed 		
	 * @param acceleration 	
	 * @param deceleration	
	 */
	public double mTime(Location location, String binID1, String binID2)
	{
		Distance distance = new Distance();

		double track = distance.mDistance(location, binID1, binID2);
		
		double aTime = aTime(ySpeed, acceleration);
		double dTime = dTime(ySpeed, deceleration);
		double lTime = lTime(ySpeed, track/1000);
		double tTime = aTime + lTime + dTime;	
		return tTime;
	}

	/**
	 * Calculates the max. time to move to the latest bin in the grid.
	 */
	public int maxTime()
	{
		int time = 100000;
		return time;
	}

	/**
	 * Calculates the time which is needed for linear traveling.
	 * 
	 * @param ySpeed 		
	 * @param distance	
	 */
	private double lTime(double ySpeed, double distance)
	{
		double lTime = distance / ySpeed;
		return lTime;	
	}
	
	/**
	 * Calculates the time which is needed for the acceleration.
	 * 
	 * @param ySpeed2 		
	 * @param acceleration	
	 */
	private double aTime(double ySpeed2, double acceleration)
	{
		double aTime = ySpeed / acceleration;
		return aTime;	
	}
	
	/**
	 * Calculates the time which is needed for the deceleration.
	 * 
	 * @param ySpeed 		
	 * @param deceleration	
	 */
	private double dTime(double ySpeed, double deceleration)
	{
		double dTime = ySpeed / deceleration;
		return dTime;	
	}
	
	/**
	 * Calculates the time for loading the good on the operating unit beam.
	 * 
	 * @param xSpeed   the traveling speed for the loading process	
	 * @param distance the distance from the bin to the beam (middle of the gap)
	 * 
	 * @return the time for picking or unloading goods in bins
	 */
	public double gTime()
	{
		double gTime = 5;
		return gTime;	
	}
	
	/**
	 * Operating time in the interface area to the loading zone.
	 * 
	 * @return the needed time for operating in the loading zone
	 */
	public double pTime()
	{
		double pTime = 2 / lSpeed;
		return pTime;	
	}
}
