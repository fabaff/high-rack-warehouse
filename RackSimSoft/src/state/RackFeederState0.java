
package state;

/**
 * @author mschaerer
 *
 * RackFeederState0 stands for an empty RackFeeder in zero-position of the current Gap, waiting for orders.
 * The current Behavior WAIT can be changed to IN or OUT.
 *
 */
public class RackFeederState0 extends RackFeederState
{
	/* (non-Javadoc)
	 * @see state.RackFeederState()
	 */
	public RackFeederState0()
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
				rackFeederState = new RackFeederState7(this.behavior);
				break;
			
			case IN :
				rackFeederState = new RackFeederState1(this.behavior);
				break;
			
			default : break;
		}
		
		return rackFeederState;
	}
}
