
package calculation;


/**
 * @author mschaerer
 *
 * The coordinate is to hold the 4 coordinates X/Y/Z/U in one container.
 */
public class Coordinate
{
	private int xCoordinate;
	private int yCoordinate;
	private int zCoordinate;
	private int uCoordinate;
	
	/**
	 * Creates a Coordinate.
	 * The 4 coordinates must be given.
	 * 
	 * @param xCoordinate the X-coordinate
	 * @param yCoordinate the Y-coordinate
	 * @param zCoordinate the Z-coordinate
	 * @param uCoordinate the U-coordinate
	 */
	public Coordinate(int xCoordinate, int yCoordinate, int zCoordinate, int uCoordinate)
	{
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.zCoordinate = zCoordinate;
		this.uCoordinate = uCoordinate;
	}
	
	/**
	 * Returns the x coordinate of the current coordinate.
	 * 
	 * @return the x coordinate
	 */
	public int getX()
	{
		return xCoordinate;
	}
	
	/**
	 * Returns the y coordinate of the current coordinate.
	 * 
	 * @return the y coordinate
	 */
	public int getY()
	{
		return yCoordinate;
	}
	
	/**
	 * Returns the z coordinate of the current coordinate.
	 * 
	 * @return the z coordinate
	 */
	public int getZ()
	{
		return zCoordinate;
	}
	
	/**
	 * Returns the u coordinate of the current coordinate.
	 * 
	 * @return the u coordinate
	 */
	public int getU()
	{
		return uCoordinate;
	}
}
