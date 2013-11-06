package event;

import java.util.EventObject;
	 
public class StateCheckEvent extends EventObject
{
	private static final long serialVersionUID = 1L;
	private Double number;
 
    public StateCheckEvent(Object source, Double number)
    {
        super(source);
        this.number = number;
    }
 
    public Double getNumber()
    {
        return number;
    }
}