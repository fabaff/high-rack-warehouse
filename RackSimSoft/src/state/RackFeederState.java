
package state;

import calculation.Coordinate;
import calculation.Movement;
import simulation.Simulation;
import job.Job;

/**
 * RackFeederState declares and defines functions, which are used to switch between the different states of the RackFeeder.
 *
 */
public abstract class RackFeederState
{
	/*
	 *  TODO
	 *  Funktionen deklarieren / definieren für das Umlagerungsverhalten
	 *  Das heisst, wechseln von WAIT nach CHANGEOUT nach CHANGEIN nach WAIT
	 *  
	 *  Ebenfalls in entsprechenden Subklassen erweitern / implementieren wo nötig
	 */
	protected Behavior behavior;
	protected int busyTime;
	protected Movement movement;
	
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
		this.behavior = behavior;
		this.movement = null;
	}
	
	/**
	 * Creates a new RackFeederState for the default state.
	 * 
	 */
	public final static RackFeederState getDefaultState()
	{
		return new RackFeederState0();
	}
	
	/**
	 * Returns a new RackFeederState depending on the current state and the behavior.
	 * 
	 * @return	the next RackFeederState
	 */
	public abstract RackFeederState getNextState();
	
	/**
	 * Returns the time needed to reach the next state depending on the current state and the behavior.
	 * Sets the correct physical values to the current RackFeeder.
	 * 
	 * @return	the time needed to reach the next state
	 */
	public abstract int prepareForMove(Job job);
	
	/**
	 * Moves the RackFeeder to the next position, loads / unloads item, dependent of the current state.
	 * 
	 * @param job the Job to execute
	 */
	public void doNextStep(Job job)
	{
	}
	
	/**
	 * Switches the Behavior to a new Behavior.
	 * 
	 * @param behavior the Behavior to set
	 */
	public final void switchBehavior(Behavior behavior)
	{
		// Nur erlaubt aus Klasse RackFeederState0
		if (this instanceof RackFeederState0)
		{
			this.behavior = behavior;
		}
	}
	
	/**
	 * Sets the current Movement depending on the current state and behavior of the RackFeeder.
	 * 
	 * @param movement the current movement to set
	 */
	protected void setMovement(Movement movement)
	{
		this.movement = movement;
	}
	
	/**
	 * Sets the time which the rack feeder needs to reach the next state.
	 * 
	 * @param busyTime the time the rack feeder needs to reach the next state
	 */
	protected final void setBusyTime(int busyTime)
	{
		this.busyTime = busyTime;
	}
	
	/**
	 * Returns the current Coordinate depending on the simulation time gone since the last state has reached.
	 * 
	 * @return the coordinate
	 */
	public Coordinate getCurrentCoordinate()
	{
		Coordinate coordinate = null;
		
		if (this.movement != null)
		{
			double factor = Simulation.getInstance().getFactor();
			coordinate = this.movement.getCurrentCoordinate((int) Math.round(this.busyTime * factor));
		}
		
		return coordinate;
	}
	
	/**
	 * Sets the item loaded to the rack feeder.
	 * 
	 * @param job the Job to execute
	 */
	protected void loadItem(Job job)
	{
	}
	
	/**
	 * Removes the item loaded to the rack feeder.
	 * The rack feeder is empty after.
	 * 
	 * @param job the Job to execute
	 */
	protected void unloadItem(Job job)
	{
	}
	
	/**
	 * Moves the rack feeder to the new position.
	 * 
	 * @param job the Job to execute
	 */
	protected void moveX(Job job)
	{
	}
	
	/**
	 * Moves the rack feeder to the new position.
	 * 
	 * @param job the Job to execute
	 */
	protected void moveYZ(Job job)
	{
	}
	
	/**
	 * Moves the rack feeder to the new position.
	 * 
	 * @param job the Job to execute
	 */
	protected void moveU(Job job)
	{
	}
}
