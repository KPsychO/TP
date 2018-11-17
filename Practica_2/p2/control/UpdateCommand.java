package p2.control;

import p2.logic.game.Game;

public class UpdateCommand extends NoParamsCommand{
	
	public static final String symbol = "";
	public static final String name = "update";
	public static final String help = "Updates the game.";

	public UpdateCommand() {
		
		super(UpdateCommand.symbol, UpdateCommand.name, UpdateCommand.help);

	}

	public void execute(Game game, Controller controller) {

		game.none(controller);
		
	}

}
