package pins;

public class SerratedPin extends AbstractPin {

	private final int serrations;
	private int currentSerration;
	
	protected SerratedPin(int pos, int serrations) {
		super(PinType.SERRATED, pos);
		this.serrations = serrations;
		this.currentSerration = 0;
	}
	
	//lift pin
	@Override
	public String lift(boolean hints) { //serrated pin will false set 30% of the time but can be lifted out of false set
						   //false sets can happen once per serration and chain into each other
		
		//return based on hints enabled/disabled
		
		//if already set
		if (isTrueSet()) {
			return test(hints); //show pin status
		}
		
		//tries to set past each serration, if it gets through them all it will be true set
		for (;currentSerration < serrations; currentSerration++) {
			
			//30% chance of false set on lift
			boolean liftFalseSet = (((int) (Math.random() * 10)) <= 2); 
			
			if (liftFalseSet) {
				falseSet(); //pin now false set
				break; //don't increment again
			}
		}
		
		if (currentSerration == serrations) { //if all serrations passed
			trueSet();
		}
		
		return test(hints); //show pin status
	}

	@Override
	//test pin
	public String test(boolean hints) {
		if (hints) {
			String correctly = (isFalseSet()) ? (" in a false ") : (" correctly ");
			String is = (isSet()) ? (correctly) : (" not ");
			String serrFalseSetHelp = (isFalseSet()) ? (" A serrated pin can be lifted out of a false set.") : ("");
			return "Pin is" + is + "set." + serrFalseSetHelp;
		}
		else {
			String not = (isSet()) ? (" ") : (" not ");
			return "Pin is" + not + "set.";
		}
	}
	
	//return current and total serrations, used for debugging
	public String serrationInfo() {
		if (!(isSet())) {
			return "Not currently binding. Total serrations: " + numSerrations();
		}
		else if (isTrueSet()) {
			return "Pin truely set. Total serrations: " + numSerrations();
		}
		//if false set on a serration
		return "Currently binding on serration " + currentBindingSerration() + " out of " + numSerrations();
	}
	
	//return number of serrations in pin
	private int numSerrations() {
		return serrations;
	}
	
	//return current binding serration
	private int currentBindingSerration() {
		return currentSerration;
	}
	
}
