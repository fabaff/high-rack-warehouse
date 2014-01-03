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
			System.out.println("Total processed lines: " + line + "\n");
	    }
	}

	public void readLocationNEW(String FileName) throws IOException 
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
			int columnCounter = 0;
			int rowCounter = 0;
			String[] gaps = null;
			String[] gapParts = null;
			String[] gridParts = null;
			String[] columnParts = null;
			String[] rowParts = null;
			Hashtable<String, String> gridTable = null;
			Gap gap = null;
			Grid grid;
			Column column;
			Row row;
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
							columnCounter = Integer.parseInt(parts[5]);
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
			System.out.println("Total processed lines: " + line + "\n");
	    }
	}
	
	/**
	 * Reading the location data from a file.
	 * 
	 * @param FileName Path and file name of the file.
	 */
	public void readLocation(String FileName) throws IOException 
	{
		/* Format of the file: flat text file
		 * 
		 * Sample data:
		 * Location: mylocation, MM
		 * ---
		 * Gap: Gasse1;1000
		 * Grid: Grid1;1000, Grid2;1000
		 * Column: A;1200, B;800, C;800, D;1200, E;1000, F;1000, G;1200
		 * Row: 1;600, 2;800, 3;500, 4;600
		 * ...
		 */
		Path path = Paths.get(FileName);
		Location myLocation;
		int line = 0;
		ArrayList<String> elements = new ArrayList<String>();
		//ArrayList<String> errors = new ArrayList<String>();
		try (Scanner scanner =  new Scanner(path, ENCODING.name()))
		{
			while (scanner.hasNextLine())
			{
				String string = scanner.nextLine();
				line++;
				elements.add(string);
				//if (string.contains(":"))
				//{
				//	line++;
				//	elements.add(string);
				//}
			}
			System.out.println("Total processed lines: " + line + "\n");
	    }
		//System.out.println(elements);
		//for (int i = 0; i < elements.size(); i++) {
		//	System.out.println(elements.toArray()[i]);
		//}
		
		String[] parts = ((String) elements.toArray()[0]).split(";");
		// Location: myLocation;MM
		Location.MeasurementUnit measurementUnit;
	
		switch (parts[1])
		{
			case "MM":
				measurementUnit = Location.MeasurementUnit.MM;
				break;
			case "CM":
				measurementUnit = Location.MeasurementUnit.CM;
				break;
			case "DM":
				measurementUnit = Location.MeasurementUnit.DM;
				break;
			case "M":
				measurementUnit = Location.MeasurementUnit.M;
				break;
			default:
				measurementUnit = Location.MeasurementUnit.MM;
		}
		myLocation = Location.getInstance(parts[0], measurementUnit);
		// TODO Get rid of the hardcoded values
		for (int i = 0; i + 5 < elements.size(); i= i + 5) {
			// Gap
			String[] partsA = ((String) elements.toArray()[5 + i]).split(";");
			int p = 300;
			Gap gap = new Gap(partsA[0], p, (p * 2 * 300) + 300 + (p * 300));  // id, width, x coordinate
			
			// Columns
			String[] partsC = ((String) elements.toArray()[2 + i]).split(";");
			int columnCounter = partsC.length;
			Column columnArray[] = new Column[columnCounter];
			
			int c_coordinate = 0;
			for (int c = 0; c < partsC.length; c++) {
				c_coordinate = c_coordinate + c;//partsC[1]
				columnArray[c] = new Column(partsC[0], 300, c_coordinate);
			}
			
			// Rows
			String[] partsR = ((String) elements.toArray()[3 + i]).split(";");
			int rowCounter = partsR.length;
			Row rowArray[] = new Row[rowCounter];

			int r_coordinate = 0;
			for (int r = 0; r < partsR.length; r++) {
				c_coordinate = c_coordinate + r;
				rowArray[r] = new Row(partsR[0], 300, r_coordinate);
			}

			// Grid
			String[] partsG = ((String) elements.toArray()[4 + i]).split(";");
			for (int g = 0; g < partsG.length; g++) {
				Grid grid = new Grid(partsG[0], gap, g % 2, columnArray, rowArray, 300);
				gap.addGrid(grid);
			}			
			// Assign gap (with assigned grids) to the location
			myLocation.addGap(gap);
		}
		System.out.println("--> Location created" + "\n");
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
			System.out.println("Total processed lines: " + line + "\n");
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
