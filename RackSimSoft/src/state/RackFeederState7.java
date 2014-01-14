
package state;

import helper.Write2File;
import calculation.Distance;
import calculation.Movement;
import job.Job;
import location.Bin;
import location.RackFeeder;

/**
 * RackFeederState7 stands for an empty RackFeeder in zero-position of the current Gap.
 * This state has a Behavior of IN or OUT.
 *
 */
public class RackFeederState7 extends RackFeederState
{
	/* (non-Javadoc)
	 * @see state.RackFeederState()
	 */
	public RackFeederState7(Behavior behavior)
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
				// Do nothing
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
				rackFeederState = new RackFeederState6(this.behavior);
				break;
				
			case IN :
				rackFeederState = new RackFeederState0();
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
		
		// TEST
		Write2File.write("Der RackFeeder ist in YZ-Richtung gefahren, neue Koordinaten " + rackFeeder.getCoordinate().toString());
		Write2File.write();
		// TEST ENDE
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
				// RackFeeder-Werte zuruecksetzen auf Maximum
				job.getRackFeeder().setMax();
				
				Distance distance = new Distance(job.getRackFeeder().getCoordinate(), job.getBin().getCoordinate());
				Movement movement = new Movement(distance, job.getRackFeeder());
				String direction = "0110";  // xYZu
				time = movement.prepareForMove(direction);
				
				// Movement setzen zum berechnen der aktuellen RackFeeder-Position nach Zeit
				this.setMovement(movement);
				
				break;
				
			case IN :
				break;
			
			default : break;
		}
		
		// Zeit setzen, bis der RackFeeder den naechsten Status erreicht
		this.setBusyTime(time);
		
		return time;
	}
}
