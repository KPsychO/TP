package p3.factory;

import p3.logic.game.Game;
import p3.logic.objects.*;

/** Class "PlantFactory":
 * 
 *		Creates plants
 *
 *  */

public class PlantFactory {
	
	/** 
	 * List of available plants
	 * Note: Sorry, I can't English sometimes and i couldn't write the word available correctly during the whole program, everywhere else it's written as "avaliable"
	 * */
	
	private static Plant[] avaliablePlants = {
			new PPeashooter(),
			new PSunflower(),
			new PNut(),
			new PCherry()
			
			
			// Add here any new plant
	};
	
	/** Receives the user input and creates a plant accordingly
	 * 	returns null if the input was incorrect
	 *  */

	public static Plant getPlant(String plantName, int x, int y, Game game) {
		
		Plant plant = null;
		
		switch (plantName.toLowerCase()) {
		
		case "peashooter": case "p": plant = new PPeashooter(x, y, game); break;
		case "sunflower": case "s": plant = new PSunflower(x, y, game); break;
		case "nut": case "n": plant = new PNut (x, y, game); break;
		case "cherry": case "c": plant = new PCherry (x, y , game); break;
			
		}
		
		return plant;
		
	}
	
	/** Recieves the plant position, hp, current frequency and game and creates it.
	 * 	Used while loading a game from a file.
	 * */
	
	public static Plant loadPlant(String plantName, int x, int y, int hp, int freq, Game game) {
		
		Plant plant = null;
		
		switch (plantName.toLowerCase()) {
		
		case "peashooter": case "p": plant = new PPeashooter(x, y, hp, freq, game); break;
		case "sunflower": case "s": plant = new PSunflower(x, y, hp, freq, game); break;
		case "nut": case "n": plant = new PNut (x, y, hp, freq, game); break;
		case "cherry": case "c": plant = new PCherry (x, y, hp, freq, game); break;
			
		}
		
		return plant;
		
	}
	
	/** Creates a printable list of plants making use of the plantInfo method to be used by ListCommand */
	
	public static String listAvaliablePlants() {
		
		String text = "Avaliable plants: \n";
		int i = 0;
		
		do {
			
			text += avaliablePlants[i].plantInfo();
			++i;
			
		} while (i < avaliablePlants.length);
		
		return text;
		
	}
	
}
