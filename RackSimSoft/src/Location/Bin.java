
package Location;

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
	private String binID;
	private String gapID;
	private String gridID;
	private String columnID;
	private String rowID;
	
	/**
	 * Creates a Bin. The ID of the Bin must be given.
	 * 
	 * @param id		the ID of the Column
	 */
	public Bin(String id)
	{
		this.binID = id;
	}
	
	public void placeBin(String gapID, String gridID, String columnID, String rowID)
	{
		this.gapID = gapID;
		this.gridID = gridID;
		this.columnID = columnID;
		this.rowID = rowID;
	}

	/**
	 * Returns the ID of the current Bin
	 * 
	 * @return the binID
	 */
	public String getBinID()
	{
		return binID;
	}
	
	/**
	 * Returns the columnID in which this Bin has been placed
	 * 
	 * @return the columnID
	 */
	public String getColumnID()
	{
		return columnID;
	}
}
