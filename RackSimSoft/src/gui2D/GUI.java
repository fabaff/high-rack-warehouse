package gui2D;

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
