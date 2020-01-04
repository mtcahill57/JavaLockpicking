package events;

public class UntensionUntilFallEvent extends AbstractEvent {

	@Override
	public EventType getType() {
		return EventType.UNTENSIONUNTILFALL;
	}
	
	@Override
	public String toString() {
		return "UNTENSIONUNTILFALLEVENT";
	}
}
