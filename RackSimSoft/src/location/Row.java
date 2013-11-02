package location;

/**
 * @author mschaerer
 * 
 * The row is the horizontal element of the grid. 
 */
public class Row
{
	private String rowID;
	private int height;
	private int zCoordinate;
	
	/**
	 * Creates a Row. The ID, the height and the z coordinate of the row 
	 * must be given.
	 * 
	 * @param id		the ID of the Row
	 * @param height	the height of the Row
	 * @param z			the z coordinate of the Row
	 */
	public Row(String id, int height, int z)
	{
		this.rowID = id;
		this.height = height;
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
	 * Returns the height of the current row.
	 * 
	 * @return the height
	 */
	public int getHeight()
	{
		return height;
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
