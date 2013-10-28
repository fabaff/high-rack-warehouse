
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
	private Column[] columnArray;
	private Row[] rowArray;
	private Bin[] binArray;
	private Hashtable<String, Bin> binTable;
	
	/**
	 * Creates a Grid. The ID, the Columns and the Rows of the Grid must be given.
	 * 
	 * @param id				the ID of the Grid
	 * @param columnArray[]		the Columns to be added to the Grid
	 * @param rowArray[]		the Rows to be added to the Grid
	 */
	public Grid(String id, Gap gap, int gridSide, Row rowArray[] , Column columnArray[])
	{
		this.gridID = id;
		this.gapID = gap.getGapID();
		this.gridSide = gridSide;
		this.rowArray = rowArray;
		this.columnArray = columnArray;
		this.binTable = createBinTable(columnArray, rowArray);
		
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
	
	private Hashtable<String, Bin> createBinTable(Column[] columnArray, Row[] rowArray)
	{
		Hashtable<String, Bin> binTable = new Hashtable<String, Bin>();
		
		for (int i = 0; i < columnArray.length; i++)
		{
			for (int j = 0; j < rowArray.length; j++)
			{
				Column column = columnArray[i];
				Row row = rowArray[j];
				Bin bin = new Bin(this.gapID + this.gridSide + "-" + this.gridID + "-" + column.getColumnID() + "-" + row.getRowID());
				bin.placeBin(this.gapID, this.gridID, column.getColumnID(), row.getRowID());
				binTable.put(bin.getBinID(), bin);
			}
		}
		
		return binTable;
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
