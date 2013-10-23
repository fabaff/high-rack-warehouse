/**
 * 
 */

/**
 * @author mschaerer
 * 
 * Der Lagerplatz ist die kleinste Einheit im Lagerort. Hier werden die einzelnen Lagergüter abgelegt.
 * Es kann nur ein Lagergut pro Lagerplatz abgelegt werden.
 */
public class Bin
{
	private int binID;
	private int rowID;
	private int columnID;
	
	/**
	 * Creates a Bin. The ID of the Bin must be given.
	 * 
	 * @param id		the ID of the Column
	 */
	public Bin(int id)
	{
		this.binID = id;
	}
	
	/**
	 * Returns the ID of the current Bin
	 * 
	 * @return the binID
	 */
	public int getBinID()
	{
		return binID;
	}
	
	public void placeBin(Row row, Column column)
	{
		this.setRowID(rowID);
		this.setColumnID(columnID);
	}

	/**
	 * Returns the rowID in which this Bin has been placed
	 * 
	 * @return the rowID
	 */
	public int getRowID()
	{
		return rowID;
	}

	/**
	 * Places the Bin in a Row
	 * 
	 * @param rowID the rowID to set
	 */
	private void setRowID(int rowID)
	{
		this.rowID = rowID;
	}

	/**
	 * Returns the columnID in which this Bin has been placed
	 * 
	 * @return the columnID
	 */
	public int getColumnID()
	{
		return columnID;
	}

	/**
	 * Places the Bin in a Column
	 * 
	 * @param columnID the columnID to set
	 */
	private void setColumnID(int columnID)
	{
		this.columnID = columnID;
	}
}
