package helper;

import item.Item;

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
		/* Format of the file
		 * Location [1];<ID>;<GapCount>;<MeasurementUnit>
		 * Gap [1..*];<ID>;<X-Size>;<LeftGridID>;<RigthGridID>;<ColumnCount>;<RowCount>
		 * Grid [2];<ID>;<U-Size>
		 * Column [1..*];<ID>;<Y-Size>
		 * Row [1..*];<ID>;<Z-Size>
		 * 
		 * Sample data:
		 * 	Location;my location;3;MM
			Gap;Gasse1;1000;Grid1;Grid2;7;4
			Grid;Grid1;1000
			Grid;Grid2;1000
			Column;A;1200
			Column;B;800
			Column;C;800
			Column;D;1200
			Column;E;1000
			Column;F;1000
			Column;G;1200
			Row;1;600
			Row;2;800
			Row;3;500
			Row;4;600
		 */
		Path path = Paths.get(FileName);
		Location myLocation;

		int line = 0;
		ArrayList<String> errors = new ArrayList<String>();
		try (Scanner scanner =  new Scanner(path, ENCODING.name()))
		{
			while (scanner.hasNextLine()){
				String parts[];
				String string = scanner.nextLine();
				if (string.contains(";"))
				{
					//System.out.println(string);
					
					parts = string.split(";");
					line++;

					if (parts[0].equals("Location"))
					{
						System.out.println("--- Location created");
						Location.MeasurementUnit measurementUnit;
					
						switch (parts[3])
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
						myLocation = Location.getInstance(parts[1], measurementUnit);
					}
					
					if (parts[0].equals("Gap"))
					{
						System.out.println("--- Gap created");
						Gap gap = new Gap(parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));

					}
					
					if (parts[0].equals("Grid"))
					{
						System.out.println("---Grid");
					}
					
					if (parts[0].equals("Column"))
					{
						System.out.println("---Column");
					}
					
					if (parts[0].equals("Row"))
					{
						System.out.println("---Row");
					}


				} else {
					errors.add("Error on line " + line);
				}
			}			
			if (errors.size() != 0) {
				for (String s : errors)
				{
					System.out.println(s);
				}
			}
			System.out.println("Total processed lines: " + line);
	    }
				/*
				Location
				Location myLocation = Location.getInstance("My Location", measurementUnit);
				Grid
				Gap gap = new Gap("" + i, width, (i * 2 * depth) + depth + (i * width));
				
				
				columnArray[k] = new Column("" + k, length, k * length);  // id, width, y coordinate
				rowArray[k] = new Row("" + k, height, k * height);  // id, height, z coordinate

				Grid grid = new Grid("" + gridID, gap, j % 2, columnArray, rowArray, depth);
				gap.addGrid(grid);
				gridID ++;
				
				myLocation.addGap(gap);

				*/
	}
	    
	/**
	 * For debugging only. Prints the given element to STDOUT aka Console in
	 * Eclipse.
	 * 
	 * @param data The value to print
	 */	
	private static void log(Object data){
		System.out.println(String.valueOf(data));
	}
}
