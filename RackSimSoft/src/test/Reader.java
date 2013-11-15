package test;

import java.io.IOException;
import helper.*;

public class Reader {

	public static void main(String[] args) throws IOException {
		System.out.println("Reading files and printing the content:");

		// Articles
		String article_file = "article_list.txt";
		ReadingFiles readerArticles = new ReadingFiles();
	    readerArticles.readArticles(article_file);
	    
	    // Location
		//String location_file = "location.xml";
		//ReadingFiles readerLocation = new ReadingFiles();
	    //readerLocation.readLocation(location_file);
	}
}
