/**
 * 
 */
package gamecore;

import gamecore.Difficulty;
import events.Event;
import pins.*;
import pins.Pin.PinType;

/**
 * @author MatthewCahill
 *
 */
public class Core {

	private final int numPins;
	private Pin[] pins;
	private boolean tensioned;
	private boolean hintsEnabled;
	
	//hints, only displayed on applicable functions if enabled
	private String untensionedHint = (hintsEnabled) ? (" Tension the core to start again.") : ("");
	private String numSetPinsHint = (hintsEnabled) ? (" There are " + numSetPins() + " pins set.") : ("");
	
	public Core(Difficulty dif) {
		this.numPins = dif.getNumPins();
		this.pins = createPins(this.numPins, dif);
		this.tensioned = false;
		this.hintsEnabled = false;
	}
	
	//returns pins in the core
	public Pin[] getPins() {
		return pins;
	}
	
	//returns number of pins in the core
	public int numPins() {
		return numPins;
	}
	
	//process event, send to appropriate function or pin
	public String processEvent(Event e) {
		
		//event type dictates response
		switch(e.getType()) {
		
		case EXIT: //exit
			//exit handled in game class
			return "Exiting game.";
		
		case RESET: //reset
			System.out.println("Reseting core.");
			return reset(hintsEnabled); //reset core
		
		case TENSION: //tension
			System.out.println("Tensioning core.");
			String already = (isTensioned()) ? (" already ") : (" ");
			tension(); //tension core
			return "Core"+ already + "tensioned." + numSetPinsHint;	
		
		case TEST: //test (will changed based on hints enabled/disabled)
			String retTest = (tensioned) ? ("") : ("Core not tensioned. ");
			retTest = retTest.concat(pins[e.getPin()].test(hintsEnabled));
			return retTest;
			
		case LIFT: //lift (will changed based on hints enabled/disabled and core tensioned)
			System.out.println("Lifting pin " + e.getPin());
			String retLift = (hintsEnabled) ? ("Core not tensioned. Pin is not set.") : ("Pin is not set.");
			retLift = (tensioned) ? (pins[e.getPin()].lift(hintsEnabled)) : (retLift);
			String win = "\nCongrats, you win!";
			if (checkPins()) {retLift = retLift.concat(win);} //if won, add win string
			Game.setComplete(checkPins());
			return retLift;
			
		case UNTENSIONUNTILFALL: //untension (will changed based on hints enabled/disabled)
			System.out.println("Lowering tension.");
			return untensionUntilFall(); //will return which pin (if any) falls and if the core is fully untensioned
		
		case DISABLEHINTS: //disable hints
			String alreadyDisabled = (hintsEnabled()) ? (" ") : (" already ");
			disableHints();
			return "Hints" + alreadyDisabled + "disabled";
			
		case ENABLEHINTS: //enable hints
			String alreadyEnabled = (hintsEnabled()) ? (" already ") : (" ");
			enableHints();
			return "Hints" + alreadyEnabled + "enabled";
		}
		
		// if event type not found above
		return "EVENT TYPE ERROR";
	}
	
	//check pins for game complete
	private boolean checkPins() {
		for (Pin pin : pins) {
			if (!(pin.isTrueSet())) { //return false if any pin not true set
				return false;
			}
		}
		return true; //if all true set, game complete
	}
	
	//sets pin types based on difficulty
	private Pin[] createPins(int numPins, Difficulty dif) {
		
		Pin[] pins = new Pin[numPins];
		
		switch (dif.getLevel()) {
		case EASY: //all normal
			for (int i = 0; i < numPins; i++) {
				pins[i] = PinFactory.newPin(PinType.NORMAL, i);
			}
			break;
		
		case INTERMEDIATE: //all normal except one random
			for (int i = 0; i < numPins; i++) {
				pins[i] = PinFactory.newPin(PinType.NORMAL, i);
			}
			int randomOtherPinPos = (int) (Math.random() * numPins);
			pins[randomOtherPinPos] = PinFactory.RandomOtherPin(randomOtherPinPos);
			break;
		
		case HARD: // two normal, three random
			for (int i = 0; i < numPins; i++) {
				pins[i] = PinFactory.RandomOtherPin(i);
			}
			//gets random positions for two normal pins
			int NormalPin1Pos = (int) (Math.random() * numPins), NormalPin2Pos = (int) (Math.random() * numPins);
			//if equal, change the second one
			if (NormalPin1Pos == NormalPin2Pos) {
				NormalPin2Pos = (NormalPin2Pos + (((int) Math.random() * (numPins-1)) + 1)) % numPins;
			}
			//set normal pins
			pins[NormalPin1Pos] = PinFactory.newPin(PinType.NORMAL, NormalPin1Pos);
			pins[NormalPin2Pos] = PinFactory.newPin(PinType.NORMAL, NormalPin2Pos);
			break;
		
		case INSANE: //all random
			for (int i = 0; i < numPins; i++) {
				pins[i] = PinFactory.RandomPin(i);
			}
			break;
		}
		
		return pins;
	}
	
	//return if hints are enabled
	private boolean hintsEnabled() {
		return hintsEnabled;
	}
	
	//enable hints
	private void enableHints() {
		hintsEnabled = true;
	}
	
	//disable hints
	private void disableHints() {
		hintsEnabled = false;
	}
	
	//return if core is tensioned
	public boolean isTensioned() {
		return tensioned;
	}
	
	//reset pins and untension core
	private String reset(boolean giveHint) {
		for (Pin pin : pins) {
			pin.makeFall();
		}
		tensioned = false;
		return "Core Reset.\nAll pins released and core untensioned." + untensionedHint;
	}
	
	//tension the core
	private void tension() {
		tensioned = true;
	}
	
	//get number of set pins
	private int numSetPins() {
		int setPins = 0;
		for (Pin pin : pins) {
			if (pin.isSet()) {
				setPins++;
			}
		}
		return setPins;
	}
	
	//untension until fall
	private String untensionUntilFall() {
		//even if all pins are unset after this, core is not fully untensioned until this function is called again on a fully unset core
		
		int numSetPins = numSetPins();
		
		//if no set pins, return that core is fully untensioned
		if (numSetPins == 0) {
			tensioned = false;
			return "No pins set, core fully relieved of tension." + untensionedHint;
		}
		
		//select a random pin to fall
		int randomFallPos = (int) (Math.random() * numPins);
		
		//validate that the pin is set
		while (!(pins[randomFallPos].isSet())) { 
			randomFallPos = (int) (Math.random() * numPins);
		}
		
		//pin falls
		pins[randomFallPos].makeFall();
		
		//tell that a pin fell
		String retNoPinHint = "A pin fell!";
		
		//if hints, tell which pin fell
		String retWithPinHint = "Pin " + randomFallPos + " fell! Core is still tensioned.";
		
		//return based on hint
		return (hintsEnabled) ? (retWithPinHint) : (retNoPinHint);
	}
	
}
