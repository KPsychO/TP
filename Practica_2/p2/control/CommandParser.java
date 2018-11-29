package p2.control;

import p2.control.Controller;

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
	
	public static Command parseCommand (String[] commandWords, Controller controller) {
		
		Command command;
		int i = 0;
		
		do {
			
			command = avaliableCommands[i].parse(commandWords, controller);
			++i;
			
		} while (i < avaliableCommands.length && command == null);
		
		return command;
		
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
