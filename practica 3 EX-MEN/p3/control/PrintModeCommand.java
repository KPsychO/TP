package p3.control;

import p3.Exceptions.CommandExecuteException;
import p3.Exceptions.CommandParseException;
import p3.logic.game.Game;

public class PrintModeCommand extends Command{
	
	public static final String symbol = "p";
	public static final String name = "printmode";
	public static final String help = "Changes the print mode [Relase | Debug].";
	private String mode;

	public PrintModeCommand() {
		
		super(PrintModeCommand.symbol, PrintModeCommand.name, PrintModeCommand.help);
		
	}
	
	public PrintModeCommand(String mode) {

		super(PrintModeCommand.symbol, PrintModeCommand.name, PrintModeCommand.help);
		this.mode = mode;
		
	}

	public void execute(Game game) throws CommandExecuteException {
		
		game.changePrintMode(this.mode);
		
	}

	public Command parse(String[] commandWords) throws CommandParseException {
		
		if ((commandWords[0].equals(PrintModeCommand.name) || commandWords[0].equals(PrintModeCommand.symbol))) {
			
			if (commandWords.length == 2) {
			
				return new PrintModeCommand(commandWords[1]);
			
			}
			else
				throw new CommandParseException("[ERROR]: print (p) takes 1 argument: 'printMode'.\n");
			
		}
		
		else {
			
			return null;
			
		}
		
		
	}

}
