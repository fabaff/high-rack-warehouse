package eventOLD;

public class State implements StateLocation {
	StateLocation locationState;

	public State(StateLocation locationState) {
		this.locationState = locationState;
	}

	public void setDoorState(StateLocation locationState) {
		this.locationState = locationState;
	}
	
	@Override
	public String in() {
		return locationState.in();
	}

	@Override
	public String out() {
		return locationState.out();
	}

	@Override
	public String move() {
		return locationState.move();
	}
}
