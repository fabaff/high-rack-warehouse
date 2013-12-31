
package state;

import item.Item;
import location.RackFeeder;

/**
 * @author mschaerer
 * 
 * RackFeederState6 stands for an empty RackFeeder in bin-position of the current Gap.
 * This state has a Behavior of IN or OUT.
 *
 */
public class RackFeederState6 extends RackFeederState
{
	/* (non-Javadoc)
	 * @see state.RackFeederState()
	 */
	public RackFeederState6(Behavior behavior)
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
				
			case IN :
				moveYZ(rackFeeder);
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
				rackFeederState = new RackFeederState5(this.behavior);
				break;
				
			case IN :
				rackFeederState = new RackFeederState7(this.behavior);
				break;
			
			default : break;
		}
		
		return rackFeederState;
	}
	
	/**
	 * Returns the item loaded to the rack feeder.
	 * The rack feeder is empty after.
	 * 
	 * @return item the item which was unloaded
	 */
	public Item unloadItem()
	{
		// TODO Artikel entladen
		
		return null;
	}
}
