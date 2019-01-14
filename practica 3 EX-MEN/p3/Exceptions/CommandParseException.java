package p3.Exceptions;

/** Exception thrown when there's a problem while parsing a command */

@SuppressWarnings("serial")
public class CommandParseException extends Exception {

	public CommandParseException(String msg) {
		super(msg);
	}
	
}
