
package job;

import java.util.Calendar;

import state.RackFeederState.Behavior;
import item.Item;
import location.Bin;
import location.RackFeeder;

/**
 * @author mschaerer
 *
 */
public class InStoreJob extends Job
{
	/**
	 * Creates a new InStoreJob.
	 * 
	 * @param item
	 * @param bin
	 * @param rackFeeder
	 */
	public InStoreJob(Calendar startTime, Item item, Bin bin, RackFeeder rackFeeder)
	{
		super(startTime, item, bin, rackFeeder);
		this.behavior = Behavior.IN;
	}
}
