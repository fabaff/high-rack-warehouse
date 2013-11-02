package gui2D;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;

public class RectangleComponent extends JComponent
{
	private static final long serialVersionUID = 1L;
	
	int width = 0;
	int heigth = 0;
	float border = 0.0f;
	String id = "";
	
	public RectangleComponent(String id, int width, int heigth, float border)
	{
		this.width = width;
		this.heigth = heigth;
		this.border = border;
		
		this.id = id;
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		// Dicke des Rechtecks
		g2.setStroke(new BasicStroke(this.border));
				
		Rectangle rectangle = new Rectangle(this.width, this.heigth);
		
		g2.draw(rectangle);
		g2.drawString("" + this.id, (this.width / 2), (this.heigth / 2));
	}
}
