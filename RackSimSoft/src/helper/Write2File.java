
package helper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import simulation.Simulation;

/**
 * Hilfsklasse zum visualisieren der Simulation.
 * Die Ausgabe wird in eine Datei geschrieben, da die Konsole u.U. nicht genuegend Platz bietet.
 *
 */
public class Write2File
{
	private static FileWriter writer;
	private static File file;
	private static final String fileName = "Ausgabe.txt"; 
	
	public static void clearFile()
	{
		try
		{
			// Datei anlegen
			file = new File(fileName);
			
			// new FileWriter(file) - falls die Datei bereits existiert
			// wird diese überschrieben
			writer = new FileWriter(file);
		}
		catch (IOException e)
		{
	      e.printStackTrace();
	    }
	}
	
	public static void write()
	{
		write("");
	}
	
	public static void write(String message)
	{
		//if (Simulation.getInstance().getSimulationType() == SimulationType.AS_FAST_AS_POSSIBLE)
		if (Simulation.isWrite2File())
		{
			// Datei anlegen
			file = new File(fileName);
			try
			{
				// new FileWriter(file ,true) - falls die Datei bereits existiert
				// werden die Bytes an das Ende der Datei geschrieben
				writer = new FileWriter(file ,true);
		
				// new FileWriter(file) - falls die Datei bereits existiert
				// wird diese überschrieben
				//writer = new FileWriter(file);
		
				// Text wird in den Stream geschrieben
				writer.write(message);
		
				// Platformunabhängiger Zeilenumbruch wird in den Stream geschrieben
				writer.write(System.getProperty("line.separator"));
				
				// Schreibt den Stream in die Datei
				// Sollte immer am Ende ausgeführt werden, sodass der Stream 
				// leer ist und alles in der Datei steht.
				writer.flush();
		
				// Stream schliessen
				writer.close();
		    }
			catch (IOException e)
			{
		      e.printStackTrace();
		    }
		}
		else
			System.out.println(message);
  }
}
