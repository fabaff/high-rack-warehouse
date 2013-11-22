
package item;

import location.Bin;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * @author mschaerer
 * 
 * The itemallocation holds the information, which item is stored in which bin.
 * The itemallocation is unique and can only exists once.
 */
public class ItemAllocation
{
	private static ItemAllocation instance;
	private String itemAllocationID;
	private Hashtable<String, ArrayList<Bin>> binListTable = new Hashtable<String, ArrayList<Bin>>();
	private Hashtable<String, Item> itemTable = new Hashtable<String, Item>();
	
	/**
	 * Returns an instance (object) of the class ItemAllocation.
	 * 
	 * @return the instance of this class
	 */
	public static ItemAllocation getInstance()
	{
		if (instance == null)
		{
			instance = new ItemAllocation("My Allocation");
		}
		
		return instance;
	}
	
	/**
	 * Creates an ItemAllocation. The ID of the itemAllocation must be given.
	 * 
	 * @param id	ID of the itemAllocation
	 */
	private ItemAllocation(String itemAllocationID)
	{
		this.itemAllocationID = itemAllocationID;
	}
	
	/**
	 * Returns the ID of the current ItemAllocation.
	 * 
	 * @return the itemAllocationID
	 */
	public String getItemAllocationID()
	{
		return this.itemAllocationID;
	}
	
	/**
	 * Adds an Item to the current Bin.
	 * 
	 * @param item	the item to add
	 * @param bin	the bin to add the item to
	 */
	public void addItem(Item item, Bin bin)
	{
		// To hash table for fast access with itemID
		// Must be saved as list since the same item can be stored in multiple bins
		ArrayList<Bin> binList = getBinList(item.getItemID());
		if (binList == null)
		{
			// For the first bin
			binList = new ArrayList<Bin>();
		}
		binList.add(bin);
		this.binListTable.put(item.getItemID(), binList);
		
		// To hash table for fast access with binID
		itemTable.put(bin.getBinID(), item);
	}
	
	/**
	 * Removes the Item from the current Bin.
	 * 
	 * @param bin	the bin to remove the item from
	 * @return the removed item
	 */
	public Item removeItem(Bin bin)
	{
		// Remove from the hash table for fast access with binID
		Item item = itemTable.remove(bin.getBinID());
		
		// Remove from the hash table for fast access with itemID
		if (item != null)
		{
			// Get list of bins storing this item
			ArrayList<Bin> binList = getBinList(item.getItemID());
			String currentBinID = bin.getBinID();
			Bin currentBin;
			int i = binList.size() - 1;
			
			// Remove bin from this list
			do
			{
				currentBin = binList.get(i);
				
				if (currentBinID == currentBin.getBinID())
				{
					binList.remove(i);
					i = 0;
				}
				
				i--;
			} while (i >= 0);
		}
		
		return item;
	}
	
	/**
	 * Returns the list of Bin for the current itemID.
	 * 
	 * @param itemID	the itemID
	 * @return the list of Bin
	 */
	private ArrayList<Bin> getBinList(String itemID)
	{
		return this.binListTable.get(itemID);
	}
	
	/**
	 * Returns a copy of the list of Bin for the current itemID.
	 * 
	 * @param itemID	the itemID
	 * @return the list of Bin
	 */
	public ArrayList<Bin> getBinListCopy(String itemID)
	{
		ArrayList<Bin> binListOriginal = this.getBinList(itemID);
		ArrayList<Bin> binListCopy = new ArrayList<Bin>();
		
		// Wurde der Artikel gefunden?
		if (binListOriginal != null)
		{
			for (int i = 0; i < binListOriginal.size(); i++)
			{
				binListCopy.add(i, binListOriginal.get(i));
			}
		}
		
		return binListCopy;
	}
	
	/**
	 * Returns the Item stored in the current binID.
	 * 
	 * @param binID	the binID
	 * @return the Item
	 */
	public Item getItem(String binID)
	{	
		return this.itemTable.get(binID);
	}
}
