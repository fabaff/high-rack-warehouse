
package gui2D;

import location.Grid;
import location.Gap;
import location.Bin;
import location.Column;
import location.Location;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
	
	
	
	/**
	 * Creates a Window in which the Location can be shown.
	 * 
	 */
	public MainFrame()
	{
		
	    
        
        
		panel.setPreferredSize(new Dimension(WIDTH-10,HEIGHT-10));
		panel.setMinimumSize(new Dimension(WIDTH-10,HEIGHT-10));
	    panel.setSize(WIDTH-10, HEIGHT-10);
        panel.setVisible(true);

                
        //guiFrame.getContentPane().add(panel);
        guiFrame.setVisible(true);
        guiFrame.add(panel);
        
        
	}
}

class DrawMain extends JComponent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected DrawMain(Graphics g)
	{
		super.paintComponent(g);
	//	int lowerLeftStart = 375;
		g.drawRect(10, 10, 440, 380);
		

		g.drawRect(15, 375, 10, 10);
		
		g.drawRect(460, 10, 80, 380);
		
		g.drawRect(550, 10, 440, 380);
	}
}

