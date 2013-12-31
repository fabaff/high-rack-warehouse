
package state;

import job.Job;
import location.Bin;
import location.RackFeeder;

/**
 * @author mschaerer
 * 
 * RackFeederState2 stands for an loaded RackFeeder in zero-position of the current Gap.
 * This state has a Behavior of IN or OUT.
 *
 */
public class RackFeederState2 extends RackFeederState
{
	/* (non-Javadoc)
	 * @see state.RackFeederState()
	 */
	public RackFeederState2(Behavior behavior)
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
				unloadItem(job);
				break;
				
			case IN :
				moveYZ(job);
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
				rackFeederState = new RackFeederState1(this.behavior);
				break;
				
			case IN :
				rackFeederState = new RackFeederState3(this.behavior);
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
	protected void moveYZ(Job job)
	{
		RackFeeder rackFeeder = job.getRackFeeder();
		Bin bin = job.getBin();
		
		// Das RBG vor das Bin fahren
		rackFeeder.moveYZ(bin.getY(), bin.getZ());
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
		
		// Den Artikel vom RBG entfernen
		rackFeeder.unloadItem();
	}
}
