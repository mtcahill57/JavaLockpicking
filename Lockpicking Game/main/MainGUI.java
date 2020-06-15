package main;

import gamecore.Game;
import gamecore.Difficulty;
import gui.DifficultyPopup;
import gui.GUI;
import javax.swing.WindowConstants;

/**
 * @author MatthewCahill
 * This is my Java 9 GUI-Interactive Lockpicking Game
 * To play the text-interactive version, start MainText.java
 * Any updates to pin types and event (action) types can be made to the respective classes and will require implementing in Core.java
 * GUI ControlPanel should be updated for new events
 * To add a difficulty, add the name, id, and pin number in Difficulty.java
 */

public class MainGUI {
	
	public static void main(String[] args) {
		
		//establish difficulty from popup resolution
		DifficultyPopup difficultyPopup = new DifficultyPopup();
		Difficulty dif = difficultyPopup.resolve();
		
		Game game = new Game(dif);
		
		GUI gui = new GUI(game);
		gui.setBounds(20, 50, 600, 500);
	    gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 3
	    gui.setVisible(true);
	}
}
