package gui2D;


import item.ItemAllocation;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JPanel;

import location.Bin;
import location.Gap;
import location.Grid;
import location.Location;


public class GridPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GridPanel(Location myLocation, String gap, int side)
	{
		
		final int WIDTH = 440;
		final int HEIGHT = 380;
		int x = 0;
		int y = 0;
		int xOffset;
		int yOffset;
		int xStart = 0;
		int yStart = 0;
		int xRight = 0;
		int columnCount = 0;
		int rowCount = 0;
		int rectangleHeight;
		int rectangleWidth;
		int rectangleSide;
		Point p = new Point(10,10);
		Grid grid = null;
		Bin binArray[][];
		Gap gap1;
		BinComponent binComponent;
		
		//Get Size of Grid (Left and Right)
		
		if(side==0)
		{
			System.out.println("links");
			columnCount = myLocation.getGap(gap).getGridLeft().getBinArray().length;
			
			gap1 = myLocation.getGap(gap);
			grid = gap1.getGridLeft();
			
			x = 10;
			y = 10;
			xStart = 430;
			yStart = 370;
			xRight = -1;
		}
		else
		{
			System.out.println("rechts");
			columnCount = myLocation.getGap(gap).getGridRight().getBinArray().length;
			
			gap1 = myLocation.getGap(gap);
			grid = gap1.getGridRight();
			
			x = 550;
			y = 10;
			xStart = 10;
			yStart = 370;
			xRight = 1;
		}
		
		
		binArray = grid.getBinArray();
		
		
		for(Bin[] bins : binArray)
		{
			rowCount++;
		}
		
		rectangleHeight = (HEIGHT-20)/columnCount;
		rectangleWidth = (WIDTH-20)/rowCount;
		
		System.out.println("rectangleHeigth: " + rectangleHeight + " rectangleWidth: " + rectangleWidth);
		System.out.println("rowCount: " + rowCount + " columntCount" + columnCount);
		
		//Calculate side-length of rectangles
		if(rectangleHeight > rectangleWidth)
		{
			rectangleSide = rectangleWidth;
		} else {
			rectangleSide = rectangleHeight;
		}
		
		if(side==0)
		{
			xOffset = xStart + (rectangleSide * xRight);
		}
		else
		{
			xOffset = xStart;
		}
		
		yOffset = yStart - rectangleSide;
		
		System.out.println("\n");
		
		
		this.setLayout(null);
		this.setVisible(true);
		this.setLocation(p);
		this.setBackground(Color.lightGray);
		this.setBounds(x, y, WIDTH, HEIGHT);
		this.setPreferredSize(new Dimension (WIDTH, HEIGHT));
		this.setMaximumSize(new Dimension (WIDTH, HEIGHT));
		this.setMinimumSize(new Dimension (WIDTH, HEIGHT));
		
		
		
		//this.add(test);
		
		
		
		for(Bin[] bins : binArray)
		{
			System.out.println("xOffset: " + xOffset + " xStart: " + xStart + "yOffset: " +yOffset + "yStart:" + yStart);
			
			int i = 0;
			for(Bin bin : bins)
			{
				
				System.out.println(bin.getBinID());
				System.out.println(rectangleSide);
				System.out.println(bin.getY());
				System.out.println(xOffset);
				
				
				JButton binButton = new JButton(bin.getBinID());
				binButton.setLayout(null);
				binButton.setBounds(xOffset, yOffset, rectangleSide, rectangleSide);
				if(ItemAllocation.getInstance().getItem(bin.getBinID())!=null)
				{
					binButton.setBackground(Color.red);
				}
				else
				{
					binButton.setBackground(Color.green);
				}
				
				
				this.add(binButton);
        		
        		xOffset = xOffset + (rectangleSide * xRight);
        		System.out.println("xOffset: " + xOffset);
        		i++;
			}
			
			if(side==0)
			{
				xOffset = xStart + (rectangleSide * xRight);
			}
			else
			{
				xOffset = xStart;
			}
			yOffset = yOffset - rectangleSide;
		}
		
	}
	
	
	

}
