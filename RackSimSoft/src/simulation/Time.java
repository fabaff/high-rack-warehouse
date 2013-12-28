
package simulation;

import java.util.Calendar;


/**
 * @author mschaerer
 *
 */
class Time
{
	private static Time instance;
	private static double factor;
	private static int year = -1;
	private static int month = -1;
	private static int day = -1;
	private static int hour = -1;
	private static int minute = -1;
	private static int second = -1;
	
	private final Calendar startWallClockCalendar;
	private final Calendar startSimulationCalendar;
	private long startWallClockMillis;
	private long startSimulationMillis;
	
	/**
	 * Returns an instance (object) of the class Location.
	 * 
	 * @return the instance of this class
	 */
	static Time getInstance()
	{
		if (instance == null)
		{
			instance = new Time();
		}
		
		return instance;
	}
	
	/**
	 * Creates a Time object.
	 * 
	 */
	private Time()
	{
		// Simulations-Startdatum und -Zeit auf gewünschten Wert setzen, Millisekunden auf 000 setzen
		this.startSimulationCalendar = Calendar.getInstance();
		this.startSimulationCalendar.set(Time.year, Time.month - 1, Time.day, Time.hour, Time.minute, Time.second);  // Format YYYY, MM, DD, HH, MM, SS -> ACHTUNG: Monat Januar = 0, Monat Dezember = 11 !!!
		this.startSimulationMillis = this.startSimulationCalendar.getTimeInMillis();
		this.startSimulationMillis = this.startSimulationMillis / 1000;
		this.startSimulationMillis = (this.startSimulationMillis * 1000);
		
		// Systemzeit holen
		this.startWallClockCalendar = Calendar.getInstance();
		this.startWallClockMillis = this.startWallClockCalendar.getTimeInMillis();
	}

	/**
	 * Sets the factor, for which the simulation time proceeds faster or slower than the real time.
	 * 
	 * @param factor the factor to set
	 */
	static void setFactor(double factor)
	{
		Time.factor = factor;
	}

	/**
	 * Sets the simulation date and time to start with
	 * The format of the String must be as following:
	 * YYYY.MM.DD HH:MM:SS
	 * 
	 * @param startSimulationTime the date and time to set
	 */
	static void setStartSimulationTime(String startSimulationTime)
	{
		Time.year = Integer.parseInt(startSimulationTime.substring(0, 4));
		Time.month = Integer.parseInt(startSimulationTime.substring(5, 7));
		Time.day = Integer.parseInt(startSimulationTime.substring(8, 10));
		Time.hour = Integer.parseInt(startSimulationTime.substring(11, 13));
		Time.minute = Integer.parseInt(startSimulationTime.substring(14, 16));
		Time.second = Integer.parseInt(startSimulationTime.substring(17, 19));
	}
	
	/**
	 * @return the startWallClockMillis
	 */
	private long getStartWallClockMillis()
	{
		return this.startWallClockMillis;
	}

	/**
	 * @return the startSimulationMillis
	 */
	private long getStartSimulationMillis()
	{
		return this.startSimulationMillis;
	}

	/**
	 * Returns the current simulation time.
	 * 
	 * @return the simulation time
	 */
	Calendar getSimulationTime()
	{
		Calendar currentSimulationCalendar = Calendar.getInstance();
		currentSimulationCalendar.setTimeInMillis(this.getStartSimulationMillis() + Math.round(Time.factor * (System.currentTimeMillis() - this.getStartWallClockMillis())));
		
		return currentSimulationCalendar;
	}
}
