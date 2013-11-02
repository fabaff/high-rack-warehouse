package test;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import gui2D.*;
import location.*;

import java.util.ArrayList;

//import java.util.Hashtable;
//import java.util.Map.Entry;
//import java.util.Set;

public class CreateLocation
{
	public static void main(String[] args)
	{
		// Creates a location
		Location myLocation = createLocation();
		System.out.println("Location was created...");
		System.out.println("-------------------------------------------------");
		
		// Prints the location
		printLocation(myLocation);
		System.out.println("-------------------------------------------------");

		// Gets predefined bin and prints their coordinates
		System.out.println("Die Koordinaten der folgenden Lagerplaetzes sind:");
		printBin(myLocation, "0-0-0-2-0");
		printBin(myLocation, "0-1-1-2-1");
		printBin(myLocation, "1-0-2-3-1");
		printBin(myLocation, "1-1-3-2-0");
		System.out.println("-------------------------------------------------");
		
		// Creates GUI
		createAndShowGui(myLocation);
	}
		
	// Creates a location
	private static Location createLocation()
	{
		// Creates a location
		Location myLocation = Location.getInstance();	
		
		// Simple model: 
		int gapCount = 2;  // Anzahl Gassen
		int columnCounter = 4;  // Anzahl Spalten pro Grid
		int rowCounter = 2;  // Anzahl Reihen pro Grid
		int width = 1000;  // Offset X-Koordinate (Breite der Gassen)
		int depth = 800;  // Tiefe des Grids
		int height = 1000;  // Offset Z-Koordinate (Hoehe der Reihen)
		int length = 1500;  // Offset Y-Koordinate (Breite der Spalten)
		
		
		// Komplizierteres Modell:
		/*
		int gapCount = 4;  // Anzahl Gassen
		int columnCounter = 7;  // Anzahl Spalten pro Grid
		int rowCounter = 30;  // Anzahl Reihen pro Grid
		int width = 1000;  // Offset X-Koordinate (Breite der Gassen)
		int depth = 600;  // Tiefe des Grids
		int height = 1000;  // Offset Z-Koordinate (Hoehe der Reihen)
		int length = 1500;  // Offset Y-Koordinate (Breite der Spalten)
		*/
		
		int gridID = 0;
		
		// Creates gaps
		for (int i = 0; i < gapCount; i++)
		{
			Gap gap = new Gap("" + i, width, (i * 2 * depth) + depth + (i * width));  // id, X-coordinate
		
			// Creates grids
			for (int j = 0; j < 2; j++)
			{
				Row rowArray[] = new Row[rowCounter];
				Column columnArray[] = new Column[columnCounter];
				
				// Filling the arrays with width and height
				for (int k = 0; k < columnArray.length; k++)
				{					
					columnArray[k] = new Column("" + k, length, k * length);  // id, width, y coordinate
				}
				
				for (int k = 0; k < rowArray.length; k++)
				{					
					rowArray[k] = new Row("" + k, height, k * height);  // id, height, z coordinate
				}
				
				// Creates grid and assign it to a gap
				Grid grid = new Grid("" + gridID, gap, j % 2, columnArray, rowArray);
				gap.addGrid(grid);
				gridID ++;
			}
			
			// Assign gap (with assigned grids) to the location
			myLocation.addGap(gap);
		}
		
		return myLocation;
	}
	
	
	// Print location for checking
	private static void printLocation(Location myLocation)
	{
		System.out.println("(Bin ID = [gapID]-[gridSide]-[gridID]-[columnID]-[rowID])");
		System.out.println("\nLocation:\nID = " + myLocation.getLocationID());
		System.out.println("Anzahl Gassen: " + myLocation.countGaps());
		
		// Load all gaps
		ArrayList<Gap> gaps = myLocation.getGapList();
		
		// Load the grids of the gap
		for(Gap gap : gaps)
		{
			System.out.println("\n\tGap:\n\tID = " + gap.getGapID() + "\n\tx-Koordinate = " + gap.getXCoordinate() + "\n\tBreite = " + gap.getWidth());
			
			//Hashtable<String, Bin> binTable;
			
			Bin binArray[][];
			Grid grid;
			
			// Left grid
			grid = gap.getGridLeft();
			if (grid != null)
			{
				System.out.println("\n\t\tGrid Links:\n\t\tID = " + grid.getGridID() + "\n\t\tgridSide = " + grid.getGridSide() + "\n\t\tBreite = " + grid.getWidth() + "\n\t\tHoehe = " + grid.getHeight());
				
				// Loop over all bins in the grid
				Bin bin;
				
				/*
				// Via Hashtabelle
				binTable = grid.getBinTable();
				Set<Entry<String, Bin>> entrySet = binTable.entrySet();
				for(Entry<String, Bin> set : entrySet)
				{
					bin = set.getValue();
					System.out.println("\n\t\t\tBin:\n\t\t\tID = " + bin.getBinID() + "\n\t\t\tKoordinaten (X/Y/Z) = " + bin.getX() + "/" + bin.getY() + "/" + bin.getZ());
				}
				*/
				
				// Via Array
				binArray = grid.getBinArray();
				for(int i = 0; i < binArray.length; i++)
				{
					for(int j = 0; j < binArray[i].length; j++)
					{
						bin = binArray[i][j];
						System.out.println("\n\t\t\tBin:\n\t\t\tID = " + bin.getBinID() + "\n\t\t\tKoordinaten (X/Y/Z) = " + bin.getX() + "/" + bin.getY() + "/" + bin.getZ());
					}	
				}
			}
			
			// Grid right
			grid = gap.getGridRight();
			if (grid != null)
			{
				System.out.println("\n\t\tGrid Rechts:\n\t\tID = " + grid.getGridID() + "\n\t\tgridSide = " + grid.getGridSide() + "\n\t\tBreite = " + grid.getWidth() + "\n\t\tHoehe = " + grid.getHeight());
				
				// Looping over the bin in the gap
				Bin bin;
				
				/*
				// Via Hashtabelle
				binTable = grid.getBinTable();
				Set<Entry<String, Bin>> entrySet = binTable.entrySet();
				for(Entry<String, Bin> set : entrySet)
				{
					bin = set.getValue();
					System.out.println("\n\t\t\tBin:\n\t\t\tID = " + bin.getBinID() + "\n\t\t\tKoordinaten (X/Y/Z) = " + bin.getX() + "/" + bin.getY() + "/" + bin.getZ());
				}
				*/
				
				// Via Array
				binArray = grid.getBinArray();
				for(int i = 0; i < binArray.length; i++)
				{
					for(int j = 0; j < binArray[i].length; j++)
					{
						bin = binArray[i][j];
						System.out.println("\n\t\t\tBin:\n\t\t\tID = " + bin.getBinID() + "\n\t\t\tKoordinaten (X/Y/Z) = " + bin.getX() + "/" + bin.getY() + "/" + bin.getZ());
					}	
				}
			}
		}
	}
	
	private static void printBin(Location location, String binID)
	{
		Bin bin;
		bin = location.getBin(binID);
		System.out.println(binID + " : " + bin.getX() + "/" + bin.getY() + "/" + bin.getZ());
	}
	
	
	private static void createAndShowGui(Location myLocation)
	{
		double guiCoordinateFactor = 0.08;
		
		// Creates windows
		MainFrame frame = new MainFrame();
		
		// Creates menu
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menu = new JMenu("Datei");
		menu.setMnemonic(KeyEvent.VK_D);
		
		// Creates menuitem
		JMenuItem menuItem = new JMenuItem("Was auch immer...", KeyEvent.VK_T);
		// Access with Alt + 1
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		
		// Put menuitem in the menu
		menu.add(menuItem);
		
		// Put menu on menubar
		menuBar.add(menu);
		
		// Put menubar on frame
		frame.setJMenuBar(menuBar);
			
		// Creates MainPane
		Container pane = frame.getContentPane();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 20;
		c.ipady = 20;
		
		JLayeredPane layeredPane = null;
		GridComponent gridComponent = null;
		BinComponent binComponent = null;
		
		// Active gap should be gap 1
		// Get gap 1 and its grids
		Gap gap = myLocation.getGap("1");
		Grid grid;
		Bin binArray[][];
		int xOffset = 20;
		int yOffset = 20;
		
		// Add to the GridLayout (3 columns, 2 rows) for every field a
		// LayeredPane. Two contains a grid (from Pkg location gui2D)
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 2; j++)
			{
				c.gridx = i;
				c.gridy = j;
				c.weightx = 1;
				c.weighty = 1;
				
				layeredPane = new JLayeredPane();
				layeredPane.setPreferredSize(new Dimension(50, 40));
		        layeredPane.setBorder(BorderFactory.createTitledBorder("This is a layered Pane"));
		        
		        // Creates grid and puts it on LayeredPane as layer 0
		        if ((j == 0) && ((i == 0) || (i == 2)))
		        {
		        	// Get the correct grid (left or right)
		        	if (i == 0)
		        	{
		        		grid = gap.getGridLeft();
		        	}
		        	else
		        	{
		        		grid = gap.getGridRight();
		        	}
		        	
			        gridComponent = new GridComponent(grid, guiCoordinateFactor);
			        gridComponent.setBounds(xOffset, yOffset, gridComponent.getWidth(), gridComponent.getHeight());
			        layeredPane.add(gridComponent, 0);
			        
			        // Creates bins and put them on LayeredPane as layer 1
			        binArray = grid.getBinArray();
			        for (Bin[] bins : binArray)
			        {
			        	for (Bin bin : bins)
				        {
				        	System.out.println(bin.getBinID());
			        		binComponent = new BinComponent(bin, guiCoordinateFactor);
					        binComponent.setBounds(xOffset + binComponent.getYOffset(), yOffset + binComponent.getZOffset(), binComponent.getWidth(), binComponent.getHeight());
					        System.out.println("BinID: " + bin.getBinID() + ", Y-Offset: " + binComponent.getYOffset() + ", Z-Offset: " + binComponent.getZOffset() +
					        		           ", Breite: " + binComponent.getWidth() + ", Hoehe: " + binComponent.getHeight());
					        layeredPane.add(binComponent, 1);
				        }
			        }
		        }
		        
		        // Put layer on MainPane
		        pane.add(layeredPane, c);
			}
		}
		
		// Display GUI
		frame.pack();
		frame.setVisible(true);
	}
}
