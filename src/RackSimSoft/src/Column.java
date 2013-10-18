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
	
	/**
	 * Creates a Column. The ID and the length of the Column must be given.
	 * 
	 * @param id		the ID of the Column
	 * @param length	the length of the Column
	 */
	public Column(int id, int length)
	{
		this.columnID = id;
		this.setLength(length);
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
}
