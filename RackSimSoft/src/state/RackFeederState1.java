
package state;

import location.RackFeeder;

/**
 * @author mschaerer
 * 
 * RackFeederState1 stands for an empty RackFeeder in zero-position of the current Gap.
 * This state has a Behavior of IN or OUT.
 *
 */
public class RackFeederState1 extends RackFeederState
{
	/* (non-Javadoc)
	 * @see state.RackFeederState()
	 */
	public RackFeederState1(Behavior behavior)
	{
		super(behavior);
	}

	/* (non-Javadoc)
	 * @see state.RackFeederState#doNextStep()
	 */
	@Override
	public void doNextStep(RackFeeder rackFeeder)
	{
		switch (this.behavior)
		{
			case OUT :
				// Do nothing
				break;
				
			case IN :
				loadItem(rackFeeder);
				break;
			
			default : break;
		}
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
				rackFeederState = new RackFeederState0();
				break;
				
			case IN :
				rackFeederState = new RackFeederState2(this.behavior);
				break;
			
			default : break;
		}
		
		return rackFeederState;
	}
	
	/**
	 * Moves the rack feeder to the new position.
	 * 
	 */
	protected void moveYZ(RackFeeder rackFeeder)
	{
		// TODO hier den Rackfeeder bewegen auf die neue Koordinate
		
	}
}
