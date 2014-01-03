
package state;

import item.Item;
import job.Job;
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
	public void doNextStep(Job job)
	{
		switch (this.behavior)
		{
			case OUT :
				// Do nothing
				break;
				
			case IN :
				loadItem(job);
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
	 * Sets the item loaded to the rack feeder.
	 * 
	 * @param job the Job to execute
	 */
	protected void loadItem(Job job)
	{
		RackFeeder rackFeeder = job.getRackFeeder();
		Item item = job.getItem();
		
		// Den Artikel auf das RBG laden
		rackFeeder.loadItem(item);
	}
	
	/* (non-Javadoc)
	 * @see state.RackFeederState#prepareForMove()
	 */
	@Override
	public int prepareForMove(Job job)
	{
		int time = 0;
		
		switch (this.behavior)
		{
			case OUT :
				
				break;
				
			case IN :
				time = job.getRackFeeder().getLoadingTime();
				break;
			
			default : break;
		}
		
		return time;
	}
}
