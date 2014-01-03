
package event;

import java.util.Calendar;

import job.Job;

/**
 * @author mschaerer
 *
 */
public class Event implements Comparable<Event>
{
	private Calendar eventTime;
	private Job job;
	//private RackFeeder rackFeeder;
	//private Bin bin;
	//private Behavior behavior;
	

	/**
	 * Creates a new event.
	 * 
	 * @param eventTime the time the event occurs
	 * @param rackFeederID the ID for the RackFeeder to which the event belongs to
	 * @param binID the ID for the Bin to which the event belongs to
	 * @param behavior the current behavior to be set
	 */
	//public Event(Calendar eventTime, String rackFeederID, String binID, Behavior behavior)
	public Event(Calendar eventTime, Job job)
	{
		//Location location = Location.getInstance();
		this.eventTime = eventTime;
		
		this.job = job;
		//this.rackFeeder = location.getGap(rackFeederID).getRackFeeder();
		//this.bin = location.getBin(binID);
		//this.behavior = behavior;
	}

	/**
	 * Returns the current event time.
	 * 
	 * @return the eventTime
	 */
	public Calendar getEventTime()
	{
		return this.eventTime;
	}
	
	/**
	 * Returns the current job.
	 * 
	 * @return the job
	 */
	public Job getJob()
	{
		return this.job;
	}

	/**
	 * Executes the current Event.
	 * Returns the time in ms to the next event.
	 * 
	 * @return the time in ms to the next event
	 */
	public int executeEvent()
	{
		return this.job.executeJob();
	}
	
	@Override
	public int compareTo(Event event)
	{
		return this.eventTime.compareTo(event.getEventTime());
	}
}
