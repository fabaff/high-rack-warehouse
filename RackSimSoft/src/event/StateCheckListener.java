package event;
import java.util.EventListener;

public interface StateCheckListener extends EventListener
{
    public void stateRead(StateCheckEvent stateReadEvent);
    public void stateStreamTerminated(StateCheckEvent stateReadEvent);
}