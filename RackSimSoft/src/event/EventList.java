
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
		
		return listCopy;
	}
}
