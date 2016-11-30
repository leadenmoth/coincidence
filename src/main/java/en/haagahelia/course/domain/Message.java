package en.haagahelia.course.domain;

//Class to store messages the user will receive
public class Message {
	//Percent value the user will specify for the request
	private int value;
	//The first message to generate based on the value
	private String msg1;
	//Second message to generate based on the value
	private String msg2;
	//A flag to show if we had to adjust the value
	private boolean match;
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getMsg1() {
		return msg1;
	}
	public void setMsg1(String msg1) {
		this.msg1 = msg1;
	}
	public String getMsg2() {
		return msg2;
	}
	public void setMsg2(String msg2) {
		this.msg2 = msg2;
	}
	
	public boolean isMatch() {
		return match;
	}
	public void setMatch(boolean match) {
		this.match = match;
	}
	@Override
	public String toString() {
		return "Message [value=" + value + ", msg1=" + msg1 + ", msg2=" + msg2 + "]";
	}
	

}
