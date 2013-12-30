
package state;

/**
 * @author mschaerer
 *
 * State7 stands for an empty RackFeeder in zero-position of the current Gap.
 *
 */
public class RackFeederState7 extends RackFeederState
{
	/* (non-Javadoc)
	 * @see state.RackFeederState()
	 */
	public RackFeederState7()
	{
		super(Behavior.WAIT);
	}
	
	/* (non-Javadoc)
	 * @see state.RackFeederState#getNextState()
	 */
	@Override
	public RackFeederState getNextState()
	{
		RackFeederState rackFeederState = null;
		
		return rackFeederState;
	}

	/**
	 * Returns the next RackFeederState belonging to the given Behavior.
	 * 
	 * @param behavior the Behavior to switch to
	 * @return the next RackFeederState
	 */
	public RackFeederState getNextState(Behavior behavior)
	{
		RackFeederState rackFeederState = null;
		
		switch (this.behavior)
		{
			case OUT :
				rackFeederState = new RackFeederState1(this.behavior);
				break;
			
			default : break;
		}
		
		return rackFeederState;
	}
}
