package location;

/**
 * The row is the horizontal element of the grid.
 *  
 */
public class Row
{
	private String rowID;
	private int zSize;
	private int zCoordinate;
	
	/**
	 * Creates a Row. The ID, the height and the z coordinate of the row 
	 * must be given.
	 * 
	 * @param id		the ID of the Row
	 * @param zSize		the zSize of the Row
	 * @param z			the z coordinate of the Row
	 */
	public Row(String id, int zSize, int z)
	{
		this.rowID = id;
		this.zSize = zSize;
		this.zCoordinate = z;
	}
	
	/**
	 * Returns the ID of the current row.
	 * 
	 * @return the rowID
	 */
	public String getRowID()
	{
		return rowID;
	}

	/**
	 * Returns the zSize of the current row.
	 * 
	 * @return the zSize
	 */
	public int getZSize()
	{
		return zSize;
	}

	/**
	 * Returns the z coordinate of the current row.
	 * 
	 * @return the zCoordinate
	 */
	public int getZCoordinate()
	{
		return zCoordinate;
	}
}
