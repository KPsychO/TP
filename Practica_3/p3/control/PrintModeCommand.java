package p3.control;

import p3.logic.game.Game;

public class PrintModeCommand extends Command{
	
	public static final String symbol = "p";
	public static final String name = "printmode";
	public static final String help = "Changes the print mode [Relase | Debug].";
	private String mode;

	public PrintModeCommand() {
		
		super(PrintModeCommand.symbol, PrintModeCommand.name, PrintModeCommand.help);
		
	}
	
	public PrintModeCommand(String mode) {

		super(PrintModeCommand.symbol, PrintModeCommand.name, PrintModeCommand.help);
		this.mode = mode;
		
	}

	public void execute(Game game) {
		
		game.changePrintMode(this.mode);
		
	}

	public Command parse(String[] commandWords) {
		
		if (commandWords.length == 2 && (commandWords[0].equals(PrintModeCommand.name) || commandWords[0].equals(PrintModeCommand.symbol))) {
			
			return new PrintModeCommand(commandWords[1]);
			
		}
		
		else {
			
			return null;
			
		}
		
		
	}

}
