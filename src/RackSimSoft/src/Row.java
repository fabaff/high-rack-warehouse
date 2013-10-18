/**
 * 
 */

/**
 * @author mschaerer
 *
 */
public class Row
{
	private int rowID;
	private int height;
	
	/**
	 * Creates a Row. The ID and the height of the Row must be given.
	 * 
	 * @param id		the ID of the Row
	 * @param height	the height of the Row
	 */
	public Row(int id, int height)
	{
		this.rowID = id;
		this.setHeight(height);
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
	 * Sets the height of the current Row
	 * 
	 * @param height the height to set
	 */
	public void setHeight(int height)
	{
		this.height = height;
	}
}
