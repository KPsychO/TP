package p2.control;

import p2.control.Controller;

/** Class "NoParamsCommand":
 * 
 *		Defines the commadSymbol, commandText (name) and helpText (small command description).
 *
 *  */

public abstract class NoParamsCommand extends Command{
	
	/** Executes the super constructor for a command */
	
	public NoParamsCommand (String commandSymbol, String commandInfo, String helpInfo) {
		
		super(commandSymbol, commandInfo, helpInfo);
		
	}
	
	/** Receives the command name and the controller, if valid, returns the new command assigning the controller */
	
	public Command parse (String[] commandWords, Controller controller) {
		
		if ((commandWords.length == 1) && (commandWords[0].equals(this.commandName) || commandWords[0].equals(this.commandSymbol))) {
			
			return this;
			
		}
		
		else {
			
			return null;
			
		}
		
	}

}
