
package event;

import java.util.ArrayList;
import java.util.Calendar;
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
		System.out.println("");
		//System.out.println("1) Simulationszeit: " + sim.getSimulationTimeFormatted());
		
		Job job = event.getJob();
		if (job == null)
			System.out.println("1) Event hinzugefügt ohne Job, Eventzeit: " + Simulation.calendar2String(event.getEventTime()));
		else
		{
			System.out.println("1) Event hinzugefügt mit Job, Eventzeit:  " + Simulation.calendar2String(event.getEventTime()));
			System.out.println("2) Jobzeit (Job): " + Simulation.calendar2String(job.getStartTime()));
		}
		System.out.println("");
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
		// TEST
		System.out.println("Job wird geprüft: " + Simulation.calendar2String(job.getStartTime()));
		// TEST ENDE
		
		for (Event event : this.list)
		{
			// TEST
			System.out.println("Event wird geprüft: " + Simulation.calendar2String(event.getEventTime()));
			// TEST ENDE
			
			if (event.getJob() != null)
			{
				if (event.getJob().getRackFeeder().equals(job.getRackFeeder()))
				{
					// TEST
					System.out.println("Return FALSE");
					// TEST ENDE
					
					return false;
				}
			}
		}
		
		// TEST
		System.out.println("Return TRUE");
		// TEST ENDE
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
		// TEST
		System.out.println("Prüfen auf Erinnerungen, wenn nötig neue anlegen...");
		// TEST ENDE
		
		for (Event event : this.list)
		{
			// TEST
			if (event.getJob() == null)
			{
				System.out.println("Event in Liste: " + Simulation.calendar2String(event.getEventTime()) + " ohne Job");
			}
			else
			{
				System.out.println("Event in Liste: " + Simulation.calendar2String(event.getEventTime()) + " mit Job");
			}
			// TEST ENDE
			
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
