package event;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
 
public class StateReader {
    private Set<StateCheckListener> listeners;
 
    public StateReader() {
        listeners = new HashSet<StateCheckListener>();
    }
 
    public void addStateReadListener(StateCheckListener listener)
    {
        this.listeners.add(listener);
    }
 
    public void removeStateReadListener(StateCheckListener listener)
    {
        this.listeners.remove(listener);
    }
 
    public void start() {
    	Scanner scanner = new Scanner(System.in);
        System.out.print("Input values (0 for OUT or something else for IN) \nand hit enter, no value and enter to finish:\n");
     
        if (scanner != null) {
            Double d = null;
            do {
                String readLine = scanner.nextLine();
                d = getDoubleValue(readLine);
                if (d != null) {
                    notifyListeners(d);
                }
            } while (d != null);
            notifyListenersOfEndOfStream();
        }   	
    }
 
    private void notifyListenersOfEndOfStream()
    {
        //System.out.println("EOS was notified...we are done.");
        for (StateCheckListener stateReadListener: listeners)
        {
            stateReadListener.stateStreamTerminated(new StateCheckEvent(this, 0D));
        }
    }
 
    private void notifyListeners(Double d)
    {
        for (StateCheckListener stateReadListener: listeners) {
            stateReadListener.stateRead(new StateCheckEvent(this, d));
            //System.out.println("Listener got notification and add values");
        }
    }
 
    private Double getDoubleValue(String readLine)
    {
        Double result;
        try
        {
            result = Double.valueOf(readLine);
        } catch (Exception e)
        {
            result = null;
        }
        return result;
    }
}