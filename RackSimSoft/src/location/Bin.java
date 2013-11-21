
package location;

import calculation.Coordinate;

/**
 * @author mschaerer
 * 
 * The bin is the smallest unit inside the location. The bins takes the item
 * for storage. A bin can only take one item.
 */
public class Bin
{
	private String binID;
	private String gapID;
	private String gridID;
	private int width;
	private int height;
	private int gridSide = -1;
	
	private int xCoordinate;
	private int yCoordinate;
	private int zCoordinate;
	
	/**
	 * Creates a bin. The ID of the bin must be given.
	 * 
	 * @param binID Identifier of the bin
	 */
	public Bin(String binID)
	{
		this.binID = binID;
	}

	/**
	 * Puts the bin at its location in the grid.
	 * 
	 * @param gap 		Gap of the bin
	 * @param grid 		Grid of the bin
	 * @param column 	Column of the bin
	 * @param row 		row of the bin
	 */
	public void placeBin(Gap gap, Grid grid, Column column, Row row)
	{
		this.gapID = gap.getGapID();
		this.gridID = grid.getGridID();
		
		this.gridSide = grid.getGridSide();
		
		this.xCoordinate = grid.getXCoordinate();
		this.yCoordinate = column.getYCoordinate();
		this.zCoordinate = row.getZCoordinate();
		
		this.width = column.getWidth();
		this.height = row.getHeight();
	}

	/**
	 * Returns the ID of the current bin.
	 * 
	 * @return the binID
	 */
	public String getBinID()
	{
		return binID;
	}
	
	/**
	 * Returns the ID of the assigned grid.
	 * 
	 * @return the gridID
	 */
	public String getgridID()
	{
		return gridID;
	}
	
	/**
	 * Returns the ID of the assigned gap.
	 * 
	 * @return the gapID
	 */
	public String getgapID()
	{
		return gapID;
	}
	
	/**
	 * Returns the x coordinate of the current bin.
	 * 
	 * @return the x coordinate
	 */
	public int getX()
	{
		return xCoordinate;
	}
	
	/**
	 * Returns the y coordinate of the current bin.
	 * 
	 * @return the y coordinate
	 */
	public int getY()
	{
		return yCoordinate;
	}
	
	/**
	 * Returns the z coordinate of the current bin.
	 * 
	 * @return the z coordinate
	 */
	public int getZ()
	{
		return zCoordinate;
	}
	
	/**
	 * Returns the coordinate of the current bin.
	 * 
	 * @return the coordinate
	 */
	public Coordinate getCoordinate()
	{
		Coordinate coordinate = new Coordinate(this.getX(), this.getY(), this.getZ());
		return coordinate;
	}
	
	/**
	 * Returns the width of the current bin.
	 * 
	 * @return the width
	 */
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * Returns the height of the current bin.
	 * 
	 * @return the height
	 */
	public int getHeight()
	{
		return height;
	}
	
	/**
	 * Returns the side of the current bin.
	 * Left side = 0
	 * Right side = 1
	 * if no side has been attached = -1
	 * 
	 * @return the grid side
	 */
	public int getGridSide()
	{
		return gridSide;
	}
}