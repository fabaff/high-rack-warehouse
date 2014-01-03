package test;

import java.io.IOException;

import location.Gap;
import location.Grid;
import location.Location;
import helper.*;

public class Reader {

	public static void main(String[] args) throws IOException {
		// Articles
		String article_file = "article_list.txt";
		System.out.println("Reading file '" + article_file + "'");

		ReadingFiles readerArticles = new ReadingFiles();
	    readerArticles.readArticles(article_file);
	    

	    // Location
		//String location_file = "location_list.txt";
	    String location_file = "Location2.txt";
		System.out.println("Reading file '" + location_file + "'");

		ReadingFiles readerLocation = new ReadingFiles();
	    //readerLocation.readLocation(location_file);
		readerLocation.readLocationNEW(location_file);

	    // Test
	    Location myLoc = Location.getInstance();
	    System.out.println("Lagerort:");
	    System.out.println("  Name = " + myLoc.getLocationID());
	    System.out.println("  Anzahl Gassen: " + myLoc.countGaps());
	    for (Gap gap : myLoc.getGapListCopy())
	    {
	    	Grid grid;
	    	System.out.println("    Name: " + gap.getGapID() + ", X-Koordinate: " + gap.getXCoordinate() + ", Breite: " + gap.getXSize());
	    	grid = gap.getGridLeft();
	    	System.out.println("    Grid L: Name: " + grid.getGridID() + ", Tiefe: " + grid.getXSize() + ", Laenge: " + grid.getYSize() + ", Hoehe: " + grid.getZSize());
	    	grid = gap.getGridRight();
	    	System.out.println("    Grid R: Name: " + grid.getGridID() + ", Tiefe: " + grid.getXSize() + ", Laenge: " + grid.getYSize() + ", Hoehe: " + grid.getZSize());
	    	System.out.println();
	    }
	    
	    System.out.println("  Anzahl Bin: " + myLoc.getBinList().size());
	    
	    
	    /*
	    // Jobs
		String jobs_file = "job_list.txt";
		System.out.println("Reading file '" + jobs_file + "'");

		ReadingFiles readerJobs = new ReadingFiles();
	    readerJobs.readJobs(jobs_file);
	    */
	}
}
