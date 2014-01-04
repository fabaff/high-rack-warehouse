package gui2D;




import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import location.Bin;
import location.Gap;
import location.Grid;
import location.Location;

public class GUI {
	
	
	/**
	 * Creates the GUI for the given location.
	 * 
	 * @param location  the location
	 */	
	public static void createAndShowGui(Location myLocation)
	{
		double guiCoordinateFactor = 0.08;
		
		final int HEIGHT = 780;
		final int WIDTH = 1020;
		final String TITLE = "RackSimSoft";
		
		
		// Create main JFrame
		JFrame frame = new JFrame();
	    frame.setLayout(null);
		frame.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		frame.setMinimumSize(new Dimension(WIDTH,HEIGHT));
		frame.setSize(WIDTH, HEIGHT);
		frame.setTitle(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	    
	    
	    //Create Panels for Left and Right Grid
	    JPanel panelLeft = new GridPanel(myLocation, "0", 0);
	    JPanel panelRight = new GridPanel(myLocation, "0", 1);
	      
	    frame.add(panelLeft);
	    frame.add(panelRight);
	     
		
	}
}
