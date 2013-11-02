
package gui2D;

import javax.swing.JFrame;

/**
 * 
 */

/**
 * @author mschaerer
 * 
 * Der Rahmen f�r das GUI. Auf diesen Rahmen werden alle anderen Komponenten gelegt.
 */

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	final int HEIGHT = 500;
	final int WIDTH = 1000;
	final String TITLE = "RackSimSoft";
	
	/**
	 * Creates a Window in which the Location can be shown.
	 * 
	 */
	public MainFrame()
	{
	    this.setSize(WIDTH, HEIGHT);
	    this.setTitle(TITLE);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
