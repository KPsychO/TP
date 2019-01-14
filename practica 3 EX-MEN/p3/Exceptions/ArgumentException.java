package p3.Exceptions;

/** Exception thrown when some command recieves invalid arguments. */

@SuppressWarnings("serial")
public class ArgumentException extends Exception {

	public ArgumentException(String msg) {
		super(msg);
	}
	
}


// To add a new exception, copy and rename this ""TEMPLATE"", use as we use this in all the project
// Most exceptions are catched in "controller" and thrown in "game"