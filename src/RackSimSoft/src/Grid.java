import java.util.ArrayList;

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
	private int gridID;
	private int gridSide;
	private int length;
	private int height;
	private ArrayList<Row> rows = new ArrayList<Row>();
	private ArrayList<Column> columns = new ArrayList<Column>();
	
	/**
	 * Creates a Grid. The ID, length and the height of the Grid must be given.
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
	 * Creates a Grid. The ID, length, height and the side of the Grid must be given.
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
		if (this.checkMaxHeight(row))
		{
			rows.add(row.getRowID() - 1, row);
		}
		/* ToDo, Fehlerhandling
		else
			throw Exception("Fehler");
		*/
	}
	
	/**
	 * Adds a Column to the current Grid
	 * 
	 * @param column	the Column to add
	 */
	public void addColumn(Column column)
	{
		if (this.checkMaxLength(column))
		{
			columns.add(column.getColumnID() - 1, column);
		}
		/* ToDo, Fehlerhandling
		else
			throw Exception("Fehler");
		*/
	}
	
	/**
	 * Checks, if a Row can be added to the current Grid
	 * Returns true, if row can be added, false if not
	 * 
	 * @param row	the Row to check for adding
	 * @return row can be added
	 */
	public boolean checkMaxHeight(Row row)
	{
		/* ToDo
		 * Prüfen, ob die maximale Höhe des Grid nicht überschritten wird
		 * über alle Rows lopen, Höhen addieren
		 */
		return true;
	}
	
	/**
	 * Checks, if a Column can be added to the current Grid
	 * Returns true, if column can be added, false if not
	 * 
	 * @param column	the Column to check for adding
	 * @return column can be added
	 */
	public boolean checkMaxLength(Column column)
	{
		/* ToDo
		 * Prüfen, ob die maximale Länge des Grid nicht überschritten wird
		 * über alle Columns lopen, Längen addieren
		 */
		return true;
	}

	/**
	 * Returns the ID of the current Grid
	 * 
	 * @return the gridID
	 */
	public int getGridID()
	{
		return gridID;
	}
}
