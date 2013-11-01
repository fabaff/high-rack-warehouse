package Gui2D;

import Location.Grid;

public class GridComponent extends RectangleComponent
{
	private static final long serialVersionUID = 1L;
	private final static int gridBorder = 1;
	
	public GridComponent(Grid grid)
	{
		super(grid.getGridID(), grid.getWidth(), grid.getHeight(), gridBorder);
	}
}
