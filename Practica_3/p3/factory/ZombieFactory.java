package p3.factory;

import p3.logic.game.Game;
import p3.logic.objects.*;

/** Class "ZombieFactory":
 * 
 *		creates zombies
 *		works as "PlantFactory"
 *
 *  */

public class ZombieFactory {
	
	private static Zombie[] avaliableZombies = {
			new ZNormal(),
			new ZAthlete(),
			new ZBucket()
	};

	public static Zombie getZombie(String zombieSymbol, int y, int i, Game game) {
		
		Zombie zombie = null;
		
		switch (zombieSymbol) {
		
		case "Z": zombie = new ZNormal(y, i, game); break;
		case "X": zombie = new ZAthlete(y, i, game); break;
		case "W": zombie = new ZBucket(y, i, game); break;
		
		}
		
		return zombie;
		
	}
	
	public static String listAvaliableZombies() {
		
		String text = "Avaliable zombies: \n";
		int i = 0;
		
		do {
			
			text += "Symbol: [" + avaliableZombies[i].getSymbol() + "]: Dmg = " + avaliableZombies[i].getDMG() + " Hp = " + avaliableZombies[i].getHP() + " Freq = " + avaliableZombies[i].getFreq() + "\n";
			++i;
			
		} while (i < avaliableZombies.length);
		
		return text;
		
	}
	
	public static int getAvaliableZombies() {
		
		return ZombieFactory.avaliableZombies.length;
		
	}
	
	public static String getSymbol(int i) {
				
		return ZombieFactory.avaliableZombies[i].getSymbol();
		
	}

}
