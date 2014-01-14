
package simulation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import helper.Write2File;
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
	private static Calendar simulationTime;
	
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
	private Simulation()
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
		// TODO Exceptionhandling fuer Faktoren <= 0 und ev. auf 3 Nachkommastellen beschränken
		if (factor <= 0)
			factor = 1;
		
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
		return this.time.getFactor();
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
		// TEST
		Calendar start = Calendar.getInstance();
		Write2File.write();
		Write2File.write("Simulation wird nun gestartet:");
		Write2File.write("Aktuelle Systemzeit: " + calendar2String(start));
		Write2File.write("Aktueller Faktor: " + this.myFactor);
		Write2File.write("Aktueller Modus: " + this.mySimulationType);
		Write2File.write();
		Write2File.write("-------------------------------------------------------------------------------------------");
		int eventCounter = 0;
		// TEST ENDE
		
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
			eventCounter += 1;
			Write2File.write(String.format("%1$-6s", "" + eventCounter) + "   ----------------------------------------------------------------------------------");
			Write2File.write("Simulationszeit: " + getInstance().getCurrentSimulationTimeFormatted());
			if (event.getJob() != null)
			{
				Write2File.write("Event zu Job '" + event.getJob().getJobID() + "', RackFeeder '" + event.getJob().getRackFeeder().getRackFeederID() + "' gefunden, Startzeit: " + calendar2String(event.getEventTime()));
			}
			else
			{
				Write2File.write("Erinnerungsevent gefunden, Startzeit: " + calendar2String(event.getEventTime()));	
			}
			// TEST ENDE
			
			nextEventTime = event.getEventTime();
			currentEventTimeMillis = nextEventTime.getTimeInMillis();
			waitMillis = currentEventTimeMillis - getCurrentSimulationTime().getTimeInMillis();
			// Faktor beruecksichtigen
			waitMillis = Math.round(waitMillis / this.getFactor());
			if (waitMillis < 0)
			{
				waitMillis = 0;
			}
			
			// wegen Rechenzeit-Verschiebung...
			setSimulationTime(event.getEventTime());
			
			try
			{
				// Abhaengig vom Typ der Simulation warten oder Zeit manipulieren
				if (this.mySimulationType == SimulationType.AS_FAST_AS_POSSIBLE)
				{
					// TEST
					Write2File.write();
					Write2File.write("Wartezeit in ms (simuliert): " + waitMillis);
					// TEST ENDE
					
					// Sofort voranschreiten
					this.time.proceed(waitMillis);
				}
				else
				{
					// TEST
					Write2File.write();
					Write2File.write("Wartezeit in ms (echt): " + waitMillis);
					// TEST ENDE
					
					// Warten bis der Event ausgefuehrt werden muss
					Thread.sleep(waitMillis);
				}
				
				// TEST
				Write2File.write();
				Write2File.write("Simulationszeit (echt) nach Wartezeit: " + getInstance().getCurrentSimulationTimeFormatted());
				Write2File.write("Simulationszeit (soll) nach Wartezeit: " + calendar2String(getSimulationTime()));
				Write2File.write();
				// TEST ENDE
				
				// Event ausfuehren, gibt die benötigte Zeit fuer den naechsten Schritt (bis zum naechsten Event) zurueck
				// ------------------------------------------------------------------------------------------------------
				int nextEventMillis = event.executeEvent();
				
				// Naechsten Event fuer diesen Job erstellen...
				// nextEventMillis zeigt die Zeit fuer den naechsten Event an (0..xxx)
				// Wenn -1, dann kein Nachfolge-Event mehr
				if (nextEventMillis >= 0)
				{
					Calendar calendar = Calendar.getInstance();
					//HANS calendar.setTimeInMillis(currentEventTimeMillis + waitMillis + nextEventMillis);
					calendar.setTimeInMillis(currentEventTimeMillis + nextEventMillis);
					event = new Event(calendar, event.getJob());
										
					eventList.add(event);
				}
				else
				{
					// TEST
					Write2File.write("Kein Nachfolgeevent. Eventuell Erinnerungsevents oder Startevents anlegen?");
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
			Write2File.write("-------------------------------------------------------------------------------------------");
			// TEST ENDE
		}
		
		// TEST
		Calendar end = Calendar.getInstance();
		//Write2File.write("Simulation wird nun beendet, aktuelle Systemzeit: " + calendar2String(end));
		//Write2File.write("                                Start-Systemzeit: " + calendar2String(start));
		Write2File.write("Simulation wird nun beendet, Start-Systemzeit: " + calendar2String(start));
		Write2File.write("                          aktuelle Systemzeit: " + calendar2String(end));
		Write2File.write();
		Write2File.write("Vergangene Systemzeit in Millis: " + (end.getTimeInMillis() - start.getTimeInMillis()));
		// TEST
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
		// Bei Fall 2 ist der RackFeeder sicher frei und der Event kann sofort generiert werden (falls faellig)
		
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
						//if  ((! (event == null)) && (! (job.getStartTime().after(getInstance().getCurrentSimulationTime()))))
						if  ((! (event == null)) && (! (job.getStartTime().after(getSimulationTime()))))
						{
							//Event newEvent = new Event(getInstance().getSimulationTime(), job);
							Event newEvent = new Event(event.getEventTime(), job);
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
					jobList.remove(job);
				}
			}
			else
			{
				// Nichts mehr zu tun, kein neuer Job mehr trotz Erinnerungsevent
				// Job wurde ueber GUI geloescht
				Write2File.write("Erinnerungsevent, aber kein Job mehr gefunden.");
			}
		}
		else
		{
			// Fall 2
			
			// Der aktuelle Event war ein normaler Event ohne Nachfolgeevent,
			// also kann der erste Event fuer den naechsten Job fuer denselben RackFeeder generiert werden (falls vorhanden)
			// Ist der Job noch nicht faellig, einen Erinnerungsevent anlegen
			
			JobList jobList = JobList.getInstance();
			ArrayList<Job> list = jobList.getJobList();
			
			for (Job job : list)
			{
				// Naechsten Job fuer denselben RackFeeder suchen
				if (job.getRackFeeder().equals(lastJob.getRackFeeder()))
				{
					EventList eventList = EventList.getInstance();
					
					// Ersten Event fuer diesen Job anlegen, falls der Job faellig ist.
					//if (! job.getStartTime().after(getInstance().getCurrentSimulationTime()))
					if (! job.getStartTime().after(getSimulationTime()))
					{
						Event newEvent = new Event(event.getEventTime(), job);
						eventList.add(newEvent);
						
						jobList.remove(job);
					}
					// Ansonsten Erinnerungsevent anlegen.
					else
					{
						eventList.addRememberEvent(job.getStartTime());
					}
					
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
	 * Returns the current simulation type.
	 * 
	 * @return the nextSimulationTime
	 */
	public SimulationType getSimulationType()
	{
		return this.mySimulationType;
	}
	
	/**
	 * Returns the current simulation time as Calendar Object.
	 * 
	 * @return the simulation time
	 */
	public Calendar getCurrentSimulationTime()
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
	public String getCurrentSimulationTimeFormatted()
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
	 * @return the nextSimulationTime
	 */
	public static Calendar getSimulationTime()
	{
		return simulationTime;
	}

	/**
	 * @param nextSimulationTime the nextSimulationTime to set
	 */
	public static void setSimulationTime(Calendar simulationTime)
	{
		Simulation.simulationTime = simulationTime;
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
