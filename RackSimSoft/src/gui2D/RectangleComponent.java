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
	
	int width = 0;
	int height = 0;
	float border = 0.0f;
	String id = "";
	
	public RectangleComponent(String id, int width, int height, float border)
	{
		this.width = width;
		this.height = height;
		this.border = border;
		
		this.id = id;
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		int size = 10;
		//int border = 2;
		
		g.setColor(Color.GREEN);
		g.drawRect(30, 30, size, size);
	
	}
}
