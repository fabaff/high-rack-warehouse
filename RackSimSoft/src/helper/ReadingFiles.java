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
		Gap gap;

		int line = 0;
		ArrayList<String> elements = new ArrayList<String>();
		ArrayList<String> errors = new ArrayList<String>();
		try (Scanner scanner =  new Scanner(path, ENCODING.name()))
		{
			while (scanner.hasNextLine())
			{
				String parts[];
				String string = scanner.nextLine();
				//System.out.println(string);
				if (string.contains(":"))
				{
					line++;
					elements.add(string);
					//parts = string.split(":");
				}
			}
			System.out.println("Total processed lines: " + line + "\n");
			//return myLocation;
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
