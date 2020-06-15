package gamecore;

public class Difficulty {
	
	// enum for difficulty level
	public enum Level {
		EASY, INTERMEDIATE, HARD, INSANE;
	}
	
	private Level level;
	private String id;
	private int numPins;
	
	// constructor
	public Difficulty(Level l) {
		this.level = l;
		this.id = getID(l);
		this.numPins = getNumPins(l);
	}
	
	public Level getLevel() {
		return level;
	}
	
	public String getID() {
		return id;
	}
	
	public int getNumPins() {
		return numPins;
	}
	
	//get id of specific level
	public static String getID(Level l) {
		switch (l) {
		case EASY: //4 pins
			return "Easy";
		case INTERMEDIATE: //5 pins
			return "Intermediate";
		case HARD: //5 pins
			return "Hard";
		case INSANE: //6 pins
			return "Insane";
		}
		return null;
	}
	
	//gets the number of pins by difficulty
	public static int getNumPins(Level l) {
		switch (l) {
		case EASY: //4 pins
			return 4;
		case INTERMEDIATE: //5 pins
			return 5;
		case HARD: //5 pins
			return 5;
		case INSANE: //6 pins
			return 6;
		}
		return 0; //shouldn't happen
	}
}
