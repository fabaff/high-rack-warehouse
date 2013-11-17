
package test;

import gui2D.BinComponent;
import gui2D.GridComponent;
import gui2D.MainFrame;
import item.Item;
import item.ItemAllocation;
import location.*;
import calculation.*;

import java.util.ArrayList;
import java.util.Random;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

//import java.util.Hashtable;
//import java.util.Map.Entry;
//import java.util.Set;

/**
 * This is a basic tester for the creation of a storage location.
 */
public class CreateLocation
{
	private static int itemCounter = 20;
	
	public static void main(String[] args)
	{		
		System.out.println();
		System.out.println("-------------------------------------------------");
		
		// Creates some items
		ArrayList<Item> itemList = createItems();
		System.out.println(itemCounter + " items were created...");
		System.out.println("-------------------------------------------------");
		
		// Creates a location
		Location myLocation = createLocation();
		System.out.println("Location and ItemAllocation were created...");
		System.out.println("-------------------------------------------------");
		
		// Adds items to location
		addItems(myLocation, itemList);
		System.out.println("Items were added...");
		System.out.println("-------------------------------------------------");
		
		// Prints item allocation
		System.out.println("Folgende Artikel sind in diesen Lagerplaetzen eingelagert:");
		printItems(myLocation);
		System.out.println("-------------------------------------------------");
		
		// Prints the location
		printLocation(myLocation);
		System.out.println("-------------------------------------------------");

		// Gets predefined bin and prints their coordinates
		System.out.println("Die Koordinaten der folgenden Lagerplaetzes sind:");
		printBin(myLocation, "0-0-0-2-0");
		printBin(myLocation, "0-1-1-2-1");
		printBin(myLocation, "1-0-2-3-1");
		printBin(myLocation, "1-1-3-2-0");
		System.out.println("-------------------------------------------------");

		// Prints some calculated times
		System.out.println("Abstaende:");
		printDistance(myLocation, "0-0-0-2-0", "0-0-0-3-1");
		
		System.out.println("-------------------------------------------------");
		
		// Prints some calculated times
		System.out.println("Zeiten:");
		printTime(myLocation, "0-0-0-0-0", "0-0-0-2-0");
		printTime(myLocation, "0-0-0-0-0", "0-0-0-3-1");
		printTime(myLocation, "0-0-0-2-0", "0-0-0-3-1");

		
		System.out.println("-------------------------------------------------");
		
		// Creates GUI
		//createAndShowGui(myLocation);
	}
		
	/**
	 * Create a list of items
	 */
	private static ArrayList<Item> createItems()
	{
		ArrayList<Item> itemList = new ArrayList<Item>();
		Item item;
		
		for (int i = 0; i <= itemCounter; i++)
		{
			// Create item
			item = Item.getInstance("Item " + i);
			
			// Create random item description
			//---------------------
			char[] symbols = new char[36];
			for (int idx = 0; idx < 10; ++idx)
			{
				symbols[idx] = (char) ('0' + idx);
			}
				
			for (int idx = 10; idx < 36; ++idx)
			{
				symbols[idx] = (char) ('a' + idx - 10);
			}
			
			Random random = new Random();
			char[] buf;
			int length = 15;
			buf = new char[length];

			for (int idx = 0; idx < buf.length; ++idx)
			{
				buf[idx] = symbols[random.nextInt(symbols.length)];
			}
			//---------------------
			
			// Set item description
			item.setItemDescription(new String(buf));
			
			itemList.add(item);
		}
		
		return itemList;
	}
	
	/**
	 * Create a location to work with.
	 */
	private static Location createLocation()
	{
		// Creates a location
		Location myLocation = Location.getInstance();	
		
		// Simple model: 
		int gapCount = 2;  // Anzahl Gassen
		int columnCounter = 4;  // Anzahl Spalten pro Grid
		int rowCounter = 2;  // Anzahl Reihen pro Grid
		int width = 1000;  // Offset X-Koordinate (Breite der Gassen)
		int depth = 800;  // Tiefe des Grids
		int height = 1000;  // Offset Z-Koordinate (Hoehe der Reihen)
		int length = 1500;  // Offset Y-Koordinate (Breite der Spalten)
		
		
		// Komplizierteres Modell:
		/*
		int gapCount = 4;  // Anzahl Gassen
		int columnCounter = 7;  // Anzahl Spalten pro Grid
		int rowCounter = 30;  // Anzahl Reihen pro Grid
		int width = 1000;  // Offset X-Koordinate (Breite der Gassen)
		int depth = 600;  // Tiefe des Grids
		int height = 1000;  // Offset Z-Koordinate (Hoehe der Reihen)
		int length = 1500;  // Offset Y-Koordinate (Breite der Spalten)
		*/
		
		int gridID = 0;
		
		// Creates gaps
		for (int i = 0; i < gapCount; i++)
		{
			Gap gap = new Gap("" + i, width, (i * 2 * depth) + depth + (i * width));  // id, X-coordinate
		
			// Creates grids
			for (int j = 0; j < 2; j++)
			{
				Row rowArray[] = new Row[rowCounter];
				Column columnArray[] = new Column[columnCounter];
				
				// Filling the arrays with width and height
				for (int k = 0; k < columnArray.length; k++)
				{					
					columnArray[k] = new Column("" + k, length, k * length);  // id, width, y coordinate
				}
				
				for (int k = 0; k < rowArray.length; k++)
				{					
					rowArray[k] = new Row("" + k, height, k * height);  // id, height, z coordinate
				}
				
				// Creates grid and assign it to a gap
				Grid grid = new Grid("" + gridID, gap, j % 2, columnArray, rowArray);
				gap.addGrid(grid);
				gridID ++;
			}
			
			// Assign gap (with assigned grids) to the location
			myLocation.addGap(gap);
		}
		
		return myLocation;
	}
	
	/**
	 * Adds items to some of the bins.
	 * 
	 * @param location the location to add the items to
	 * @param itemList a list with the items to add
	 */
	private static void addItems(Location myLocation, ArrayList<Item> itemList)
	{
		Item item;
		ItemAllocation itemAllocation = myLocation.getItemAllocation();
		ArrayList<Bin> binList = myLocation.getBinList();
		Bin bin;
		
		// Add items to Bin
		Random random = new Random();
		int counter = random.nextInt(binList.size());
		for (int i = 0; i <= counter; i ++)
		{
			bin = binList.get(random.nextInt(binList.size()));
			item = itemList.get(random.nextInt(itemList.size()));
			itemAllocation.addItem(item, bin);
		}
	}
	
	/**
	 * Prints the allocated Bin to a the specified Items
	 * 
	 * @param location  the location
	 */
	private static void printItems(Location location)
	{
		String itemID;
		String binString;
		
		for (int i = 1; i <= itemCounter; i++)
		{
			itemID = "Item " + i;
			binString = getBinString(location, itemID);
			System.out.println("Artikel " + itemID + " (" + Item.getItemDescription(itemID) + ") : " + binString);
		}
	}
	
	/**
	 * Returns the allocated Bin to a specific Item ID
	 * 
	 * @param location  the location
	 * @param itemID	the itemID of the item
	 * @return binID	the binID
	 */
	private static String getBinString(Location location, String itemID)
	{
		ArrayList<Bin> bins = location.getBinList(itemID);
		String binString = "";
		
		for (Bin bin : bins)
		{
			binString += (bin.getBinID() + ", ");
		}
		
		if (bins.size() > 0)
			binString = binString.substring(0, binString.length() - 2);
		
		return binString;
	}
	
	/**
	 * Prints out all relevant elements of a location.
	 * 
	 * @param location the location to display
	 */
	private static void printLocation(Location myLocation)
	{
		// Load ItemAllocation
		ItemAllocation itemAllocation = myLocation.getItemAllocation();
		Item item;
		RackFeeder rackFeeder;
		
		System.out.println("(Bin ID = [gapID]-[gridSide]-[gridID]-[columnID]-[rowID])");
		System.out.println("\nLocation:\nID = " + myLocation.getLocationID());
		System.out.println("Anzahl Gassen: " + myLocation.countGaps());
		
		// Load all gaps
		ArrayList<Gap> gaps = myLocation.getGapListCopy();
		
		// Load the grids of the gap
		for(Gap gap : gaps)
		{
			// Get rack feeder
			rackFeeder = gap.getRackFeeder();
			
			System.out.println();
			System.out.println("\tGap:");
			System.out.println("\tID = " + gap.getGapID());
			System.out.println("\tX-Koordinate = " + gap.getXCoordinate());
			System.out.println("\tBreite = " + gap.getWidth());
			System.out.println();
			System.out.println("\tZugehoeriges RBG:");
			System.out.println("\tID = " + rackFeeder.getrackFeederID());
			System.out.println("\tX-Koordinate = " + rackFeeder.getXCoordinate());
			System.out.println("\tY-Koordinate = " + rackFeeder.getYCoordinate());
			System.out.println("\tZ-Koordinate = " + rackFeeder.getZCoordinate());
			System.out.println("\tArtikel auf RBG = " + rackFeeder.getItem());
			//Hashtable<String, Bin> binTable;
			
			Bin binArray[][];
			Grid grid;
			
			// Left grid
			grid = gap.getGridLeft();
			if (grid != null)
			{
				System.out.println("\n\t\tGrid Links:\n\t\tID = " + grid.getGridID() + "\n\t\tgridSide = " + grid.getGridSide() + "\n\t\tBreite = " + grid.getWidth() + "\n\t\tHoehe = " + grid.getHeight());
				
				// Loop over all bins in the grid
				Bin bin;
				
				/*
				// Via Hashtabelle
				binTable = grid.getBinTable();
				Set<Entry<String, Bin>> entrySet = binTable.entrySet();
				for(Entry<String, Bin> set : entrySet)
				{
					bin = set.getValue();
					System.out.println("\n\t\t\tBin:\n\t\t\tID = " + bin.getBinID() + "\n\t\t\tKoordinaten (X/Y/Z) = " + bin.getX() + "/" + bin.getY() + "/" + bin.getZ());
				}
				*/
				
				// Via Array
				binArray = grid.getBinArray();
				for(int i = 0; i < binArray.length; i++)
				{
					for(int j = 0; j < binArray[i].length; j++)
					{
						bin = binArray[i][j];
						item = itemAllocation.getItem(bin.getBinID());
						System.out.println();
						System.out.println("\t\t\tBin:");
						System.out.println("\t\t\tID = " + bin.getBinID());
						System.out.println("\t\t\tKoordinaten (X/Y/Z) = " + bin.getX() + "/" + bin.getY() + "/" + bin.getZ());
						if (item != null)
							System.out.println("\t\t\tArtikel: " + item.getItemID() + " (" + item.getItemDescription() + ")");
					}	
				}
			}
			
			// Grid right
			grid = gap.getGridRight();
			if (grid != null)
			{
				System.out.println("\n\t\tGrid Rechts:\n\t\tID = " + grid.getGridID() + "\n\t\tgridSide = " + grid.getGridSide() + "\n\t\tBreite = " + grid.getWidth() + "\n\t\tHoehe = " + grid.getHeight());
				
				// Looping over the bin in the gap
				Bin bin;
				
				/*
				// Via Hashtabelle
				binTable = grid.getBinTable();
				Set<Entry<String, Bin>> entrySet = binTable.entrySet();
				for(Entry<String, Bin> set : entrySet)
				{
					bin = set.getValue();
					System.out.println("\n\t\t\tBin:\n\t\t\tID = " + bin.getBinID() + "\n\t\t\tKoordinaten (X/Y/Z) = " + bin.getX() + "/" + bin.getY() + "/" + bin.getZ());
				}
				*/
				
				// Via Array
				binArray = grid.getBinArray();
				for(int i = 0; i < binArray.length; i++)
				{
					for(int j = 0; j < binArray[i].length; j++)
					{
						bin = binArray[i][j];
						item = itemAllocation.getItem(bin.getBinID());
						System.out.println();
						System.out.println("\t\t\tBin:");
						System.out.println("\t\t\tID = " + bin.getBinID());
						System.out.println("\t\t\tKoordinaten (X/Y/Z) = " + bin.getX() + "/" + bin.getY() + "/" + bin.getZ());
						if (item != null)
							System.out.println("\t\t\tArtikel: " + item.getItemID() + " (" + item.getItemDescription() + ")");
					}	
				}
			}
		}
	}
	
	/**
	 * Takes the binID and give back the xyz coordinates.
	 * 
	 * @param location  the location
	 * @param binID		the binID of the bin to show
	 */
	private static void printBin(Location location, String binID)
	{
		Bin bin;
		bin = location.getBin(binID);
		System.out.println(binID + " : " + bin.getX() + "/" + bin.getY() + "/" + bin.getZ());
	}
	
	/**
	 * Takes two binIDs and calculates the distances between those bins.
	 * 
	 * @param location  the location
	 * @param binID1	the binID of the bin to start
	 * @param binID2	the binID of the bin to go
	 */
	private static void printDistance(Location location, String binID1, String binID2)
	{
		Distance distance = new Distance();
		
		Bin bin1;
		Bin bin2;
		bin1 = location.getBin(binID1);
		bin2 = location.getBin(binID2);
		System.out.print(binID1 + " : " + bin1.getX() + "/" + bin1.getY() + "/" + bin1.getZ() + " --- ");
		System.out.println(binID2 + " : " + bin2.getX() + "/" + bin2.getY() + "/" + bin2.getZ());
		
		double zeroDistance1 = distance.zeroDistance(location, binID1);
		System.out.println(binID1 + " zu 0/0:\t" + zeroDistance1);

		double zeroDistance2 = distance.zeroDistance(location, binID2);
		System.out.println(binID2 + " zu 0/0:\t" + zeroDistance2);
		
		int xDistance = distance.xDistance(location, bin1.getgapID(), binID1);
		System.out.println("x-Distanz:\t" + xDistance + " (bis in die Mitte der Gasse)");
		
		int yDistance = distance.yDistance(location, binID1, binID2);
		System.out.println("y-Distanz:\t" + yDistance);
		
		int zDistance = distance.zDistance(location, binID1, binID2);
		System.out.println("z-Distanz:\t" + zDistance);
		
		double mDistance = distance.mDistance(location, binID1, binID2);
		System.out.println("Distanz:\t" + mDistance);
	}
	
	/**
	 * Prints the needed time for putting good into their assigned bin.
	 * 
	 * @param location  the location
	 * @param binID	the binID of the bin to place the good
	 */
	private static void printTime(Location location, String binID1, String binID2)
	{
		Movement movement = new Movement(2.0, 2.0, 2.0, 2.0, 0.5, 0.5);
		double cTime = movement.cTime(location, binID1, binID2);
		
		System.out.println("Kompl. Zyklus:\t\t" + cTime);
	}
	
	/**
	 * Creates the GUI for the given location.
	 * 
	 * @param location  the location
	 */	
	private static void createAndShowGui(Location myLocation)
	{
		double guiCoordinateFactor = 0.08;
		
		// Creates windows
		MainFrame frame = new MainFrame();
		
		// Creates menu
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menu = new JMenu("Datei");
		menu.setMnemonic(KeyEvent.VK_D);
		
		// Creates menuitem
		JMenuItem menuItem = new JMenuItem("Was auch immer...", KeyEvent.VK_T);
		// Access with Alt + 1
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		
		// Put menuitem in the menu
		menu.add(menuItem);
		
		// Put menu on menubar
		menuBar.add(menu);
		
		// Put menubar on frame
		frame.setJMenuBar(menuBar);
			
		// Creates MainPane
		Container pane = frame.getContentPane();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 20;
		c.ipady = 20;
		
		JLayeredPane layeredPane = null;
		GridComponent gridComponent = null;
		BinComponent binComponent = null;
		
		// Active gap should be gap 1
		// Get gap 1 and its grids
		Gap gap = myLocation.getGap("1");
		Grid grid;
		Bin binArray[][];
		int xOffset = 20;
		int yOffset = 20;
		
		// Add to the GridLayout (3 columns, 2 rows) for every field a
		// LayeredPane. Two contains a grid (from Pkg location gui2D)
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 2; j++)
			{
				c.gridx = i;
				c.gridy = j;
				c.weightx = 1;
				c.weighty = 1;
				
				layeredPane = new JLayeredPane();
				layeredPane.setPreferredSize(new Dimension(50, 40));
		        layeredPane.setBorder(BorderFactory.createTitledBorder("This is a layered Pane"));
		        
		        // Creates grid and puts it on LayeredPane as layer 0
		        if ((j == 0) && ((i == 0) || (i == 2)))
		        {
		        	// Get the correct grid (left or right)
		        	if (i == 0)
		        	{
		        		grid = gap.getGridLeft();
		        	}
		        	else
		        	{
		        		grid = gap.getGridRight();
		        	}
		        	
			        gridComponent = new GridComponent(grid, guiCoordinateFactor);
			        gridComponent.setBounds(xOffset, yOffset, gridComponent.getWidth(), gridComponent.getHeight());
			        layeredPane.add(gridComponent, 0);
			        
			        // Creates bins and put them on LayeredPane as layer 1
			        binArray = grid.getBinArray();
			        for (Bin[] bins : binArray)
			        {
			        	for (Bin bin : bins)
				        {
				        	System.out.println(bin.getBinID());
			        		binComponent = new BinComponent(bin, guiCoordinateFactor);
					        binComponent.setBounds(xOffset + binComponent.getYOffset(), yOffset + binComponent.getZOffset(), binComponent.getWidth(), binComponent.getHeight());
					        System.out.println("BinID: " + bin.getBinID() + ", Y-Offset: " + binComponent.getYOffset() + ", Z-Offset: " + binComponent.getZOffset() +
					        		           ", Breite: " + binComponent.getWidth() + ", Hoehe: " + binComponent.getHeight());
					        layeredPane.add(binComponent, 1);
				        }
			        }
		        }
		        
		        // Put layer on MainPane
		        pane.add(layeredPane, c);
			}
		}
		
		// Display GUI
		frame.pack();
		frame.setVisible(true);
	}
}
