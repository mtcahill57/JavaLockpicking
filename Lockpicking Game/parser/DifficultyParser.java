package parser;

import java.util.Scanner;

import gamecore.Difficulty;
import gamecore.Difficulty.Level;

public class DifficultyParser {

	private Scanner scanner;
	
	// constructor
	public DifficultyParser(Scanner scanner) {
		this.scanner = scanner;
	}
	
	// create itemized difficulty prompt
	private String getDifficultyPrompt() {
		String difficultyPrompt = "Select difficulty from ";
		String separator = "";
		
		// concat difficulties into prompt
		for (int i = 0; i < Level.values().length; i++) {
			difficultyPrompt = difficultyPrompt.concat(separator + Difficulty.getID(Level.values()[i]));
			
			// separator to make list "x, y, or z"
			separator = (i != Level.values().length -2) ? ", " : ", or ";
		}
		
		return difficultyPrompt;
	}
	
	//returns user selected difficulty
	public Difficulty getDifficulty() {
		
		//print prompt
		String difficultyPrompt = this.getDifficultyPrompt();
		System.out.println(difficultyPrompt);
		
		Difficulty dif = null;
		boolean difSelected = false;
		
		String response = "";
		
		//loop to find valid difficulty selection
		while (difSelected == false) {
			String select = scanner.nextLine();
			select = select.toLowerCase();
			
			// check input for valid difficulty id
			for (Level l : Level.values()) {
				
				String id = Difficulty.getID(l);
				
				if (select.contains(id.toLowerCase())) {
					dif = new Difficulty(l);
					difSelected = true;
				}
			}
			if (difSelected) {
				response = "Difficulty Selected: " + dif.getID();
			}
			else {
				response = "Invalid response. " + difficultyPrompt;
			}
			System.out.println(response);
		}
		
		return dif;
	}
}
