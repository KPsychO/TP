package p2.control;

import java.util.*;
import p2.logic.game.*;

/** Class "Controller":
 * 
 *		Controls the execution of the game.
 *		Recieves the commands from the user and executes the corresponding actions, calls for the updates of the board and prints the game.
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
	
	/** Recieves the game and assigns the default values for the control variables of the game */
	
	public Controller(Game game) {
		
		this.game = game;
		this.in = new Scanner(System.in);
		this.print = true;
		this.exit = false;
		this.update = true;
		this.GPrinter = new RelasePrinter();
		
	}
	
	/** Recieves the command input, calls for the parse of the command and checks if it is a valid input. Then executes the corresponding actions
	 * 	May or may not print & Update the game
	 * 	Checks if the game keeps going or is ended
	 *  */
	
	public void Run () {
		
		String input;
		String[] command;
		
		this.game.update();
		printGame(this.game);
		
		do {
			
			System.out.print("Command > ");
			
			this.print  = true;
			this.update = true;
			
			input = in.nextLine();
			command = input.toLowerCase().split(" ");
			
			this.command = CommandParser.parseCommand(command, this);
			
			if (this.command != null) {
				
				this.command.execute(this.game, this);
				
				if (this.update)
					this.game.update();
				
				if (this.print)
					printGame(this.game);

				
				if (!exit)
					exit = game.endGame();
				
			}
			
			else {
				
				System.out.println("[ERROR]: Command not recognised, try again.");
				
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
	
	/** Recieves the desired printing mode and asigns the new GamePrinter */
	
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