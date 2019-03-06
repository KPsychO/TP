package p3.control;

import p3.Exceptions.ArgumentException;
import p3.logic.game.Game;

public class ResetCommand extends NoParamsCommand{
	
	public static final String symbol = "r";
	public static final String name = "reset";
	public static final String help = "Resets current game.";
	
	public ResetCommand() {
		
		super(ResetCommand.symbol, ResetCommand.name, ResetCommand.help);
		
	}
	
	public void execute(Game game) {
		
		try {
			game.reset();
			
		} catch (ArgumentException ex) {

			System.out.format(ex.getMessage() + "%n%n");
			
		}
		
	}

}