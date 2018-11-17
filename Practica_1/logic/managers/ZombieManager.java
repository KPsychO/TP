package logic.managers;

import java.util.*;
import logic.game.*;

/** Clase que controla la gestión de los zombies */

public class ZombieManager {
	
	// Atributes
	
	private int zombie_to_play;
	private Game game;
	private Random rand;
	private double freq;
	
	// Methods
	
	/** Constructor de ZombieManager, recibe los parametros necesarios del enumerado Level */
	
	public ZombieManager(Game game, Level level, long seed){
		
		this.game = game;
		this.zombie_to_play = level.GetNZombies();
		this.rand = new Random(seed);
		this.freq = level.GetFreq();
		
	}
	
	public int GetZRemaining() {
		
		return this.zombie_to_play;
		
	}
	
	/** Añade un zombie al juego si el random que sale es menor a la frequencia de spawn y reduce el númerod e zombies en espera de salir */
	
	public void AddZombie () {
		
		if ((rand.nextDouble() < this.freq) && (this.zombie_to_play > 0)) {
			
			game.AddZombie();
			this.zombie_to_play--;
			
		}
		
	}
		
}