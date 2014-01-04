/**
 * 
 */
package gui2D;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import location.Location;

/**
 * @author turi
 *
 */
public class GapPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GapPanel(Location myLocation, String gap)
	{
		final int WIDTH = 80;
		final int HEIGHT = 380;
		int x = 460;
		int y = 10;
		
		JLabel label = new JLabel(gap);
		label.setFont(new Font("Verdana",1,20));
		label.setLayout(null);
		label.setBounds(0, 0, 100, 20);
		
		
		this.setLayout(null);
		this.setVisible(true);
		//this.setLocation(p);
		this.setBackground(Color.lightGray);
		this.setBounds(x, y, WIDTH, HEIGHT);
		this.setPreferredSize(new Dimension (WIDTH, HEIGHT));
		this.setMaximumSize(new Dimension (WIDTH, HEIGHT));
		this.setMinimumSize(new Dimension (WIDTH, HEIGHT));


		this.add(label);
		
	}

}
