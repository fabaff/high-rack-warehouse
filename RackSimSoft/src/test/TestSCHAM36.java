
package test;

import event.EventList;
import event.Event;
import simulation.Simulation;
import state.RackFeederState.Behavior;


public class TestSCHAM36
{
	public static void main(String[] args) throws InterruptedException
	{
		Simulation.setFactor(1);
		Simulation.setStartSimulationTime("2013.12.25 12:00:00");
		Simulation sim = Simulation.getInstance();
		
		
		System.out.println("Time: " + sim.getSimulationTimeFormatted());
		
		Thread.sleep(5000);
		
		System.out.println("Time: " + sim.getSimulationTimeFormatted());
		
		sim.start();
		
		System.out.println("Time: " + sim.getSimulationTimeFormatted());
		
		/*
		Thread.sleep(2000);
		
		System.out.println("Time: " + sim.getSimulationTimeFormatted());
		
		Thread.sleep(5432);
		
		System.out.println("Time: " + sim.getSimulationTimeFormatted());
		
		Thread.sleep(1234);
		
		System.out.println("Time: " + sim.getSimulationTimeFormatted());
		
		sim.proceed(1000);
		
		System.out.println("Time: " + sim.getSimulationTimeFormatted());
		*/
		
		
		EventList eventList = EventList.getInstance();
		Event event = new Event(Simulation.string2Calendar("2013.12.25 12:01:00.000") , "1", "1-1-3-2-0", Behavior.OUT);
		
		eventList.add(event);
		
		
	}
}