package logic.game;

/** Enumerado que contienen la información respectiva a cada nivel de dificultad del juego. */

public enum Level {
	
	EASY("easy", 3, 0.1), HARD("hard", 5, 0.2), INSANE("insane", 10, 0.3);

	private String dif;
	private int num_zombies;
	private double freq;

	/** No s ellma nunca, está para que compile sin warnings */
	
	private Level (String param, int n_zombies, double frequency){
		
		dif = param;
		num_zombies = n_zombies;
		freq = frequency;
		
	}
	
	/** Recibe una string (args[o]) de los argumentos y devuelve el nivel (enumerado) correspondiente al nivel indicado en la string */

	public static Level fromParam(String args) {
		
		for (Level level : Level.values()) {
			
			if (level.dif.equals(args))
				return level;
			
		}
		
		return null;
	}
	
	/** Getters de Level */
	
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