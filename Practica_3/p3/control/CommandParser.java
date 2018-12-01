package p3.control;

import p3.Exceptions.CommandParseException;

/** Class "CommandParser":
 * 
 *		Returns a new command by receiving the user input
 *
 *  */

public class CommandParser {
	
	/** List of the available commands */
	
	private static Command[] avaliableCommands = {
			new HelpCommand(),
			new ResetCommand(),
			new AddCommand(),
			new ListCommand(),
			new ListZombiesCommand(),
			new ExitCommand(),
			new UpdateCommand(),
			new PrintModeCommand()
		};
	
	/** Receives the user input and creates the new command by using the function parse on each of them
	 * 	returns "NULL" if there wasn't any corresponding command to the input
	 *  */
	
	public static Command parseCommand (String[] commandWords) throws CommandParseException{
		
		Command command;
		int i = 0;
		
		do {
			
			command = avaliableCommands[i].parse(commandWords);
			++i;
			
		} while (i < avaliableCommands.length && command == null);
		
		if (command != null) {
			return command;
		}
		else
			throw new CommandParseException("[Error]: Command not recognised.");
		
	}
	
	/** Appends all the helpText of the commands to be printed by HelpCommand */
	
	public static String commandHelp() {
		
		String text = "Avaliable commands: \n";
		int i = 0;
		
		do {
			
			text += avaliableCommands[i].helpText();
			++i;
			
		} while (i < avaliableCommands.length);
		
		return text;
		
	}

}
