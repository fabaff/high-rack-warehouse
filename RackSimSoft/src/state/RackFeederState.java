
package state;

import location.RackFeeder;


public abstract class RackFeederState
{
	protected Behavior behavior;
	
	public static enum Behavior
	{
		IN, OUT, WAIT, CHANGEIN, CHANGEOUT
	}
	
	/**
	 * Creates a new RackFeederState.
	 * 
	 * @param behavior the current behavior to be set
	 */
	public RackFeederState(Behavior behavior)
	{
		this.behavior = Behavior.IN;
	}
	
	/**
	 * Creates a new RackFeederState for the default state.
	 * 
	 */
	public final static RackFeederState getDefaultState()
	{
		return new RackFeederState7();
	}
	
	/**
	 * Returns a new RackFeederState depending on the current state and the behavior.
	 * 
	 */
	public abstract RackFeederState getNextState();
	
	/**
	 * Moves the RackFeeder to the next position, loads / unloads item, dependent of the current state.
	 * 
	 * @param rackFeeder the rackFeeder to work with
	 */
	public void doNextStep(RackFeeder rackFeeder)
	{
	}
	
	/**
	 * Sets the item loaded to the rack feeder.
	 * 
	 * @param rackFeeder the rackFeeder to work with
	 */
	protected void loadItem(RackFeeder rackFeeder)
	{
	}
	
	/**
	 * Returns the item loaded to the rack feeder.
	 * The rack feeder is empty after.
	 * 
	 * @param rackFeeder the rackFeeder to work with
	 */
	protected void unloadItem(RackFeeder rackFeeder)
	{
	}
	
	/**
	 * Moves the rack feeder to the new position.
	 * 
	 * @param rackFeeder the rackFeeder to work with
	 */
	protected void moveX(RackFeeder rackFeeder)
	{
	}
	
	/**
	 * Moves the rack feeder to the new position.
	 * 
	 * @param rackFeeder the rackFeeder to work with
	 */
	protected void moveYZ(RackFeeder rackFeeder)
	{
	}
	
	/**
	 * Moves the rack feeder to the new position.
	 * 
	 * @param rackFeeder the rackFeeder to work with
	 */
	protected void moveU(RackFeeder rackFeeder)
	{
	}
}
