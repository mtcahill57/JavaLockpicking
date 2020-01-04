/**
 * 
 */
package events;

/**
 * @author MatthewCahill
 *
 */
public class TestEvent extends AbstractEvent {

	private int pin;
	
	public TestEvent(int pin) {
		this.pin = pin;
	}

	@Override
	public EventType getType() {
		return EventType.TEST;
	}

	@Override
	public int getPin() {
		return pin;
	}
	
	@Override
	public String toString() {
		return "TESTEVENT :: PIN:" + pin;
	}
}
