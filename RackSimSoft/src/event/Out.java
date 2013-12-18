package event;

public class Out implements StateLocation {
	@Override
	public String in() {
		return "In is not permitted";
	}

	@Override
	public String out() {
		return "Only Out is allowed";
	}

	@Override
	public String move() {
		return "Move is not permitted";
	}
}
