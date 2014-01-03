
package job;

import java.util.ArrayList;

/**
 * @author mschaerer
 *
 */
public class JobList
{
	private static JobList instance;
	
	private ArrayList<Job> list = new ArrayList<Job>();
	
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
	 * Adds a new job to the JobList.
	 * 
	 * @param job the job to add
	 */
	public void add(Job job)
	{
		this.list.add(job);
	}
	
	/**
	 * Returns the list of Jobs.
	 * 
	 * @return the list of jobs
	 */
	public ArrayList<Job> getJobList()
	{
		return this.list;
	}
}
