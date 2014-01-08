
package job;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author mschaerer
 *
 */
public class JobList
{
	private static JobList instance;
	
	private ArrayList<Job> list;
	
	/**
	 * Returns an instance (object) of the class JobList.
	 * 
	 * @return the instance of this class
	 */
	public static JobList getInstance()
	{
		if (instance == null)
		{
			instance = new JobList();
		}
		
		return instance;
	}
	
	/**
	 * Creates an instance of the class JobList.
	 * 
	 */
	private JobList()
	{
		this.list = new ArrayList<Job>();
	}
	
	/**
	 * Adds a new job to the JobList.
	 * 
	 * @param job the job to add
	 */
	public void add(Job job)
	{
		this.list.add(job);
	}
	
	/**
	 * Removes a job from the JobList.
	 * 
	 * @param job the job to remove
	 */
	public void remove(Job job)
	{
		this.list.remove(job);
	}
	
	/**
	 * Returns the list of Jobs.
	 * 
	 * @return the list of jobs
	 */
	public ArrayList<Job> getJobList()
	{
		Collections.sort(this.list);
		return this.list;
	}
}
