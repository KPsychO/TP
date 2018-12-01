package p3.control;

import p3.Exceptions.*;
import p3.factory.PlantFactory;
import p3.logic.game.Game;
import p3.logic.objects.*;

public class AddCommand extends Command{
	
	public static final String symbol = "a";
	public static final String name = "add";
	public static final String help = "Adds the desired plant to the board.";
	private String plant;
	private int x;
	private int y;

	public AddCommand() {
		
		super(AddCommand.symbol, AddCommand.name, AddCommand.help);
		
	}
	
	public AddCommand(String plant, int x, int y) {

		super(AddCommand.symbol, AddCommand.name, AddCommand.help);
		this.plant = plant;
		this.x = x;
		this.y = y;
		
	}

	public void execute(Game game) throws CommandExecuteException {
		
		Plant auxplant = PlantFactory.getPlant(this.plant, this.x, this.y, game);
		
		game.addPlant(auxplant);
		
	}

	public Command parse(String[] commandWords) throws CommandParseException {
		
		if ((commandWords[0].equals(AddCommand.name) || commandWords[0].equals(AddCommand.symbol))) {
			
			if (commandWords.length == 4) {
			
				return new AddCommand(commandWords[1], Integer.valueOf(commandWords[2]), Integer.valueOf(commandWords[3]));
				
			}
			else 
				throw new CommandParseException("[ERROR]: add (a) takes 3 arguments: 'plantName', 'x', 'y'.\n");
			
		}
		
		else {
			
			return null;
			
		}
		
		
	}

}
