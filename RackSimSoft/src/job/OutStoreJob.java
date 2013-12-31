
package job;

import state.RackFeederState.Behavior;
import item.Item;
import location.Bin;
import location.RackFeeder;

/**
 * @author mschaerer
 *
 */
public class OutStoreJob extends Job
{
	/**
	 * Creates a new OutStoreJob.
	 * 
	 * @param item
	 * @param bin
	 * @param rackFeeder
	 */
	public OutStoreJob(Item item, Bin bin, RackFeeder rackFeeder)
	{
		super(item, bin, rackFeeder);
		this.behavior = Behavior.OUT;
	}
}
