package events;

public class ResetEvent extends AbstractEvent {

	@Override
	public EventType getType() {
		return EventType.RESET;
	}

	@Override
	public String toString() {
		return "RESETEVENT";
	}
}
