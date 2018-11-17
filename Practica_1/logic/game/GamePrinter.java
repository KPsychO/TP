package logic.game;

import util.MyStringUtils;

public class GamePrinter {
	
	Game game;
	int dimX; 
	int dimY;
	String[][] board;
	final String space = " ";
	
	/** Constructor de GamePrinter */
	
	public GamePrinter(Game game, int dimX, int dimY) {
		
		this.game = game;
		this.dimX = dimX;
		this.dimY = dimY;
		
		encodeGame(game);
		
	}
	
	/** Crea un array bidimensional de String y lo rellena de espacios. Luego recorre las listas añadiendo lo que sea oportuno en cada casilla y llama a el método toString. */
	
	private void encodeGame(Game game) {
		
		board = new String[dimX][dimY];
		
		for (int i = 0; i < dimX; i++) {
			
			for (int j = 0; j < dimY; j++) {
				
				board[i][j] = space;
				
			}
			
		}
		
		for (int i = 0; i < game.GetPScount(); i++) {
			
			board[game.GetPSy(i)][game.GetPSx(i)] = game.PStoString(i);
			
		}
		
		for (int i = 0; i < game.GetSFcount(); i++) {
			
			board[game.GetSFy(i)][game.GetSFx(i)] = game.SFtoString(i);
			
		}
		
		for (int i = 0; i < game.GetZcount(); i++) {
			
			board[game.GetZy(i)][game.GetZx(i)] = this.game.ZtoString(i);

		}
		
		toString();
		
	}
	
	@Override
	
	/** Usa MyStringUtils para hacer que el tablero sea imprimible de forma correcta y devuelve la string a imprimir.s */
	
	public String toString() {

		int cellSize = 7;
		int marginSize = 2;
		String vDelimiter = "|";
		String hDelimiter = "-";
		
		String rowDelimiter = MyStringUtils.repeat(hDelimiter, (dimY * (cellSize + 1)) - 1);
		String margin = MyStringUtils.repeat(space, marginSize);
		String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);
		
		StringBuilder str = new StringBuilder();
				
		str.append(lineDelimiter);
		
		for(int i=0; i<dimX; i++) {
				
				str.append(margin).append(vDelimiter);
				
				for (int j=0; j<dimY; j++) {
					
					str.append( MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
				}
				
				str.append(lineDelimiter);
				
		}
		
		return str.toString();
		
	}
}