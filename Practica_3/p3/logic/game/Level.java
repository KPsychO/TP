package p3.logic.game;

/** Class "Level":
 * 
 *		Does... interesting... things with the given attributes. Some say that it also define the difficulty of the current game.
 *
 *  */

public enum Level {
	
	EASY("easy", 3, 0.1), HARD("hard", 5, 0.2), INSANE("insane", 10, 0.3);

	private String dif;
	private int num_zombies;
	private double freq;

	private Level (String param, int n_zombies, double frequency){
		
		dif = param;
		num_zombies = n_zombies;
		freq = frequency;
		
	}
	
	/** Sets the difficulty according to the execution argument */
	
	public static Level fromParam(String args) {
		
		for (Level level : Level.values()) {
			
			if (level.dif.equals(args))
				return level;
			
		}
		
		return null;
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