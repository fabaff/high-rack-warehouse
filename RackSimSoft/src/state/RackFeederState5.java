
package state;

import helper.Write2File;
import calculation.Distance;
import calculation.Movement;
import item.Item;
import item.ItemAllocation;
import job.Job;
import location.Bin;
import location.RackFeeder;

/**
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
		//Bin bin = job.getBin();
		
		// Das RBG aus dem Bin heraus fahren
		// TODO Bin sollte eine Funktion haben für die Neutralpostition der U-Axhse bzw. X-Achse
		//rackFeeder.moveU(bin.getU());
		rackFeeder.moveU(0);
		
		// TEST
		Write2File.write("Der RackFeeder ist in U-Richtung gefahren, neue Koordinaten " + rackFeeder.getCoordinate().toString());
		Write2File.write();
		// TEST ENDE
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
		
		// TEST
		Write2File.write("Der Artikel '" + rackFeeder.getItem().getItemDescription() + "' wurde auf den RackFeeder geladen");
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
				
				time = job.getRackFeeder().getLoadingTime();
				break;
				
			case IN :
				// RackFeeder-Werte zuruecksetzen auf Maximum
				job.getRackFeeder().setMax();
				
				Distance distance = new Distance(job.getRackFeeder().getCoordinate(), job.getBin().getCoordinate("1110"));
				Movement movement = new Movement(distance, job.getRackFeeder());
				String direction = "0001";  // xyzU
				time = movement.prepareForMove(direction);
				
				// Movement setzen zum berechnen der aktuellen RackFeeder-Position nach Zeit
				this.setMovement(movement);
				
				break;
			
			default : break;
		}
		
		// Zeit setzen, bis der RackFeeder den naechsten Status erreicht
		this.setBusyTime(time);
		
		return time;
	}
}
