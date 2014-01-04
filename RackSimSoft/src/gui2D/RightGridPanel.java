package gui2D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JPanel;

import location.Location;

public class RightGridPanel extends JPanel
{
	
	public RightGridPanel(Location myLocation,String gap)
	{
		
		
		JButton test = new JButton("Test");
		test.setName("testbutton");
		
		Point p = new Point(550,10);
		
		//test.setLayout(null);
		
		this.setVisible(true);
		this.setLocation(p);
		this.setBackground(Color.blue);
		
		this.setBounds(550, 10, 440, 380);
		
		this.setPreferredSize(new Dimension (440, 380));
		this.setMaximumSize(new Dimension (440, 380));
		this.setMinimumSize(new Dimension (440, 380));
		
		this.add(test);
		
		
	}

}
