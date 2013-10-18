
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
	 * Returns an instance (object) of the class Location
	 * 
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
	 * Creates a Location. The ID of the Location must be given.
	 * 
	 * @param id	die ID des Lagerortes
	 */
	private Location(int id)
	{
		this.locationID = id;
	}
	
	/**
	 * @return the next free locationID
	 */
	private static int getNextID()
	{
		return 1;
	}

	/**
	 * Returns the number of gaps allocated to the current Location
	 * 
	 * @return the number of gaps
	 */
	public int countGaps()
	{
		return gaps.size();
	}


	/**
	 * Adds a Gap to the current Location
	 * 
	 * @param gap	the gap to add
	 */
	public void addGap(Gap gap)
	{
		gaps.add(gap.getGapID(), gap);
	}
}
