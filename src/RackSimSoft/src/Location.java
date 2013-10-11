
import java.util.Vector;
import java.util.ArrayList;

/**
 * 
 */

/**
 * @author mschaerer
 * Der Lagerort eines Szenarios, definiert die Gesamtgrösse eines Lagers
 * Der Lagerort ist einzigartig. Nur ein Lagerort kann existieren.
 */
public class Location
{
	private static Location instance;
	private int locationID;
	private ArrayList<Gap> gaps = new ArrayList<Gap>();
	
	

	/**
	 * @return the instance of this class
	 */
	public static Location getInstance()
	{
		if (instance == null)
		{
			instance = new Location(getNextID());
		}
		
		return instance;
	}
	
	/**
	 * Kreiert einen Lagerort. Die ID des Lagerortes muss angegeben werden.
	 * 
	 * @param id	die ID des Lagerortes
	 */
	private Location(int id)
	{
		this.locationID = id;
	}
	
	/**
	 * @return	the next free locationID
	 */
	private static int getNextID()
	{
		return 1;
	}

	/**
	 * @return the number of gaps
	 */
	public int countGaps()
	{
		return gaps.size();
	}


	/**
	 * @param gap the gap to add
	 */
	public void addGap(Gap gap)
	{
		gaps.add(gap.getGapID(), gap);
	}
}
