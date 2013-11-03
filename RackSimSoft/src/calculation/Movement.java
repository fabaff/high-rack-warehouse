package calculation;

import location.*;

/**
 * The operating unit is moving inside the gap in the direction of the 
 * y axis and its beam is only able to move in the direction of the z axis.
 * The superposition of both moving vectors let the operating unit move
 * inclined in side the yz area of the grid.
 */

public class Movement {
	private double ySpeed = 2;
	private double zSpeed = 2;
	private double acceleration = 0.5f;
	private double deceleration = 0.5f;

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
		Bin point1 = location.getBin(binID1);
		Bin point2 = location.getBin(binID2);
		
		// Get coordinates of all involved points
		int y1 = point1.getY();
		int z1 = point1.getZ();

		int y2 = point2.getY();
		int z2 = point2.getZ();
		
		int distance = Distance.getyDistance(Location location, String binID1, String binID2);
		
		double aTime = aTime(ySpeed, acceleration);
		double dTime = dTime(ySpeed, deceleration);
		double lTime = lTime(ySpeed, 100);
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
		double aTime = ySpeed2 / acceleration;
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
}
