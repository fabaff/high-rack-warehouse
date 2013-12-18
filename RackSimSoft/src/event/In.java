package event;

public class In implements StateLocation {
	@Override
	public String out() {
		return "Out is not permitted";
	}

	@Override
	public String move() {
		return "Move is not permitted";
	}

	@Override
	public String in() {
		return "Only In is allowed";
	}
}
