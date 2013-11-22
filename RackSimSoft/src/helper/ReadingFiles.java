package helper;

import item.Item;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

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
			if (errors.size() != 0) {
			for (String s : errors)
			    System.out.println(s);
			}
			System.out.println("Total processed lines: " + line);
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
		 */
		Path path = Paths.get(FileName);
		try (Scanner scanner =  new Scanner(path, ENCODING.name()))
		{
			while (scanner.hasNextLine())
			{
				// Code goes here...
				log(scanner.nextLine());
			}      
	    }
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
