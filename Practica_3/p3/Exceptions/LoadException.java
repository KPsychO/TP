package p3.Exceptions;

/** Exception thrown if there's any problem while trying to load a new game. It's not a CommandExecuteException because it references problems related with corrupted files. */

@SuppressWarnings("serial")
public class LoadException extends Exception {

	public LoadException(String msg) {
		super(msg);
	}
	
}
