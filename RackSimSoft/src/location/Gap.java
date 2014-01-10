package location;

/**
 * The gap contains two grids of bins. The bins are placed on the left and the
 * right side of the gap. Every gap is associated to a location.
 * The gaps are divided into a left an a right grid.
 */
public class Gap
{
	private String gapID;
	private Grid gridLeft = null;
	private Grid gridRight = null;
	private int xSize;
	private int xCoordinate;
	private RackFeeder rackFeeder;

	/**
	 * Creates a Gap. The ID, the width and the x coordinate of the gap must
	 * be given.
	 * 
	 * @param id	the ID of the gap
	 * @param width	the width of the gap
	 * @param x		the x coordinate of the gap
	 */
	public Gap(String id, int xSize, int x)
	{
		this.gapID = id;
		this.xSize = xSize;
		this.xCoordinate = x;
		this.rackFeeder = new RackFeeder(this);
	}
	
	/**
	 * Returns the ID of the current gap.
	 * 
	 * @return the gapID
	 */
	public String getGapID()
	{
		return this.gapID;
	}
	
	/**
	 * Returns the x coordinate of the current gap
	 * 
	 * @return the xCoordinate
	 */
	public int getXCoordinate()
	{
		return this.xCoordinate;
	}
	
	/**
	 * Returns the xSize of the current Gap
	 * 
	 * @return the xSize
	 */
	public int getXSize()
	{
		return this.xSize;
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

			//TODO Exceptionhandling
			/*
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
		return this.gridLeft;
	}

	/**
	 * Returns the Grid assigned to the right hand side
	 * 
	 * @return the gridRight
	 */
	public Grid getGridRight()
	{
		return this.gridRight;
	}
	
	/**
	 * Returns the RackFeeder assigned to this gap
	 * 
	 * @return the rackFeeder
	 */
	public RackFeeder getRackFeeder()
	{
		return this.rackFeeder;
	}
}
