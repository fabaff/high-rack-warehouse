package gui2D;

import location.Grid;

public class GridComponent extends RectangleComponent
{
	private static final long serialVersionUID = 1L;
	private final static float gridBorder = 2.5f;
	
	public GridComponent(Grid grid, double guiCoordinateFactor)
	{
		super(grid.getGridID(), (int) (guiCoordinateFactor * grid.getYSize()), (int) (guiCoordinateFactor * grid.getZSize()), gridBorder);
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public int getHeight()
	{
		return this.heigth;
	}
}
