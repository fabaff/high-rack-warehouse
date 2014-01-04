/**
 * 
 */
package gui2D;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import location.Gap;
import location.Location;

/**
 * @author turi
 *
 */
public class LocationPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int WIDTH = 980;
	int HEIGHT = 200;
	int x = 20;
	int y = 20;
	public LocationPanel(Location myLocation)
	{
		ArrayList<Gap> GapArray = myLocation.getGapListCopy();
		
		this.setLayout(null);
		this.setVisible(true);
		//this.setLocation(p);
		this.setBackground(Color.lightGray);
		this.setBounds(10, 400, WIDTH, HEIGHT);
		this.setPreferredSize(new Dimension (WIDTH, HEIGHT));
		this.setMaximumSize(new Dimension (WIDTH, HEIGHT));
		this.setMinimumSize(new Dimension (WIDTH, HEIGHT));
		
		int anzahlGassen = GapArray.size();
		
		int test = myLocation.countGaps();
		System.out.println(test);
		
		for(Gap gaps : GapArray)
		{
			JButton gapButton = new JButton(gaps.getGapID());
			
			gapButton.setBounds(x, 20, 300, 150);
			x+=300;
			
			this.add(gapButton);
		}
	}

}
