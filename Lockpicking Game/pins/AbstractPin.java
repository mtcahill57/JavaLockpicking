package pins;

abstract class AbstractPin implements Pin {

	private PinType type; //the pin's type
	private int position; //the pin's relative position in the core, starts at 0
	private boolean trueSet; //a true set means the pin is not binding, when all pins are set the core will rotate
	private boolean falseSet; //false setting can occur on certain pin types
	
	protected AbstractPin(PinType type, int pos) {
		this.type = type;
		this.position = pos;
		this.trueSet = false;
		this.falseSet = false;
	}
	
	//test pin
	public String test(boolean hints) {
		if (hints) {
			String correctly = (isFalseSet()) ? (" in a false ") : (" correctly ");
			String is = (isSet()) ? (correctly) : (" not ");
			return "Pin is" + is + "set.";
		}
		else {
			String not = (isSet()) ? (" ") : (" not ");
			return "Pin is" + not + "set.";
		}
	}
	
	//lift pin
	public String lift(boolean hints) {
		return "ABSTRACT LIFT";
	}
	
	//make pin fall (unset)
	public void makeFall() {
		trueSet = false;
		falseSet = false;
	}
	
	//return pin type
	public PinType getType() {
		return type;
	}
	
	//return relative position of pin in core
	public int getPosition() {
		return position;
	}
	
	//return if pin is set at all
	public boolean isSet() {
		return trueSet || falseSet;
	}
	
	//return if pin is incorrectly set
	public boolean isFalseSet() {
		return falseSet;
	}
	
	//return if pin is correctly set
	public boolean isTrueSet() {
		return trueSet;
	}
	
	//set pin in a false set
	public void falseSet() {
		falseSet = true;
		trueSet = false;
	}
	
	//set pin in a true set
	public void trueSet() {
		falseSet = false;
		trueSet = true;
	}	
}
