package main;

import parser.EventParser;
import parser.DifficultyParser;
import java.util.Scanner;
import gamecore.Game;
import gamecore.Difficulty;
import pins.Pin.PinType;

/**
 * @author MatthewCahill
 * This is my Java 9 Text-Interactive Lockpicking Game
 * To play the GUI version, start MainGUI.java
 * My parsing technique uses nextLine and contains to find the applicable values from input (case insensitive)
 * Any updates to pin types and event (action) types can be made to the respective classes and will require implementing in Core.java
 * To change or add a difficulty, implement the name, id, and pin number in Difficulty.java
 */
public class MainText {
	
	public static void main(String[] args) {
		//initial prompts
		String welcome = "Welcome to Java Lockpicking V1, a text-interactive lockpicking simulation delevoped by Matthew Cahill";
		System.out.println(welcome);
		
		//scanner for user input
		Scanner uInput = new Scanner(System.in);
		
		//get difficulty response from parser, which prints itemized prompt based on difficulties present
		DifficultyParser difficultyParser = new DifficultyParser(uInput);
		Difficulty dif = difficultyParser.getDifficulty();
		
		//print instructions
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
		while (!game.isComplete()) {
			game.processEvent(eventParser.getNextEvent());
		}
		
		//TODO implement secure timer
			//String time = 
			//System.out.println("Game complete after " + time);
		
		//close input, only do this once at end of game, because closing scanner closes the System.in input stream
		uInput.close();
		
		//thank you!
		System.out.println("Thank you for playing!");
	}
}
