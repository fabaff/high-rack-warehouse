
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
	private int xCoordinate = 0;
	private int yCoordinate = 0;
	private int zCoordinate = 0;
	private Item item = null;
	
	private final double MAX_X_SPEED = 2; // Only relevant for the delivery
	private final double MAX_Y_SPEED = 2; // Speed in y axis
	private final double MAX_Z_SPEED = 2; // Speed in z axis
	private final double MAX_L_SPEED = 1; // Loading speed
	private final double MAX_ACCELERATION = 0.5;
	private final double MAX_DECELERATION = 0.5;
	
	private double xSpeed;
	private double ySpeed;
	private double zSpeed;
	private double lSpeed;
	private double acceleration;
	private double deceleration;
	
	/**
	 * Creates a Rack Feeder.
	 * The ID of the rack feeder must be given.
	 * 
	 * @param gap the gap which includes the rack feeder
	 */
	public RackFeeder(Gap gap)
	{
		this.gap = gap;
		
		this.setXSpeed(this.MAX_X_SPEED);
		this.setYSpeed(this.MAX_Y_SPEED);
		this.setZSpeed(this.MAX_Z_SPEED);
		this.setLSpeed(this.MAX_L_SPEED);
		this.setAcceleration(this.MAX_ACCELERATION);
		this.setDeceleration(this.MAX_DECELERATION);
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
	 * Returns the width of the current RackFeeder
	 * 
	 * @return the width
	 */
	public int getWidth()
	{
		return gap.getWidth();
	}
	
	/**
	 * Returns the X-coordinate of the current rack feeder
	 * 
	 * @return the xCoordinate
	 */
	public int getXCoordinate()
	{
		return this.xCoordinate;
	}
	
	/**
	 * Sets the X-coordinate of the current rack feeder
	 * 
	 * @param xCoordinate the xCoordinate to set
	 */
	private void setXCoordinate(int xCoordinate)
	{
		this.xCoordinate = xCoordinate;
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
	private void setYCoordinate(int yCoordinate)
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
	private void setZCoordinate(int zCoordinate)
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
	public void loadItem(Item item)
	{
		// TODO
		// Exception if rack feeder is not empty
		
		this.item = item;
	}
	
	/**
	 * Returns the item loaded to the rack feeder.
	 * The rack feeder is empty after.
	 * 
	 * @param item the item to set
	 */
	public Item unloadItem()
	{
		Item unloadedItem = this.getItem();
		this.item = null;
		
		return unloadedItem;
	}
	
	/**
	 * Moves the rack feeder to the new position
	 * 
	 * @param xCoordinate the X-coordinate to move to
	 */
	public void moveX(int xCoordinate)
	{
		this.setXCoordinate(xCoordinate);
	}
	
	/**
	 * Moves the rack feeder to the new position
	 * 
	 * @param yCoordinate the Y-coordinate to move to
	 * @param zCoordinate the Z-coordinate to move to
	 */
	public void moveYZ(int yCoordinate, int zCoordinate)
	{
		this.setYCoordinate(yCoordinate);
		this.setZCoordinate(zCoordinate);
	}

	/**
	 * Returns the current x speed of the rack feeder
	 * 
	 * @return the xSpeed
	 */
	public double getXSpeed()
	{
		return xSpeed;
	}

	/**
	 * Sets the current x speed of the rack feeder.
	 * The speed is set to maximum value if the new speed is 0 or it exceeds the maximum speed.
	 * 
	 * @param xSpeed the xSpeed to set
	 */
	public void setXSpeed(double newXSpeed)
	{
		if ((newXSpeed > this.MAX_X_SPEED) || (newXSpeed == 0))
			newXSpeed = this.MAX_X_SPEED;
		this.xSpeed = newXSpeed;
	}

	/**
	 * Returns the current y speed of the rack feeder
	 * 
	 * @return the ySpeed
	 */
	public double getYSpeed()
	{
		return ySpeed;
	}

	/**
	 * Sets the current y speed of the rack feeder
	 * The speed is set to maximum value if the new speed is 0 or it exceeds the maximum speed.
	 * 
	 * @param ySpeed the ySpeed to set
	 */
	public void setYSpeed(double newYSpeed)
	{
		if ((newYSpeed > this.MAX_Y_SPEED) || (newYSpeed == 0))
			newYSpeed = this.MAX_Y_SPEED;
		this.ySpeed = newYSpeed;
	}

	/**
	 * Returns the current z speed of the rack feeder
	 * 
	 * @return the zSpeed
	 */
	public double getZSpeed()
	{
		return zSpeed;
	}

	/**
	 * Sets the current z speed of the rack feeder
	 * The speed is set to maximum value if the new speed is 0 or it exceeds the maximum speed.
	 * 
	 * @param zSpeed the zSpeed to set
	 */
	public void setZSpeed(double newZSpeed)
	{
		if ((newZSpeed > this.MAX_Z_SPEED) || (newZSpeed == 0))
			newZSpeed = this.MAX_Z_SPEED;
		this.zSpeed = newZSpeed;
	}
	
	/**
	 * Returns the current l speed of the rack feeder
	 * 
	 * @return the lSpeed
	 */
	public double getLSpeed()
	{
		return lSpeed;
	}

	/**
	 * Sets the current l speed of the rack feeder
	 * The speed is set to maximum value if the new speed is 0 or it exceeds the maximum speed.
	 * 
	 * @param lSpeed the lSpeed to set
	 */
	public void setLSpeed(double newLSpeed)
	{
		if ((newLSpeed > this.MAX_L_SPEED) || (newLSpeed == 0))
			newLSpeed = this.MAX_L_SPEED;
		this.lSpeed = newLSpeed;
	}

	/**
	 * Returns the current acceleration of the rack feeder
	 * 
	 * @return the acceleration
	 */
	public double getAcceleration()
	{
		return acceleration;
	}

	/**
	 * Sets the current acceleration of the rack feeder
	 * The acceleration is set to maximum value if the new acceleration is 0 or it exceeds the maximum acceleration.
	 * 
	 * @param acceleration the acceleration to set
	 */
	public void setAcceleration(double newAcceleration)
	{
		if ((newAcceleration > this.MAX_ACCELERATION) || (newAcceleration == 0))
			newAcceleration = this.MAX_ACCELERATION;
		this.acceleration = newAcceleration;
	}

	/**
	 * Returns the current deceleration of the rack feeder
	 * The deceleration is set to maximum value if the new deceleration is 0 or it exceeds the maximum deceleration.
	 * 
	 * @return the deceleration
	 */
	public double getDeceleration()
	{
		return deceleration;
	}

	/**
	 * Sets the current deceleration of the rack feeder
	 * 
	 * @param deceleration the deceleration to set
	 */
	public void setDeceleration(double newDeceleration)
	{
		if ((newDeceleration > this.MAX_DECELERATION) || (newDeceleration == 0))
			newDeceleration = this.MAX_DECELERATION;
		this.deceleration = newDeceleration;
	}
}
