
package Location;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * 
 */

/**
 * @author mschaerer
 * 
 * Der Lagerort eines Szenarios, definiert die Gesamtgrösse eines Lagers
 * Der Lagerort ist einzigartig. Nur ein Lagerort kann existieren.
 * Der Lagerort wird in Gassen unterteilt.
 */
public class Location
{
	private static Location instance;
	private String locationID;
	private Hashtable<String, Gap> gapTable = new Hashtable<String, Gap>();
	private ArrayList<Gap> gapList = new ArrayList<Gap>();
	
	/**
	 * Returns an instance (object) of the class Location
	 * 
	 * @return the instance of this class
	 */
	public static Location getInstance()
	{
		if (instance == null)
		{
			instance = new Location("My Location");
		}
		
		return instance;
	}
	
	/**
	 * Creates a Location. The ID of the Location must be given.
	 * 
	 * @param id	die ID des Lagerortes
	 */
	private Location(String id)
	{
		this.locationID = id;
	}

	/**
	 * Returns the number of gaps allocated to the current Location
	 * 
	 * @return the number of gaps
	 */
	public int countGaps()
	{
		return gapList.size();
	}

	/**
	 * Adds a Gap to the current Location
	 * 
	 * @param gap	the gap to add
	 */
	public void addGap(Gap gap)
	{
		// In Hashtabelle
		gapTable.put(gap.getGapID(), gap);
		
		// In ArrayList
		gapList.add(gap);
	}
	
	/**
	 * Returns the list of gaps allocated to the current Location
	 * 
	 * @return the list of gaps
	 */
	public ArrayList<Gap> getGapList()
	{
		return gapList;
	}
	
	/**
	 * Returns the ID of the current Location
	 * 
	 * @return the locationID
	 */
	public String getLocationID()
	{
		return locationID;
	}
	
	/**
	 * Returns the Bin for the current binID
	 * 
	 * @param binID	the binID
	 * @return the Bin
	 */
	public Bin getBin(String binID)
	{
		int i = this.countGaps() - 1;
		int j;
		Gap gap = null;
		Grid grid = null;
		Bin bin = null;
		
		while ((i >= 0) && (bin == null))
		{
			gap = gapList.get(i);
			j = 1;
			while ((j >= 0) && (bin == null))
			{
				if (j == 1)
					// Rechtes Grid prüfen
					grid = gap.getGridLeft();
				else
					// Linkes Grid prüfen
					grid = gap.getGridRight();
				
				if (grid != null)
				{
					bin = grid.getBin(binID);
				}
				
				j--;
			}
			i--;
		}
		
		return bin;
	}
	
	/**
	 * Returns the Gap for the current gapID
	 * 
	 * @param gapID	the gapID
	 * @return the Gap
	 */
	public Gap getGap(String gapID)
	{	
		return gapTable.get(gapID);
	}
}
