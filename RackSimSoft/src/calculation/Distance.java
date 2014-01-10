
package calculation;

/**
 * This class is used to calculate all relevant informations about the distances between two coordinates inside the location.
 */

public class Distance
{
	private Coordinate startCoordinate;
	private Coordinate targetCoordinate;
	
	/**
	 * Creates a Distance.
	 * The Coordinate from and Coordinate to must be given.
	 * 
	 * @param startCoordinate the coordinate, from which the distance goes
	 * @param targetCoordinate the coordinate, to which the distance goes
	 */
	public Distance(Coordinate startCoordinate, Coordinate targetCoordinate)
	{
		this.startCoordinate = startCoordinate;
		this.targetCoordinate = targetCoordinate;
	}
	
	/**
	 * Calculates the x distance between 2 coordinates.
	 * This distance can be negative!
	 * 
	 * @return the xDistance
	 */
	public int getXDistance()
	{
		int xDistance = this.targetCoordinate.getX() - this.startCoordinate.getX(); 
		return xDistance;
	}
	
	/**
	 * Calculates the y distance between 2 coordinates.
	 * This distance can be negative!
	 * 
	 * @return the yDistance
	 */
	public int getYDistance()
	{
		int yDistance = this.targetCoordinate.getY() - this.startCoordinate.getY(); 
		return yDistance;
	}
	
	/**
	 * Calculates the z distance between 2 coordinates.
	 * This distance can be negative!
	 * 
	 * @return the zDistance	 
	 * */
	public int getZDistance()
	{
		int zDistance = this.targetCoordinate.getZ() - this.startCoordinate.getZ(); 
		return zDistance;
	}
	
	/**
	 * Calculates the u distance between 2 coordinates.
	 * This distance can be negative!
	 * 
	 * @return the uDistance	 
	 * */
	public int getUDistance()
	{
		int uDistance = this.targetCoordinate.getU() - this.startCoordinate.getU(); 
		return uDistance;
	}
	
	/**
	 * Calculates the overall distance between 2 coordinates in the chosen axes.
	 * This distance is always positive!
	 * 
	 * @return the zDistance	 
	 * */
	public int getDistanceLength(String direction)
	{
		// direction: "XYZU", 1 means true, 0 or other signs means false
		
		int xDistance = 0;
		int yDistance = 0;
		int zDistance = 0;
		int uDistance = 0;
		
		if (direction.substring(0, 1).equals("1"))
			xDistance = this.getXDistance();
		if (direction.substring(1, 2).equals("1"))
			yDistance = this.getYDistance();
		if (direction.substring(2, 3).equals("1"))
			zDistance = this.getZDistance();
		if (direction.substring(3, 4).equals("1"))
			uDistance = this.getUDistance();
		
		int length = (int) Math.round(Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2) + Math.pow(zDistance, 2) + Math.pow(uDistance, 2)));
		
		return length;
	}
}
