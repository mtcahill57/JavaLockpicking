package events;

import events.Event.EventType;

public class EventFactory {
	
	public static Event newEvent(EventType type, int pin) {
		
		// TODO update whenever a new event type is added
		switch (type) {
		case LIFT:
			return new LiftEvent(pin);
		case TENSION:
			return new TensionEvent();
		case UNTENSIONUNTILFALL:
			return new UntensionUntilFallEvent();
		case TEST:
			return new TestEvent(pin);
		case RESET:
			return new ResetEvent();
		case EXIT:
			return new ExitEvent();
		case DISABLEHINTS:
			return new DisableHintsEvent();
		case ENABLEHINTS:
			return new EnableHintsEvent();
		default:
			break;
		}
		
		//TODO add exception throw
		return null; //shouldn't happen
	}
	
}
