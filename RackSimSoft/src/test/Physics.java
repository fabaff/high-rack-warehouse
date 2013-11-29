package test;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Zeichnet einen Kreis mit dem Mittelpunkt mx/my und dem Radius r
 * 
 */
public class Physics extends JFrame
{
	private static final long serialVersionUID = 1L;
	JPanel panel;
    
    public Physics()
    {
        panel = new PhysicsGUI();
        add(panel);

        this.setSize(1500, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        new Physics();
    }
} 