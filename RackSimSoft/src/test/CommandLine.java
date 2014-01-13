package test;

import simulation.Simulation;
import location.Location;

public class CommandLine {

	/**
	 * Handling of Command line arguments 
	 */
    public static void main(String[] args) {
        // Check if the String array is empty
        if (args.length == 0)
        {
            System.out.println("There were no commandline arguments passed!");
        }
        else
        {
            boolean factor = false;
            boolean mode = false;
            boolean mode2 = false;
            int f = 1;
            int m = 0;
            
            for(String argument: args)
            {
                if(argument.equals("-m"))
                {
                    mode = true;
                }
                else if (argument.equals("-2"))
                {
                    mode2 = true;
                }
                else if (argument.equals("-f"))
                {
                    factor = true;
                }
                else if (mode)
                {
                    try {
                    	m = Integer.parseInt(argument);
                    } catch (NumberFormatException e) {
                    	System.out.println("Arguments passed with -m must be integers!");
                    }
                }
                else if (factor)
                {
                    try {
                    	f = Integer.parseInt(argument);
                    } catch (NumberFormatException e) {
                    	System.out.println("Arguments passed with -f must be integers!");
                    }
                }
            }
               
            if (factor)
            {
            	//Simulation.setFactor(1);
                System.out.println("The factor is: " + f);
            }
            if (mode)
            {
				switch (m)
				{
					case 1 :
						//ArrayList<String> files = new ArrayList<String>();
						//files.add("location1.txt");
						//files.add("item_list1.txt");
						//files.add("job_list1.txt");
						break;
						
					default :
		                System.out.println("The mode is: " + m);
						break;
				}
            }
        }
    }
}