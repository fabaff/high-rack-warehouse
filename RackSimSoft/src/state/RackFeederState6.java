
package state;

import item.Item;
import location.RackFeeder;

/**
 * @author mschaerer
 * 
 * State6 stands for an loaded RackFeeder in zero-position of the current Gap.
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
				unloadItem(rackFeeder);
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
				rackFeederState = new RackFeederState7();
				break;
				
			case IN :
				rackFeederState = new RackFeederState7();
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
