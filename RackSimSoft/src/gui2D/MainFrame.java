
package gui2D;

import location.Grid;
import location.Gap;
import location.Bin;
import location.Column;
import location.Location;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;



/**
 * 
 */

/**
 * @author mschaerer
 * 
 * Der Rahmen f�r das GUI. Auf diesen Rahmen werden alle anderen Komponenten gelegt.
 */

public class MainFrame
{
	final int HEIGHT = 780;
	final int WIDTH = 1020;
	final String TITLE = "RackSimSoft";
	
	
	/**
	 * Creates a Window in which the Location can be shown.
	 * 
	 */
	public MainFrame()
	{
		
	    JFrame frame = new JFrame();
	    
//	    JPanel panel = new JPanel();
	    
		frame.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		frame.setMinimumSize(new Dimension(WIDTH,HEIGHT));
	    frame.setSize(WIDTH, HEIGHT);
	    frame.setTitle(TITLE);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        
		
        
//		panel.setPreferredSize(new Dimension(WIDTH,HEIGHT));
//		panel.setMinimumSize(new Dimension(WIDTH,HEIGHT));
//	    panel.setSize(WIDTH, HEIGHT);
//	    panel.setTitle(TITLE);
//	    panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        panel.setVisible(true);
        
        
        frame.getContentPane().add(new DrawMain());
        
	}
}

class DrawMain extends JComponent
{
	public void drawMain(Graphics g)
	{
		
	//	int lowerLeftStart = 375;
		g.drawRect(10, 10, 440, 380);
		

		g.drawRect(15, 375, 10, 10);
		
		g.drawRect(460, 10, 80, 380);
		
		g.drawRect(550, 10, 440, 380);
	}
}

