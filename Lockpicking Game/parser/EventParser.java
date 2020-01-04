package parser;

import java.util.Scanner;

import gamecore.Game;
import events.Event;
import events.EventFactory;
import events.Event.EventType;

public class EventParser {
	
	private Scanner scanner;
	private String questionPrompt = "What action would you like to perform?";
	
	//actions initialized on constructor call
	private String actionPrompt = "Select from ";
	
	//parser constructor sets up scanner and creates the action list prompt
	public EventParser(Scanner scanner) { //parser will be called from main to get an event
		this.scanner = scanner;
		
		//load actions into prompt
		for (EventType e : EventType.values()) {
			actionPrompt = actionPrompt.concat(e.getAction().toUpperCase() + ", ");
		}
		//trim last comma
		actionPrompt = actionPrompt.substring(0, actionPrompt.length()-2);
	}
	
	//prompts user input for event
	public Event getNextEvent() {
		
		//prompt
		System.out.println(questionPrompt + "\n" + actionPrompt);
		
		//get event type from input
		EventType type = parseEventType();
		
		//input pin if necessary
		int pin = 0;
		if (type.equals(EventType.LIFT) || type.equals(EventType.TEST)) {
			String action = type.getAction();
			pin = parsePin(action);
		}
		
		//return the created event
		return EventFactory.newEvent(type, pin);
	}
	
	//parse event from user input
	private EventType parseEventType() {
		//format : type, pin (optional)
		
		//input string
		String input = scanner.nextLine();
		input = input.toLowerCase();
		
		//parse event type from string
		EventType type = getEventType(input);
		
		//validate (if null, no valid input for event type provided)
		boolean invalid = (type == null);
		
		//prompt until valid
		while (invalid) {
			System.out.println("No valid action provided. " + actionPrompt);
			type = getEventType(scanner.nextLine().toLowerCase());
			invalid = (type == null);
		}
		return type;
	}
	
	//get event type from string
	private EventType getEventType(String input) {
		for (EventType e : EventType.values()) {
			if (input.toLowerCase().contains(e.getAction().toLowerCase())) {
				return e;
			}
		}
		return null;
	}
	
	//parse pin number from user input after prompt
	private int parsePin(String action) {
		//prompts
		String pinPrompt = ("What pin would you like perform action: " + action.toUpperCase() + " on?");
		String selectPrompt = ("Select from 0-" + (Game.getCore().numPins()-1));
		System.out.println(pinPrompt + "\n" + selectPrompt);
		
		//input pin, detect if invalid or formatted incorrectly
		int pin = -1;
		while (true) { 
			try {
				pin = Integer.parseInt(scanner.nextLine());
			}
			catch (NumberFormatException e) {
				//TODO handle error differently if necessary
			}
			finally { //if formatted and valid
				if (pin < Game.getCore().numPins() && pin >= 0) {
					break; //return pin choice
				}
				else {
					System.out.println("Invalid Pin. " + selectPrompt); //prompt different user selection
				}
			}	
		}
		//return pin choice to parser
		return pin;
	}
}
