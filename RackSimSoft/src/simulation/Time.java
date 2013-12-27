
package simulation;

import java.util.Calendar;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author mschaerer
 *
 */
public class Time
{
	private static Time instance;
	
	private final Calendar startWallClockCalendar;
	private final Calendar startSimulationCalendar;
	private long startWallClockMillis;
	private long startSimulationMillis;
	
	/**
	 * Returns an instance (object) of the class Location.
	 * 
	 * @return the instance of this class
	 */
	public static Time getInstance()
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
		this.startWallClockCalendar = Calendar.getInstance();
		this.startWallClockMillis = this.startWallClockCalendar.getTimeInMillis();
		
		this.startSimulationCalendar = Calendar.getInstance();
		this.startSimulationCalendar.set(2013, 11, 27, 07, 00, 00);  // Format YYYY, MM, DD, HH, MM, SS -> ACHTUNG: Monat Januar = 0, Monat Dezember = 11 !!!
		this.startSimulationMillis = this.startSimulationCalendar.getTimeInMillis();
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		Time myTime = getInstance();
		
		Thread.sleep(10000);
		
		System.out.println(System.currentTimeMillis() - myTime.getStartWallClockMillis());
		
		Calendar currentSimulationCalendar = Calendar.getInstance();
		currentSimulationCalendar.setTimeInMillis(myTime.getStartSimulationMillis() + 5 * (System.currentTimeMillis() - myTime.getStartWallClockMillis()));
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss.SSS");
		System.out.println(format.format(new Date(currentSimulationCalendar.getTimeInMillis())));
		
		Calendar now = Calendar.getInstance();
		System.out.println(format.format(new Date(now.getTimeInMillis())));
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
	 * Returns the current system time.
	 * 
	 * @return the current system time
	 */
	private static String getWallClockTime()
	{
		return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSS").format(new Date());
	}
	
	/**
	 * Returns the current simulation time.
	 * 
	 * @return the current simulation time
	 */
	private static String getSimulationTime()
	{
		String wCT = getWallClockTime();
		int wCTYear = Integer.parseInt(wCT.substring(6, 10));
		int wCTMonth = Integer.parseInt(wCT.substring(3, 5));
		int wCTDay = Integer.parseInt(wCT.substring(0, 2));
		int wCTHour = Integer.parseInt(wCT.substring(11, 13));
		int wCTMinute = Integer.parseInt(wCT.substring(14, 16));
		int wCTSecond = Integer.parseInt(wCT.substring(17, 19));
		int wCTMilliSecond = Integer.parseInt(wCT.substring(20, 23));
		
		
		
		return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSS").format(new Date());
	}
}
