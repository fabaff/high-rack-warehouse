package Test;

import Location.*;

public class CreateLocation
{

	private static Location myLocation;
	private static Row rowArray[];
	private static Column columnArray[];
	
	public static void main(String[] args)
	{
		myLocation = createLocation();
		
		System.out.println("myLocation: " + myLocation.toString());
	}

	private static Location createLocation()
	{
		// Lagerort erstellen
		myLocation = Location.getInstance();
		
		int gapCount = 4;  // Anzahl Gassen
		int columnCounter = 7;  // Anzahl Spalten pro Grid
		int rowCounter = 30;  // Anzahl Reihen pro Grid
		int depth = 1000;  // Offset X-Koordinate
		int height = 1000;  // Offset Z-Koordinate
		int length = 1500;  // Offset Y-Koordinate
		
		// Gaps erstellen
		for (int i = 0; i < gapCount; i++)
		{
			Gap gap = new Gap("" + i, (i * 3 * depth) + depth);  // id, X-coordinate
		
			// Grids erstellen
			for (int j = 0; j < 2; j++)
			{
				rowArray = new Row[rowCounter];
				columnArray = new Column[columnCounter];
				// Arrays abfüllen mit Breite bzw. Höhe
				for (int k = 0; k < rowArray.length; k++)
				{					
					rowArray[k] = new Row("" + k, height, k * height);  // id, height, Z-coordinate
				}
				
				for (int k = 0; k < columnArray.length; k++)
				{					
					columnArray[k] = new Column("" + k, length, k * length);  // id, width, Y-coordinate
				}
				
				// Grid erzeugen, wird in Konstruktor von Grid dierekt an übergebenes Gap zugewiesen
				//createGrid("" + j, gap, j % 2, rowArray, columnArray);
				new Grid("" + j, gap, j % 2, rowArray, columnArray);
			}
			
			// Gap (mit zugewiesenen Grids) an Location zuweisen
			myLocation.addGap(gap);
		}
		
		return myLocation;
	}
}
