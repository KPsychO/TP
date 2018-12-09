package p3.logic.managers;

import p3.logic.game.*;

/** Class "SuncoinManager":
 * 
 *		Controls the suncoins
 *
 *  */

public class SuncoinManager {
	
	Game game;
	int suns;
	
	/** Creates a new suncoinManager with 50 SC */
	
	public SuncoinManager(Game game){
		
		this.game = game;
		this.suns = 50;		
		
	}
	
	/** Creates a new suncoinManager with the given suncoins. */
	
	public SuncoinManager(Game game, int sunCoins){
		
		this.game = game;
		this.suns = sunCoins;		
		
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
