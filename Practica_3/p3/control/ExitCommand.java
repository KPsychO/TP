package p3.control;

import p3.control.Controller;
import p3.logic.game.Game;

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
