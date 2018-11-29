package p2.logic.game;

import p2.util.*;

/** Class "DebugPrinter":
 * 
 *		I dare you to use that view for debugging purposes. Prints some extra information
 *
 *  */

public class DebugPrinter extends BoardPrinter implements GamePrinter{
	
	@Override
	public void printGame(Game game) {

		boardToString(game);
	
	}
	
	public void boardToString(Game game) {
		
		board = new String[1][game.getObjectCount()];
		String str = "";
		
		str = encodeGame(board, game);
		
		System.out.println("Current cicle : " + game.GetCurrentCicle());
		System.out.println("SunCoins : " + game.GetSuns());
		System.out.println("Zombies remaining : " + game.GetZombiesRemaining());
		System.out.println("Level : " + game.GetDif());
		System.out.println("Seed : " + game.GetSeed());
		System.out.println("###########################");
		System.out.print(str);
		
	}
	
	@Override
	public String encodeGame(String[][] board, Game game) {
		
		String str = "";
		
		if (game.getObjectCount() > 0) {
		
			for (int i = 0; i < game.getObjectCount(); i++) {
				
				board[0][i] = game.objectDebugToString(i);
				
			}
	
			str = toString(this.board, game);
			
		}
		
		return str;
	}

	public String toString(String[][] board, Game game) {

		int dimY = game.getObjectCount();
		int dimX = 1;
		int cellSize = 25;
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
