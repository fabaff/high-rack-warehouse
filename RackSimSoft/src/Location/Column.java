
package Location;

/**
 * 
 */

/**
 * @author mschaerer
 *
 */
public class Column
{
	private String columnID;
	private int width;
	private int yCoordinate;
	
	/**
	 * Creates a Column. The ID, the width and the Y-coordinate of the Column must be given.
	 * 
	 * @param id		the ID of the Column
	 * @param width	the width of the Column
	 * @param y			the Y-coordinate of the Column
	 */
	public Column(String id, int width, int y)
	{
		this.columnID = id;
		this.setWidth(width);
		this.yCoordinate = y;
	}
	
	/**
	 * Returns the ID of the current Column
	 * 
	 * @return the columnID
	 */
	public String getColumnID()
	{
		return columnID;
	}

	/**
	 * Returns the width of the current Column
	 * 
	 * @return the width
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * Sets the width of the current Column
	 * 
	 * @param width the length to set
	 */
	public void setWidth(int width)
	{
		this.width = width;
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
