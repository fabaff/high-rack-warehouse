
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
	private int ySize;
	private int zSize;
	private int gridSide = -1;
	
	private int xCoordinate;
	private int yCoordinate;
	private int zCoordinate;
	private int uCoordinate;
	
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
		
		if (grid.getGridSide() == 0)
		{
			// Left Grid
			this.uCoordinate = (grid.getXSize() * -1);
		}
		else
		{
			// Right Grid
			this.uCoordinate = grid.getXSize();
		}
		
		this.ySize = column.getYSize();
		this.zSize = row.getZSize();
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
	 * Returns the u coordinate of the current bin.
	 * 
	 * @return the u coordinate
	 */
	public int getU()
	{
		return uCoordinate;
	}
	
	/**
	 * Returns the coordinate of the current bin.
	 * 
	 * @return the coordinate
	 */
	public Coordinate getCoordinate()
	{
		Coordinate coordinate = new Coordinate(this.getX(), this.getY(), this.getZ(), this.getU());
		return coordinate;
	}
	
	/**
	 * Returns the coordinate of the current bin for the chosen axes.
	 * 
	 * @param axes	the axes taken for the coordinate
	 * @return the coordinate
	 */
	public Coordinate getCoordinate(String axes)
	{
		int xCoordinate = 0;
		int yCoordinate = 0;
		int zCoordinate = 0;
		int uCoordinate = 0;
		
		if (axes.substring(0, 1).equals("1"))
			xCoordinate = this.getX();
		if (axes.substring(1, 2).equals("1"))
			yCoordinate = this.getY();
		if (axes.substring(2, 3).equals("1"))
			zCoordinate = this.getZ();
		if (axes.substring(3, 4).equals("1"))
			uCoordinate = this.getU();
		
		Coordinate coordinate = new Coordinate(xCoordinate, yCoordinate, zCoordinate, uCoordinate);
		return coordinate;
	}
	
	/**
	 * Returns the ySize of the current bin.
	 * 
	 * @return the ySize
	 */
	public int getYSize()
	{
		return ySize;
	}
	
	/**
	 * Returns the zSize of the current bin.
	 * 
	 * @return the zSize
	 */
	public int getZSize()
	{
		return zSize;
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