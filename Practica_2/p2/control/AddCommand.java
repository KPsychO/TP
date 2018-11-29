package p2.control;

import p2.factory.PlantFactory;
import p2.logic.game.Game;
import p2.logic.objects.*;

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

	public void execute(Game game, Controller controller) {
		
		Plant auxplant = PlantFactory.getPlant(this.plant, this.x, this.y, game);
		
		game.addPlant(controller, auxplant);
		
	}

	public Command parse(String[] commandWords, Controller controller) {
		
		if (commandWords.length == 4 && (commandWords[0].equals(AddCommand.name) || commandWords[0].equals(AddCommand.symbol))) {
			
			return new AddCommand(commandWords[1], Integer.valueOf(commandWords[2]), Integer.valueOf(commandWords[3]));
			
		}
		
		else {
			
			return null;
			
		}
		
		
	}

}
