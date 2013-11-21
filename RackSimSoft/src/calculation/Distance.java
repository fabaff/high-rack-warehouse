
package calculation;



/**
 * Calculations on the yz area in the grid.
 */

public class Distance
{
	private Coordinate startCoordinate;
	private Coordinate targetCoordinate;
	
	/*
	private int distance;
	private int xDistance;
	private int yDistance;
	private int zDistance;
	private int lDistance;
	*/
	
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
	 * Calculates the overall distance between 2 coordinates.
	 * This distance is always positive!
	 * 
	 * @return the zDistance	 
	 * */
	public int getDistanceLength()
	{
		int xDistance = this.getXDistance();
		int yDistance = this.getYDistance();
		int zDistance = this.getZDistance();
		int length = (int) Math.round(Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2) + Math.pow(zDistance, 2)));
		
		return length;
	}
	
	/**
	 * Calculates the distance of a bin to the zero point in the grid.
	 * 
	 * @param location
     * @param binID
	 */
	/*
	public double getZeroDistance(Location location, String binID)
	{
		Bin bin;
		bin = location.getBin(binID);
		double zeroDistance = Math.round(Math.sqrt(Math.pow((bin.getY()), 2) + 
				Math.pow((bin.getZ()), 2)));
		return zeroDistance;
	}
	*/
	
	/**
	 * Calculates the x distance of a bin to the middle of the gap.
	 * 
	 * @param location
     * @param binID1
	 * @param binID2
	 */
	/*
	public int getXDistance(Location location, String gapID, String binID)
	{
		Gap gap;
		gap = location.getGap(gapID);
		Bin bin;
		bin = location.getBin(binID);
		
		int xDistance = (gap.getWidth() / 2) + (bin.getWidth() / 2);
		return xDistance;
	}
	*/
	
	/**
	 * Calculates the y distance of two bins in the grid.
	 * 
	 * @param location
     * @param binID1
	 * @param binID2
	 */
	/*
	public int getYDistance(Location location, String binID1, String binID2)
	{
		Bin bin1;
		Bin bin2;
		bin1 = location.getBin(binID1);
		bin2 = location.getBin(binID2);
		int yDistance = Math.abs(bin1.getY() - bin2.getY());
		return yDistance;
	}
	*/

	/**
	 * Calculates the z distance of two bins in the grid.
	 * 
	 * @param location
     * @param binID1
	 * @param binID2
	 */
	/*
	public int getZDistance(Location location, String binID1, String binID2)
	{
		Bin bin1;
		Bin bin2;
		bin1 = location.getBin(binID1);
		bin2 = location.getBin(binID2);
		int zDistance = Math.abs(bin1.getZ() - bin2.getZ());
		return zDistance;
	}
	*/
	
	/**
	 * Calculates the distance of two bins in the grid which is the traveling
	 * distance for the operating unit.
	 * 
	 * @param location
     * @param binID1
	 * @param binID2
	 */
	/*
	public double getMDistance(Location location, String binID1, String binID2)
	{
		Bin bin1;
		Bin bin2;
		bin1 = location.getBin(binID1);
		bin2 = location.getBin(binID2);
		// The result is rounded.
		double mDistance = Math.round(Math.sqrt(Math.pow((bin1.getY() - 
				bin2.getY()), 2) + Math.pow((bin1.getZ() - bin2.getZ()), 2)));
		return mDistance;
	}
	*/
	
	/**
	 * Calculates the distance in the interface area.
	 * 
	 * @param location
     * @param binID1
	 */
	/*
	public double getLDistance(Location location, String binID1)
	{
		Bin bin1;
		bin1 = location.getBin(binID1);
		double lDistance = bin1.getWidth();
		return lDistance;
	}
	*/
	
	/**
	 * Returns the distance between two points.
	 * 
	 * @return the distance between two point
	 */
	/*
	public int getDistance()
	{
		return distance;
	}
	*/
	
	/*
	public void setDistance(int distance)
	{
		this.distance = distance;
	}
	*/
	
	/*
	public int getxDistance()
	{
		return xDistance;
	}
	*/
	
	/*
	public void setxDistance(int xDistance)
	{
		this.xDistance = xDistance;
	}
	*/
	
	/*
	public int getyDistance() 
	{
		return yDistance;
	}
	*/
	
	/*
	public void setyDistance(int yDistance) 
	{
		this.yDistance = yDistance;
	}
	*/
	
	/*
	public int getzDistance()
	{
		return zDistance;
	}
	*/
	
	/*
	public void setzDistance(int zDistance)
	{
		this.zDistance = zDistance;
	}
	*/
	
	/*
	public int getlDistance() 
	{
		return lDistance;
	}
	*/
	
	/*
	public void setlDistance(int lDistance) 
	{
		this.lDistance = lDistance;
	}
	*/
}
