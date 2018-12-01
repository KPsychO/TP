package p3.logic.game;

import p3.Exceptions.ArgumentException;

/** Class "Level":
 * 
 *		Does... interesting... things with the given attributes. Some say that it also define the difficulty of the current game.
 *
 *  */

public enum Level {
	
	EASY("EASY", 3, 0.1), HARD("HARD", 5, 0.2), INSANE("INSANE", 10, 0.3);

	private String dif;
	private int num_zombies;
	private double freq;

	private Level (String param, int n_zombies, double frequency){
		
		dif = param;
		num_zombies = n_zombies;
		freq = frequency;
		
	}
	
	/** Sets the difficulty according to the execution argument 
	 * @throws ArgumentException */
	
	public static Level fromParam(String args) throws ArgumentException {
		
		for (Level level : Level.values()) {
			
			if (level.dif.equals(args))
				return level;
			
		}
		
		throw new ArgumentException("[ERROR]: Level (args[0]) must be one of: 'EASY', 'HARD', 'INSANE'. \n");
		
	}
	
	// getters
	
	public String GetDif () {
		
		return this.dif;
		
	}
	
	public int GetNZombies () {
		
		return this.num_zombies;
		
	}
	
	public double GetFreq () {
		
		return this.freq;
		
	}
	
}