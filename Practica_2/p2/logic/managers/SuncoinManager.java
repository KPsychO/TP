package p2.logic.managers;

import p2.logic.game.*;

/** Class "SuncoinManager":
 * 
 *		Controls the suncoins
 *
 *  */

public class SuncoinManager {
	
	Game game;
	int suns;
	
	/** Creates a new suncoin manager with 50 SC */
	
	public SuncoinManager(Game game){
		
		this.game = game;
		this.suns = 50;		
		
	}
	
	// getters
	
	public int GetSuns(){
		
		return this.suns;
		
	}
	
	// setters
	
	public void AddSC(int suns){
		
		this.suns += suns;
		
	}
	
	/** Decreases SC in the game by the given value */
	
	public void DecreaseSC (int suns){
		
		this.suns -= suns;
		
	}
		
}
