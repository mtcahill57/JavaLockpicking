package pins;

class SpoolPin extends AbstractPin {
	
	protected SpoolPin(int pos) {
		super(PinType.SPOOL, pos);
	}
	
	//lift pin
	@Override
	public String lift(boolean hints) { //spool pin will false set 30% of the time and can't be lifted out of a false set
		
		//return based on hints enabled/disabled
		
		//if already set (true or false set)
		if (isSet()) {
			return test(hints); //show pin status
		}
		
		//30% chance of false set on lift
		boolean liftFalseSet = (((int) (Math.random() * 10)) <= 2); 
		
		if (liftFalseSet) {
			falseSet(); //pin now false set
		}
		else {
			trueSet(); //pin now true set
		}
		
		return test(hints); //show pin status
	}
	
	@Override
	//test pin
	public String test(boolean hints) {
		if (hints) {
			String correctly = (isFalseSet()) ? (" in a false ") : (" correctly ");
			String is = (isSet()) ? (correctly) : (" not ");
			String spoolFalseSetHelp = (isFalseSet()) ? (" A spool pin must be untensioned and tried again to successfully set.") : ("");
			return "Pin is" + is + "set." + spoolFalseSetHelp;
		}
		else {
			String not = (isSet()) ? (" ") : (" not ");
			return "Pin is" + not + "set.";
		}
	}
	
}
