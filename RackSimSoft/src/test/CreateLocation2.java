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

public class CreateLocation2
{
	public static void main(String[] args)
	{
		// Lagerort erzeugen
		Location myLocation = createLocation();
		System.out.println("Location wurde erzeugt...");
		
		// Lagerort ausgeben
		printLocation(myLocation);
		
		// Einen bestimmten Lagerplatz holen und die Koordinaten ausgeben
		printBin(myLocation, "0-0-0-2-0");
		printBin(myLocation, "0-1-1-2-1");
		printBin(myLocation, "1-0-2-3-1");
		printBin(myLocation, "1-1-3-2-0");
		
		// Gui erstellen
		createAndShowGui();
	}
	
	
	// Lagerort erzeugen
	private static Location createLocation()
	{
		// Lagerort erstellen
		Location myLocation = Location.getInstance();
		
		
		// Einfaches Modell:
		int gapCount = 2;  // Anzahl Gassen
		int columnCounter = 4;  // Anzahl Spalten pro Grid
		int rowCounter = 2;  // Anzahl Reihen pro Grid
		int width = 1000;  // Offset X-Koordinate (Breite der Gassen)
		int depth = 800;  // Tiefe des Grids
		int height = 1000;  // Offset Z-Koordinate (H�he der Reihen)
		int length = 1500;  // Offset Y-Koordinate (Breite der Spalten)
		
		
		// Komplizierteres Modell:
		/*
		int gapCount = 4;  // Anzahl Gassen
		int columnCounter = 7;  // Anzahl Spalten pro Grid
		int rowCounter = 30;  // Anzahl Reihen pro Grid
		int width = 1000;  // Offset X-Koordinate (Breite der Gassen)
		int depth = 600;  // Tiefe des Grids
		int height = 1000;  // Offset Z-Koordinate (H�he der Reihen)
		int length = 1500;  // Offset Y-Koordinate (Breite der Spalten)
		*/
		
		int gridID = 0;
		
		// Gaps erstellen
		for (int i = 0; i < gapCount; i++)
		{
			Gap gap = new Gap("" + i, width, (i * 2 * depth) + depth + (i * width));  // id, X-coordinate
		
			// Grids erstellen
			for (int j = 0; j < 2; j++)
			{
				Row rowArray[] = new Row[rowCounter];
				Column columnArray[] = new Column[columnCounter];
				
				// Arrays abf�llen mit Breite bzw. H�he
				for (int k = 0; k < columnArray.length; k++)
				{					
					columnArray[k] = new Column("" + k, length, k * length);  // id, width, Y-coordinate
				}
				
				for (int k = 0; k < rowArray.length; k++)
				{					
					rowArray[k] = new Row("" + k, height, k * height);  // id, height, Z-coordinate
				}
				
				// Grid erzeugen und an Gap zuweisen
				Grid grid = new Grid("" + gridID, gap, j % 2, columnArray, rowArray);
				gap.addGrid(grid);
				gridID ++;
			}
			
			// Gap (mit zugewiesenen Grids) an Location zuweisen
			myLocation.addGap(gap);
		}
		
		return myLocation;
	}
	
	
	// Lagerort ausgeben zum anschauen
	private static void printLocation(Location myLocation)
	{
		System.out.println("(Bin ID = [gapID]-[gridSide]-[gridID]-[columnID]-[rowID])");
		System.out.println("\nLocation:\nID = " + myLocation.getLocationID());
		System.out.println("Anzahl Gassen: " + myLocation.countGaps());
		
		// Alle Gassen holen
		ArrayList<Gap> gaps = myLocation.getGapList();
		
		// Von jeder Gasse die Grids holen
		for(Gap gap : gaps)
		{
			System.out.println("\n\tGap:\n\tID = " + gap.getGapID() + "\n\tX-Koordinate = " + gap.getXCoordinate() + "\n\tBreite = " + gap.getWidth());
			
			//Hashtable<String, Bin> binTable;
			
			Bin binArray[][];
			Grid grid;
			
			// Linkes Grid
			grid = gap.getGridLeft();
			if (grid != null)
			{
				System.out.println("\n\t\tGrid Links:\n\t\tID = " + grid.getGridID() + "\n\t\tgridSide = " + grid.getGridSide() + "\n\t\tBreite = " + grid.getWidth() + "\n\t\tH�he = " + grid.getHeight());
				
				// �ber alle Lagerpl�tze im Grid loopen
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
			
			// Rechtes Grid
			grid = gap.getGridRight();
			if (grid != null)
			{
				System.out.println("\n\t\tGrid Rechts:\n\t\tID = " + grid.getGridID() + "\n\t\tgridSide = " + grid.getGridSide() + "\n\t\tBreite = " + grid.getWidth() + "\n\t\tH�he = " + grid.getHeight());
				
				// �ber alle Lagerpl�tze im Grid loopen
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
		
		System.out.println("\nDie Koordinaten des Lagerplatzes " + binID + " lauten:");
		bin = location.getBin(binID);
		System.out.println(bin.getX() + "/" + bin.getY() + "/" + bin.getZ());
	}
	
	
	private static void createAndShowGui()
	{
		// Fenster erstellen
		MainFrame frame = new MainFrame();
		
		// Men� erstellen
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menu = new JMenu("Datei");
		menu.setMnemonic(KeyEvent.VK_D);
		
		// Men�item erstellen
		JMenuItem menuItem = new JMenuItem("Was auch immer...", KeyEvent.VK_T);
		// Zugriff �ber Alt + 1
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		
		// Men�item auf Men� legen
		menu.add(menuItem);
		
		// Men� auf Men�bar legen
		menuBar.add(menu);
		
		// Men�bar auf Frame legen
		frame.setJMenuBar(menuBar);
				
		// MainPane erstellen
		Container pane = frame.getContentPane();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 20;
		c.ipady = 20;
		
		JLayeredPane layeredPane = null;
		RectangleComponent rectangle = null;
		int width, heigth, border;
		
		// Im GridLayout (3 Spalten, 2 Reihen) jeweils ein neues LayeredPane hinzuf�gen,
		// in dieses kommt ein neues Pane auf welchem ein Rectangle gezeichnet wird
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 2; j++)
			{
				c.gridx = i;
				c.gridy = j;
				c.weightx = 1;
				c.weighty = 1;
				
				layeredPane = new JLayeredPane();
				layeredPane.setPreferredSize(new Dimension(200, 150));
		        layeredPane.setBorder(BorderFactory.createTitledBorder(
		                                    "This is a layered Pane"));
		        
		        // Rectangle erstellen und auf LayeredPane legen, Layer 0
		        width = 150;
		        heigth = 100;
		        border = 10;
		        rectangle = new RectangleComponent("1", width, heigth, border);
		        rectangle.setBounds(20, 20, width, heigth);
		        layeredPane.add(rectangle, 0);
		        
		        // Rectangle erstellen und auf LayeredPane legen, Layer 1
		        width = 150;
		        heigth = 100;
		        border = 5;
		        rectangle = new RectangleComponent("2", width, heigth, border);
		        rectangle.setBounds(40, 40, width, heigth);
		        layeredPane.add(rectangle, 1);
		        
		        // Rectangle erstellen und auf LayeredPane legen, Layer 2
		        width = 150;
		        heigth = 100;
		        border = 1;
		        rectangle = new RectangleComponent("3", width, heigth, border);
		        rectangle.setBounds(60, 60, width + 1, heigth + 1);
		        layeredPane.add(rectangle, 1);
		        
		        // Layer auf das MainPane legen
		        pane.add(layeredPane, c);
			}
		}
		
		// GUI anzeigen
		frame.pack();
		frame.setVisible(true);
	}
}
