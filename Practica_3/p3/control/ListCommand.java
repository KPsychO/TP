package p3.control;

import p3.factory.PlantFactory;
import p3.logic.game.Game;

public class ListCommand extends NoParamsCommand{

	public static final String symbol = "l";
	public static final String name = "list";
	public static final String help = "Lists the avaliable plants.";
	
	public ListCommand() {
		
		super(ListCommand.symbol, ListCommand.name, ListCommand.help);
		
	}

	public void execute(Game game, Controller controller) {

		System.out.println(PlantFactory.listAvaliablePlants());
		game.list(controller);
		
	}
	
}