package Test;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Set;

import Location.*;

public class CreateLocation
{
	public static void main(String[] args)
	{
		// Lagerort erzeugen
		Location myLocation = createLocation();
		System.out.println("Location wurde erzeugt...");
		
		// Lagerort ausgeben
		printLocation(myLocation);
		
		// Einen bestimmten Lagerplatz holen und die Koordinaten ausgeben
		String binID;
		Bin bin;
		
		binID = "0-0-0-2-0";
		System.out.println("\n\nDie Koordinaten des Lagerplatzes " + binID + " lauten:");
		bin = myLocation.getBin(binID);
		System.out.println(bin.getX() + "/" + bin.getY() + "/" + bin.getZ());
		
		binID = "0-1-1-2-1";
		System.out.println("\n\nDie Koordinaten des Lagerplatzes " + binID + " lauten:");
		bin = myLocation.getBin(binID);
		System.out.println(bin.getX() + "/" + bin.getY() + "/" + bin.getZ());
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
		int depth = 800; // Tiefe des Grids
		int height = 1000;  // Offset Z-Koordinate (Höhe der Reihen)
		int length = 1500;  // Offset Y-Koordinate (Breite der Spalten)
		
		
		// Komplizierteres Modell:
		/*
		int gapCount = 4;  // Anzahl Gassen
		int columnCounter = 7;  // Anzahl Spalten pro Grid
		int rowCounter = 30;  // Anzahl Reihen pro Grid
		int width = 1000;  // Offset X-Koordinate (Breite der Gassen)
		int depth = 600; // Tiefe des Grids
		int height = 1000;  // Offset Z-Koordinate (Höhe der Reihen)
		int length = 1500;  // Offset Y-Koordinate (Breite der Spalten)
		*/
		
		int gridID = 0;
		
		// Gaps erstellen
		for (int i = 0; i < gapCount; i++)
		{
			Gap gap = new Gap("" + i, (i * 2 * depth) + depth + (i * width));  // id, X-coordinate
		
			// Grids erstellen
			for (int j = 0; j < 2; j++)
			{
				Row rowArray[] = new Row[rowCounter];
				Column columnArray[] = new Column[columnCounter];
				
				// Arrays abfüllen mit Breite bzw. Höhe
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
		ArrayList<Gap> gaps = myLocation.getGaps();
		
		// Von jeder Gasse die Grids holen
		for(Gap gap : gaps)
		{
			System.out.println("\n\tGap:\n\tID = " + gap.getGapID() + "\n\tX-Koordinate = " + gap.getXCoordinate());
			
			Hashtable<String, Bin> binTable;
			Bin binArray[][];
			Grid grid;
			
			// Linkes Grid
			grid = gap.getGridLeft();
			if (grid != null)
			{
				System.out.println("\n\t\tGrid Links:\n\t\tID = " + grid.getGridID() + "\n\t\tgridSide = " + grid.getGridSide());
				
				// Über alle Lagerplätze im Grid loopen
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
				System.out.println("\n\t\tGrid Rechts:\n\t\tID = " + grid.getGridID() + "\n\t\tgridSide = " + grid.getGridSide());
				
				// Über alle Lagerplätze im Grid loopen
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
}
