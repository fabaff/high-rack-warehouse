
package state;

import location.RackFeeder;

/**
 * @author mschaerer
 *
 */
public class RackFeederState7 extends RackFeederState
{

	/* (non-Javadoc)
	 * @see state.RackFeederState()
	 */
	private RackFeederState7(Behavior behavior)
	{
		super(behavior);
	}
	
	/* (non-Javadoc)
	 * @see state.RackFeederState()
	 */
	public RackFeederState7()
	{
		super(Behavior.WAIT);
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
			// TODO hier das Verhalten übergeben zum wissen, in welchen Status mit welchem Verhalten gewechselt werden muss / kann
			
			default : break;
		}
		
		return rackFeederState;
	}

}
