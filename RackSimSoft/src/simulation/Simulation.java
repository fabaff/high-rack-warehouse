
package simulation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import event.Event;
import event.EventList;

/**
 * @author mschaerer
 *
 */
public class Simulation
{
	private static Simulation instance;
	private static double factor;
	private static Calendar startSimulationTime;
	
	private double myFactor;
	private Calendar myStartSimulationTime;
	
	private Time time;
	
	/**
	 * Returns an instance (object) of the class Simulation.
	 * 
	 * @return the instance of this class
	 */
	public static Simulation getInstance()
	{
		if (instance == null)
		{
			instance = new Simulation();
		}
		
		return instance;
	}
	
	/**
	 * Creates a Simulation object.
	 * 
	 */
	public Simulation()
	{
		this.time = null;
		this.myFactor = Simulation.factor;
		this.myStartSimulationTime = Simulation.startSimulationTime;
	}
	
	/**
	 * Sets the factor, for which the simulation time proceeds faster or slower than the real time.
	 * 
	 * @param factor the factor to set
	 */
	public static void setFactor(double factor)
	{
		Simulation.factor = factor;
	}

	/**
	 * Sets the simulation date and time to start with
	 * The format of the String must be as following:
	 * YYYY.MM.DD HH:MM:SS.sss
	 * 
	 * @param startSimulationTime the date and time to set
	 */
	public static void setStartSimulationTime(String startSimulationTime)
	{
		Simulation.startSimulationTime = Time.string2Calendar(startSimulationTime);
	}
	
	/**
	 * Starts the Simulation.
	 * 
	 */
	public void start()
	{
		this.time = Time.getInstance(this.myFactor, this.myStartSimulationTime);
		EventList eventList = EventList.getInstance();
		Event event = eventList.getNextEvent();
		Calendar nextEventTime = null;
		long nextEventTimeMillis = 0;
		long waitMillis = 0;
		
		while (event != null)
		{
			System.out.println("Event gefunden");
			nextEventTime = event.getEventTime();
			nextEventTimeMillis = nextEventTime.getTimeInMillis();
			waitMillis = nextEventTimeMillis - getSimulationTime().getTimeInMillis();
			try
			{
				System.out.println("Warte für " + waitMillis + " Millisekunden...");
				// Warten bis der Event ausgeführt werden muss
				Thread.sleep(waitMillis);
				
				System.out.println("Fertig mit warten, Event ausführen...");
				
				// Event ausführen
				event.executeEvent();
				
				System.out.println("Event ausgeführt, nächster Event?");
				
				// Nächster Event erstellen?
				event = null;
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Stops the Simulation.
	 * 
	 */
	public void stop()
	{
		
	}
	
	/**
	 * Returns the current simulation time as Calendar Object.
	 * 
	 * @return the simulation time
	 */
	public Calendar getSimulationTime()
	{
		Calendar calendar = null;
		
		if (this.time != null)
		{
			calendar = this.time.getSimulationTime();
		}
		
		return calendar;
	}
	
	/**
	 * Returns the current simulation time as formatted String.
	 * 
	 * @return the simulation time
	 */
	public String getSimulationTimeFormatted()
	{
		String simulationTime = "";
		
		if (this.time != null)
		{
			SimpleDateFormat format = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss.SSS");
			simulationTime = format.format(new Date(this.time.getSimulationTime().getTimeInMillis()));
		}
		
		return simulationTime;
	}
	
	/**
	 * Sets the correction in milliseconds, for which the simulation time proceeds forward against the real time.
	 * 
	 * @param correction the correction to set
	 */
	public void proceed(long correction)
	{
		if (this.time != null)
		{
			this.time.proceed(correction);
		}
	}
	
	/**
	 * Converts a time String into a Calendar object.
	 * The format of the String must be as following:
	 * YYYY.MM.DD HH:MM:SS.sss
	 * 
	 * @param calendarString the date and time to return as Calendar
	 * @return the Calendar object
	 */
	public static Calendar string2Calendar(String calendarString)
	{
		return Time.string2Calendar(calendarString);
	}
}
