import java.util.ArrayList;

/**
 * 
 */

/**
 * @author mschaerer
 * Das Lagergestell innerhalb einer Gasse ist in Reihen und Spalten unterteilt,
 * welche die eigentlichen Lagerplätze beinhaltet
 */
public class Grid
{
	private int gridID;
	private int gridSide;
	private int length;
	private int height;
	private ArrayList<Row> rows = new ArrayList<Row>();
	private ArrayList<Column> columns = new ArrayList<Column>();
	
	/**
	 * Creates a Grid. The ID of the Grid must be given.
	 * 
	 * @param id		the ID of the Grid
	 * @param length	the length of the Grid
	 * @param height	the height of the Grid
	 */
	public Grid(int id, int length, int height)
	{
		this.gridID = id;
		this.length = length;
		this.height = height;
	}
	
	/**
	 * Creates a Grid. The ID of the Grid and the side must be given.
	 * 
	 * @param id		the ID of the Grid
	 * @param length	the length of the Grid
	 * @param height	the height of the Grid
	 * @param side		the side of the Grid
	 */
	public Grid(int id, int length, int height, int side)
	{
		this.gridID = id;
		this.length = length;
		this.height = height;
		this.setGridSide(side);
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
	 * Sets the side of the Grid in the Gap to the left or right side
	 * Left side = 1
	 * Right side = 2
	 * 
	 * @param gridSide the gridSide to set
	 */
	public void setGridSide(int gridSide)
	{
		this.gridSide = gridSide;
	}

	/**
	 * Adds a Row to the current Grid
	 * 
	 * @param row	the Row to add
	 */
	public void addrow(Row row)
	{
		rows.add(row.getRowID(), row);
	}
	
	/**
	 * Adds a Column to the current Grid
	 * 
	 * @param column	the Column to add
	 */
	public void addColumn(Column column)
	{
		columns.add(column.getColumnID(), column);
	}
}
