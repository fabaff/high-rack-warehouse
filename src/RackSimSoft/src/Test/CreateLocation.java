package Test;

import Location.*;

public class CreateLocation
{

	private static Location myLocation;
	
	public static void main(String[] args)
	{
		Gap gap;
		Grid grid;
		Row row;
		Column column;
		
		// Lagerort erstellen
		myLocation = Location.getInstance();
				
		// Gassen hinzufügen
		// Gasse 1
		gap = new Gap(1, 0);		// Gasse 1, X-Koordinate 0
		
		// Grids, Reihen und Spalten zu Gasse hinzufügen
		grid = new Grid(1, 10000, 7000, 1);
		
		row = new Row(1, 1000, 0);
		grid.addRow(row);
		row = new Row(2, 1000, 1000);
		grid.addRow(row);
		row = new Row(3, 1000, 2000);
		grid.addRow(row);
		row = new Row(4, 1000, 3000);
		grid.addRow(row);
		row = new Row(5, 1000, 4000);
		grid.addRow(row);
		row = new Row(6, 1000, 5000);
		grid.addRow(row);
		row = new Row(7, 1000, 6000);
		grid.addRow(row);
		
		column = new Column(1, 1000, 0);
		grid.addColumn(column);
		column = new Column(1, 1000, 1000);
		grid.addColumn(column);
		column = new Column(1, 1000, 2000);
		grid.addColumn(column);
		column = new Column(1, 1000, 3000);
		grid.addColumn(column);
		column = new Column(1, 1000, 4000);
		grid.addColumn(column);
		column = new Column(1, 1000, 5000);
		grid.addColumn(column);
		column = new Column(1, 1000, 6000);
		grid.addColumn(column);
		column = new Column(1, 1000, 7000);
		grid.addColumn(column);
		column = new Column(1, 1000, 8000);
		grid.addColumn(column);
		column = new Column(1, 1000, 9000);
		grid.addColumn(column);
		
		gap.addGrid(grid);
		myLocation.addGap(gap);
		
		// Gasse 2
		gap = new Gap(2, 0);		// Gasse 2, X-Koordinate 3000
		
		// Grids, Reihen und Spalten zu Gasse hinzufügen
		grid = new Grid(1, 10000, 7000, 1);
		
		row = new Row(1, 1000, 0);
		grid.addRow(row);
		row = new Row(2, 1000, 1000);
		grid.addRow(row);
		row = new Row(3, 1000, 2000);
		grid.addRow(row);
		row = new Row(4, 1000, 3000);
		grid.addRow(row);
		row = new Row(5, 1000, 4000);
		grid.addRow(row);
		row = new Row(6, 1000, 5000);
		grid.addRow(row);
		row = new Row(7, 1000, 6000);
		grid.addRow(row);
		
		column = new Column(1, 1000, 0);
		grid.addColumn(column);
		column = new Column(1, 1000, 1000);
		grid.addColumn(column);
		column = new Column(1, 1000, 2000);
		grid.addColumn(column);
		column = new Column(1, 1000, 3000);
		grid.addColumn(column);
		column = new Column(1, 1000, 4000);
		grid.addColumn(column);
		column = new Column(1, 1000, 5000);
		grid.addColumn(column);
		column = new Column(1, 1000, 6000);
		grid.addColumn(column);
		column = new Column(1, 1000, 7000);
		grid.addColumn(column);
		column = new Column(1, 1000, 8000);
		grid.addColumn(column);
		column = new Column(1, 1000, 9000);
		grid.addColumn(column);
		
		gap.addGrid(grid);
		myLocation.addGap(gap);
		
		gap = new Gap(3, 6000);		// Gasse 3, X-Koordinate 6000
		myLocation.addGap(gap);
	}
}
