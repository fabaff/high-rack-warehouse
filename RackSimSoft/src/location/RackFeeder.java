
package location;

import item.Item;

/**
 * @author mschaerer
 *
 * The rack feeder is the element which takes the items to the bin in a grid.
 */
public class RackFeeder
{
	private Gap gap;
	private int yCoordinate = 0;
	private int zCoordinate = 0;
	private Item item = null;
	
	/**
	 * Creates a Rack Feeder.
	 * The ID of the rack feeder must be given.
	 */
	public RackFeeder(Gap gap)
	{
		this.gap = gap;
	}
	
	/**
	 * Returns the ID of the current rack feeder.
	 * 
	 * @return the rackFeederID
	 */
	public String getrackFeederID()
	{
		return gap.getGapID();
	}
	
	/**
	 * Returns the x coordinate of the current rackFeeder
	 * 
	 * @return the xCoordinate
	 */
	public int getXCoordinate()
	{
		return gap.getXCoordinate();
	}
	
	/**
	 * Returns the width of the current RackFeeder
	 * 
	 * @return the width
	 */
	public int getWidth()
	{
		return gap.getWidth();
	}

	/**
	 * Returns the Y-coordinate of the current rack feeder
	 * 
	 * @return the yCoordinate
	 */
	public int getYCoordinate()
	{
		return this.yCoordinate;
	}

	/**
	 * Sets the Y-coordinate of the current rack feeder
	 * 
	 * @param yCoordinate the yCoordinate to set
	 */
	public void setYCoordinate(int yCoordinate)
	{
		this.yCoordinate = yCoordinate;
	}

	/**
	 * Returns the Z-coordinate of the current rack feeder
	 * 
	 * @return the zCoordinate
	 */
	public int getZCoordinate()
	{
		return this.zCoordinate;
	}

	/**
	 * Sets the Z-coordinate of the current rack feeder
	 * 
	 * @param zCoordinate the zCoordinate to set
	 */
	public void setZCoordinate(int zCoordinate)
	{
		this.zCoordinate = zCoordinate;
	}

	/**
	 * Returns the item loaded to the rack feeder
	 * 
	 * @return the item
	 */
	public Item getItem()
	{
		return this.item;
	}

	/**
	 * Sets the item loaded to the rack feeder
	 * 
	 * @param item the item to set
	 */
	public void setItem(Item item)
	{
		this.item = item;
	}
}
