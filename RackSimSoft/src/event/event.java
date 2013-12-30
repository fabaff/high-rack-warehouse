
package event;

import java.util.Calendar;

import state.RackFeederState.Behavior;
import location.Bin;
import location.Location;
import location.RackFeeder;

/**
 * @author mschaerer
 *
 */
public class Event implements Comparable<Event>
{
	private Calendar eventTime;
	private RackFeeder rackFeeder;
	private Bin bin;
	private Behavior behavior;
	

	/**
	 * Creates a new event.
	 * 
	 * @param eventTime the time the event occurs
	 * @param rackFeederID the ID for the RackFeeder to which the event belongs to
	 * @param binID the ID for the Bin to which the event belongs to
	 * @param behavior the current behavior to be set
	 */
	public Event(Calendar eventTime, String rackFeederID, String binID, Behavior behavior)
	{
		Location location = Location.getInstance();
		this.eventTime = eventTime;
		this.rackFeeder = location.getGap(rackFeederID).getRackFeeder();
		this.bin = location.getBin(binID);
		this.behavior = behavior;
	}

	/**
	 * Returns the current event time.
	 * 
	 * @return the eventTime
	 */
	public Calendar getEventTime()
	{
		return eventTime;
	}

	/**
	 * Returns the current RackFeeder.
	 * 
	 * @return the rackFeeder
	 */
	public RackFeeder getRackFeeder()
	{
		return rackFeeder;
	}

	/**
	 * Returns the current Bin.
	 * 
	 * @return the bin
	 */
	public Bin getBin()
	{
		return bin;
	}

	/**
	 * Returns the current Behavior.
	 * 
	 * @return the behavior
	 */
	public Behavior getBehavior()
	{
		return behavior;
	}

	@Override
	public int compareTo(Event event)
	{
		return this.eventTime.compareTo(event.getEventTime());
	}
}
