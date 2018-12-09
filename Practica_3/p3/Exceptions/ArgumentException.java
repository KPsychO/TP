package p3.Exceptions;

/** Exception thrown when some command recieves invalid arguments. */

@SuppressWarnings("serial")
public class ArgumentException extends Exception {

	public ArgumentException(String msg) {
		super(msg);
	}
	
}
