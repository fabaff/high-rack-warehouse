package helper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Reading data from external files as input for creating of all needed elements.
 */
public class ReadingFiles {
	final static boolean DEBUG = true;
	final static Charset ENCODING = StandardCharsets.UTF_8;
	
	/**
	 * Reading the article data from a flat text file.
	 * 
	 * @param FileName Path and file name of the file.
	 */
	public void readArticles(String FileName) throws IOException 
	{
		Path path = Paths.get(FileName);
		try (Scanner scanner =  new Scanner(path, ENCODING.name())){
			while (scanner.hasNextLine()){
				if (DEBUG) {
					log(scanner.nextLine());
				};
				// Code goes here...
			}      
	    }
	}

	/**
	 * Reading the location data from a file.
	 * 
	 * @param FileName Path and file name of the file.
	 */
	public void readLocation(String FileName) throws IOException 
	{
		Path path = Paths.get(FileName);
		try (Scanner scanner =  new Scanner(path, ENCODING.name())){
			while (scanner.hasNextLine()){				
				if (DEBUG) {
					log(scanner.nextLine());
				};
				// Code goes here...
			}      
	    }
	}
	    
	/**
	 * For debugging only. Prints the given element to STDOUT.
	 * 
	 * @param data The value to print
	 */	
	private static void log(Object data){
		System.out.println(String.valueOf(data));
	}
}