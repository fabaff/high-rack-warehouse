package gui2D;

import location.Bin;

public class BinComponent extends RectangleComponent
{
	private static final long serialVersionUID = 1L;
	private final static float binBorder = 1.5f;
	private int yOffset;
	private int zOffset;
	
	public BinComponent(Bin bin, double guiCoordinateFactor)
	{
		super(bin.getBinID(), (int) (guiCoordinateFactor * bin.getYSize()), (int) (guiCoordinateFactor * bin.getZSize()), binBorder);
		yOffset = (int) (guiCoordinateFactor * bin.getY());
		zOffset = (int) (guiCoordinateFactor * bin.getZ());
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public int getHeight()
	{
		return this.heigth;
	}
	
	public int getYOffset()
	{
		return this.yOffset;
	}
	
	public int getZOffset()
	{
		return this.zOffset;
	}
}
