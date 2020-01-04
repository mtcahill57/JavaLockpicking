package events;

public class TensionEvent extends AbstractEvent {

	@Override
	public EventType getType() {
		return EventType.TENSION;
	}
	
	@Override
	public String toString() {
		return "TENSIONEVENT";
	}
	
}
