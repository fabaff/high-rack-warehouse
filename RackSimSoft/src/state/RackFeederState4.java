
package state;

import location.RackFeeder;

/**
 * @author mschaerer
 * 
 * State4 stands for an loaded RackFeeder inside a bin of the current Gap.
 *
 */
public class RackFeederState4 extends RackFeederState
{
	/* (non-Javadoc)
	 * @see state.RackFeederState()
	 */
	public RackFeederState4(Behavior behavior)
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
				moveU(rackFeeder);
				break;
				
			/*
			case IN :
				moveYZ(rackFeeder);
				break;
			*/
			
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
				rackFeederState = new RackFeederState5(this.behavior);
				break;
				
			/*
			case IN :
				moveYZ(rackFeeder);
				break;
			*/
			
			default : break;
		}
		
		return rackFeederState;
	}
	
	/**
	 * Moves the rack feeder to the new position.
	 * 
	 */
	protected void moveU(RackFeeder rackFeeder)
	{
		// TODO hier den Rackfeeder bewegen auf die neue Koordinate
		
	}
}