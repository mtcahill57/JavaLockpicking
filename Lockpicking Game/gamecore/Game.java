package gamecore;

import events.Event;

public class Game {

	private static Core core = null;
	private static boolean completed = false;
	
	//game constructor
	public Game(Difficulty dif) {
		Game.core = new Core(dif);
		Game.completed = false;
	}
	
	//return the core that the game functions on
	public static Core getCore() {
		return core;
	}
	
	//sends the event to be processed at the core, a call to exit will mark the game as complete
	public void processEvent(Event e) {
		
		if (!completed) {
			
			System.out.println(core.processEvent(e)); //core will process the event and return a string representing the internal change
			
			switch (e.getType()) { //test for exit
			case EXIT:
					setComplete(true); //end the game
					break;
			
			default: //all other events
					break;
			}
		}
	}
	
	//return state of game completion
	public boolean isComplete() {
		return completed;
	}
	
	//set the game to complete based on check
	public static void setComplete(boolean check) {
		completed = check;
	}
	
}
