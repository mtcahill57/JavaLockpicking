package events;

public class ExitEvent extends AbstractEvent {

	@Override
	public EventType getType() {
		return EventType.EXIT;
	}

	@Override
	public String toString() {
		return "EXITEVENT";
	}
}
