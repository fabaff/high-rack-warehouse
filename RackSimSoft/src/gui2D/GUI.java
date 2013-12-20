package gui2D;




import java.awt.BorderLayout;
import java.awt.Color;
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
		
		final int HEIGHT = 780;
		final int WIDTH = 1020;
		final String TITLE = "RackSimSoft";
		
		String gap = "0";

		
		JFrame guiFrame = new JFrame();
		
		
		guiFrame.setLayout(null);
		
		
		guiFrame.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		guiFrame.setMinimumSize(new Dimension(WIDTH,HEIGHT));
		guiFrame.setMaximumSize(new Dimension(WIDTH,HEIGHT));
		guiFrame.setSize(WIDTH, HEIGHT);
		guiFrame.setTitle(TITLE);
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    guiFrame.setLocationRelativeTo(null);	      
	    guiFrame.setVisible(true);
	    
	    
	    
	    LeftGridPanel left = new LeftGridPanel(myLocation, gap);
	    
	    RightGridPanel right = new RightGridPanel(myLocation, gap);
	   
	    guiFrame.add(left);
	    guiFrame.add(right);
		
	    
	}
}
