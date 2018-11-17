package p2.control;

import p2.control.Controller;
import p2.logic.game.Game;

public class ExitCommand extends NoParamsCommand{
	
	public static final String symbol = "e";
	public static final String name = "exit";
	public static final String help = "Ends the game and terminates the program.";
	
	public ExitCommand() {
		
		super(ExitCommand.symbol, ExitCommand.name, ExitCommand.help);
		
	}
	
	public void execute(Game game, Controller controller) {
		
		game.exit(controller);
		
	}

}
