
package test;



import java.util.ArrayList;
import java.util.Random;

import job.InStoreJob;
import job.Job;
import job.OutStoreJob;
import simulation.Simulation;
import simulation.Simulation.SimulationType;
import state.RackFeederState.Behavior;
import item.Item;
import item.ItemAllocation;
import location.*;
import calculation.*;
import event.Event;
import event.EventList;

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
		System.out.println("Die Koordinaten der folgenden Lagerplaetze sind (X/Y/Z/U):");
		printBin(myLocation, "0-0-0-2-0");
		printBin(myLocation, "0-1-1-2-1");
		printBin(myLocation, "1-0-2-3-1");
		printBin(myLocation, "1-1-3-2-0");
		System.out.println("-------------------------------------------------");

		// Prints some calculated times
		System.out.println("Abstaende:");
		printDistance(myLocation, "0-0-0-3-1", "0-0-0-2-0");
		System.out.println("-------------------------------------------------");
		
		// Print some moves of rack feeder
		System.out.println("Zeiten zum fahren des RBG:");
		printMove(myLocation, "0", "0-0-0-2-1");  // Location, GapID, BinID
		printMove(myLocation, "1", "1-1-3-2-0");  // Location, GapID, BinID
		System.out.println("-------------------------------------------------");
		
		// Prints a complete cycle
		System.out.println("Ein kompletter Zyklus:");
		System.out.println();
		printCycle(myLocation, "0", "0-1-1-1-1");  // Location, GapID, BinID
		
		// Simulates a complete cycle
		try
		{
			simulateCycle();
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Creates GUI
		//GUI.createAndShowGui(myLocation);
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
		// Simple model:
		int gapCount = 2;  // Anzahl Gassen
		int columnCounter = 4;  // Anzahl Spalten pro Grid
		int rowCounter = 2;  // Anzahl Reihen pro Grid
		Location.MeasurementUnit measurementUnit = Location.MeasurementUnit.MM;	// Possible values = "MM", "CM", "DM", "M"
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
		
		// Creates a location
		Location myLocation = Location.getInstance("My Location", measurementUnit);
		
		int gridID = 0;
		
		// Creates gaps
		for (int i = 0; i < gapCount; i++)
		{
			Gap gap = new Gap("" + i, width, (i * 2 * depth) + depth + (i * width));  // id, width, X-coordinate
		
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
				Grid grid = new Grid("" + gridID, gap, j % 2, columnArray, rowArray, depth);
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
			System.out.println("\tBreite = " + gap.getXSize());
			System.out.println();
			System.out.println("\tZugehoeriges RBG:");
			System.out.println("\tID = " + rackFeeder.getRackFeederID());
			System.out.println("\tX-Koordinate = " + rackFeeder.getX());
			System.out.println("\tY-Koordinate = " + rackFeeder.getY());
			System.out.println("\tZ-Koordinate = " + rackFeeder.getZ());
			System.out.println("\tU-Koordinate = " + rackFeeder.getU());
			System.out.println("\tX-Speed / -Beschl. / -Reduktion = " + rackFeeder.getXSpeed() + "/" + rackFeeder.getXAcceleration() + "/" + rackFeeder.getXDeceleration());
			System.out.println("\tY-Speed / -Beschl. / -Reduktion = " + rackFeeder.getYSpeed() + "/" + rackFeeder.getYAcceleration() + "/" + rackFeeder.getYDeceleration());
			System.out.println("\tZ-Speed / -Beschl. / -Reduktion = " + rackFeeder.getZSpeed() + "/" + rackFeeder.getZAcceleration() + "/" + rackFeeder.getZDeceleration());
			System.out.println("\tU-Speed / -Beschl. / -Reduktion = " + rackFeeder.getUSpeed() + "/" + rackFeeder.getUAcceleration() + "/" + rackFeeder.getUDeceleration());
			System.out.println("\tArtikel auf RBG = " + rackFeeder.getItem());
			//Hashtable<String, Bin> binTable;
			
			Bin binArray[][];
			Grid grid;
			
			// Left grid
			grid = gap.getGridLeft();
			if (grid != null)
			{
				System.out.println("\n\t\tGrid Links:\n\t\tID = " + grid.getGridID() + "\n\t\tgridSide = " + grid.getGridSide() + "\n\t\tBreite = " + grid.getYSize() + "\n\t\tHoehe = " + grid.getZSize());
				
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
						System.out.println("\t\t\tKoordinaten (X/Y/Z/U) = " + bin.getX() + "/" + bin.getY() + "/" + bin.getZ() + "/" + bin.getU());
						if (item != null)
							System.out.println("\t\t\tArtikel: " + item.getItemID() + " (" + item.getItemDescription() + ")");
					}	
				}
			}
			
			// Grid right
			grid = gap.getGridRight();
			if (grid != null)
			{
				System.out.println("\n\t\tGrid Rechts:\n\t\tID = " + grid.getGridID() + "\n\t\tgridSide = " + grid.getGridSide() + "\n\t\tBreite = " + grid.getYSize() + "\n\t\tHoehe = " + grid.getZSize());
				
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
						System.out.println("\t\t\tKoordinaten (X/Y/Z/U) = " + bin.getX() + "/" + bin.getY() + "/" + bin.getZ() + "/" + bin.getU());
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
		System.out.println(binID + " : " + bin.getX() + "/" + bin.getY() + "/" + bin.getZ() + "/" + bin.getU());
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
		Bin bin1;
		Bin bin2;
		bin1 = location.getBin(binID1);
		bin2 = location.getBin(binID2);
		System.out.print(binID1 + " : " + bin1.getX() + "/" + bin1.getY() + "/" + bin1.getZ() + "/" + bin1.getU() + " --- ");
		System.out.println(binID2 + " : " + bin2.getX() + "/" + bin2.getY() + "/" + bin2.getZ() + "/" + bin2.getU());
		
		Coordinate zeroCoordinate = new Coordinate(bin1.getX(), 0, 0, 0);
		Distance distance;
		int distanceLength;
		int coordinateDistance;
		
		distance = new Distance(bin1.getCoordinate(), zeroCoordinate);
		distanceLength = distance.getDistanceLength("0110");
		System.out.println(binID1 + " zu X/0/0/U:\t" + distanceLength);

		distance = new Distance(bin2.getCoordinate(), zeroCoordinate);
		distanceLength = distance.getDistanceLength("0110");
		System.out.println(binID2 + " zu X/0/0/U:\t" + distanceLength);
		
		/*
		int xDistance = distance.getXDistance(location, bin1.getgapID(), binID1);
		System.out.println("x-Distanz:\t" + xDistance + " (bis in die Mitte der Gasse)");
		*/
		
		distance = new Distance(bin1.getCoordinate(), bin2.getCoordinate());
		coordinateDistance = distance.getXDistance();
		System.out.println("X-Distanz:\t" + coordinateDistance);
		coordinateDistance = distance.getYDistance();
		System.out.println("Y-Distanz:\t" + coordinateDistance);
		coordinateDistance = distance.getZDistance();
		System.out.println("Z-Distanz:\t" + coordinateDistance);
		coordinateDistance = distance.getUDistance();
		System.out.println("U-Distanz:\t" + coordinateDistance);
		
		/*
		double mDistance = distance.getMDistance(location, binID1, binID2);
		System.out.println("Distanz:\t" + mDistance);
		*/
	}
	
	/**
	 * Prints the needed time for putting good into their assigned bin.
	 * 
	 * @param location  the location
	 * @param binID	the binID of the bin to place the good
	 */
	/*
	private static void printTime(Location location, String binID1, String binID2)
	{
		Movement movement = new Movement(2.0, 2.0, 2.0, 2.0, 0.5, 0.5);
		double cTime = movement.cTime(location, binID1, binID2);
		
		System.out.println("Kompl. Zyklus:\t\t" + cTime);
	}
	*/
	
	private static void printMove(Location myLocation, String gapID, String binID)
	{
		RackFeeder rackFeeder = myLocation.getGap(gapID).getRackFeeder();
		Bin bin = myLocation.getBin(binID);
		Distance distance = new Distance(rackFeeder.getCoordinate(), bin.getCoordinate());
		Movement movement = new Movement(distance, rackFeeder);
		String direction;
		
		direction = "0110";  // nur YZ von "XYZU"
		System.out.println("Rack Feeder " + rackFeeder.getRackFeederID());
		System.out.println("Bin " + bin.getBinID());
		System.out.println("Direction: " + direction);
		System.out.println("Strecke: " + distance.getDistanceLength(direction));
		System.out.println("Zeit (linear): " + movement.getLinearTime(direction));
		System.out.println("Zeit (in echt): " + movement.prepareForMove(direction));
		// Die Bechleunigungen und Geschwindigkeiten wieder reseten, da diese in der Funktion geaendert wurden
		rackFeeder.setMax();
		System.out.println();
		
		direction = "0010";  // nur Z von "XYZU"
		System.out.println("Rack Feeder " + rackFeeder.getRackFeederID());
		System.out.println("Bin " + bin.getBinID());
		System.out.println("Direction: " + direction);
		System.out.println("Strecke: " + distance.getDistanceLength(direction));
		System.out.println("Zeit (linear): " + movement.getLinearTime(direction));
		System.out.println("Zeit (in echt): " + movement.prepareForMove(direction));
		// Die Bechleunigungen und Geschwindigkeiten wieder reseten, da diese in der Funktion geaendert wurden
		rackFeeder.setMax();
		System.out.println();
				
		direction = "1000";  // nur X von "XYZU"
		System.out.println("Rack Feeder " + rackFeeder.getRackFeederID());
		System.out.println("Bin " + bin.getBinID());
		System.out.println("Direction: " + direction);
		System.out.println("Strecke: " + distance.getDistanceLength(direction));
		System.out.println("Zeit (linear): " + movement.getLinearTime(direction));
		System.out.println("Zeit (in echt): " + movement.prepareForMove(direction));
		// Die Bechleunigungen und Geschwindigkeiten wieder reseten, da diese in der Funktion geaendert wurden
		rackFeeder.setMax();
		System.out.println();
				
		direction = "0001";  // nur U von "XYZU"
		System.out.println("Rack Feeder " + rackFeeder.getRackFeederID());
		System.out.println("Bin " + bin.getBinID());
		System.out.println("Direction: " + direction);
		System.out.println("Strecke: " + distance.getDistanceLength(direction));
		System.out.println("Zeit (linear): " + movement.getLinearTime(direction));
		System.out.println("Zeit (in echt): " + movement.prepareForMove(direction));
		// Die Bechleunigungen und Geschwindigkeiten wieder reseten, da diese in der Funktion geaendert wurden
		rackFeeder.setMax();
		System.out.println();
	}
	
	private static void printCycle(Location myLocation, String gapID, String binID)
	{
		RackFeeder rackFeeder = myLocation.getGap(gapID).getRackFeeder();
		Bin bin = myLocation.getBin(binID);
		Distance distance;
		Movement movement;
		Coordinate coordinate;
		String direction;
		String itemID;
		Item item;
		
		System.out.println("RBG ist auf Position " + rackFeeder.getCoordinate().toString());
		item = rackFeeder.getItem();
		if (item != null)
			itemID = item.getItemID();
		else
			itemID = "<leer>";
		System.out.println("Artikel auf dem RBG: " + itemID);
		
		System.out.println("Bin ist auf Position " + bin.getCoordinate().toString());
		item = myLocation.getItem(bin.getBinID());
		if (item != null)
			itemID = item.getItemID();
		else
			itemID = "<leer>";
		System.out.println("Artikel im Bin: " + itemID);
		System.out.println();
		
		System.out.println("Fahre RBG bis vor das Bin:");
		distance = new Distance(rackFeeder.getCoordinate(), bin.getCoordinate());
		movement = new Movement(distance, rackFeeder);
		direction = "0110";
		System.out.println("Distanz linear (mm): " + distance.getDistanceLength(direction));
		System.out.println("Zeit linear (ms): " + movement.getLinearTime(direction));
		System.out.println("Geschwindigkeiten linear (mm/ms) in X/Y/Z/U: " + rackFeeder.getXSpeed() + "/" + rackFeeder.getYSpeed() + "/" + rackFeeder.getZSpeed() + "/" + rackFeeder.getUSpeed());
		// Alles zuruecksetzen auf Maximum
		rackFeeder.setMax();
		System.out.println("Zeit in echt (ms): " + movement.prepareForMove(direction));
		System.out.println("Geschwindigkeiten in echt (mm/ms) in X/Y/Z/U: " + rackFeeder.getXSpeed() + "/" + rackFeeder.getYSpeed() + "/" + rackFeeder.getZSpeed() + "/" + rackFeeder.getUSpeed());
		// Fahren
		rackFeeder.moveYZ(bin.getY(), bin.getZ());
		// Alles zuruecksetzen auf Maximum
		rackFeeder.setMax();
		System.out.println("RBG ist auf Position " + rackFeeder.getCoordinate().toString());
		System.out.println();
		
		System.out.println("Fahre RBG in das Bin hinein:");
		distance = new Distance(rackFeeder.getCoordinate(), bin.getCoordinate());
		movement = new Movement(distance, rackFeeder);
		direction = "0001";
		System.out.println("Distanz linear (mm): " + distance.getDistanceLength(direction));
		System.out.println("Zeit linear (ms): " + movement.getLinearTime(direction));
		System.out.println("Geschwindigkeiten linear (mm/ms) in X/Y/Z/U: " + rackFeeder.getXSpeed() + "/" + rackFeeder.getYSpeed() + "/" + rackFeeder.getZSpeed() + "/" + rackFeeder.getUSpeed());
		// Alles zuruecksetzen auf Maximum
		rackFeeder.setMax();
		System.out.println("Zeit in echt (ms): " + movement.prepareForMove(direction));
		System.out.println("Geschwindigkeiten in echt (mm/ms) in X/Y/Z/U: " + rackFeeder.getXSpeed() + "/" + rackFeeder.getYSpeed() + "/" + rackFeeder.getZSpeed() + "/" + rackFeeder.getUSpeed());
		// Fahren
		rackFeeder.moveU(bin.getU());
		// Alles zuruecksetzen auf Maximum
		rackFeeder.setMax();
		System.out.println("RBG ist auf Position " + rackFeeder.getCoordinate().toString());
		System.out.println();
		
		System.out.println("Lade Artikel vom Bin auf RBG:");
		// Artikel umladen
		item = myLocation.removeItem(bin);
		rackFeeder.loadItem(item);
		
		item = rackFeeder.getItem();
		if (item != null)
			itemID = item.getItemID();
		else
			itemID = "<leer>";
		System.out.println("Artikel auf dem RBG: " + itemID);
		
		item = myLocation.getItem(bin.getBinID());
		if (item != null)
			itemID = item.getItemID();
		else
			itemID = "<leer>";
		System.out.println("Artikel im Bin: " + itemID);
		System.out.println();
		
		System.out.println("Fahre RBG aus dem Bin hinaus:");
		coordinate = bin.getCoordinate("1110");
		distance = new Distance(rackFeeder.getCoordinate(), coordinate);
		movement = new Movement(distance, rackFeeder);
		direction = "0001";
		System.out.println("Distanz linear (mm): " + distance.getDistanceLength(direction));
		System.out.println("Zeit linear (ms): " + movement.getLinearTime(direction));
		System.out.println("Geschwindigkeiten linear (mm/ms) in X/Y/Z/U: " + rackFeeder.getXSpeed() + "/" + rackFeeder.getYSpeed() + "/" + rackFeeder.getZSpeed() + "/" + rackFeeder.getUSpeed());
		// Alles zuruecksetzen auf Maximum
		rackFeeder.setMax();
		System.out.println("Zeit in echt (ms): " + movement.prepareForMove(direction));
		System.out.println("Geschwindigkeiten in echt (mm/ms) in X/Y/Z/U: " + rackFeeder.getXSpeed() + "/" + rackFeeder.getYSpeed() + "/" + rackFeeder.getZSpeed() + "/" + rackFeeder.getUSpeed());
		// Fahren
		rackFeeder.moveU(coordinate.getU());
		// Alles zuruecksetzen auf Maximum
		rackFeeder.setMax();
		System.out.println("RBG ist auf Position " + rackFeeder.getCoordinate().toString());
		System.out.println();
		
		System.out.println("Fahre RBG wieder auf Nullposition:");
		coordinate = new Coordinate(rackFeeder.getX(), 0, 0, 0);
		distance = new Distance(rackFeeder.getCoordinate(), coordinate);
		movement = new Movement(distance, rackFeeder);
		direction = "0110";
		System.out.println("Distanz linear (mm): " + distance.getDistanceLength(direction));
		System.out.println("Zeit linear (ms): " + movement.getLinearTime(direction));
		System.out.println("Geschwindigkeiten linear (mm/ms) in X/Y/Z/U: " + rackFeeder.getXSpeed() + "/" + rackFeeder.getYSpeed() + "/" + rackFeeder.getZSpeed() + "/" + rackFeeder.getUSpeed());
		// Alles zuruecksetzen auf Maximum
		rackFeeder.setMax();
		System.out.println("Zeit in echt (ms): " + movement.prepareForMove(direction));
		System.out.println("Geschwindigkeiten in echt (mm/ms) in X/Y/Z/U: " + rackFeeder.getXSpeed() + "/" + rackFeeder.getYSpeed() + "/" + rackFeeder.getZSpeed() + "/" + rackFeeder.getUSpeed());
		// Fahren
		rackFeeder.moveYZ(coordinate.getY(), coordinate.getZ());
		// Alles zuruecksetzen auf Maximum
		rackFeeder.setMax();
		System.out.println("RBG ist auf Position " + rackFeeder.getCoordinate().toString());
		System.out.println();
	}
	
	private static void simulateCycle() throws InterruptedException
	{
		Simulation.setFactor(1);
		//Simulation.setSimulationType(SimulationType.FACTOR);
		Simulation.setSimulationType(SimulationType.AS_FAST_AS_POSSIBLE);
		Simulation.setStartSimulationTime("2013.12.24 23:59:45.000");
		Simulation sim = Simulation.getInstance();
		
		Job job;
		Event event;
		String startTime;
		
		System.out.println();
		System.out.println();
		System.out.println("Simulation wird gestartet:");
		EventList eventList = EventList.getInstance();
		
		startTime = "2013.12.25 00:00:00.000";
		job = new InStoreJob(Simulation.string2Calendar(startTime), Item.getInstance("Item 1"), Location.getInstance().getBin("1-1-3-2-0"), Location.getInstance().getGap("1").getRackFeeder());
		event = new Event(Simulation.string2Calendar(startTime), job);
		eventList.add(event);
		
		startTime = "2013.12.25 00:00:00.050";
		job = new OutStoreJob(Simulation.string2Calendar(startTime), Location.getInstance().getBin("0-1-1-2-1"), Location.getInstance().getGap("0").getRackFeeder());
		event = new Event(Simulation.string2Calendar(startTime), job);
		eventList.add(event);
		
		
		
		sim.start();
		
		System.out.println("Simulation ist beendet");
	}
}
