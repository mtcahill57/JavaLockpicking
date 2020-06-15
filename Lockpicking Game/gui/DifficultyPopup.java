package gui;

import gamecore.Difficulty;
import gamecore.Difficulty.Level;

public class DifficultyPopup {

	// TODO
	public DifficultyPopup() {
		
	}
	
	
	public Difficulty resolve() {
		//TODO implement the resolve to close window after button choice
		return new Difficulty(Level.EASY);
	}
}
