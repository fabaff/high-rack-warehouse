package Test;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;

import Gui2D.MainFrame;
import Gui2D.RectangleComponent;

public class TestGui
{

	public static void main(String[] args)
	{
		createAndShowGui();
	}
	
	private static void createAndShowGui()
	{
		// Fenster erstellen
		MainFrame frame = new MainFrame();
		
		// MainPane erstellen
		Container pane = frame.getContentPane();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 20;
		c.ipady = 20;
		
		JLayeredPane layeredPane = null;
		RectangleComponent rectangle = null;
		int width, heigth, border;
		
		// Im GridLayout (3 Spalten, 2 Reihen) jeweils ein neues LayeredPane hinzufügen,
		// in dieses kommt ein neues Pane auf welchem ein Rectangle gezeichnet wird
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 2; j++)
			{
				c.gridx = i;
				c.gridy = j;
				c.weightx = 1;
				c.weighty = 1;
				
				layeredPane = new JLayeredPane();
				layeredPane.setPreferredSize(new Dimension(200, 150));
		        layeredPane.setBorder(BorderFactory.createTitledBorder(
		                                    "This is a layered Pane"));
		        
		        // Rectangle erstellen und auf LayeredPane legen
		        width = 150;
		        heigth = 100;
		        border = 10;
		        rectangle = new RectangleComponent("1", width, heigth, border);
		        rectangle.setBounds(20, 20, width, heigth);
		        layeredPane.add(rectangle, JLayeredPane.DEFAULT_LAYER);
		        
		        // Layer auf das MainPane legen
		        pane.add(layeredPane, c);
			}
		}
		
		// GUI anzeigen
		frame.pack();
		frame.setVisible(true);
	}
}
