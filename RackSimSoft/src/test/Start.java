
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
import location.Location;
import gui2D.GUI;
import helper.ReadingFiles;

/**
 * @author mschaerer
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
		// Simulation initialisieren
		Simulation.setFactor(1);
		
		//Simulation.setSimulationType(SimulationType.FACTOR);
		Simulation.setSimulationType(SimulationType.AS_FAST_AS_POSSIBLE);
		
		Simulation.setStartSimulationTime("2013.12.24 23:59:59.000");
		Simulation simulation = Simulation.getInstance();
				
		// Dateien einlesen
		// ----------------
		String fileName;
		ReadingFiles readingFiles;
		
		// Lagerort einlesen
		fileName = "location1.txt";
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
		fileName = "item_list1.txt";
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
		fileName = "job_list1.txt";
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
		
		// Events erstellen anhand der JobListe
		Simulation.createEvents();

		/*
		// TEST
		Location myLocation = Location.getInstance();
		String str;
		System.out.println("VORHER:");
		
		if (myLocation.getItem("Gasse3-1-Grid6-C-4") != null)
			str = myLocation.getItem("Gasse3-1-Grid6-C-4").getItemDescription();
		else
			str = "<leer>";
		System.out.println("Artikel in Bin Gasse3-1-Grid6-C-4 : " + str);
		
		if (myLocation.getItem("Gasse2-0-Grid3-E-2") != null)
			str = myLocation.getItem("Gasse2-0-Grid3-E-2").getItemDescription();
		else
			str = "<leer>";
		System.out.println("Artikel in Bin Gasse2-0-Grid3-E-2 : " + str);
		
		if (myLocation.getItem("Gasse3-1-Grid6-C-2") != null)
			str = myLocation.getItem("Gasse3-1-Grid6-C-2").getItemDescription();
		else
			str = "<leer>";
		System.out.println("Artikel in Bin Gasse3-1-Grid6-C-2 : " + str);
		*/
		
		// GUI starten
		//GUI.createAndShowGui();
		
		// Simulation starten
		simulation.start();
		
		System.out.println("Simulation ist beendet");
		
		/*
		// TEST
		//Location myLocation = Location.getInstance();
		System.out.println("NACHHER:");
		
		if (myLocation.getItem("Gasse3-1-Grid6-C-4") != null)
			str = myLocation.getItem("Gasse3-1-Grid6-C-4").getItemDescription();
		else
			str = "<leer>";
		System.out.println("Artikel in Bin Gasse3-1-Grid6-C-4 : " + str);
		
		if (myLocation.getItem("Gasse2-0-Grid3-E-2") != null)
			str = myLocation.getItem("Gasse2-0-Grid3-E-2").getItemDescription();
		else
			str = "<leer>";
		System.out.println("Artikel in Bin Gasse2-0-Grid3-E-2 : " + str);
		
		if (myLocation.getItem("Gasse3-1-Grid6-C-2") != null)
			str = myLocation.getItem("Gasse3-1-Grid6-C-2").getItemDescription();
		else
			str = "<leer>";
		System.out.println("Artikel in Bin Gasse3-1-Grid6-C-2 : " + str);
		*/
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
}
