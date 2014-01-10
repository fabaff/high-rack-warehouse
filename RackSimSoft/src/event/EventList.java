
package event;

import java.util.ArrayList;
import java.util.Collections;

import job.Job;
import simulation.Simulation;


/**
 * The class EventList stores all known events. From Outside, only the next event is accessible.
 *
 */
public class EventList
{
	private static EventList instance;
	
	private ArrayList<Event> list;
	
	/**
	 * Returns an instance (object) of the class EventList.
	 * 
	 * @return the instance of this class
	 */
	public static EventList getInstance()
	{
		if (instance == null)
		{
			instance = new EventList();
		}
		
		return instance;
	}
	
	/**
	 * Creates an instance of the class EventList.
	 * 
	 */
	private EventList()
	{
		this.list = new ArrayList<Event>();
	}
	
	/**
	 * Adds a new event to the EventList.
	 * 
	 * @param event the event to add
	 */
	public void add(Event event)
	{
		this.list.add(event);
		
		// TEST
		Simulation sim = Simulation.getInstance();
		System.out.println("Simulationszeit: " + sim.getSimulationTimeFormatted());
		// TEST ENDE
		
		Job job = event.getJob();
		if (job == null)
			System.out.println("Event hinzugefügt ohne Job: " + Simulation.calendar2String(event.getEventTime()));
		else
		{
			System.out.println("Event hinzugefügt mit Job:  " + Simulation.calendar2String(event.getEventTime()));
			System.out.println("Jobzeit " + Simulation.calendar2String(job.getStartTime()));
		}
	}

	/**
	 * Returns an event if any event is ready to occur.
	 * Returns null if there is no such event.
	 * 
	 * @return the next event to occur
	 */
	public Event getNextEvent()
	{
		Event event;
		
		Collections.sort(list);
		
		try
		{
			event = list.remove(0);
		}
		catch (IndexOutOfBoundsException e)
		{
			event = null;
		}
		
		return event;
	}
	
	/**
	 * Returns a copy of the event list.
	 * 
	 * @return a copy of the list of events
	 */
	public ArrayList<Event> getEventListCopy()
	{
		Collections.sort(list);
		
		ArrayList<Event> listCopy = new ArrayList<Event>();
		
		for (Event event : list)
		{
			listCopy.add(event);
		}
		
		Collections.sort(listCopy);
		
		return listCopy;
	}
}
