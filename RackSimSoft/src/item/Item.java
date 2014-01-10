
package item;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * The item is the unit which can be stored in a bin.
 * The item is unique for its ID, the same ID only can exists once.
 */
public class Item
{
	private static Hashtable<String, Item> itemTable = new Hashtable<String, Item>();
	private static ArrayList<Item> itemList = new ArrayList<Item>();
	
	private String itemID;
	private String itemDescription;
	
	/**
	 * Creates an item. The ID of the item must be given.
	 * 
	 * @param itemID Identifier of the item
	 */
	private Item(String itemID)
	{
		this.itemID = itemID;
		this.itemDescription = "";
	}
	
	/**
	 * Returns the ID of the current item.
	 * 
	 * @return the itemID
	 */
	public String getItemID()
	{
		return this.itemID;
	}
	
	/**
	 * Returns the list of all known items.
	 * 
	 * @return the list of items
	 */
	public static ArrayList<Item> getItemList()
	{
		return itemList;
	}
	
	/**
	 * Returns the correct instance (object) of the class Item.
	 * 
	 * @return an instance of Item for this itemID
	 */
	public static Item getInstance(String itemID)
	{
		Item item = itemTable.get(itemID);
		if (item == null)
		{
			item = new Item(itemID);
			addItem(item);
		}
		
		return item;
	}
	
	/**
	 * Adds an Item to the static containers.
	 * 
	 * @param item	the item to add
	 */
	private static void addItem(Item item)
	{
		// To hash table
		itemTable.put(item.getItemID(), item);
		
		// To array list
		itemList.add(item);
	}

	/**
	 * Returns the description of the current item
	 * 
	 * @return the itemDescription
	 */
	public String getItemDescription()
	{
		return this.itemDescription;
	}
	
	/**
	 * Returns the description of the current item
	 * 
	 * @return the itemDescription
	 */
	public static String getItemDescription(String itemID)
	{
		String itemDescription = "";
		Item item = itemTable.get(itemID);
		if (item != null)
			itemDescription = item.getItemDescription();
		
		return itemDescription;
	}

	/**
	 * Sets the description of the current item
	 * 
	 * @param itemDescription the itemDescription to set
	 */
	public void setItemDescription(String itemDescription)
	{
		this.itemDescription = itemDescription;
	}
}