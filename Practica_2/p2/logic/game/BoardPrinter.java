package p2.logic.game;

/** Class "BoardPrinter":
 * 
 *		Creates a board and defines the FUNDAMENTAL "space" variable
 *
 *  */

public abstract class BoardPrinter {
	
	String[][] board;
	final String space = " ";
	
	/** Creates an empty board, calls the encode method and prints some things depending on the print mode */
	
	public abstract void boardToString(Game game);
	
	/** Transforms the ObjectList list into a printable, bidimensional (on relase mode) board */
	
	public abstract String encodeGame(String[][] board, Game game);

}
