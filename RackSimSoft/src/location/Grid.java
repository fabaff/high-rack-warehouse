package location;

import java.util.Hashtable;

/**
 * @author mschaerer
 * 
 * The storage units are divided into columns and rows inside a gap which
 * contains the bins.
 */
public class Grid
{
	private String gridID;
	private String gapID;
	private Hashtable<String, Bin> binTable;
	private Bin binArray[][];
	private int gridSide = -1;
	private int ySize;
	private int zSize;
	private int xSize;
	private int xCoordinate;
	
	/**
	 * Creates a grid. The ID, the columns and the rows of the grid must be
	 * given.
	 * 
	 * @param gridID			the ID of the grid
	 * @param gapID				the ID of the gap in which the grid is integrated
	 * @param gridSide			the side of the grid in the gap
	 * @param columnArray[]		the columns to be added to the grid
	 * @param rowArray[]		the rows to be added to the grid
	 */
	public Grid(String gridID, Gap gap, int gridSide, Column columnArray[], Row rowArray[], int xSize)
	{
		this.gridID = gridID;
		this.gapID = gap.getGapID();
		this.gridSide = gridSide;
		this.xSize = xSize;
		
		if (gridSide == 0)
		{
			// Left grid
			this.xCoordinate = gap.getXCoordinate();
		}
		else
		{
			// Right grid
			this.xCoordinate = gap.getXCoordinate() + gap.getXSize();
		}
		
		// Creates bins and puts those into a container
		createBins(gap, columnArray, rowArray);
		
		// Calculate ySize
		int ySize = 0;
		for(Column column : columnArray)
		{
			ySize += column.getYSize();
		}
		this.ySize = ySize;
		
		// Calculate height
		int zSize = 0;
		for(Row row : rowArray)
		{
			zSize += row.getZSize();
		}
		this.zSize = zSize;
	}
	
	private void createBins(Gap gap, Column[] columnArray, Row[] rowArray)
	{
		this.binTable = new Hashtable<String, Bin>();
		this.binArray = new Bin[columnArray.length][rowArray.length];
		
		for (int i = 0; i < columnArray.length; i++)
		{
			for (int j = 0; j < rowArray.length; j++)
			{
				Column column = columnArray[i];
				Row row = rowArray[j];
				Bin bin = new Bin(this.gapID + "-" + this.gridSide + "-" + this.gridID + "-" + column.getColumnID() + "-" + row.getRowID());
				bin.placeBin(gap, this, column, row);
				
				// Hash table
				binTable.put(bin.getBinID(), bin);
				
				// Array
				binArray[i][j] = bin;
			}
		}
	}
	
	/**
	 * Returns the table containing all bins of the current grid.
	 * 
	 * @return the binTable
	 */
	public Hashtable<String, Bin> getBinTable()
	{
		return this.binTable;
	}
	
	/**
	 * Returns an array containing all bins of the current grid.
	 * 
	 * @return the binArray
	 */
	public Bin[][] getBinArray()
	{
		return binArray;
	}
	
	/**
	 * Returns a bin if assigned to current grid.
	 * 
	 * @return the binArray
	 */
	public Bin getBin(String binID)
	{
		return this.binTable.get(binID);
	}
	
	/**
	 * Returns the side of the current grid.
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
	
	/**
	 * Returns the ID of the current grid.
	 * 
	 * @return the gridID
	 */
	public String getGridID()
	{
		return gridID;
	}

	/**
	 * Returns the ySize of the current grid.
	 * 
	 * @return the ySize
	 */
	public int getYSize()
	{
		return ySize;
	}

	/**
	 * Returns the height of the current grid.
	 * 
	 * @return the height
	 */
	public int getZSize()
	{
		return zSize;
	}
	
	/**
	 * Returns the depth of the current grid.
	 * 
	 * @return the depth
	 */
	public int getXSize()
	{
		return xSize;
	}
	
	/**
	 * Returns the x coordinate of the current grid.
	 * 
	 * @return the xCoordinate
	 */
	public int getXCoordinate()
	{
		return xCoordinate;
	}
}
