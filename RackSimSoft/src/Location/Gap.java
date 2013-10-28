
package Location;

/**
 * 
 */

/**
 * @author mschaerer
 * 
 * Die Gasse beinhaltet 1 oder 2 Lagergestelle auf der linken und / oder rechten Seite der Gasse
 * Die Gasse ist einem Lagerort zugeordnet
 * Die Gasse wird in ein linkes und rechtes Lagergestell unterteilt.
 */
public class Gap
{
	private String gapID;
	private Grid gridLeft = null;
	private Grid gridRight = null;
	private int width;
	private int xCoordinate;

	/**
	 * Creates a Gap. The ID, the width and the X-coordinate of the Gap must be given.
	 * 
	 * @param id	the ID of the Gap
	 * @param width	the width of the Gap
	 * @param x		the X-coordinate of the Gap
	 */
	public Gap(String id, int width, int x)
	{
		this.gapID = id;
		this.width = width;
		this.xCoordinate = x;
	}
	
	/**
	 * Returns the ID of the current Gap
	 * 
	 * @return the gapID
	 */
	public String getGapID()
	{
		return gapID;
	}
	
	/**
	 * Returns the X-coordinate of the current Gap
	 * 
	 * @return the xCoordinate
	 */
	public int getXCoordinate()
	{
		return xCoordinate;
	}
	
	/**
	 * Returns the width of the current Gap
	 * 
	 * @return the width
	 */
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * Adds a Grid to the current Gap. The ID and the side must be given.
	 * Parameter left = TRUE means, Grid will be added to the left side, otherwise to the right side
	 * 
	 * @param grid	the grid to add
	 * @param left	the side, to which the Grid must be added
	 */
	public void addGrid(Grid grid)
	{
		// Grid auf die richtige Seite im Gap legen
		switch (grid.getGridSide())
		{
			case 0: this.gridLeft = grid;
				break;
			case 1: this.gridRight = grid;
				break;

			/* ToDo: Exceptionhandling
			default: throw Exception("Fehler");
				break;
			*/
		}
	}

	/**
	 * Returns the Grid assigned to the left hand side
	 * 
	 * @return the gridLeft
	 */
	public Grid getGridLeft()
	{
		return gridLeft;
	}

	/**
	 * Returns the Grid assigned to the right hand side
	 * 
	 * @return the gridRight
	 */
	public Grid getGridRight()
	{
		return gridRight;
	}
}
