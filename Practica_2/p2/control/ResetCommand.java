package p2.control;

import p2.control.Controller;
import p2.logic.game.Game;

public class ResetCommand extends NoParamsCommand{
	
	public static final String symbol = "r";
	public static final String name = "reset";
	public static final String help = "Resets current game.";
	
	public ResetCommand() {
		
		super(ResetCommand.symbol, ResetCommand.name, ResetCommand.help);
		
	}
	
	public void execute(Game game, Controller controller) {
		
		game.reset();
		
	}

}
