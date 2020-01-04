package pins;

import pins.Pin.PinType;

public class PinFactory {
	
	//makes pin
	public static Pin newPin(PinType type, int pos) {
		
		// TODO update whenever a new pin type is added
		switch (type) {
		case NORMAL:
			return new NormalPin(pos);
		case SPOOL:
			return new SpoolPin(pos);
		case SERRATED:
			int serrations = (((int) (Math.random() * 4)) + 1); //between 1 and 4 serrations
			return new SerratedPin(pos, serrations);
		}
		
		//TODO add exception throw
		return null;
	}
	
	//random pin from all types
	public static Pin RandomPin(int pos) {
		int rand = (int) (Math.random() * PinType.values().length);
		PinType type = PinType.values()[rand];
		
		return newPin(type, pos);
	}
	
	//random pin from all non-normal types
	public static Pin RandomOtherPin(int pos) {
		int rand = (int) ((Math.random() * PinType.values().length - 1) + 1); //selects from pintypes != normal
		PinType type = PinType.values()[rand];
		
		return newPin(type, pos);
	}
	
}
