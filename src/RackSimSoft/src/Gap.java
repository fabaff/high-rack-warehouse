/**
 * 
 */

/**
 * @author mschaerer
 * Die Gasse beinhaltet 1 oder 2 Lagergestelle auf der linken und / oder rechten Seite der Gasse
 * Die Gasse ist einem Lagerort zugeordnet
 */
public class Gap
{
	private int gapID;
	private Grid gridLeft;
	private Grid gridRight;

	/**
	 * Creates a Gap. The ID of the Gap must be given.
	 * 
	 * @param id	the ID of the Gap
	 */
	public Gap(int id)
	{
		this.gapID = id;
	}
	
	/**
	 * Returns the ID of the current Gap
	 * 
	 * @return the gapID
	 */
	public int getGapID()
	{
		return gapID;
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
			case 1: gridLeft = grid;
				break;
			case 2: gridRight = grid;
				break;

			/* ToDo: Exceptionhandling
			default: throw Exception("Fehler");
				break;
			*/
		}
	}
}
