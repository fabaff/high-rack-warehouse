
package event;

import java.util.Calendar;

import job.Job;

/**
 * The class Event holds the functionality and information for the simple steps of a Job.
 *
 */
public class Event implements Comparable<Event>
{
	private Calendar eventTime;
	private Job job;
	
	/**
	 * Creates a new event.
	 * 
	 * @param eventTime the time the event occurs
	 * @param job the job for which the event occurs
	 */
	public Event(Calendar eventTime, Job job)
	{
		this.eventTime = eventTime;
		this.job = job;
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
		if (this.job != null)
		{
			// "Richtiger" Event
			return this.job.executeJob();
		}
		else
		{
			// "Erinnerungs-Event"
			return -1;
		}
	}
	
	@Override
	public int compareTo(Event event)
	{
		return this.eventTime.compareTo(event.getEventTime());
	}
}
