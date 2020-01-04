package parser;

import java.util.Scanner;

import gamecore.Game.Difficulty;

public class DifficultyParser {

	private Scanner scanner;
	
	public DifficultyParser(Scanner scanner) {
		this.scanner = scanner;
	}
	
	//returns user selected difficulty
	public Difficulty newDifficultyPromptGet(String prompt) {
		
		//print prompt
		System.out.println(prompt);
		
		Difficulty dif = null;
		boolean difSelected = false;
		
		String response = "";
		
		//loop to find valid difficulty selection
		while (difSelected == false) {
			String select = scanner.nextLine();
			select = select.toLowerCase().trim();
			
			if (select.contains("easy")) {
				dif = Difficulty.EASY;
				difSelected = true;
				response = "Difficulty Selected: Easy";
			}
			else if (select.contains("intermediate")) {
				dif = Difficulty.INTERMEDIATE;
				difSelected = true;
				response = "Difficulty Selected: Intermediate";
			}
			else if (select.contains("hard")) {
				dif = Difficulty.HARD;
				difSelected = true;
				response = "Difficulty Selected: Hard";
			}
			else if (select.contains("insane")) {
				dif = Difficulty.INSANE;
				difSelected = true;
				response = "Difficulty Selected: Insane";
			}
			else {
				response = "Invalid response. Please select from 'Easy' 'Intermediate' 'Hard' or 'Insane'";
				break;
			}
			
			System.out.println(response);
		}
		
		return dif;
	}
}
