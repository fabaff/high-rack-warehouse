package test;

import event.Move;
import event.Out;
import event.In;
import event.State;

public class States {

	public static void main(String[] args) {
		State state = new State(new In());
		System.out.println("Put something into the location:");
		System.out.println("- " + state.in());
		System.out.println("- " + state.out());
		System.out.println("- " + state.move());
		
		state.setDoorState(new Out());
		System.out.println("Get something out of the location:");
		System.out.println("- " + state.in());
		System.out.println("- " + state.out());
		System.out.println("- " + state.move());

		state.setDoorState(new Move());
		System.out.println("Move something inside the location:");
		System.out.println("- " + state.in());
		System.out.println("- " + state.out());
		System.out.println("- " + state.move());
	}

}
