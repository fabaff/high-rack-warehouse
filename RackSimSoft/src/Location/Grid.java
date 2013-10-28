
package Location;

import java.util.Hashtable;

/**
 * 
 */

/**
 * @author mschaerer
 * 
 * Das Lagergestell innerhalb einer Gasse ist in Reihen und Spalten unterteilt,
 * welche die eigentlichen Lagerplätze beinhaltet
 */
public class Grid
{
	private String gridID;
	private String gapID;
	private int gridSide = -1;
	private int width;
	private int height;
	private Hashtable<String, Bin> binTable;
	private Bin binArray[][];
	
	/**
	 * Creates a Grid. The ID, the Columns and the Rows of the Grid must be given.
	 * 
	 * @param gridID			the ID of the Grid
	 * @param gapID				the ID of the Gap in which the Grid is integrated
	 * @param gridSide			the side of the Grid in the Gap
	 * @param columnArray[]		the Columns to be added to the Grid
	 * @param rowArray[]		the Rows to be added to the Grid
	 */
	public Grid(String gridID, Gap gap, int gridSide, Column columnArray[], Row rowArray[])
	{
		this.gridID = gridID;
		this.gapID = gap.getGapID();
		this.gridSide = gridSide;
		this.binTable = createBinContainer(gap, columnArray, rowArray);
		
		int width = 0;
		// Calculate Width
		for(Column column : columnArray)
		{
			width += column.getWidth();
		}
		this.width = width;
		
		// Calculate Height
		int height = 0;
		for(Row row : rowArray)
		{
			height += row.getHeight();
		}
		this.height = height;
	}
	
	private Hashtable<String, Bin> createBinContainer(Gap gap, Column[] columnArray, Row[] rowArray)
	{
		Hashtable<String, Bin> binTable = new Hashtable<String, Bin>();
		binArray = new Bin[columnArray.length][rowArray.length];
		
		for (int i = 0; i < columnArray.length; i++)
		{
			for (int j = 0; j < rowArray.length; j++)
			{
				Column column = columnArray[i];
				Row row = rowArray[j];
				Bin bin = new Bin(this.gapID + "-" + this.gridSide + "-" + this.gridID + "-" + column.getColumnID() + "-" + row.getRowID());
				bin.placeBin(gap, this, column, row);
				
				// In Hashtabelle
				binTable.put(bin.getBinID(), bin);
				
				// In Array
				binArray[i][j] = bin;
			}
		}
		
		return binTable;
	}
	
	/**
	 * Returns the table containing all Bins of the current Grid
	 * 
	 * @return the binTable
	 */
	public Hashtable<String, Bin> getBinTable()
	{
		return this.binTable;
	}
	
	/**
	 * Returns an array containing all Bins of the current Grid
	 * 
	 * @return the binArray
	 */
	public Bin[][] getBinArray()
	{
		return binArray;
	}
	
	/**
	 * Returns a Bin if assigned to current Grid
	 * 
	 * @return the binArray
	 */
	public Bin getBin(String binID)
	{
		return this.binTable.get(binID);
	}
	
	/**
	 * Returns the side of the current Grid
	 * Left side = 1
	 * Right side = 2
	 * if no side has been attached = 0
	 * 
	 * @return the gridSide
	 */
	public int getGridSide()
	{
		return gridSide;
	}
	
	/**
	 * Returns the ID of the current Grid
	 * 
	 * @return the gridID
	 */
	public String getGridID()
	{
		return gridID;
	}

	/**
	 * Returns the width of the current Grid
	 * 
	 * @return the width
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * Returns the height of the current Grid
	 * 
	 * @return the height
	 */
	public int getHeight()
	{
		return height;
	}
}
