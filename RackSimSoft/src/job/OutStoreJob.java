
package job;

import state.RackFeederState.Behavior;
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
	public OutStoreJob(Bin bin, RackFeeder rackFeeder)
	{
		super(bin, rackFeeder);
		this.behavior = Behavior.OUT;
	}
}
