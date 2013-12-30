
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
	
	private long correction = 0;
	
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
		// Simulations-Startdatum und -Zeit auf gewuenschten Wert setzen, Millisekunden auf 000 setzen
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
	 * Returns the real start time in milliseconds.
	 * 
	 * @return the startWallClockMillis
	 */
	private long getStartWallClockMillis()
	{
		return this.startWallClockMillis;
	}

	/**
	 * Returns the simulation start time in milliseconds.
	 * 
	 * @return the startSimulationMillis
	 */
	private long getStartSimulationMillis()
	{
		return this.startSimulationMillis;
	}

	/**
	 * Sets the correction in milliseconds, for which the simulation time proceeds forward against the real time.
	 * 
	 * @param correction the correction to set
	 */
	void proceed(long correction)
	{
		this.correction = correction;
	}
	
	/**
	 * Returns the current simulation time.
	 * 
	 * @return the simulation time
	 */
	Calendar getSimulationTime()
	{
		Calendar currentSimulationCalendar = Calendar.getInstance();
		currentSimulationCalendar.setTimeInMillis(this.getStartSimulationMillis() + Math.round(Time.factor * (System.currentTimeMillis() - this.getStartWallClockMillis())) + this.correction);
		
		return currentSimulationCalendar;
	}
	
	/**
	 * Converts a time String into a Calendar object.
	 * The format of the String must be as following:
	 * YYYY.MM.DD HH:MM:SS.sss
	 * 
	 * @param calendarString the date and time to return as Calendar
	 * @return the Calendar object
	 */
	static Calendar string2Calendar(String calendarString)
	{
		int year = Integer.parseInt(calendarString.substring(0, 4));
		int month = Integer.parseInt(calendarString.substring(5, 7));
		int day = Integer.parseInt(calendarString.substring(8, 10));
		int hour = Integer.parseInt(calendarString.substring(11, 13));
		int minute = Integer.parseInt(calendarString.substring(14, 16));
		int second = Integer.parseInt(calendarString.substring(17, 19));
		int milliSecond = Integer.parseInt(calendarString.substring(20, 23));
		long millis;
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day, hour, minute, second);  // Format YYYY, MM, DD, HH, MM, SS -> ACHTUNG: Monat Januar = 0, Monat Dezember = 11 !!!
		millis = calendar.getTimeInMillis();
		millis /= 1000;
		millis *= 1000;
		millis += milliSecond;
		
		calendar.setTimeInMillis(millis);
		
		return calendar;
	}
}
