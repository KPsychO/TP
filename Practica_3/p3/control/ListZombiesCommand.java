package p3.control;

import p3.logic.game.Game;

public class ListZombiesCommand extends NoParamsCommand{

	public static final String symbol = "lz";
	public static final String name = "listzombies";
	public static final String help = "Lists the avaliable zombies.";
	
	public ListZombiesCommand() {
		
		super(ListZombiesCommand.symbol, ListZombiesCommand.name, ListZombiesCommand.help);
		
	}

	public void execute(Game game, Controller controller) {

		game.listZombies(controller);
		
	}
	
	

}
