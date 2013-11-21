
package location;

import item.ItemAllocation;

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
	public static enum MeasurementUnit
	{
		MM, CM, DM, M
	}
	
	private static Location instance;
	private String locationID;
	private MeasurementUnit measurementUnit = MeasurementUnit.MM;
	private int measurementUnitFactor = 1;
	private Hashtable<String, Gap> gapTable = new Hashtable<String, Gap>();
	private ArrayList<Gap> gapList = new ArrayList<Gap>();
	private ItemAllocation itemAllocation;
	
	/**
	 * Returns an instance (object) of the class Location.
	 * 
	 * @return the instance of this class
	 */
	public static Location getInstance(String locationID, MeasurementUnit measurementUnit)
	{
		if (instance == null)
		{
			instance = new Location(locationID, measurementUnit);
			instance.itemAllocation = ItemAllocation.getInstance();
		}
		
		return instance;
	}
	
	/**
	 * Creates a Location. The ID of the location and the measurement unit must be given.
	 * 
	 * @param id	ID of the location
	 * @param measurementUnit	The measurement unit of the location
	 */
	private Location(String locationID, MeasurementUnit measurementUnit)
	{
		this.locationID = locationID;
		this.measurementUnit = measurementUnit;
		
		switch (this.getMeasurementUnit())
		{
			case MM :
				this.measurementUnitFactor = 1;
				break;
				
			case CM :
				this.measurementUnitFactor = 10;
				break;
				
			case DM :
				this.measurementUnitFactor = 100;
				break;
				
			case M :
				this.measurementUnitFactor = 1000;
				break;
		}
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
	 * @return a copy of the list of gaps
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
	 * Returns a list of bins allocated to the current location.
	 * 
	 * @return the list of bins
	 */
	public ArrayList<Bin> getBinList()
	{
		ArrayList<Bin> binList = new ArrayList<Bin>();
		Grid grid;
		for (Gap gap : this.gapList)
		{
			grid = gap.getGridLeft();
			for (Bin[] binArray : grid.getBinArray())
			{
				for (Bin bin : binArray)
				{
					binList.add(bin);
				}
			}
			
			grid = gap.getGridRight();
			for (Bin[] binArray : grid.getBinArray())
			{
				for (Bin bin : binArray)
				{
					binList.add(bin);
				}
			}
		}
		
		return binList;
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
	 * Returns the MeasurementUnit of the current location.
	 * 
	 * @return the measurement unit
	 */
	public MeasurementUnit getMeasurementUnit()
	{
		return this.measurementUnit;
	}
	
	/**
	 * Returns the MeasurementUnit of the current location as a String.
	 * 
	 * @return the measurement unit string
	 */
	public String getMeasurementUnitString()
	{
		String measurementUnitString = null;
		
		switch (this.getMeasurementUnit())
		{
			case MM :
				measurementUnitString = "mm";
				break;
				
			case CM :
				measurementUnitString = "cm";
				break;
				
			case DM :
				measurementUnitString = "dm";
				break;
				
			case M :
				measurementUnitString = "m";
				break;
		}
		
		return measurementUnitString;
	}
	
	/**
	 * Returns the factor of measurement for the current location.
	 * 
	 * @return the measurement unit factor
	 */
	public int getMeasurementUnitFactor()
	{
		return this.measurementUnitFactor;
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

	/**
	 * Returns the ItemAllocation of this Location
	 * 
	 * @return the itemAllocation
	 */
	public ItemAllocation getItemAllocation()
	{
		return itemAllocation;
	}
	
	/**
	 * Returns the list of Bin for the current itemID.
	 * 
	 * @param itemID	the itemID
	 * @return the list of Bin
	 */
	public ArrayList<Bin> getBinList(String itemID)
	{
		ItemAllocation itemAllocation = this.getItemAllocation();
		return itemAllocation.getBinListCopy(itemID);
	}
}
