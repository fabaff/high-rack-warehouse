
package simulation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import job.Job;
import job.JobList;
import location.Gap;
import location.Location;
import location.RackFeeder;
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
		long currentEventTimeMillis = 0;
		long waitMillis = 0;
		
		while (event != null)
		{
			//System.out.println("Event gefunden, Startzeit: " + calendar2String(event.getEventTime()));
			nextEventTime = event.getEventTime();
			currentEventTimeMillis = nextEventTime.getTimeInMillis();
			waitMillis = currentEventTimeMillis - getSimulationTime().getTimeInMillis();
			if (waitMillis < 0)
			{
				waitMillis = 0;
			}
			
			try
			{
				//System.out.println("Warte für " + waitMillis + " Millisekunden...");
				// Warten bis der Event ausgeführt werden muss
				Thread.sleep(waitMillis);
				
				//System.out.println("Fertig mit warten, Event ausführen...");
				
				
				/*
				// Ausgabe:
				Item item;
				String itemID;
				item = event.getJob().getRackFeeder().getItem();
				if (item != null)
					itemID = item.getItemID();
				else
					itemID = "<leer>";
				
				System.out.println("RackFeeder vorher:");
				System.out.println("  Status: " + event.getJob().getRackFeeder().getState().getClass().getName());
				System.out.println("  Koordinaten: " + event.getJob().getRackFeeder().getCoordinate().toString());
				System.out.println("  Artikel: " + itemID);
				*/
				
				// Event ausführen
				int nextEventMillis = event.executeEvent();
				
				/*
				// Ausgabe:
				item = event.getJob().getRackFeeder().getItem();
				if (item != null)
					itemID = item.getItemID();
				else
					itemID = "<leer>";
				
				System.out.println("RackFeeder nachher:");
				System.out.println("  Status: " + event.getJob().getRackFeeder().getState().getClass().getName());
				System.out.println("  Koordinaten: " + event.getJob().getRackFeeder().getCoordinate().toString());
				System.out.println("  Artikel: " + itemID);
				*/
				
				
				// Nächsten Event für diesen Job erstellen...
				// nextEventMillis zeigt die Zeit für den nächsten Event an (0..xxx)
				// Wenn -1, dann kein Nachfolge-Event mehr
				if (nextEventMillis >= 0)
				{
					System.out.println("Event ausgeführt, Nachfolge-Event in " + nextEventMillis + " Millisekunden");
					
					Calendar calendar = Calendar.getInstance();
					calendar.setTimeInMillis(currentEventTimeMillis + nextEventMillis);
					
					event = new Event(calendar, event.getJob());
										
					eventList.add(event);
				}
				else
				{
					System.out.println("Event ausgeführt, kein Nachfolge-Event mehr.");
					
					/*
					// Aus Jobliste neuen Event erstellen für diesen RackFeeder, da dieser wieder frei ist?
					JobList jobList = JobList.getInstance();
					// JobListe ist aufsteigend sortiert nach Startzeit
					for (Job job : jobList.getJobList())
					{
						// Job vorhanden für diesen RackFeeder?
						if (event.getJob().getRackFeeder().equals(job.getRackFeeder()))
						{
							// Event generieren, Zeit muss ev. angepasst werden da der Job hinausgezögert wurde
							Calendar startTime = job.getStartTime();
							if (startTime.before(this.getSimulationTime()))
							{
								startTime = this.getSimulationTime();
							}
							
							event = new Event(startTime, job);
							eventList.add(event);
							
							// Job aus Liste entfernen
							jobList.remove(job);
							
							// Abbrechen
							break;
						}
					}
					*/
				}
				
				// Aus Jobliste neuen Event erstellen, weil der Job nun fällig ist?
				createEvents();
				
				// Nächsten Event holen
				event = eventList.getNextEvent();
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
	 * Creates Events depending on the job list.
	 * Just one event per gap is created, even if there are more jobs per gap.
	 */
	public static void createEvents()
	{
		JobList jobList = JobList.getInstance();
		ArrayList<Job> jobRemoveList = new ArrayList<Job>();
		ArrayList<Gap> gapList = Location.getInstance().getGapListCopy();
		Hashtable<String, RackFeeder> rackFeederTable = new Hashtable<String, RackFeeder>();
		RackFeeder rackFeeder;
		Event event;
		EventList eventList = EventList.getInstance();
		
		// Alle RackFeeder in HashTable abfüllen
		for (Gap gap : gapList)
		{
			rackFeeder = gap.getRackFeeder();
			rackFeederTable.put(rackFeeder.getRackFeederID(), rackFeeder);
		}
		
		// Alle RackFeeder, welche in Events vorhanden sind, wieder entfernen
		for (Event e : eventList.getEventListCopy())
		{
			if (e.getJob() != null)
			{
				rackFeederTable.remove(e.getJob().getRackFeeder().getRackFeederID());
			}
		}
		
		// JobListe ist aufsteigend sortiert nach Startzeit
		for (Job job : jobList.getJobList())
		{
			// Falls RackFeeder noch in Tabelle, dann kann der Job als Event generiert werden und der RackFeeder aus der Tabelle entfernt werden
			if (rackFeederTable.get(job.getRackFeeder().getRackFeederID()) != null)
			{
				// Event generieren
				// Unterscheiden, ob der Job bereits faellig ist oder nur ein Event als Erinnerung zum generieren eines Events generiert werden soll
				// Ist der Job faellig, wird der Job entfernt und daraus eine Event generiert
				// Ist der Job noch nicht faellig, wird per Faelligkeit ein "Erinnerungs-Event" generiert und erst beim ausfuehren dieses Events ein Job-Event generiert
				if (job.getStartTime().before(getInstance().getSimulationTime()))
				{
					event = new Event(job.getStartTime(), job);
					
					// Job vormerken zum aus Liste entfernen
					jobRemoveList.add(job);
				}
				else
				{
					// Erinnerungsevent per Faelligkeit generieren
					event = new Event(job.getStartTime(), null);
				}
				
				eventList.add(event);
				
				// RackFeeder aus Tabelle entfernen
				rackFeederTable.remove(job.getRackFeeder().getRackFeederID());
				
				// Ev. abbrechen weil keine freien RackFeeder mehr vorhanden?
				if (rackFeederTable.size() == 0)
					break;
			}
		}
		
		// Jobs aus Liste entfernen
		for (Job job : jobRemoveList)
		{
			jobList.remove(job);
		}
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
	
	/**
	 * Converts a Calendar object into a time String.
	 * The format of the String is as following:
	 * YYYY.MM.DD HH:MM:SS.sss
	 * 
	 * @param calendar the Calendar object to return as String
	 * @return the Calendar String
	 */
	public static String calendar2String(Calendar calendar)
	{
		return Time.calendar2String(calendar);
	}
}
