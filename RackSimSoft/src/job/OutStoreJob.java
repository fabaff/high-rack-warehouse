
package job;

import java.util.Calendar;

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
	public OutStoreJob(Calendar startTime, Bin bin, RackFeeder rackFeeder)
	{
		super(startTime, bin, rackFeeder);
		this.behavior = Behavior.OUT;
	}
}
