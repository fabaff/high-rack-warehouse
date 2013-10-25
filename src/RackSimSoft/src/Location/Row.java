
package Location;

/**
 * 
 */

/**
 * @author mschaerer
 * 
 * 
 */
public class Row
{
	private int rowID;
	private int height;
	private int zCoordinate;
	
	/**
	 * Creates a Row. The ID, the height and the Z-coordinate of the Row must be given.
	 * 
	 * @param id		the ID of the Row
	 * @param height	the height of the Row
	 * @param z			the Z-coordinate of the Row
	 */
	public Row(int id, int height, int z)
	{
		this.rowID = id;
		this.height = height;
		this.zCoordinate = z;
	}
	
	/**
	 * Returns the ID of the current Row
	 * 
	 * @return the rowID
	 */
	public int getRowID()
	{
		return rowID;
	}

	/**
	 * Returns the height of the current Row
	 * 
	 * @return the height
	 */
	public int getHeight()
	{
		return height;
	}

	/**
	 * Returns the Z-coordinate of the current Row
	 * 
	 * @return the zCoordinate
	 */
	public int getZCoordinate()
	{
		return zCoordinate;
	}
}
