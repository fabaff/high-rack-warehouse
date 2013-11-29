package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

class PhysicsGUI extends JPanel
{
    public void paintComponent(Graphics g)
    {
    	// Physikalische Werte
    	// Y-Achse
    	double vY = 100;
    	double aY = 1;
    	double dY = -1;
    	// Z-Achse
    	double vZ = 10;
    	double aZ = 1;
    	double dZ = -1;
    	
    	// Weg
    	double sY = 900;
    	double sZ = 900;
    	
    	// FAKTOR
        double factor = 1;
    	
    	double current_sY = 0;
    	double current_sZ = 0;
    	
    	
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        
        // Kreise
        Ellipse2D.Double circle;
        
        int zeroPointY;
        int zeroPointZ;
        
        // Nullpunkt = 10 / 950
        zeroPointY = 10;
        zeroPointZ = 950;
        
        // Zentrum und Radius des Kreises
        int my = 0;
        int mz = 0;
        int r = 2;
        
        // Werte berechnen für jede Achse
        // Beschleunigungsweg
        double saY = Math.abs(Math.pow(vY, 2) / (2 * aY));
        double saZ = Math.abs(Math.pow(vZ, 2) / (2 * aZ));
        
        // Beschleunigungszeit
        double taY = (vY / aY);
        double taZ = (vZ / aZ);

        // Bremsweg
        double sdY = Math.abs(Math.pow(vY, 2) / (2 * dY));
        double sdZ = Math.abs(Math.pow(vZ, 2) / (2 * dZ));
        
        // Bremszeit
        double tdY = Math.abs(vY / dY);
        double tdZ = Math.abs(vZ / dZ);
        
        // Restweg zum fahren mit max. Geschwindigkeit
        double svY = sY - (saY + sdY);
        double svZ = sZ - (saZ + sdZ);
        
        // Zeit zum fahren mit max. Geschwindigkeit
        double tvY = svY / vY;
        double tvZ = svZ / vZ;
        
        // Prüfen, ob Weg überschritten wird
        if (tvY < 0)
        {
        	System.out.println("Fehler in Y, Geschwindigkeit zu hoch für die gegebene Distanz und die Beschleunigungen!");
        	
        	
        }
        if (tvZ < 0)
        {
        	System.out.println("Fehler in Z, Geschwindigkeit zu hoch für die gegebene Distanz und die Beschleunigungen!");
        }
        
        System.out.println("----------------------");
        System.out.println("----------------------");
        System.out.println("Beschleunigungszeit Y: " + taY);
        System.out.println("Fahrzeit Y: " + tvY);
        System.out.println("Bremszeit Y: " + tdY);
        System.out.println("Total Zeit Y: " + (taY + tvY + tdY));
        System.out.println("");
        System.out.println("Beschleunigungsweg Y: " + saY);
        System.out.println("Fahrweg Y: " + svY);
        System.out.println("Bremsweg Y: " + sdY);
        System.out.println("Total Weg Y: " + (saY + svY + sdY));
        System.out.println("----------------------");
        
        System.out.println("Beschleunigungszeit Z: " + taZ);
        System.out.println("Fahrzeit Z: " + tvZ);
        System.out.println("Bremszeit Z: " + tdZ);
        System.out.println("Total Zeit Z: " + (taZ + tvZ + tdZ));
        System.out.println("");
        System.out.println("Beschleunigungsweg Z: " + saZ);
        System.out.println("Fahrweg Z: " + svZ);
        System.out.println("Bremsweg Z: " + sdZ);
        System.out.println("Total Weg Z: " + (saZ + svZ + sdZ));
        System.out.println("----------------------");
        System.out.println("----------------------");
        
        
        // Total
        double maxTime = (taY + tvY + tdY);
        if (maxTime < (taZ + tvZ + tdZ))
        {
        	maxTime = (taZ + tvZ + tdZ);
        }
        
        
        for (int t = 0; t <= (maxTime * factor); t++)
        {
        	//System.out.print("t :" + t + "/" + factor + " s    ");
        	
        	
        	// Beschleunigen
        	// -------------
        	// Nur ändern, wenn Y noch beschleunigt wird
        	if (t <= (taY * factor))
        	{
        		current_sY = Math.round(Math.abs(0.5 * aY * Math.pow(t / factor, 2)));
        		//System.out.println("Y beschleunigt bei i: " + t);
        	}
        	
        	// Nur ändern, wenn Z noch beschleunigt wird
        	if (t <= (taZ * factor))
        	{
        		current_sZ = Math.round(Math.abs(0.5 * aZ * Math.pow(t / factor, 2)));
        		//System.out.println("Z beschleunigt bei i: " + t);
        	}
        	// +++++++++++++
        	
        	
        	
        	// Fahren
        	// ------
        	// Nur ändern, wenn Y schon / noch fahren muss
        	if ((t <= (int) ((taY * factor) + (tvY * factor))) && (t > (taY * factor)))
        	{
        		current_sY = saY + (vY * (t - (taY * factor)));
        		//System.out.println("Y faehrt bei i: " + t);
        	}
        	
        	// Nur ändern, wenn Z schon / noch fahren muss
        	if ((t <= (int) ((taZ * factor) + (tvZ * factor))) && (t > (taZ * factor)))
        	{
        		current_sZ = saZ + (vZ * (t - (taZ * factor)));
        		//System.out.println("Z faehrt bei i: " + t);
        	}
        	// ++++++
        	
        	
        	
        	// Bremsen
        	// -------
        	// Nur ändern, wenn Y noch gebremst wird
        	if ((t <= (int) ((taY * factor) + (tvY * factor) + (tdY * factor))) && (t > (int) ((taY * factor) + (tvY * factor))))
        	{
        		//t = (t - (taY * factor) - (tvY * factor));
        		current_sY = saY + svY + ((vY * (t - (taY * factor) - (tvY * factor))) + (0.5 * dY * Math.pow((t - (taY * factor) - (tvY * factor)), 2)));
        		//current_sY = saY + svY + ((vY * (t - (taY * factor) - (tvY * factor))) + (0.5 * cY * Math.pow((t - (taY * factor) - (tvY * factor)), 2)));
        		//System.out.println("Bremsen Y: " + current_sY + "  bei t: " + t);
        		//System.out.println("Y bremst bei i: " + t);
        	}
        	
        	// Nur ändern, wenn Z noch gebremst wird
        	if ((t <= (int) ((taZ * factor) + (tvZ * factor) + (tdZ * factor))) && (t > (int) ((taZ * factor) + (tvZ * factor))))
        	{
        		current_sZ = saZ + svZ + ((vZ * (t - (taZ * factor) - (tvZ * factor))) + (0.5 * dZ * Math.pow((t - (taZ * factor) - (tvZ * factor)), 2)));
        		//current_sZ = saZ + svZ + ((vY * t) + (0.5 * cZ * Math.pow(t - (taZ * factor) - (tvZ * factor), 2)));
        		//System.out.println("Bremsen Z: " + current_sZ + "  bei t: " + t + "   Werte saZ/svZ/Rest: " + saZ + "/" + svZ + "/" + ((vZ * (t - (taZ * factor) - (tvZ * factor))) + (0.5 * cZ * Math.pow((t - (taZ * factor) - (tvZ * factor)), 2))));
        		//System.out.println("Z bremst bei i: " + t);
        	}
        	// +++++++
        	
        	
        	
        	my = (int) (zeroPointY + current_sY);
        	mz = (int) (zeroPointZ - current_sZ);
        	
        	//System.out.println("Zeit t: " + t + "    Y/Z: " + my + "/" + mz + "    Berechnet: " + current_sY + "/" + current_sZ);
        	
        	g2d.setColor(Color.BLUE);
            circle = new Ellipse2D.Double(my-r, mz-r, 2*r, 2*r);
            g2d.draw(circle);
            
            /*
            // Hilfskreise
            g2d.setColor(Color.RED);
            my = (int) (zeroPointY + saY);
        	mz = (int) (zeroPointZ - saZ);
        	circle = new Ellipse2D.Double(my-(2*r), mz-(2*r), 2*(2*r), 2*(2*r));
            g2d.draw(circle);
            
            my = (int) (zeroPointY + saY + svY);
        	mz = (int) (zeroPointZ - (saZ + svZ));
        	circle = new Ellipse2D.Double(my-(2*r), mz-(2*r), 2*(2*r), 2*(2*r));
            g2d.draw(circle);
            
            my = (int) (zeroPointY + saY + svY + scY);
        	mz = (int) (zeroPointZ - (saZ + svZ + scZ));
            circle = new Ellipse2D.Double(my-(2*r), mz-(2*r), 2*(2*r), 2*(2*r));
            g2d.draw(circle);
            */
        }
        
        
        
        g2d.setColor(Color.BLACK);
        Line2D.Double line;

        line = new Line2D.Double(zeroPointY, zeroPointY, zeroPointY, zeroPointZ);
        g2d.draw(line);
        
        line = new Line2D.Double(zeroPointY, zeroPointZ, 1450, zeroPointZ);
        g2d.draw(line);
        
        /*
        for (int i = 1; i <= 15; i++)
        {
        	if (i <= 10)
        	{
        		line = new Line2D.Double(0, i * 100, 20, i * 100);
                g2d.draw(line);
        	}
            
            line = new Line2D.Double(i * 100, 940, i * 100, 960);
            g2d.draw(line);
        }
        */
        for (int i = 1; i <= 14; i++)
        {
        	if (i <= 9)
        	{
        		line = new Line2D.Double(zeroPointY, zeroPointZ - (i * 100), 1450, zeroPointZ - (i * 100));
                g2d.draw(line);
        	}
            
        	line = new Line2D.Double(zeroPointY + (i * 100), zeroPointY, zeroPointY + (i * 100), zeroPointZ);
            g2d.draw(line);
        }
    }
}