
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
		// Simulation initialisieren
		Simulation.setFactor(0.2);
		
		Simulation.setSimulationType(SimulationType.FACTOR);
		//Simulation.setSimulationType(SimulationType.AS_FAST_AS_POSSIBLE);
		
		Simulation.setStartSimulationTime("2000.01.01 00:00:00.000");
		Simulation simulation = Simulation.getInstance();
		
		// Dateien einlesen
		// ----------------
		ArrayList<String> files = new ArrayList<String>();
		files.add("location1.txt");
		files.add("item_list1.txt");
		files.add("job_list1.txt");
		
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
		
		// TODO
		// TEST
		System.out.println("Bereits bekannte Jobs beim Start der Simulation:");
		JobList jobList = JobList.getInstance();
		ArrayList<Job> list = jobList.getJobList();
		for (int i = 0; i < list.size(); i++)
		{
			Job job = list.get(i);
			System.out.println("Job: ID = '" + job.getJobID() + "', RackFeeder = '" + job.getRackFeeder().getRackFeederID() + "', Startzeit = " + Simulation.calendar2String(job.getStartTime()));
		}
		System.out.println();
		// TEST ENDE
		
		// Artikel per Zufall auf die Bins verteilen
		addItems();
		
		// Anhand der Jobs sicherstellen, dass Artikel in Bins sind, bzw. diese Bins leer sind
		checkItems();
		
		// Events erstellen anhand der JobListe
		Simulation.createEvents(null);
		
		// GUI starten
		//GUI.createAndShowGui();
		
		// Simulation starten
		simulation.start();
		
		System.out.println();
		System.out.println("**************************************************************");
		System.out.println("Simulation ist beendet, keine weiteren Events und Jobs mehr...");
		System.out.println("**************************************************************");
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
