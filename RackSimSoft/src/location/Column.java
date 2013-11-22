
package location;

/**
 * 
 */

/**
 * @author mschaerer
 *
 * The column is the vertical element of the grid. 
 */
public class Column
{
	private String columnID;
	private int ySize;
	private int yCoordinate;
	
	/**
	 * Creates a Column. The ID, the width, and the y coordinate of the
	 * column must be given.
	 * 
	 * @param id 	the ID of the column
	 * @param width	the width of the column
	 * @param y 	the y coordinate of the column
	 */
	public Column(String id, int ySize, int y)
	{
		this.columnID = id;
		this.ySize = ySize;
		this.yCoordinate = y;
	}
	
	/**
	 * Returns the ID of the current column.
	 * 
	 * @return the ColumnID
	 */
	public String getColumnID()
	{
		return this.columnID;
	}

	/**
	 * Returns the ySize of the current column.
	 * 
	 * @return the ySize
	 */
	public int getYSize()
	{
		return this.ySize;
	}

	/**
	 * Returns the y coordinate of the current row.
	 * 
	 * @return the YCoordinate
	 */
	public int getYCoordinate()
	{
		return yCoordinate;
	}
}