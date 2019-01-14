package p3.Exceptions;

/** Exception thrown when there's a problem during a command execution */

@SuppressWarnings("serial")
public class CommandExecuteException extends Exception {

	public CommandExecuteException(String msg) {
		super(msg);
	}
	
}
