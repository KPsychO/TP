package p3.control;

import java.util.*;
import p3.logic.game.*;
import p3.Exceptions.*;

/** Class "Controller":
 * 
 *		Controls the execution of the game.
 *		Receives the commands from the user and executes the corresponding actions, calls for the updates of the board and prints the game.
 *
 *  */


public class Controller {
	
	private Game game;
	private Scanner in;
	private Boolean print;
	private Boolean exit;
	private Boolean update;
	private Command command;
	private GamePrinter GPrinter;
	
	/** Receives the game and assigns the default values for the control variables of the game */
	
	public Controller(Game game) {
		
		this.game = game;
		this.in = new Scanner(System.in);
		this.print = true;
		this.exit = false;
		this.update = true;
		this.GPrinter = new RelasePrinter();
		
		this.game.setController(this);
		
	}
	
	/** Receives the command input, calls for the parse of the command and checks if it is a valid input. Then executes the corresponding actions
	 * 	May or may not print & update the game
	 * 	Checks if the game keeps going or is ended
	 *  */
	
	public void Run () {
		
		String[] command;
		
		this.game.update();
		printGame(this.game);
		
		do {
			
			System.out.print("Command > ");
			
			this.print  = true;
			this.update = true;
			
			command =  in.nextLine().trim().toLowerCase().split("\\s+");
			
			try {
			
				this.command = CommandParser.parseCommand(command);
								
				this.command.execute(this.game);
				
				if (this.update)
					this.game.update();
				
				if (this.print)
					printGame(this.game);

				
				if (!exit)
					exit = game.endGame();
					
			} catch (CommandParseException | CommandExecuteException ex) {
							
				System.out.format(ex.getMessage() + "%n%n");
				
			}
			
		} while (!exit);
		
	}
	
	/** Calls for the game print */
	
	public void printGame(Game game) {
		
		this.GPrinter.printGame(this.game);
		
	}
	
	/** Avoids printing the game this turn */
	
	public void setNoPrintGameState() {
		
		this.print = false;
		
	}
	
	/** Avoids updating the game this turn */
	
	public void setNoUpdateGameState() {
		
		this.update = false;
		
	}
	
	/** Receives the desired printing mode and assigns the new GamePrinter */
	
	public void setPrintMode(String mode) {
		
		switch (mode) {
		
		case "debug": case "d": this.GPrinter = new DebugPrinter(); break;
		case "relase": case "r": this.GPrinter = new RelasePrinter(); break;
		default: this.GPrinter = new RelasePrinter(); break;
		
		}
		
	}
	
	/** Ends the game */
	
	public void exit () {
		
		this.exit = true;
		
	}
	
}