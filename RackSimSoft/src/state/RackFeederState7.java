
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
		
		switch (this.behavior)
		{
			case OUT :
				rackFeederState = new RackFeederState1(this.behavior);
				break;
			
			case IN :
				rackFeederState = new RackFeederState6(this.behavior);
				break;
			
			default : break;
		}
		
		return rackFeederState;
	}
}
