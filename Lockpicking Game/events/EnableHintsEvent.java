package events;

public class EnableHintsEvent extends AbstractEvent {

	@Override
	public EventType getType() {
		return EventType.ENABLEHINTS;
	}

	@Override
	public String toString() {
		return "ENABLEHINTS";
	}
}
