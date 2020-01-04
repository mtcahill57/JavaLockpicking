package events;

abstract class AbstractEvent implements Event {
	
	public EventType getType() {
		return null;
	}
	
	public String toString() {
		return "ABSTRACTEVENT";
	}
	
	public int getPin() {
		return Integer.MIN_VALUE;
	}
}
