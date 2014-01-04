package gui2D;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;

public class RectangleComponent extends JComponent
{
	private static final long serialVersionUID = 1L;
	
	int side = 0;
	float border = 0.0f;
	String id = "";
	
	public RectangleComponent(String id, int x, int y, int side)
	{
		this.side = side;
		float border = 0.2f;
		this.id = id;
		
		
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//int side = side;
		//int border = 2;
		
		g.setColor(Color.GREEN);
		g.drawRect(50, 50, side, side);
	
	}
}
