package p2.control;

import p2.logic.game.Game;

public class HelpCommand extends NoParamsCommand{
	
	public static final String symbol = "h";
	public static final String name = "help";
	public static final String help = "Displays this help message.";

	public HelpCommand() {
		
		super(HelpCommand.symbol, HelpCommand.name, HelpCommand.help);

	}

	public void execute(Game game, Controller controller) {

		game.help(controller);
		
	}

}
