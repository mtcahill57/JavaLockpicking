package events;

public class DisableHintsEvent extends AbstractEvent {

	@Override
	public EventType getType() {
		return EventType.DISABLEHINTS;
	}

	@Override
	public String toString() {
		return "DISABLEHINTS";
	}
}
