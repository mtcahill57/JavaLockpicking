/**
 * 
 */
package events;

/**
 * @author MatthewCahill
 *
 */
public class LiftEvent extends AbstractEvent {

	private int pin;
	
	public LiftEvent(int pin) {
		this.pin = pin;
	}

	@Override
	public boolean needsPin() {
		return true;
	}
	
	@Override
	public EventType getType() {
		return EventType.LIFT;
	}

	@Override
	public int getPin() {
		return pin;
	}
	
	@Override
	public String toString() {
		return "LIFTEVENT :: PIN:" + pin;
	}
}
