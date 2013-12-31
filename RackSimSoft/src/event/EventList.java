
package event;

import java.util.ArrayList;
import java.util.Collections;


/**
 * @author mschaerer
 *
 */
public class EventList
{
	private static EventList instance;
	
	private ArrayList<Event> list = new ArrayList<Event>();
	
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
	 * Adds a new event to the EventList.
	 * 
	 * @param event the event to add
	 */
	public void add(Event event)
	{
		this.list.add(event);
	}

	/**
	 * Returns an event if any event is ready to occur.
	 * Returns null if there is no such event.
	 * 
	 * @return the next event to occur
	 */
	public Event getNextEvent()
	{
		//Event e = null;
		Collections.sort(list);
		Event event = list.remove(0);
		
		/*
		Simulation sim = Simulation.getInstance();
		Calendar simulationTime = sim.getSimulationTime();
		
		// Ist die Zeit für diesen Event erreicht (oder ueberschritten), dann den Event aus der Liste entfernen und verarbeiten
		if ((simulationTime != null) && (simulationTime.after(event.getEventTime())))
		{
			e = list.remove(0);
		}
		*/
		
		return event;
	}
}
