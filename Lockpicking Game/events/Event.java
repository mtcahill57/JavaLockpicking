package events;

public interface Event {
	
	enum EventType {
		LIFT("Lift"), TEST("Test"), UNTENSIONUNTILFALL("Untension"), TENSION("Tension"), 
		RESET("Reset"), EXIT("Exit"), ENABLEHINTS("Hints on"), DISABLEHINTS("Hints off");
		
		private String action;
		
		public String getAction() {
			return this.action;
		}
		
		private EventType(String action) {
			this.action = action;
		}		
	}
	
	public boolean needsPin();
	
	public int getPin();
	
	public String toString();
	
	public EventType getType();
}
