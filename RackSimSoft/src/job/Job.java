
package job;

import java.util.Calendar;

import state.RackFeederState;
import state.RackFeederState.Behavior;
import location.Bin;
import location.RackFeeder;
import item.Item;
import item.ItemAllocation;

/**
 * The Job is a complete serie of events, which a rackfeeder executes for instore or outstore items from the location. 
 *
 */
public abstract class Job implements Comparable<Job>
{
	private Calendar startTime;
	private Item item;
	private Bin bin;
	private RackFeeder rackFeeder;
	protected Behavior behavior;

	/**
	 * Creates a new Job.
	 * 
	 * @param item the Item to set or get
	 * @param bin the Bin to set to or get the item from
	 * @param rackFeeder the RackFeeder to work with
	 */
	public Job(Calendar startTime, Item item, Bin bin, RackFeeder rackFeeder)
	{
		this.startTime = startTime;
		this.item = item;
		this.bin = bin;
		this.rackFeeder = rackFeeder;
	}
	
	/**
	 * Creates a new Job.
	 * 
	 * @param bin the Bin to set to or get the item from
	 * @param rackFeeder the RackFeeder to work with
	 */
	public Job(Calendar startTime, Bin bin, RackFeeder rackFeeder)
	{
		this.startTime = startTime;
		ItemAllocation itemAllocation = ItemAllocation.getInstance();
		this.item = itemAllocation.getItem(bin.getBinID());
		this.bin = bin;
		this.rackFeeder = rackFeeder;
	}
	
	/**
	 * Returns the current start time.
	 * 
	 * @return the startTime
	 */
	public final Calendar getStartTime()
	{
		return this.startTime;
	}
	
	/**
	 * Returns the current Item.
	 * 
	 * @return the item
	 */
	public final Item getItem()
	{
		return this.item;
	}
	
	/**
	 * Returns the current Bin.
	 * 
	 * @return the bin
	 */
	public final Bin getBin()
	{
		return this.bin;
	}
	
	/**
	 * Returns the current RackFeeder.
	 * 
	 * @return the rackFeeder
	 */
	public final RackFeeder getRackFeeder()
	{
		return this.rackFeeder;
	}
	
	/**
	 * Returns the current Behavior.
	 * 
	 * @return the behavior
	 */
	public final Behavior getBehavior()
	{
		return this.behavior;
	}
	
	/**
	 * Executes the next Step for the current Job.
	 * Returns the time in ms to the next state change.
	 * 
	 * @return the time in ms to the next state change
	 */
	public final int executeJob()
	{
		RackFeederState state = this.getRackFeeder().getState();
		state.switchBehavior(this.getBehavior());
		state.doNextStep(this);
		state = state.getNextState();
		this.getRackFeeder().setState(state);
		
		// Zeit in ms bis zum naechsten Statuswechsel zurueckgeben
		return state.prepareForMove(this);
	}
	
	@Override
	public int compareTo(Job job)
	{
		return this.startTime.compareTo(job.getStartTime());
	}
}
