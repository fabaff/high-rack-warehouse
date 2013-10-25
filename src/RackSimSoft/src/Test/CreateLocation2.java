package Test;

import Location.*;

public class CreateLocation2
{

	private static Location myLocation;
	private static Row rowArray[];
	private static Column columnArray[];
	
	public static void main(String[] args)
	{
		createLocation();
	}

	private static Location createLocation()
	{
		// Lagerort erstellen
		myLocation = Location.getInstance();
		
		// Gaps erstellen
		int depth = 1000;
		for (int i = 0; i < 4; i++)
		{
			Gap gap = new Gap(i, (i * 3 * depth) + depth);  // id, X-coordinate
		
			// Grids erstellen
			for (int j = 0; j < 2; j++)
			{
				rowArray = new Row[4];
				columnArray = new Column[5];
				// Arrays abfüllen mit Breite bzw. Höhe
				for (int k = 0; k < rowArray.length; k++)
				{
					int height = 1000;
					rowArray[k] = new Row(k, height, k * height);  // id, height, Z-coordinate
				}
				
				for (int k = 0; k < columnArray.length; k++)
				{
					int length = 1500;
					columnArray[k] = new Column("" + k, length, k * length);  // id, width, Y-coordinate
				}
				
				Grid grid = createGrid(j, rowArray, columnArray);
				
				// Grid an Gap zuweisen
				gap.addGrid(grid);
				
				// Gap an Location zuweisen
				myLocation.addGap(gap);
			}
		}
		
		return myLocation;
	}
	
	private static Grid createGrid(int gridID, Row rowArray[], Column columnArray[])
	{
		Grid grid = new Grid(gridID, gridID % 2, rowArray, columnArray);
		
		return grid;
	}
}
