package main;

import parser.EventParser;
import parser.DifficultyParser;
import java.util.Scanner;

import gamecore.Game;
import gamecore.Game.Difficulty;
import pins.Pin.PinType;

/**
 * @author MatthewCahill
 * This is my Java 9 Text-Interactive Lockpicking Game
 * Code could be cleaned up in places, but I wanted the processes to be logical and easily followable
 * My parsing technique uses nextLine and contains to find the applicable values from input
 * Any updates to pin types and event(action) types can be made to the enum in the respective interface class
 * If an event(action) is added, the function processEvent in core must be updated to support it
 * Enum for difficulty stored in Game.java
 * 
 */
public class Main {
	
	public static void main(String[] args) {
		//initial prompts
		String welcome = "Welcome to Java Lockpicking V1, a text-interactive lockpicking simulation delevoped by Matthew Cahill";
		System.out.println(welcome);
		
		//scanner for user input
		Scanner uInput = new Scanner(System.in);
		
		//print prompt and get difficulty response
		DifficultyParser difficultyParser = new DifficultyParser(uInput);
		String difficultyPrompt = "Select difficulty from 'Easy' 'Intermediate' 'Hard' or 'Insane'";
		Difficulty dif = difficultyParser.newDifficultyPromptGet(difficultyPrompt);
		
		//print instructions and run game
		String instructions = "\nThere are multiple actions you can perform. Some affect one pin at a time, and others affect the whole core.\n"
		+ "The untension action will release tension on the core until a pin falls. If all pins have fallen, you will have to tension the core again.\n"
		+ "You can enable hints at any time if you need help. There are " + PinType.values().length + " different types of pins.\n"
		+ "Remember that pins have a chance of binding in a false set, and certain pins are harder to get out of a false set.\n";
		System.out.println(instructions);
		
		//event parser will parse actions from user input
		EventParser eventParser = new EventParser(uInput);
		
		//game object contains the core and functions for play
		Game game = new Game(dif);
		
		//run game until complete
		while (game.isComplete() == false) {
			game.processEvent(eventParser.getNextEvent());
		}
		
		//TODO implement timer
			//String time = 
			//System.out.println("Game complete after " + time);
		
		//thank you!
		System.out.println("Thank you for playing!");
		
		//close input, only do this once at end of game, because closing scanner closes the System.in inputstream
		uInput.close();
	}
}
