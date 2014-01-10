
package location;

import state.RackFeederState;
import calculation.Coordinate;
import item.Item;

/**
 * The rack feeder is the element which takes the items to the bin in a grid.
 * 
 */
public class RackFeeder
{
	private RackFeederState state;
	private Gap gap;
	private int xCoordinate;
	private int yCoordinate;
	private int zCoordinate;
	private int uCoordinate;
	private int xLoadingCoordinate;
	private int yLoadingCoordinate;
	private int zLoadingCoordinate;
	private int uLoadingCoordinate;
	private Item item = null;
	
	// Speed is given in mm/ms which is the same as m/s
	// This means, 1 mm/ms = 3.6 km/h
	// Acceleration is given in um/ms^2 which is the same as m/s^2
	// TODO diese Einheit pruefen
	
	private final double MAX_X_SPEED = 1; // Only relevant for the delivery
	private final double MAX_Y_SPEED = 0.5; // Speed in y axis
	private final double MAX_Z_SPEED = 1; // Speed in z axis
	private final double MAX_U_SPEED = 1.5; // Loading speed, U is the parallel-axis to X
	private final double MAX_X_ACCELERATION = 0.5;
	private final double MAX_X_DECELERATION = 0.5;
	private final double MAX_Y_ACCELERATION = 0.5;
	private final double MAX_Y_DECELERATION = 0.5;
	private final double MAX_Z_ACCELERATION = 0.5;
	private final double MAX_Z_DECELERATION = 0.5;
	private final double MAX_U_ACCELERATION = 0.5;
	private final double MAX_U_DECELERATION = 0.5;
	
	private double xSpeed;
	private double ySpeed;
	private double zSpeed;
	private double uSpeed;
	private double xAcceleration;
	private double xDeceleration;
	private double yAcceleration;
	private double yDeceleration;
	private double zAcceleration;
	private double zDeceleration;
	private double uAcceleration;
	private double uDeceleration;
	
	private final int LOADING_TIME = 1000;
	
	/**
	 * Creates a Rack Feeder.
	 * The ID of the rack feeder must be given.
	 * 
	 * @param gap the gap which includes the rack feeder
	 */
	public RackFeeder(Gap gap)
	{
		this.gap = gap;
		
		setLoadingCoordinate();
		
		// RBG auf Ladeposition initialisieren
		this.xCoordinate = this.xLoadingCoordinate;
		this.yCoordinate = this.yLoadingCoordinate;
		this.zCoordinate = this.zLoadingCoordinate;
		this.uCoordinate = this.uLoadingCoordinate;
		
		this.state = RackFeederState.getDefaultState();
		
		this.setXSpeed(this.MAX_X_SPEED);
		this.setYSpeed(this.MAX_Y_SPEED);
		this.setZSpeed(this.MAX_Z_SPEED);
		this.setUSpeed(this.MAX_U_SPEED);
		this.setXAcceleration(this.MAX_X_ACCELERATION);
		this.setXDeceleration(this.MAX_X_DECELERATION);
		this.setYAcceleration(this.MAX_Y_ACCELERATION);
		this.setYDeceleration(this.MAX_Y_DECELERATION);
		this.setZAcceleration(this.MAX_Z_ACCELERATION);
		this.setZDeceleration(this.MAX_Z_DECELERATION);
		this.setUAcceleration(this.MAX_U_ACCELERATION);
		this.setUDeceleration(this.MAX_U_DECELERATION);
	}
	
	/**
	 * Returns the ID of the current rack feeder.
	 * 
	 * @return the rackFeederID
	 */
	public String getRackFeederID()
	{
		return gap.getGapID();
	}
	
	/**
	 * Returns the State of the current rack feeder.
	 * 
	 * @return the state
	 */
	public RackFeederState getState()
	{
		return this.state;
	}
	
	/**
	 * Sets the new RackFeederState for the current rack feeder.
	 * 
	 * @param state the new state to set
	 */
	public void setState(RackFeederState state)
	{
		this.state = state;
	}
	
	/**
	 * Returns the xSize of the current RackFeeder.
	 * 
	 * @return the xSize
	 */
	public int getXSize()
	{
		return gap.getXSize();
	}
	
	/**
	 * Returns the X-coordinate of the current rack feeder.
	 * 
	 * @return the xCoordinate
	 */
	public int getX()
	{
		return this.xCoordinate;
	}
	
	/**
	 * Sets the X-coordinate of the current rack feeder.
	 * 
	 * @param xCoordinate the xCoordinate to set
	 */
	private void setX(int xCoordinate)
	{
		this.xCoordinate = xCoordinate;
	}
	
	/**
	 * Returns the Y-coordinate of the current rack feeder.
	 * 
	 * @return the yCoordinate
	 */
	public int getY()
	{
		return this.yCoordinate;
	}

	/**
	 * Sets the Y-coordinate of the current rack feeder.
	 * 
	 * @param yCoordinate the yCoordinate to set
	 */
	private void setY(int yCoordinate)
	{
		this.yCoordinate = yCoordinate;
	}

	/**
	 * Returns the Z-coordinate of the current rack feeder.
	 * 
	 * @return the zCoordinate
	 */
	public int getZ()
	{
		return this.zCoordinate;
	}

	/**
	 * Sets the Z-coordinate of the current rack feeder.
	 * 
	 * @param zCoordinate the zCoordinate to set
	 */
	private void setZ(int zCoordinate)
	{
		this.zCoordinate = zCoordinate;
	}
	
	/**
	 * Returns the U-coordinate of the current rack feeder.
	 * 
	 * @return the uCoordinate
	 */
	public int getU()
	{
		return this.uCoordinate;
	}

	/**
	 * Sets the U-coordinate of the current rack feeder.
	 * 
	 * @param uCoordinate the zCoordinate to set
	 */
	private void setU(int uCoordinate)
	{
		this.uCoordinate = uCoordinate;
	}

	/**
	 * Returns the current Coordinate depending on the simulation time gone since the last state has reached.
	 * 
	 * @return the coordinate
	 */
	public Coordinate getCurrentCoordinate()
	{
		Coordinate coordinate = this.state.getCurrentCoordinate();
		if (coordinate == null)
		{
			coordinate = getCoordinate();
		}
		
		return coordinate;
	}
	
	/**
	 * Returns the coordinate of the current rack feeder.
	 * 
	 * @return the coordinate
	 */
	public Coordinate getCoordinate()
	{
		Coordinate coordinate = new Coordinate(this.getX(), this.getY(), this.getZ(), this.getU());
		return coordinate;
	}
	
	/**
	 * Returns the zero-coordinate of the current rack feeder.
	 * 
	 * @return the zero-coordinate
	 */
	public Coordinate getLoadingCoordinate()
	{
		Coordinate coordinate = new Coordinate(this.xLoadingCoordinate, this.yLoadingCoordinate, this.zLoadingCoordinate, this.uLoadingCoordinate);
		return coordinate;
	}
	
	/**
	 * Sets the zero-coordinate of the current rack feeder.
	 * 
	 */
	public void setLoadingCoordinate()
	{
		this.xLoadingCoordinate = gap.getXCoordinate();
		this.yLoadingCoordinate = 0;
		this.zLoadingCoordinate = 0;
		this.uLoadingCoordinate = 0;
	}
	
	/**
	 * Returns the needed time for load / unload the RackFeeder.
	 * 
	 * @return the LOADING_TIME
	 */
	public int getLoadingTime()
	{
		return this.LOADING_TIME;
	}

	/**
	 * Returns the item loaded to the rack feeder.
	 * 
	 * @return the item
	 */
	public Item getItem()
	{
		return this.item;
	}

	/**
	 * Sets the item loaded to the rack feeder.
	 * 
	 * @param item the item to set
	 */
	public void loadItem(Item item)
	{
		// TODO Exception if rack feeder is not empty
		this.item = item;
	}
	
	/**
	 * Returns the item loaded to the rack feeder.
	 * The rack feeder is empty after.
	 * 
	 * @return item the item which was unloaded
	 */
	public Item unloadItem()
	{
		Item unloadedItem = this.getItem();
		this.item = null;
		
		return unloadedItem;
	}
	
	/**
	 * Moves the rack feeder to the new position.
	 * 
	 * @param xCoordinate the X-coordinate to move to
	 */
	public void moveX(int xCoordinate)
	{
		this.setX(xCoordinate);
	}
	
	/**
	 * Moves the rack feeder to the new position.
	 * 
	 * @param yCoordinate the Y-coordinate to move to
	 * @param zCoordinate the Z-coordinate to move to
	 */
	public void moveYZ(int yCoordinate, int zCoordinate)
	{
		this.setY(yCoordinate);
		this.setZ(zCoordinate);
	}
	
	/**
	 * Moves the rack feeder to the new position.
	 * 
	 * @param uCoordinate the U-coordinate to move to
	 */
	public void moveU(int uCoordinate)
	{
		this.setU(uCoordinate);
	}

	/**
	 * Sets the speed, the acceleration and the deceleration of the rack 
	 * feeder to the maximum in all axes.
	 * 
	 */
	public void setMax()
	{
		this.setMaxSpeed();
		this.setMaxAcceleration();
		this.setMaxDeceleration();
	}
	
	/**
	 * Sets the speed of the rack feeder to the maximum in all axes.
	 * 
	 */
	public void setMaxSpeed()
	{
		this.setXSpeed(0);
		this.setYSpeed(0);
		this.setZSpeed(0);
		this.setUSpeed(0);
	}
	
	/**
	 * Sets the acceleration of the rack feeder to the maximum in all axes.
	 * 
	 */
	public void setMaxAcceleration()
	{
		this.setXAcceleration(0);
		this.setYAcceleration(0);
		this.setZAcceleration(0);
		this.setUAcceleration(0);
	}
	
	/**
	 * Sets the deceleration of the rack feeder to the maximum in all axes.
	 * 
	 */
	public void setMaxDeceleration()
	{
		this.setXDeceleration(0);
		this.setYDeceleration(0);
		this.setZDeceleration(0);
		this.setUDeceleration(0);
	}
	
	/**
	 * Returns the current x speed of the rack feeder.
	 * 
	 * @return the xSpeed
	 */
	public double getXSpeed()
	{
		return xSpeed;
	}

	/**
	 * Sets the current x speed of the rack feeder.
	 * The speed is set to maximum value if the new speed is 0 or it exceeds 
	 * the maximum speed.
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
	 * Returns the current y speed of the rack feeder.
	 * 
	 * @return the ySpeed
	 */
	public double getYSpeed()
	{
		return ySpeed;
	}

	/**
	 * Sets the current y speed of the rack feeder.
	 * The speed is set to maximum value if the new speed is 0 or it exceeds 
	 * the maximum speed.
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
	 * Returns the current z speed of the rack feeder.
	 * 
	 * @return the zSpeed
	 */
	public double getZSpeed()
	{
		return zSpeed;
	}

	/**
	 * Sets the current z speed of the rack feeder.
	 * The speed is set to maximum value if the new speed is 0 or it exceeds 
	 * the maximum speed.
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
	 * Returns the current l speed of the rack feeder.
	 * 
	 * @return the lSpeed
	 */
	public double getUSpeed()
	{
		return uSpeed;
	}

	/**
	 * Sets the current u speed of the rack feeder.
	 * The speed is set to maximum value if the new speed is 0 or it exceeds 
	 * the maximum speed.
	 * 
	 * @param uSpeed the uSpeed to set
	 */
	public void setUSpeed(double newUSpeed)
	{
		if ((newUSpeed > this.MAX_U_SPEED) || (newUSpeed == 0))
			newUSpeed = this.MAX_U_SPEED;
		this.uSpeed = newUSpeed;
	}

	/**
	 * Returns the current x acceleration of the rack feeder.
	 * 
	 * @return the xAcceleration
	 */
	public double getXAcceleration()
	{
		return xAcceleration;
	}

	/**
	 * Sets the current x acceleration of the rack feeder.
	 * The acceleration is set to maximum value if the new acceleration is 0 
	 * or it exceeds the maximum acceleration.
	 * 
	 * @param acceleration the acceleration to set
	 */
	public void setXAcceleration(double newAcceleration)
	{
		if ((newAcceleration > this.MAX_X_ACCELERATION) || (newAcceleration == 0))
			newAcceleration = this.MAX_X_ACCELERATION;
		this.xAcceleration = newAcceleration;
	}

	/**
	 * Returns the current x deceleration of the rack feeder.
	 * The x deceleration is set to maximum value if the new deceleration is 
	 * 0 or it exceeds the maximum deceleration.
	 * 
	 * @return the xDeceleration
	 */
	public double getXDeceleration()
	{
		return xDeceleration;
	}

	/**
	 * Sets the current xDeceleration of the rack feeder.
	 * 
	 * @param xDeceleration the deceleration to set
	 */
	public void setXDeceleration(double newDeceleration)
	{
		if ((newDeceleration > this.MAX_X_DECELERATION) || (newDeceleration == 0))
			newDeceleration = this.MAX_X_DECELERATION;
		this.xDeceleration = newDeceleration;
	}
	
	/**
	 * Returns the current y acceleration of the rack feeder.
	 * 
	 * @return the yAcceleration
	 */
	public double getYAcceleration()
	{
		return yAcceleration;
	}

	/**
	 * Sets the current y acceleration of the rack feeder.
	 * The acceleration is set to maximum value if the new acceleration is 0 
	 * or it exceeds the maximum acceleration.
	 * 
	 * @param acceleration the acceleration to set
	 */
	public void setYAcceleration(double newAcceleration)
	{
		if ((newAcceleration > this.MAX_Y_ACCELERATION) || (newAcceleration == 0))
			newAcceleration = this.MAX_Y_ACCELERATION;
		this.yAcceleration = newAcceleration;
	}

	/**
	 * Returns the current y deceleration of the rack feeder.
	 * The y deceleration is set to maximum value if the new deceleration is 
	 * 0 or it exceeds the maximum deceleration.
	 * 
	 * @return the yDeceleration
	 */
	public double getYDeceleration()
	{
		return yDeceleration;
	}

	/**
	 * Sets the current yDeceleration of the rack feeder.
	 * 
	 * @param yDeceleration the deceleration to set
	 */
	public void setYDeceleration(double newDeceleration)
	{
		if ((newDeceleration > this.MAX_Y_DECELERATION) || (newDeceleration == 0))
			newDeceleration = this.MAX_Y_DECELERATION;
		this.yDeceleration = newDeceleration;
	}
	
	/**
	 * Returns the current z acceleration of the rack feeder.
	 * 
	 * @return the zAcceleration
	 */
	public double getZAcceleration()
	{
		return zAcceleration;
	}

	/**
	 * Sets the current z acceleration of the rack feeder.
	 * The acceleration is set to maximum value if the new acceleration is 0 
	 * or it exceeds the maximum acceleration.
	 * 
	 * @param acceleration the acceleration to set
	 */
	public void setZAcceleration(double newAcceleration)
	{
		if ((newAcceleration > this.MAX_Z_ACCELERATION) || (newAcceleration == 0))
			newAcceleration = this.MAX_Z_ACCELERATION;
		this.zAcceleration = newAcceleration;
	}

	/**
	 * Returns the current z deceleration of the rack feeder.
	 * The z deceleration is set to maximum value if the new deceleration is 0
	 * or it exceeds the maximum deceleration.
	 * 
	 * @return the zDeceleration
	 */
	public double getZDeceleration()
	{
		return zDeceleration;
	}

	/**
	 * Sets the current zDeceleration of the rack feeder.
	 * 
	 * @param zDeceleration the deceleration to set
	 */
	public void setZDeceleration(double newDeceleration)
	{
		if ((newDeceleration > this.MAX_Z_DECELERATION) || (newDeceleration == 0))
			newDeceleration = this.MAX_Z_DECELERATION;
		this.zDeceleration = newDeceleration;
	}
	
	/**
	 * Returns the current u acceleration of the rack feeder.
	 * 
	 * @return the uAcceleration
	 */
	public double getUAcceleration()
	{
		return uAcceleration;
	}

	/**
	 * Sets the current u acceleration of the rack feeder.
	 * The acceleration is set to maximum value if the new acceleration is 0
	 * or it exceeds the maximum acceleration.
	 * 
	 * @param acceleration the acceleration to set
	 */
	public void setUAcceleration(double newAcceleration)
	{
		if ((newAcceleration > this.MAX_U_ACCELERATION) || (newAcceleration == 0))
			newAcceleration = this.MAX_U_ACCELERATION;
		this.uAcceleration = newAcceleration;
	}

	/**
	 * Returns the current u deceleration of the rack feeder.
	 * The u deceleration is set to maximum value if the new deceleration is 0
	 * or it exceeds the maximum deceleration.
	 * 
	 * @return the uDeceleration
	 */
	public double getUDeceleration()
	{
		return uDeceleration;
	}

	/**
	 * Sets the current uDeceleration of the rack feeder.
	 * 
	 * @param uDeceleration the deceleration to set
	 */
	public void setUDeceleration(double newDeceleration)
	{
		if ((newDeceleration > this.MAX_U_DECELERATION) || (newDeceleration == 0))
			newDeceleration = this.MAX_U_DECELERATION;
		this.uDeceleration = newDeceleration;
	}
}
