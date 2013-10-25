
package Location;

/**
 * 
 */

/**
 * @author mschaerer
 * 
 * Die Gasse beinhaltet 1 oder 2 Lagergestelle auf der linken und / oder rechten Seite der Gasse
 * Die Gasse ist einem Lagerort zugeordnet
 */
public class Gap
{
	private String gapID;
	private Grid gridLeft;
	private Grid gridRight;
	private int xCoordinate;

	/**
	 * Creates a Gap. The ID and the X-coordinate of the Gap must be given.
	 * 
	 * @param id	the ID of the Gap
	 * @param x		the X-coordinate of the Gap
	 */
	public Gap(String id, int x)
	{
		this.gapID = id;
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
	 * Adds a Grid to the current Gap. The ID and the side must be given.
	 * Parameter left = TRUE means, Grid will be added to the left side, otherwise to the right side
	 * 
	 * @param grid	the grid to add
	 * @param left	the side, to which the Grid must be added
	 */
	public void addGrid(Grid grid)
	{
		switch (grid.getGridSide())
		{
			case 0: gridLeft = grid;
				break;
			case 1: gridRight = grid;
				break;

			/* ToDo: Exceptionhandling
			default: throw Exception("Fehler");
				break;
			*/
		}
	}
}
