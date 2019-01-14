package p3.control;

import p3.Exceptions.CommandParseException;

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
	
	/** Receives the command name and the controller, if valid, returns the new command assigning the controller 
	 * @throws CommandParseException */
	
	public Command parse (String[] commandWords) throws CommandParseException {
		
		if (commandWords[0].equals(this.commandName) || commandWords[0].equals(this.commandSymbol)) {
			
			if (commandWords.length == 1) {
				
				return this;
				
			}
			else {
				
				throw new CommandParseException("[ERROR]: " + this.commandName + " (" + this.commandSymbol + ") takes no arguments.\n");
				
			}
			
		}
		
		else
			return null;
		
	}

}
