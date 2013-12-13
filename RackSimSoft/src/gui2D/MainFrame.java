
package gui2D;

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
	JFrame panel;
	
	/**
	 * Creates a Window in which the Location can be shown.
	 * 
	 */
	public MainFrame()
	{
		
	    JFrame panel = new JFrame();
	    

	    
		panel.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		panel.setMinimumSize(new Dimension(WIDTH,HEIGHT));
	    panel.setSize(WIDTH, HEIGHT);
	    panel.setTitle(TITLE);
	    panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setVisible(true);
        
        panel.getContentPane().add(new Grid());
       
	}
}

class Grid extends JComponent
{
	public void paint(Graphics g)
	{
		g.drawRect(10, 10, 440, 380);
		
	
		
		g.drawRect(460, 10, 80, 380);
		
		g.drawRect(550, 10, 440, 380);
	}
}

