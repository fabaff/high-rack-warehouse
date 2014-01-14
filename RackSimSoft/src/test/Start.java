
package test;

import item.Item;
import item.ItemAllocation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import simulation.Simulation;
import simulation.Simulation.SimulationType;
import job.Job;
import job.JobList;
import location.Bin;
import location.Gap;
import location.Grid;
import location.Location;
import gui2D.GUI;
import helper.ReadingFiles;
import helper.Write2File;

/**
 * Die Klasse Start beinhaltet die main Funktion und dient zum starten der Simulationssoftware.
 *
 */
public class Start
{
	/**
	 * Creates a new location from file, adds items from file, adds jobs from file, starts simulation.
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		// Standardwerte setzen, werden ev. via Argumente ueberschrieben
		double factor = 1;
		SimulationType simulationType = SimulationType.AS_FAST_AS_POSSIBLE;
		int locationNumber = 1;
		
		// TEST
		int argLength = args.length;
		if (argLength > 0)
		{
			int pos = 0;
			String arg1 = args[pos];
			String arg2 = "";
			
			while (pos < argLength)
			{
				arg1 = args[pos];
				arg2 = "";
				
				switch (arg1)
				{
					// Modus
					case "-m" :
						pos += 1;
						arg2 = args[pos];
						switch (arg2)
						{
							case "afap" :
								simulationType = SimulationType.AS_FAST_AS_POSSIBLE;
								break;
								
							case "f" :
								simulationType = SimulationType.FACTOR;
								break;
						}
						break;
					
					// Faktor
					case "-f" :
						try
						{
							pos += 1;
							arg2 = args[pos];
							factor = Double.parseDouble(arg2);
	                    }
						catch (NumberFormatException e)
	                    {
	                    	System.out.println("Argument passed with -f must be double!");
	                    }
						
						break;
					
						// Faktor
					case "-l" :
						try
						{
							pos += 1;
							arg2 = args[pos];
							locationNumber = Integer.parseInt(arg2);
	                    }
						catch (NumberFormatException e)
	                    {
	                    	System.out.println("Argument passed with -l must be int!");
	                    }
						
						break;
				}
				
				pos += 1;
			}
		}
		// TEST ENDE
		
		// TEST
		Write2File.clearFile();
		// TEST ENDE
		
		// Simulation initialisieren
		Simulation.setFactor(factor);
		Simulation.setSimulationType(simulationType);
		
		Simulation.setStartSimulationTime("2000.01.01 00:00:00.000");
		Simulation simulation = Simulation.getInstance();
		
		// Dateien einlesen
		// ----------------
		ArrayList<String> files = new ArrayList<String>();
		files.add("location" + locationNumber + ".txt");
		files.add("item_list" + locationNumber + ".txt");
		files.add("job_list" + locationNumber + ".txt");
		
		String fileName;
		ReadingFiles readingFiles;
		
		// Lagerort einlesen
		fileName = files.remove(0);
		readingFiles = new ReadingFiles();
		try
		{
			readingFiles.readLocation(fileName);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Artikel einlesen
		fileName = files.remove(0);
		readingFiles = new ReadingFiles();
		try
		{
			readingFiles.readArticles(fileName);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Jobs einlesen
		fileName = files.remove(0);
		readingFiles = new ReadingFiles();
		try
		{
			readingFiles.readJobs(fileName);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ++++++++++++++++
		
		// Artikel per Zufall auf die Bins verteilen
		addItems();
		
		// Anhand der Jobs sicherstellen, dass Artikel in Bins sind, bzw. diese Bins leer sind
		checkItems();
		
		// TODO
		// TEST
		Write2File.write("Lagerplatzzuweisung vor Start der Simulation:");
		Write2File.write("---------------------------------------------");
		printLocation();
		// TEST ENDE
		
		// TODO
		// TEST
		Write2File.write();
		Write2File.write("Bereits bekannte Jobs beim Start der Simulation:");
		Write2File.write("------------------------------------------------");
		JobList jobList = JobList.getInstance();
		ArrayList<Job> list = jobList.getJobList();
		for (int i = 0; i < list.size(); i++)
		{
			Job job = list.get(i);
			Write2File.write("Job: ID = '" + String.format("%1$3s", job.getJobID()) + "', RackFeeder = '" + job.getRackFeeder().getRackFeederID() + "', Startzeit = " + Simulation.calendar2String(job.getStartTime()));
		}
		Write2File.write();
		// TEST ENDE
		
		// Events erstellen anhand der JobListe
		Simulation.createEvents(null);
		
		// GUI starten
		//GUI.createAndShowGui();
		
		// Simulation starten
		simulation.start();
		
		Write2File.write();
		Write2File.write("**************************************************************");
		Write2File.write("Simulation ist beendet, keine weiteren Events und Jobs mehr...");
		Write2File.write("**************************************************************");
		
		// TODO
		// TEST
		Write2File.write();
		Write2File.write("Lagerplatzzuweisung nach der Simulation:");
		Write2File.write("----------------------------------------");
		printLocation();
		// TEST ENDE
	}

	/**
	 * Adds items to some of the bins.
	 */
	private static void addItems()
	{
		Location myLocation = Location.getInstance();
		ArrayList<Item> itemList = Item.getItemList();
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
	 * Adds / Removes items to some of the bins, depending on the job list.
	 */
	private static void checkItems()
	{
		Location myLocation = Location.getInstance();
		Item item;
		ItemAllocation itemAllocation = myLocation.getItemAllocation();
		Bin bin;
		Hashtable<String, Bin> binTable = new Hashtable<String, Bin>();
		
		JobList jobList = JobList.getInstance();
		ArrayList<Job> jobs = jobList.getJobList();
		
		for (Job job : jobs)
		{
			bin = job.getBin();
			
			// Ev. kommt derselbe Lagerplatz mehrfach vor, es kann nur auf den ersten Job getestet werden
			if (binTable.get(bin.getBinID()) == null)
			{
				binTable.put(bin.getBinID(), bin);
				
				switch (job.getBehavior())
				{
					case IN :
						if (itemAllocation.getItem(bin.getBinID()) != null)
						{
							
							itemAllocation.removeItem(bin);
						}
						
						break;
					
					case OUT :
						if (itemAllocation.getItem(bin.getBinID()) == null)
						{
							item = Item.getInstance("01976");
							itemAllocation.addItem(item, bin);
						}
						
						break;
						
					default :
						break;
				}
			}	
		}
	}
	
	/**
	 * Shows the current location in console
	 * 
	 */
	private static void printLocation()
	{
		// TODO Diese Funktion loeschen, sobald GUI ready
		// Test
		Location myLocation = Location.getInstance();
		ItemAllocation itemAllocation = myLocation.getItemAllocation();
		
	    Write2File.write("Lagerort:");
	    Write2File.write("  Name = " + myLocation.getLocationID());
	    Write2File.write("  Anzahl Gassen: " + myLocation.countGaps());
	    for (Gap gap : myLocation.getGapListCopy())
	    {
	    	Grid grid;
	    	Write2File.write("    Name: " + gap.getGapID() + ", X-Koordinate: " + gap.getXCoordinate() + ", Breite: " + gap.getXSize());
	    	grid = gap.getGridLeft();
	    	Write2File.write("    Grid L: Name: " + grid.getGridID() + ", Tiefe: " + grid.getXSize() + ", Laenge: " + grid.getYSize() + ", Hoehe: " + grid.getZSize());
	    	grid = gap.getGridRight();
	    	Write2File.write("    Grid R: Name: " + grid.getGridID() + ", Tiefe: " + grid.getXSize() + ", Laenge: " + grid.getYSize() + ", Hoehe: " + grid.getZSize());
	    	Write2File.write();
	    }
	
	    Write2File.write("  Anzahl Bin: " + myLocation.getBinList().size());
	    Write2File.write();
		
	    int i = 0;
	    
	    for (Bin bin : myLocation.getBinList())
	    {
	    	i ++;
	    	if (itemAllocation.getItem(bin.getBinID()) != null)
	    		Write2File.write(String.format("%1$3s", "" + i) + " - ID: " + String.format("%1$24s", bin.getBinID()) + ", Artikel: " + String.format("%1$-15s", itemAllocation.getItem(bin.getBinID()).getItemDescription()) + ", Koordinaten: " + bin.getCoordinate().toString());
	    	else
	    		Write2File.write(String.format("%1$3s", "" + i) + " - ID: " + String.format("%1$24s", bin.getBinID()) + ", Artikel: " + String.format("%1$-15s", "") + ", Koordinaten: " + bin.getCoordinate().toString());
	    }
	    // Test Ende
	}
}
