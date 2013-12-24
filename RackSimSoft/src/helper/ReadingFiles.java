package helper;

import item.Item;
import item.ItemAllocation;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

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
		 * <Timestamp>;<Operation>;<Article>;[optional values]
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
					//Code goes here...
					// TODO Create an object that can hold the data.
					//System.out.println(parts[0] + " : " + parts[1] + " : " + parts[2]);
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
