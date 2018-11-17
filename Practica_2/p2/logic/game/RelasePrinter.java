package p2.logic.game;

import p2.util.*;

/** Class "RelasePrinter":
 * 
 *		The cute board where you see some things happening, extends BoardPrinter and implements GamePrinter
 *
 *  */

public class RelasePrinter extends BoardPrinter implements GamePrinter{
	
	@Override
	public void printGame(Game game) {

		boardToString(game);
	
	}
	
	public void boardToString(Game game) {
		
		board = new String[4][8];
		String str = "";
		
		for (int i = 0; i < 4; i++) {
			
			for (int j = 0; j < 8; j++) {
				
				board[i][j] = space;
				
			}
			
		}
		
		str = encodeGame(board, game);
		
		System.out.println("Current cicle : " + game.GetCurrentCicle());
		System.out.println("SunCoins : " + game.GetSuns());
		System.out.println("Zombies remaining : " + game.GetZombiesRemaining());
		System.out.println("###########################");
		System.out.print(str);
		
	}
	
	@Override
	public String encodeGame(String[][] board, Game game) {
		
		String str = "";
		
		for (int i = 0; i < game.getObjectCount(); i++) {
			
			board[game.getObjectY(i)][game.getObjectX(i)] = game.objectToString(i);
			
		}

		str = toString(this.board);
		
		return str;
	}

	public String toString(String[][] board) {

		int dimY = 8;
		int dimX = 4;
		int cellSize = 7;
		int marginSize = 2;
		String vDelimiter = "|";
		String hDelimiter = "-";
		
		String rowDelimiter = MyStringUtils.repeat(hDelimiter, (dimY * (cellSize + 1)) - 1);
		String margin = MyStringUtils.repeat(space, marginSize);
		String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);
		
		StringBuilder str = new StringBuilder();
				
		str.append(lineDelimiter);
		
		for(int i = 0; i < dimX; i++) {
				
				str.append(margin).append(vDelimiter);
				
				for (int j = 0; j < dimY; j++) {
					
					str.append( MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
					
				}
				
				str.append(lineDelimiter);
				
		}
		
		return str.toString();
		
	}

}
