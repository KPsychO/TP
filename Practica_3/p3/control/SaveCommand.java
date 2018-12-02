package p3.control;

import java.io.IOException;

import p3.Exceptions.CommandExecuteException;
import p3.Exceptions.CommandParseException;
import p3.logic.game.Game;

public class SaveCommand extends Command{
	
	public static final String symbol = "s";
	public static final String name = "save";
	public static final String help = "Saves the current game state.";
	private String fileName;

	public SaveCommand() {
		
		super(PrintModeCommand.symbol, PrintModeCommand.name, PrintModeCommand.help);
		
	}
	
	public SaveCommand(String fileName) {

		super(PrintModeCommand.symbol, PrintModeCommand.name, PrintModeCommand.help);
		this.fileName = fileName;
		
	}

	public void execute(Game game) throws CommandExecuteException {
		
		try {
			
			game.saveState(this.fileName);
			
		} catch (IOException ex) {
			
			ex.printStackTrace();
			
		}
		
	}

	public Command parse(String[] commandWords) throws CommandParseException {
		
		if ((commandWords[0].equals(SaveCommand.name) || commandWords[0].equals(SaveCommand.symbol))) {
			
			if (commandWords.length == 2) {
			
				return new SaveCommand(commandWords[1]);
			
			}
			else
				throw new CommandParseException("[ERROR]: save (s) takes 1 argument: 'fileName'.\n");
			
		}
		
		else {
			
			return null;
			
		}
		
		
	}

}
