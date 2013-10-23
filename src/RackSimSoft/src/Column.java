/**
 * 
 */

/**
 * @author mschaerer
 *
 */
public class Column
{
	private int columnID;
	private int length;
	private int yCoordinate;
	
	/**
	 * Creates a Column. The ID, the length and the Y-coordinate of the Column must be given.
	 * 
	 * @param id		the ID of the Column
	 * @param length	the length of the Column
	 * @param y			the Y-coordinate of the Column
	 */
	public Column(int id, int length, int y)
	{
		this.columnID = id;
		this.setLength(length);
		this.yCoordinate = y;
	}
	
	/**
	 * Returns the ID of the current Column
	 * 
	 * @return the columnID
	 */
	public int getColumnID()
	{
		return columnID;
	}

	/**
	 * Returns the height of the current Column
	 * 
	 * @return the height
	 */
	public int getLength()
	{
		return length;
	}

	/**
	 * Sets the length of the current Column
	 * 
	 * @param length the length to set
	 */
	public void setLength(int length)
	{
		this.length = length;
	}
	
	/**
	 * Returns the Z-coordinate of the current Row
	 * 
	 * @return the zCoordinate
	 */
	public int getYCoordinate()
	{
		return yCoordinate;
	}
}
