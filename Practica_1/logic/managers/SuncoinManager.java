package logic.managers;

import logic.game.*;

/** Clase que gestiona los SunCoins del juego */

public class SuncoinManager {
	
	// Atributes
	
	Game game;
	int suns;
	
	// Methods
	
	/** Inicializa el contador de Soles a 50 */
	
	public SuncoinManager(Game game){
		
		this.game = game;
		this.suns = 50;		
		
	}
	
	/** Getters y setters */
	
	public int GetSuns(){
		
		return this.suns;
		
	}
	
	public void AddSC(int suns){
		
		this.suns += suns;
		
	}
	
	public void DecreaseSC (int suns){
		
		this.suns -= suns;
		
	}
		
}
