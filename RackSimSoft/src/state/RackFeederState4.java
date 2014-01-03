
package state;

import item.Item;
import item.ItemAllocation;
import job.Job;
import location.Bin;
import location.RackFeeder;

/**
 * @author mschaerer
 * 
 * RackFeederState4 stands for an loaded RackFeeder inside a bin of the current Gap.
 * This state has a Behavior of IN or OUT.
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
	public void doNextStep(Job job)
	{
		switch (this.behavior)
		{
			case OUT :
				moveU(job);
				break;
				
			case IN :
				unloadItem(job);
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
				rackFeederState = new RackFeederState3(this.behavior);
				break;
				
			case IN :
				rackFeederState = new RackFeederState5(this.behavior);
				break;
			
			default : break;
		}
		
		return rackFeederState;
	}
	
	/**
	 * Removes the item loaded to the rack feeder.
	 * The rack feeder is empty after.
	 * 
	 * @param job the Job to execute
	 */
	protected void unloadItem(Job job)
	{
		RackFeeder rackFeeder = job.getRackFeeder();
		Bin bin = job.getBin();
		ItemAllocation itemAllocation = ItemAllocation.getInstance();
		
		// Den Artikel vom RBG entfernen
		Item item = rackFeeder.unloadItem();
		
		// Den Artikel im Bin einlagern
		itemAllocation.addItem(item, bin);
	}
	
	/**
	 * Moves the rack feeder to the new position.
	 * 
	 * @param job the Job to execute
	 */
	protected void moveU(Job job)
	{
		RackFeeder rackFeeder = job.getRackFeeder();
		Bin bin = job.getBin();
		
		// Das RBG aus dem Bin heraus fahren
		rackFeeder.moveU(bin.getU());
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
