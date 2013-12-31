
package state;

import item.Item;
import item.ItemAllocation;
import job.Job;
import location.Bin;
import location.RackFeeder;

/**
 * @author mschaerer
 * 
 * RackFeederState5 stands for an empty RackFeeder inside a bin of the current Gap.
 * This state has a Behavior of IN or OUT.
 *
 */
public class RackFeederState5 extends RackFeederState
{
	/* (non-Javadoc)
	 * @see state.RackFeederState()
	 */
	public RackFeederState5(Behavior behavior)
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
				loadItem(job);
				break;
				
			case IN :
				moveU(job);
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
				rackFeederState = new RackFeederState4(this.behavior);
				break;
				
			case IN :
				rackFeederState = new RackFeederState6(this.behavior);
				break;
			
			default : break;
		}
		
		return rackFeederState;
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
	
	/**
	 * Sets the item loaded to the rack feeder.
	 * 
	 * @param job the Job to execute
	 */
	protected void loadItem(Job job)
	{
		RackFeeder rackFeeder = job.getRackFeeder();
		Bin bin = job.getBin();
		ItemAllocation itemAllocation = ItemAllocation.getInstance();
		
		// Den Artikel aus dem Bin entfernen
		Item item = itemAllocation.removeItem(bin);
		
		// Den Artikel auf das RBG laden
		rackFeeder.loadItem(item);
	}
}
