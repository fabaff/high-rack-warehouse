package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

class PhysicsGUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g)
    {
    	// Physikalische Werte
    	// Y-Achse
    	double vY = 50;
    	double aY = 1;
    	double dY = -0.5;
    	// Z-Achse
    	double vZ = 50;
    	double aZ = 5;
    	double dZ = -0.3;
    	
    	// Weg
    	double sY = 1400;
    	double sZ = 900;
    	
    	// FAKTOR
        //double factor = 1;
    	
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
        
        // Werte berechnen fuer jede Achse
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
        
        // Pruefen, ob Weg ueberschritten wird
        if (tvY < 0)
        {
        	System.out.println("Fehler in Y, Geschwindigkeit zu hoch fuer die gegebene Distanz und die Beschleunigungen!");
        	// v neu berechnen
        	vY = Math.sqrt((2 * sY * aY * Math.abs(dY)) / (aY + Math.abs(dY)));
        	System.out.println("Neues vY: " + vY);
        	// Alle Werte neu berechnen
        	saY = Math.abs(Math.pow(vY, 2) / (2 * aY));
        	taY = (vY / aY);
        	sdY = Math.abs(Math.pow(vY, 2) / (2 * dY));
        	tdY = Math.abs(vY / dY);
        	svY = sY - (saY + sdY);
        	tvY = svY / vY;
        }
        if (tvZ < 0)
        {
        	System.out.println("Fehler in Z, Geschwindigkeit zu hoch fuer die gegebene Distanz und die Beschleunigungen!");
        	// v neu berechnen
        	vZ = Math.sqrt((2 * sZ * aZ * Math.abs(dZ)) / (aZ + Math.abs(dZ)));
        	System.out.println("Neues vZ: " + vZ);
        	// Alle Werte neu berechnen
        	saZ = Math.abs(Math.pow(vZ, 2) / (2 * aZ));
        	taZ = (vZ / aZ);
        	sdZ = Math.abs(Math.pow(vZ, 2) / (2 * dZ));
        	tdZ = Math.abs(vZ / dZ);
        	svZ = sZ - (saZ + sdZ);
        	tvZ = svZ / vZ;
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
        
        
        for (int t = 0; t <= maxTime; t++)
        {
        	// Beschleunigen
        	// -------------
        	// Nur aendern, wenn Y noch beschleunigt wird
        	if (t <= taY)
        	{
        		current_sY = Math.round(Math.abs(0.5 * aY * Math.pow(t, 2)));
        		//System.out.println("Y beschleunigt bei i: " + t);
        	}
        	
        	// Nur aendern, wenn Z noch beschleunigt wird
        	if (t <= taZ)
        	{
        		current_sZ = Math.round(Math.abs(0.5 * aZ * Math.pow(t, 2)));
        		//System.out.println("Z beschleunigt bei i: " + t);
        	}
        	// +++++++++++++
        	
        	
        	// Fahren
        	// ------
        	// Nur aendern, wenn Y schon / noch fahren muss
        	if ((t <= (int) (taY + tvY)) && (t > taY))
        	{
        		current_sY = saY + (vY * (t - taY));
        		//System.out.println("Y faehrt bei i: " + t);
        	}
        	
        	// Nur aendern, wenn Z schon / noch fahren muss
        	if ((t <= (int) (taZ + tvZ)) && (t > taZ))
        	{
        		current_sZ = saZ + (vZ * (t - taZ));
        		//System.out.println("Z faehrt bei i: " + t);
        	}
        	// ++++++
        	
        	
        	// Bremsen
        	// -------
        	// Nur aendern, wenn Y noch gebremst wird
        	if ((t <= (int) (taY + tvY + tdY)) && (t > (int) (taY + tvY)))
        	{
        		current_sY = saY + svY + ((vY * (t - taY - tvY)) + (0.5 * dY * Math.pow((t - taY - tvY), 2)));
        	}
        	
        	// Nur aendern, wenn Z noch gebremst wird
        	if ((t <= (int) (taZ + tvZ + tdZ)) && (t > (int) (taZ + tvZ)))
        	{
        		current_sZ = saZ + svZ + ((vZ * (t - taZ - tvZ)) + (0.5 * dZ * Math.pow((t - taZ - tvZ), 2)));
        	}
        	// +++++++
        	
        	
        	my = (int) (zeroPointY + current_sY);
        	mz = (int) (zeroPointZ - current_sZ);
        	
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