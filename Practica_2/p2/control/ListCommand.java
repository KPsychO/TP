package p2.control;

import p2.logic.game.Game;

public class ListCommand extends NoParamsCommand{

	public static final String symbol = "l";
	public static final String name = "list";
	public static final String help = "Lists the avaliable plants.";
	
	public ListCommand() {
		
		super(ListCommand.symbol, ListCommand.name, ListCommand.help);
		
	}

	public void execute(Game game, Controller controller) {

		game.list(controller);
		
	}
	
	

}
