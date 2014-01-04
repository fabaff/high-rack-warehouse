package helper;

import item.Item;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

import job.InStoreJob;
import job.Job;
import job.JobList;
import job.OutStoreJob;
import simulation.Simulation;
import location.*;

/**
 * Reading data from external files as input for creating of all needed
 * elements.
 */
public class ReadingFiles {
	final static Charset ENCODING = StandardCharsets.UTF_8;
	
	/**
	 * Reading the article data from a flat text file.
	 * 
	 * @param FileName Path and file name of the file.
	 */
	public void readArticles(String FileName) throws IOException 
	{
		/* Format of the file
		 * Article-No; Article description
		 */
		Path path = Paths.get(FileName);
		int line = 0;
		ArrayList<String> errors = new ArrayList<String>();
		try (Scanner scanner =  new Scanner(path, ENCODING.name()))
		{
			while (scanner.hasNextLine()){
				String parts[];
				String string = scanner.nextLine();
				if (string.contains(";"))
				{
					parts = string.split(";");
					line++;
					Item.getInstance(parts[0]).setItemDescription(parts[1]);
				} else {
					errors.add("Error on line " + line);
				}
			}			
			if (errors.size() != 0)
			{
				for (String s : errors)
				{
					System.out.println(s);
				}
			}
			//System.out.println("Total processed lines: " + line + "\n");
	    }
	}

	/**
	 * Reading the location data from a file.
	 * 
	 * @param FileName Path and file name of the file.
	 */
	public void readLocation(String FileName) throws IOException 
	{
		/* Format of the file
		 * see LocationDefinition.txt
		 */
		Path path = Paths.get(FileName);
		int line = 0;
		ArrayList<String> errors = new ArrayList<String>();
		try (Scanner scanner =  new Scanner(path, ENCODING.name()))
		{
			Location myLocation = null;
			Location.MeasurementUnit measurementUnit;	// Possible values = "MM", "CM", "DM", "M"
			int gapCounter = 0;
			int gridCounter = 0;
			int rowCounter = 0;
			String[] gaps = null;
			String[] gapParts = null;
			String[] gridParts = null;
			String[] columnParts = null;
			String[] rowParts = null;
			Hashtable<String, String> gridTable = null;
			Gap gap = null;
			Grid grid;
			ArrayList<String> columnList = null;
			ArrayList<String> rowList = null;
			int lastX = 0;
			int lastY = 0;
			int lastZ = 0;
			
			while (scanner.hasNextLine())
			{
				String parts[];
				String string = scanner.nextLine();
				
				if (string.contains(";"))
				{
					parts = string.split(";");
					line++;
					
					switch (parts[0])
					{
						case "Location" :
							switch (parts[3])
							{
								case "MM" :
									measurementUnit = Location.MeasurementUnit.MM;
									break;
									
								case "CM" :
									measurementUnit = Location.MeasurementUnit.CM;
									break;
									
								case "DM" :
									measurementUnit = Location.MeasurementUnit.DM;
									break;
									
								case "M" :
									measurementUnit = Location.MeasurementUnit.M;
									break;
									
								default :
									measurementUnit = Location.MeasurementUnit.MM;
									break;
							}
							
							myLocation = Location.getInstance(parts[1], measurementUnit);
							gapCounter = Integer.parseInt(parts[2]);
							gaps = new String[gapCounter];
							
							break;
							
						case "Gap" :
							gapCounter -= 1;
							rowCounter = Integer.parseInt(parts[6]);
							gaps[gapCounter] = string;
							gridTable = new Hashtable<String, String>();
							gridCounter = 2;
							break;
						
						case "Grid" :
							gridCounter -= 1;
							gridTable.put(parts[1], string);
							
							// Alle Grid eingelesen?
							if (gridCounter == 0)
							{
								// Aktuelles Gap holen
								gapParts = gaps[gapCounter].split(";");
								
								// Linkes Grid holen
								gridParts = gridTable.get(gapParts[3]).split(";");
								
								gap = new Gap(gapParts[1], Integer.parseInt(gapParts[2]), lastX + Integer.parseInt(gridParts[2]));  // id, width, X-coordinate
								myLocation.addGap(gap);
								
								// Rechtes Grid holen, aktuelle X-Position mit Breite des Gap und Tiefe des rechten Grid addieren
								gridParts = gridTable.get(gapParts[4]).split(";");
								lastX += Integer.parseInt(gapParts[2]) + gap.getXSize() + Integer.parseInt(gridParts[2]);
								
								// Initialisieren zum einlesen der Columns und Rows
								columnList = new ArrayList<String>();
								rowList = new ArrayList<String>();
								lastY = 0;
								lastZ = 0;
							}
							
							break;
							
						case "Column" :
							columnList.add(string);
							break;
						
						case "Row" :
							rowCounter -= 1;
							rowList.add(string);
							
							if (rowCounter == 0)
							{
								Row rowArray[] = new Row[rowList.size()];
								Column columnArray[] = new Column[columnList.size()];
								
								// Filling the arrays with width and height
								for (int i = 0; i < columnArray.length; i++)
								{
									columnParts = columnList.get(i).split(";");
									columnArray[i] = new Column(columnParts[1], Integer.parseInt(columnParts[2]), lastY);  // id, width, y coordinate
									lastY += Integer.parseInt(columnParts[2]);
								}
								
								for (int i = 0; i < rowArray.length; i++)
								{
									rowParts = rowList.get(i).split(";");
									rowArray[i] = new Row(rowParts[1], Integer.parseInt(rowParts[2]), lastZ);  // id, height, z coordinate
									lastZ += Integer.parseInt(rowParts[2]);
								}
								
								// Creates grid and assign it to the gap
								// Linkes Grid holen
								gridParts = gridTable.get(gapParts[3]).split(";");
								grid = new Grid(gridParts[1], gap, 0, columnArray, rowArray, Integer.parseInt(gridParts[2]));
								gap.addGrid(grid);
								
								// Rechtes Grid holen
								gridParts = gridTable.get(gapParts[4]).split(";");
								grid = new Grid(gridParts[1], gap, 1, columnArray, rowArray, Integer.parseInt(gridParts[2]));
								gap.addGrid(grid);
							}
							
							break;
						
						default : break;
					}
					
				}
				else
				{
					errors.add("Error on line " + line);
				}
			}
			
			if (errors.size() != 0)
			{
				for (String s : errors)
				{
					System.out.println(s);
				}
			}
			//System.out.println("Total processed lines: " + line + "\n");
	    }
	}

	/**
	 * Reading the job data from a flat text file.
	 * 
	 * @param FileName Path and file name of the file.
	 */
	public void readJobs(String FileName) throws IOException 
	{
		/* Format of the file
		 * <Timestamp>;<Operation>;<BinID>;<ItemID>(optional)
		 */
		Path path = Paths.get(FileName);
		int line = 0;
		ArrayList<String> errors = new ArrayList<String>();
		try (Scanner scanner =  new Scanner(path, ENCODING.name()))
		{
			while (scanner.hasNextLine()){
				String parts[];
				String string = scanner.nextLine();
				if (string.contains(";"))
				{
					parts = string.split(";");
					line++;
					
					Job job;
					JobList jobList = JobList.getInstance();
					Location location = Location.getInstance();
					Bin bin = location.getBin(parts[2]);
					Gap gap = location.getGap(bin.getgapID());
					
					switch (parts[1])
					{
						case "O" :
							job = new OutStoreJob(Simulation.string2Calendar(parts[0]), bin, gap.getRackFeeder());
							jobList.add(job);
							break;
							
						case "I" :
							job = new InStoreJob(Simulation.string2Calendar(parts[0]), Item.getInstance(parts[3]), bin, gap.getRackFeeder());
							jobList.add(job);
							break;
						
						default : break;
					}
					
				} else {
					errors.add("Error on line " + line);
				}
			}			
			if (errors.size() != 0)
			{
				for (String s : errors)
				{
					System.out.println(s);
				}
			}
			//System.out.println("Total processed lines: " + line + "\n");
	    }
	}
	
	/**
	 * For debugging only. Prints the given element to STDOUT aka Console in
	 * Eclipse.
	 * 
	 * @param data The value to print
	 */	
	private static void log(Object data)
	{
		System.out.println(String.valueOf(data));
	}
}
