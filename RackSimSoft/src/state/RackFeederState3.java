
package state;

import location.RackFeeder;

/**
 * @author mschaerer
 * 
 * RackFeederState3 stands for an loaded RackFeeder in bin-position of the current Gap.
 * This state has a Behavior of IN or OUT.
 *
 */
public class RackFeederState3 extends RackFeederState
{
	/* (non-Javadoc)
	 * @see state.RackFeederState()
	 */
	public RackFeederState3(Behavior behavior)
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
				moveYZ(rackFeeder);
				break;
				
			case IN :
				moveU(rackFeeder);
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
				rackFeederState = new RackFeederState2(this.behavior);
				break;
				
			case IN :
				rackFeederState = new RackFeederState4(this.behavior);
				break;
			
			default : break;
		}
		
		return rackFeederState;
	}
	
	/**
	 * Sets the item loaded to the rack feeder.
	 * 
	 */
	public void loadItem(RackFeeder rackFeeder)
	{
		// TODO load item to the rack feeder
		
	}
}
