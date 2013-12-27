package eventOLD;

public class Move implements StateLocation {
	@Override
	public String move() {
		return "Only moving is allowed.";
	}

	@Override
	public String in() {
		return "In is not permitted.";
	}

	@Override
	public String out() {
		return "Out is not permitted.";
	}
}
