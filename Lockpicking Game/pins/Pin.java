package pins;

public interface Pin {
	enum PinType {
		NORMAL, SPOOL, SERRATED
	}
	
	public PinType getType();
	public int getPosition();
	public String lift(boolean hints);
	public String test(boolean hints);
	public void makeFall();
	public boolean isSet();
	public boolean isFalseSet();
	public boolean isTrueSet();
	public void falseSet();
	public void trueSet();
	
}
