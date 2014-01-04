package gui2D;

import location.Bin;

public class BinComponent extends RectangleComponent
{
	private static final long serialVersionUID = 1L;
	private final static float binBorder = 1.5f;
	private int yOffset;
	private int zOffset;
	
	public BinComponent(Bin bin, int rectangleSide)
	{
		super(bin.getBinID(), (int) (rectangleSide), (int) (rectangleSide), binBorder);
		yOffset = (int) (rectangleSide);
		zOffset = (int) (rectangleSide);
	}
	
	public int getSide()
	{
		return this.width;
	}
	
}
