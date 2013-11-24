package test;

import java.io.IOException;
import helper.*;

public class Reader {

	public static void main(String[] args) throws IOException {
		// Articles
		String article_file = "article_list.txt";
		System.out.println("Reading file '" + article_file + "'");

		ReadingFiles readerArticles = new ReadingFiles();
	    readerArticles.readArticles(article_file);
	    
	    // Location
		String location_file = "location1.txt";
		System.out.println("Reading file '" + location_file + "'");

		ReadingFiles readerLocation = new ReadingFiles();
	    readerLocation.readLocation(location_file);
	    
	    // Jobs
		String jobs_file = "job_list.txt";
		System.out.println("Reading file '" + jobs_file + "'");

		ReadingFiles readerJobs = new ReadingFiles();
	    readerJobs.readJobs(jobs_file);
	}
}
