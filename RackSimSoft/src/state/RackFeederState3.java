
package state;

import location.RackFeeder;

/**
 * @author mschaerer
 * 
 * State3 stands for an empty RackFeeder inside a bin of the current Gap.
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
				loadItem(rackFeeder);
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
				rackFeederState = new RackFeederState4(this.behavior);
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
	 * Sets the item loaded to the rack feeder.
	 * 
	 */
	public void loadItem(RackFeeder rackFeeder)
	{
		// TODO load item to the rack feeder
		
	}
}
