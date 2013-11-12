package location;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * @author mschaerer
 * 
 * The location defines the overall size of the storage unit. The location
 * is unique and can only exists once.
 * The location contains gaps.
 */
public class Location
{
	private static Location instance;
	private String locationID;
	private Hashtable<String, Gap> gapTable = new Hashtable<String, Gap>();
	private ArrayList<Gap> gapList = new ArrayList<Gap>();
	
	/**
	 * Returns an instance (object) of the class Location.
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
	 * Creates a Location. The ID of the location must be given.
	 * 
	 * @param id	ID of the location
	 */
	private Location(String locationID)
	{
		this.locationID = locationID;
	}

	/**
	 * Returns the number of gaps allocated to the current location.
	 * 
	 * @return the number of gaps
	 */
	public int countGaps()
	{
		return gapList.size();
	}

	/**
	 * Adds a Gap to the current location.
	 * 
	 * @param gap	the gap to add
	 */
	public void addGap(Gap gap)
	{
		// To hash table
		gapTable.put(gap.getGapID(), gap);
		
		// To array list
		gapList.add(gap);
	}
	
	/**
	 * Returns a copy of the list of gaps allocated to the current location.
	 * 
	 * @return the list of gaps
	 */
	public ArrayList<Gap> getGapListCopy()
	{
		ArrayList<Gap> gapListCopy = new ArrayList<Gap>();
		for (int i = 0; i < gapList.size(); i++)
		{
			gapListCopy.add(i, gapList.get(i));
		}
		
		return gapListCopy;
	}
	
	/**
	 * Returns the ID of the current location.
	 * 
	 * @return the locationID
	 */
	public String getLocationID()
	{
		return this.locationID;
	}
	
	/**
	 * Returns the Bin for the current binID.
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
					// Check right grid
					grid = gap.getGridLeft();
				else
					// Check left grid
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
	 * Returns the Gap for the current gapID.
	 * 
	 * @param gapID	the gapID
	 * @return the Gap
	 */
	public Gap getGap(String gapID)
	{	
		return gapTable.get(gapID);
	}
}
