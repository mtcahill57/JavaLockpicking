package pins;

public class NormalPin extends AbstractPin {

	protected NormalPin(int pos) {
		super(PinType.NORMAL, pos);
	}
	
	//lift pin
	@Override
	public String lift(boolean hints) { //normal pin will false set 20% of the time but can be lifted out of false set with same chance
		
		 //return based on hints enabled/disabled
		
		//if already set
		if (isTrueSet()) {
			return test(hints); //show pin status
		}
		
		//20% chance of false set on lift
		boolean liftFalseSet = (((int) (Math.random() * 5)) == 0); 
		
		if (liftFalseSet) {
			falseSet(); //pin now false set
		}
		else {
			trueSet(); //pin now true set
		}
		
		return test(hints); //show pin status
	}
	
	
	
}
