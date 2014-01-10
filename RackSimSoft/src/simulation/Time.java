
package simulation;

import java.util.Calendar;


/**
 * This class is used to handle the different time aspects of the system time and the simulation time.
 * It's taught to be used only from the Simulation class.
 * 
 */
class Time
{
	private static Time instance;
	
	private final Calendar startWallClockCalendar;
	private final Calendar startSimulationCalendar;
	
	private double factor;
	private long startWallClockMillis;
	private long startSimulationMillis;
	
	private long correction = 0;
	
	/**
	 * Returns an instance (object) of the class Time.
	 * The factor, for which the simulation time proceeds faster or slower than the real time, must be given.
	 * The simulation date and time to start with must be given.
	 * The format of the String must be as following:
	 * YYYY.MM.DD HH:MM:SS.sss
	 * 
	 * @param factor the factor to set
	 * @param startSimulationTime the date and time to set
	 * 
	 * @return the instance of this class
	 */
	static Time getInstance(double factor, Calendar startSimulationTime)
	{
		if (instance == null)
		{
			instance = new Time(factor, startSimulationTime);
		}
		
		return instance;
	}
	
	/**
	 * Creates a Time object.
	 * 
	 */
	private Time(double factor, Calendar startSimulationTime)
	{
		this.factor = factor;
		
		// Simulations-Startdatum und -Zeit auf gewuenschten Wert setzen, Millisekunden auf 000 setzen
		this.startSimulationCalendar = startSimulationTime;
		this.startSimulationMillis = startSimulationCalendar.getTimeInMillis();
		
		// Systemzeit holen
		this.startWallClockCalendar = Calendar.getInstance();
		this.startWallClockMillis = this.startWallClockCalendar.getTimeInMillis();
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
		this.correction += correction;
	}
	
	/**
	 * Returns the current simulation time.
	 * 
	 * @return the simulation time
	 */
	Calendar getSimulationTime()
	{
		Calendar currentSimulationCalendar = Calendar.getInstance();
		currentSimulationCalendar.setTimeInMillis(this.getStartSimulationMillis() + Math.round(this.factor * (System.currentTimeMillis() - this.getStartWallClockMillis())) + this.correction);
		
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
	
	/**
	 * Converts a Calendar object into a time String.
	 * The format of the String is as following:
	 * YYYY.MM.DD HH:MM:SS.sss
	 * 
	 * @param calendar the Calendar object to return as String
	 * @return the Calendar String in format YYYY.MM.DD HH:MM:SS.sss
	 */
	static String calendar2String(Calendar calendar)
	{
		String calendarString = "";
		calendarString += calendar.get(Calendar.YEAR) + ".";
		calendarString += String.format("%02d", (calendar.get(Calendar.MONTH) + 1)) + ".";  // -> ACHTUNG: Monat Januar = 0, Monat Dezember = 11 !!!
		calendarString += String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH)) + " ";
		calendarString += String.format("%02d", calendar.get(Calendar.HOUR_OF_DAY)) + ":";
		calendarString += String.format("%02d", calendar.get(Calendar.MINUTE)) + ":";
		calendarString += String.format("%02d", calendar.get(Calendar.SECOND)) + ".";
		calendarString += String.format("%03d", calendar.get(Calendar.MILLISECOND));
		
		return calendarString;
	}
}
