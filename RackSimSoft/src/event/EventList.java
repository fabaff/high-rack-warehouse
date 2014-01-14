
package event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import job.Job;
import simulation.Simulation;
import helper.Write2File;


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
		
		// TODO
		// TEST
		Job job = event.getJob();
		if (job == null)
		{
			Write2File.write("Erinnerungsevent hinzugefuegt; Eventzeit: " + Simulation.calendar2String(event.getEventTime()));
		}
		else
		{
			Write2File.write("Event hinzugefuegt fuer Job '" + job.getJobID() + "', RackFeeder '" + job.getRackFeeder().getRackFeederID() + "'; Eventzeit:  " + Simulation.calendar2String(event.getEventTime()));
		}
		// TEST ENDE
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
	 * Returns an boolean, if the next Event can be created for the same RackFeeder.
	 * Returns false, if the RackFeeder is busy.
	 * Returns true, if the RackFeeder is ready for further Events.
	 * 
	 * @return the busyness of the RackFeeder
	 */
	public boolean checkNextEvent(Job job)
	{
		for (Event event : this.list)
		{
			if (event.getJob() != null)
			{
				if (event.getJob().getRackFeeder().equals(job.getRackFeeder()))
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Returns an boolean, if the next Event can be created for the same RackFeeder.
	 * Returns false, if the RackFeeder is busy.
	 * Returns true, if the RackFeeder is ready for further Events.
	 * 
	 * @return the busyness of the RackFeeder
	 */
	public boolean addRememberEvent(Calendar eventTime)
	{
		for (Event event : this.list)
		{
			if (event.getJob() == null && Simulation.calendar2String(event.getEventTime()).equals(Simulation.calendar2String(eventTime)))
			{
				// Erinnerungsevent besteht bereits, keinen neuen anlegen
				return false;
			}
		}
		
		// Erinnerungsevent per Faelligkeit generieren
		Event event = new Event(eventTime, null);
		add(event);
		
		return true;
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
