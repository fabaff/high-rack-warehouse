
package simulation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import job.Job;
import job.JobList;
import event.Event;
import event.EventList;

/**
 * This class manages all simulation relevant functionality.
 * It also works as wrapper for the simulation time from class Time.
 *
 */
public class Simulation
{
	public static enum SimulationType
	{
		FACTOR, AS_FAST_AS_POSSIBLE
	}
	
	private static Simulation instance;
	private static double factor;
	private static SimulationType simulationType;
	private static Calendar startSimulationTime;
	
	private double myFactor;
	private SimulationType mySimulationType;
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
		this.mySimulationType = Simulation.simulationType;
		if (this.mySimulationType == SimulationType.AS_FAST_AS_POSSIBLE)
		{
			this.myFactor = 1;
		}
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
	 * Sets the simulation type, for which the simulation time proceeds faster or slower than the real time or as fast as possible.
	 * 
	 * @param simulationType the simulationType to set
	 */
	public static void setSimulationType(SimulationType simulationType)
	{
		Simulation.simulationType = simulationType;
	}

	/**
	 * Returns the current factor for calculating the simulation time.
	 * 
	 * @return the factor
	 */
	public double getFactor()
	{
		return this.myFactor;
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
		// Simulationszeit starten
		this.time = Time.getInstance(this.myFactor, this.myStartSimulationTime);

		EventList eventList = EventList.getInstance();
		Event event = eventList.getNextEvent();
		Calendar nextEventTime = null;
		long currentEventTimeMillis = 0;
		long waitMillis = 0;
		
		while (event != null)
		{
			// TEST
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("Dieser Event: " + calendar2String(event.getEventTime()));
			System.out.println("Simulationszeit: " + getInstance().getSimulationTimeFormatted());
			// TEST ENDE
			
			nextEventTime = event.getEventTime();
			currentEventTimeMillis = nextEventTime.getTimeInMillis();
			waitMillis = currentEventTimeMillis - getSimulationTime().getTimeInMillis();
			if (waitMillis < 0)
			{
				waitMillis = 0;
			}
			
			try
			{
				// Abhaengig vom Typ der Simulation warten oder Zeit manipulieren
				if (this.mySimulationType == SimulationType.AS_FAST_AS_POSSIBLE)
				{
					// Sofort voranschreiten
					this.time.proceed(waitMillis);
				}
				else
				{
					// Warten bis der Event ausgefuehrt werden muss
					Thread.sleep(waitMillis);
				}
				
				// TEST
				System.out.println("Simulationszeit nach Sleep: " + getInstance().getSimulationTimeFormatted());
				System.out.println("");
				// TEST ENDE
				
				// Event ausfuehren
				int nextEventMillis = event.executeEvent();
				
				// Naechsten Event fuer diesen Job erstellen...
				// nextEventMillis zeigt die Zeit fuer den naechsten Event an (0..xxx)
				// Wenn -1, dann kein Nachfolge-Event mehr
				if (nextEventMillis >= 0)
				{
					Calendar calendar = Calendar.getInstance();
					calendar.setTimeInMillis(currentEventTimeMillis + waitMillis + nextEventMillis);
					
					event = new Event(calendar, event.getJob());
										
					eventList.add(event);
					
					// TEST
					System.out.println("Nachfolgeevent angelegt: " + calendar2String(event.getEventTime()));
					// TEST ENDE
				}
				else
				{
					// TEST
					System.out.println("Kein Nachfolgeevent. Eventuell Erinnerungsevents oder Startevents anlegen?");
					// TEST ENDE
					
					// Aus Jobliste neuen Event erstellen, weil der Job nun faellig ist?
					createEvents(event);
				}
				
				// Naechsten Event holen
				event = eventList.getNextEvent();
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// TEST
			System.out.println("-----------------------------------------------------------------------");
			// TEST ENDE
		}
	}
	
	/**
	 * Stops the Simulation.
	 * 
	 */
	public void stop()
	{
		// TODO implementieren, so dass eine laufende Simulation vorzeitig beendet werden könnte, ev. noch eine Methode pause() und unpause() implementieren
	}
	
	
	/**
	 * Creates Events depending on the job list.
	 * Just one event per gap is created at the time, even if there are more jobs per gap.
	 */
	public static void createEvents(Event event)
	{
		// Diese Funktion wird ausgefuehrt
		// zum initialisieren vor dem starten der Simulation (event == null), wird gleich behandelt wie ein Erinnerungsevent,
		// oder weil Fall 1: ein Erinnerungsevent ausgefuehrt wurde (Job == null)
		// oder weil Fall 2: ein "normaler" Event ausgefuehrt wurde und kein Nachfolgeevent mehr kommt (der RackFeeder ist nun frei fuer allfaellige neue Jobs in dieser Gasse)
		//
		// Bei Fall 1, muss zuerst geprüft werden, ob der RackFeeder frei ist. Wenn nicht, einen neuen, spaeteren Erinnerungs-Event generieren.
		// Bei Fall 2 ist der RackFeeder sicher frei und der Event kann sofort generiert werden
		
		Job lastJob = null;
		
		// Initialisierung oder Event wurde ausgeführt
		if (event != null)
			lastJob = event.getJob();
		
		if (lastJob == null)
		{
			// Fall 1 oder Initialisierung
			
			// Der aktuelle Event war ein Erinnerungsevent,
			// also naechste Jobs pruefen und Startevents dafuer anlegen falls der RackFeeder frei ist
			EventList eventList = EventList.getInstance();
			JobList jobList = JobList.getInstance();
			ArrayList<Job> list = jobList.getJobList();
			
			// Gibt es ueberhaupt noch neue Jobs?
			if (list.size() != 0)
			{
				ArrayList<Job> jobRemoveList = new ArrayList<Job>();
				
				// Alle Jobs pruefen
				for (Job job : list)
				{
					// Pruefen, ob der RackFeeder frei ist und ggf. ersten Event fuer den Job anlegen
					if (eventList.checkNextEvent(job))
					{
						// Ersten Event fuer diesen Job anlegen, falls der Job faellig ist.
						// und es sich nicht um die Initialisierung handelt!
						if  ((! (event == null)) && (! (job.getStartTime().after(getInstance().getSimulationTime()))))
						{
							Event newEvent = new Event(getInstance().getSimulationTime(), job);
							eventList.add(newEvent);
							
							jobRemoveList.add(job);
						}
						// Ansonsten Erinnerungsevent anlegen.
						else
						{
							eventList.addRememberEvent(job.getStartTime());
						}
					}
					else
					{
						// Nichts mehr zu tun, Erster Event fuer den naechsten Job wird angelegt,
						// Sobald der echte Event ausgefuehrt wurde. Dieser Job wird somit verzoegert ausgefuehrt.
					}
				}
				
				// Wo noetig, Jobs aus Liste entfernen
				for (Job job : jobRemoveList)
				{
					list.remove(job);
				}
			}
			else
			{
				// Nichts mehr zu tun, kein neuer Job mehr trotz Erinnerungsevent
				// Job wurde ueber GUI geloescht
			}
		}
		else
		{
			// Fall 2
			
			// Der aktuelle Event war ein normaler Event ohne Nachfolgeevent,
			// also kann der erste Event fuer den naechsten Job fuer denselben RackFeeder generiert werden (falls vorhanden)
			
			JobList jobList = JobList.getInstance();
			ArrayList<Job> list = jobList.getJobList();
			
			for (Job job : list)
			{
				// Naechsten Job fuer denselben RackFeeder suchen
				if (job.getRackFeeder().equals(lastJob.getRackFeeder()))
				{
					// Event anlegen
					// Ersten Event fuer diesen Job anlegen
					EventList eventList = EventList.getInstance();
					Event newEvent = new Event(getInstance().getSimulationTime(), job);
					eventList.add(newEvent);
					
					jobList.remove(job);
					
					// Keine weiteren Events mehr anlegen
					break;
				}
			}
		}
	}
	
	/**
	 * Creates Events depending on the job list.
	 * 
	 * This events figure to remeber, that there are jobs which start at a later time.
	 */
	public static void createInitialEvents()
	{
		JobList jobList = JobList.getInstance();
		EventList eventList = EventList.getInstance();
		
		// JobListe ist aufsteigend sortiert nach Startzeit
		for (Job job : jobList.getJobList())
		{
			// Erinnerungsevent per Faelligkeit generieren
			eventList.addRememberEvent(job.getStartTime());
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
		/*
		 * TODO
		 * 
		 * Exceptionhandling implementieren!
		 */
		/*
		if (correction < 0)
			throw new Exception("Es darf nicht negativ korrigiert werden!");
		*/
		
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
