package event;

import java.io.*;

class WriteFile
{
  public static void writeLog(String logText) throws IOException
  {
    FileWriter fw = new FileWriter("C:\\hans\\ausgabe.txt");
    BufferedWriter bw = new BufferedWriter(fw);

    bw.write("Letzter Aufruf: " + logText);
    bw.newLine();

    bw.close();
  }
}