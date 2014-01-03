
package state;

import calculation.Coordinate;
import calculation.Distance;
import calculation.Movement;
import job.Job;
import location.Bin;
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
	public void doNextStep(Job job)
	{
		switch (this.behavior)
		{
			case OUT :
				moveYZ(job);
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
	 * Moves the rack feeder to the new position.
	 * 
	 * @param job the Job to execute
	 */
	protected void moveU(Job job)
	{
		RackFeeder rackFeeder = job.getRackFeeder();
		Bin bin = job.getBin();
		
		// Das RBG in das Bin fahren
		rackFeeder.moveU(bin.getU());
	}
	
	/**
	 * Moves the rack feeder to the new position.
	 * 
	 * @param job the Job to execute
	 */
	protected void moveYZ(Job job)
	{
		RackFeeder rackFeeder = job.getRackFeeder();
		
		// Das RBG auf Nullposition fahren
		Coordinate coordinate = rackFeeder.getLoadingCoordinate();
		rackFeeder.moveYZ(coordinate.getY(), coordinate.getZ());
	}
	
	/* (non-Javadoc)
	 * @see state.RackFeederState#prepareForMove()
	 */
	@Override
	public int prepareForMove(Job job)
	{
		int time = 0;
		
		Distance distance;
		Movement movement;
		String direction;
		
		switch (this.behavior)
		{
			case OUT :
				// RackFeeder-Werte zuruecksetzen auf Maximum
				job.getRackFeeder().setMax();
				
				distance = new Distance(job.getRackFeeder().getCoordinate(), job.getRackFeeder().getLoadingCoordinate());
				movement = new Movement(distance, job.getRackFeeder());
				direction = "0110";  // xYZu
				time = movement.prepareForMove(direction);
				break;
				
			case IN :
				// RackFeeder-Werte zuruecksetzen auf Maximum
				job.getRackFeeder().setMax();
				
				distance = new Distance(job.getRackFeeder().getCoordinate(), job.getBin().getCoordinate());
				movement = new Movement(distance, job.getRackFeeder());
				direction = "0001";  // xyzU
				time = movement.prepareForMove(direction);
				break;
			
			default : break;
		}
		
		return time;
	}
}
