package p3.logic.managers;

import java.util.*;

import p3.logic.game.*;

/** Class "ZombieManager":
 * 
 *		Controls the zombies in the game
 *
 *  */

public class ZombieManager {
	
	private int zombie_to_play;
	private Game game;
	private Random rand;
	private double freq;
	
	/** Creates a new manager according to the given difficulty */
	
	public ZombieManager(Game game, Level level, long seed){
		
		this.game = game;
		this.zombie_to_play = level.GetNZombies();
		this.rand = new Random(seed);
		this.freq = level.GetFreq();
		
	}
	
	public ZombieManager(Game game, Level level, long seed, int newZRemaining){
		
		this.game = game;
		this.zombie_to_play = newZRemaining;
		this.rand = new Random(seed);
		this.freq = level.GetFreq();
		
	}
	
	/** Returns the zombies remaining (zombies to appear) */
	
	public int GetZRemaining() {
		
		return this.zombie_to_play;
		
	}
	
	/** If the RNGod desires it, adds a new zombie */
	
	public void AddZombie () {
		
		if ((rand.nextDouble() < this.freq) && (this.zombie_to_play > 0)) {
			
			game.AddZombie();
			this.zombie_to_play--;
			
		}
		
	}
		
}