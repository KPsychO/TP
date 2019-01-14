package p3.control;

import java.io.IOException;

import p3.Exceptions.ArgumentException;
import p3.Exceptions.CommandExecuteException;
import p3.Exceptions.CommandParseException;
import p3.Exceptions.LoadException;
import p3.logic.game.Game;

public class LoadCommand extends Command{
	
	public static final String symbol = "ld";
	public static final String name = "load";
	public static final String help = "Saves the current game state.";
	private String fileName;

	public LoadCommand() {
		
		super(LoadCommand.symbol, LoadCommand.name, LoadCommand.help);
		
	}
	
	public LoadCommand(String fileName) {

		super(LoadCommand.symbol, LoadCommand.name, LoadCommand.help);
		this.fileName = fileName;
		
	}

	public void execute(Game game) throws CommandExecuteException {
		
		try {
			
			game.loadState(this.fileName);
			
		} catch (IOException | LoadException | ArgumentException ex) {
			
			ex.printStackTrace();
			
		}
		
	}

	public Command parse(String[] commandWords) throws CommandParseException {
		
		if ((commandWords[0].equals(LoadCommand.name) || commandWords[0].equals(LoadCommand.symbol))) {
			
			if (commandWords.length == 2) {
			
				return new LoadCommand(commandWords[1]);
			
			}
			else
				throw new CommandParseException("[ERROR]: load (ld) takes 1 argument: 'fileName'.\n");
			
		}
		
		else {
			
			return null;
			
		}
		
		
	}

}
