package event;

public class StateReadListen implements StateCheckListener
{
    Double totalIn = 0D;
    Double totalOut = 0D;
	Double totalOps = 0D;


    @Override
	public void stateRead(StateCheckEvent stateReadEvent)
    {
    	Double input = stateReadEvent.getNumber();
    	if (input == 0)
    	{
    		totalOut +=1;
    	} else
    	{
    		totalIn +=1;
    	}
    	
        System.out.println("Sum of the IN operations: " + totalIn);
        System.out.println("Sum of the OUT operations: " + totalOut);
    }
	 
    @Override
    public void stateStreamTerminated(StateCheckEvent stateReadEvent)
    {
    	totalOps = totalOut + totalIn;
    	System.out.println("Number of total operations: " + totalOps);    	
    }
}