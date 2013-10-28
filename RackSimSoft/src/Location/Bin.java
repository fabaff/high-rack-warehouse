
package Location;

/**
 * 
 */

/**
 * @author mschaerer
 * 
 * Der Lagerplatz ist die kleinste Einheit im Lagerort. Hier werden die einzelnen LagergŁter abgelegt.
 * Es kann nur ein Lagergut pro Lagerplatz abgelegt werden.
 */
public class Bin
{
	private String binID;
	private String gapID;
	private String gridID;
	
	private int gridSide = -1;
	
	private int xCoordinate;
	private int yCoordinate;
	private int zCoordinate;
	
	/**
	 * Creates a Bin. The ID of the Bin must be given.
	 * 
	 * 
	 */
	public Bin(String binID)
	{
		this.binID = binID;
	}
	
	public void placeBin(Gap gap, Grid grid, Column column, Row row)
	{
		this.gapID = gap.getGapID();
		this.gridID = grid.getGridID();
		
		this.gridSide = grid.getGridSide();
		
		this.xCoordinate = grid.getXCoordinate();
		this.yCoordinate = column.getYCoordinate();
		this.zCoordinate = row.getZCoordinate();
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
	 * Returns the ID of the assigned Grid
	 * 
	 * @return the gridID
	 */
	public String getgridID()
	{
		return gridID;
	}
	
	/**
	 * Returns the ID of the assigned Gap
	 * 
	 * @return the gapID
	 */
	public String getgapID()
	{
		return gapID;
	}
	
	/**
	 * Returns the X-coordinate of the current Bin
	 * 
	 * @return the X-coordinate
	 */
	public int getX()
	{
		return xCoordinate;
	}
	
	/**
	 * Returns the Y-coordinate of the current Bin
	 * 
	 * @return the Y-coordinate
	 */
	public int getY()
	{
		return yCoordinate;
	}
	
	/**
	 * Returns the Z-coordinate of the current Bin
	 * 
	 * @return the Z-coordinate
	 */
	public int getZ()
	{
		return zCoordinate;
	}
	
	/**
	 * Returns the side of the current Bin
	 * Left side = 0
	 * Right side = 1
	 * if no side has been attached = -1
	 * 
	 * @return the gridSide
	 */
	public int getGridSide()
	{
		return gridSide;
	}
}
