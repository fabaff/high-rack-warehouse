
package calculation;


/**
 * @author mschaerer
 *
 * The coordinate is to hold the 3 coordinates X/Y/Z in one container.
 */
public class Coordinate
{
	private int xCoordinate;
	private int yCoordinate;
	private int zCoordinate;
	
	/**
	 * Creates a Coordinate.
	 * The 3 coordinates must be given.
	 * 
	 * @param xCoordinate the X-coordinate
	 * @param yCoordinate the Y-coordinate
	 * @param zCoordinate the Z-coordinate
	 */
	public Coordinate(int xCoordinate, int yCoordinate, int zCoordinate)
	{
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.zCoordinate = zCoordinate;
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
}
