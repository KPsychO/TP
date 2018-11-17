package p2.control;

import p2.control.Controller;
import p2.logic.game.Game;

/** Class "Command":
 * 
 *		Defines the commadSymbol, commandText (name), helpText (small command description), and the command text (parameters).
 *
 *  */

public abstract class Command {
	
	protected final String commandSymbol;
	private final String helpText;
	protected final String commandName;
	private String commandText;
	
	/** Recieves the name, the symbol and the input from the user and initializes the atributes acordingly */
	
	public Command(String symbol, String commandInfo, String helpInfo) {
		
		this.commandSymbol = symbol;
		this.commandText = commandInfo;
		this.helpText = helpInfo;
		String[] commandInfoWords = commandText.split("\\s+"); //eliminates blank spaces
		commandName = commandInfoWords[0];
		
	}
	
	/** Executes the corresponding action in the classs game */
	
	public abstract void execute (Game game, Controller controller);
	
	/** Recieves the input from the user to create a new command with the corresponding info, also assigns the controller*/
	public abstract Command parse (String[] commandWords, Controller controller);
	
	/** Generates the HelpCommand output for each of the commands */
	
	public String helpText () {
		
		return " [" + commandSymbol + "] " + commandName + ": " + helpText + "\n";
		
	}

}
